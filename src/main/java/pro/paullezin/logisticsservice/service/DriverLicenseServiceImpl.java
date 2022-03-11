package pro.paullezin.logisticsservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pro.paullezin.logisticsservice.error.IllegalRequestDataException;
import pro.paullezin.logisticsservice.model.DriverLicense;
import pro.paullezin.logisticsservice.repo.DriverLicenseRepo;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class DriverLicenseServiceImpl implements DriverLicenseService {
    private final DriverLicenseRepo driverLicenseRepo;

    @Override
    public DriverLicense saveDriverLicense(DriverLicense license) {
        log.info("Save driver license [{}]", license.getNumber());
        return driverLicenseRepo.save(license);
    }

    @Override
    public DriverLicense getDriverLicense(String number) {
        log.info("Get driver license [{}]", number);
        return driverLicenseRepo.findByNumber(number).orElseThrow(
                () -> new IllegalRequestDataException("Driver license with number [" + number + "] not found"));
    }

    @Override
    public List<DriverLicense> getExpired(LocalDate date) {
        log.info("Get expired driver licenses");
        return driverLicenseRepo.findExpired(date);
    }

    @Override
    public List<DriverLicense> getAll() {
        log.info("Get all driver licenses:");
        return driverLicenseRepo.findAll();
    }
}