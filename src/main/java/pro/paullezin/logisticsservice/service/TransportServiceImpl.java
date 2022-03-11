package pro.paullezin.logisticsservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pro.paullezin.logisticsservice.error.IllegalRequestDataException;
import pro.paullezin.logisticsservice.model.Transport;
import pro.paullezin.logisticsservice.repo.TransportRepo;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class TransportServiceImpl implements TransportService {
    private final TransportRepo transportRepo;

    @Override
    public Transport saveTransport(Transport transport) {
        log.info("Save transport [{}]", transport.getRegnum());
        return transportRepo.save(transport);
    }

    @Override
    public Transport getTransport(String regnum) {
        log.info("Get transport with regnum [{}]", regnum);
        return transportRepo.findByRegnum(regnum).orElseThrow(
                () -> new IllegalRequestDataException("Transport with regnum [" + regnum + "] not found")
        );
    }

    @Override
    public List<Transport> getAll() {
        log.info("Get all transport:");
        return transportRepo.findAll();
    }
}