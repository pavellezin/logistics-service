package pro.paullezin.logisticsservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pro.paullezin.logisticsservice.model.Driver;
import pro.paullezin.logisticsservice.properties.ServicePropertyProvider;
import pro.paullezin.logisticsservice.repo.DriverRepo;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class TransportControlServiceImpl implements TransportControlService {
    private final DriverRepo driverRepo;
    private final ServicePropertyProvider servicePropertyProvider;

    @Override
    public boolean assignTransport(String driver_id, String regnum) {
        log.info("Assign transport [{}] for driver with id [{}]", regnum, driver_id);
        int limit = Integer.parseInt(servicePropertyProvider.getTransportLimit());
        Driver driver = driverRepo.getById(driver_id);
        return false;
    }

    @Override
    public boolean removeTransport(String driver_id, String regnum) {
        log.info("Remove transport [{}] for driver with id [{}]", regnum, driver_id);
        return false;
    }

    @Override
    public boolean removeAllTransport(String driver_id) {
        return false;
    }
}