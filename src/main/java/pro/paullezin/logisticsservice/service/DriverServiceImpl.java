package pro.paullezin.logisticsservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pro.paullezin.logisticsservice.error.IllegalRequestDataException;
import pro.paullezin.logisticsservice.model.Driver;
import pro.paullezin.logisticsservice.model.DriverLicense;
import pro.paullezin.logisticsservice.repo.DriverLicenseRepo;
import pro.paullezin.logisticsservice.repo.DriverRepo;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class DriverServiceImpl implements DriverService {
    private final DriverRepo driverRepo;
    private final DriverLicenseRepo driverLicenseRepo;

    @Override
    public Driver saveDriver(Driver driver) {
        log.info("Save driver [{}]", driver.getId());
        return driverRepo.save(driver);
    }

    @Override
    public List<Driver> getAll() {
        log.info("Get all drivers:");
        return driverRepo.findAll();
    }

    @Override
    public List<Driver> getByName(String name) {
        log.info("Get drivers by name [{}]", name);
        return driverRepo.findByPartName(name);
    }

    @Override
    public Driver getById(String id) {
        log.info("Get drivers by id [{}]", id);
        return driverRepo.findById(id).orElseThrow(
                () -> new IllegalRequestDataException("Driver with id [" + id + "] not found")
        );
    }

    @Override
    public List<Driver> getWithExpiredLicense(LocalDate date) {
        log.info("Get drivers with expired license");
        return driverRepo.findWithExpiredLicense(date);
    }

    @Override
    public Driver assignLicense(String driver_id, String number) {
        log.info("Trying assign license [{}] for driver with id [{}]", number, driver_id);
        DriverLicense license = driverLicenseRepo.findByNumber(number).orElseThrow(
                () -> new IllegalRequestDataException("Driver license with number [" + number + "] not found")
        );
        Driver driver = driverRepo.findById(driver_id).orElseThrow(
                () -> new IllegalRequestDataException("Driver with id [" + driver_id + "] not found")
        );
        if (driver.getLicense() == null) {
            driver.setLicense(license);
            driverRepo.save(driver);
            log.info("Successfully assignment");
        } else {
            throw new IllegalRequestDataException("The driver [" + driver.getId() + "] already have a driver's license number [" + license.getNumber() + "]");
        }
        return driver;
    }

}