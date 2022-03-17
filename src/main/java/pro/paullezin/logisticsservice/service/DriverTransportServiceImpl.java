package pro.paullezin.logisticsservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pro.paullezin.logisticsservice.error.IllegalRequestDataException;
import pro.paullezin.logisticsservice.error.InvalidDriverLicenseException;
import pro.paullezin.logisticsservice.model.*;
import pro.paullezin.logisticsservice.repo.DriverRepo;
import pro.paullezin.logisticsservice.repo.DriverTransportRepo;
import pro.paullezin.logisticsservice.repo.TransportRepo;

import java.sql.Timestamp;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class DriverTransportServiceImpl implements DriverTransportService {
    private final DriverRepo driverRepo;
    private final TransportRepo transportRepo;
    private final DriverTransportRepo driverTransportRepo;
    private final KafkaTemplate<String, DriverTransport> kafkaTemplate;

    @Override
    @Transactional
    public DriverTransport assignTransport(String driver_id, String regnum) {
        log.info("Assign transport [{}] for driver with id [{}]", regnum, driver_id);
        Driver driver = driverRepo.findById(driver_id).orElseThrow(
                () -> new IllegalRequestDataException("Driver with id [" + driver_id + "] not found")
        );
        Transport transport = transportRepo.findByRegnum(regnum).orElseThrow(
                () -> new IllegalRequestDataException("Transport with regnum [" + regnum + "] not found")
        );
        if (!driver.getLicense().getCategories().contains(transport.getCategory())) {
            throw new InvalidDriverLicenseException("Driver with id [" + driver_id + "] have no valid driver license category");
        }
        DriverTransport driverTransport = driverTransportRepo.saveAndFlush(new DriverTransport(driver, transport));
        kafkaTemplate.send("transport-order", driverTransport);
        return driverTransport;
    }

    @Override
    @Transactional
    public DriverTransport removeTransport(String driver_id, String regnum, Action action) {
        log.info("Remove transport [{}] for driver with id [{}]", regnum, driver_id);

        DriverTransport driverTransport = driverTransportRepo.findActiveDriverTransport(driver_id, regnum).orElseThrow(
                () -> new IllegalRequestDataException("Active transport with regnum [" + regnum + "] for driver with id [" + driver_id + "] not found")
        );
        driverTransport.setAction(action);
        driverTransport.setStatus(Status.ARCHIVE);
        driverTransport.setModified(new Timestamp(System.currentTimeMillis()));
        kafkaTemplate.send("transport-order", driverTransport);
        return driverTransport;
    }

    @Override
    @Transactional
    public int removeAllTransport(String driver_id, Action action) {
        log.info("Remove all transport for driver with id [{}]", driver_id);
        List<DriverTransport> transports = driverTransportRepo.findAllActiveDriverTransport(driver_id);
        transports.forEach(t -> removeTransport(driver_id, t.getTransport().getRegnum(), action));
        return transports.size();
    }

    @Override
    public List<DriverTransport> findAllDriverTransport(String driver_id) {
        log.info("Find all transport history for driver with id [{}]", driver_id);
        return driverTransportRepo.findAllDriverTransport(driver_id);
    }

    @Override
    public List<DriverTransport> findAllDriverTransportAssignment() {
        log.info("Find all transport assigment history");
        return driverTransportRepo.findAll();
    }
}