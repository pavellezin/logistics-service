package pro.paullezin.logisticsservice.service;

import pro.paullezin.logisticsservice.model.DriverLicense;

import java.time.LocalDate;
import java.util.List;

public interface DriverLicenseService {

    DriverLicense saveDriverLicense(DriverLicense license);

    DriverLicense getDriverLicense(String number);

    List<DriverLicense> getExpired(LocalDate date);

    List<DriverLicense> getAll();
}
