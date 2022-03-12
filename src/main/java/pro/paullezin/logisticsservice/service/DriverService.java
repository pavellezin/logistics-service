package pro.paullezin.logisticsservice.service;

import pro.paullezin.logisticsservice.model.Driver;
import pro.paullezin.logisticsservice.model.Transport;

import java.time.LocalDate;
import java.util.List;

public interface DriverService {

    Driver saveDriver(Driver driver);

    List<Driver> getAll();

    List<Driver> getByName(String name);

    Driver getById(String id);

    List<Driver> getWithExpiredLicense(LocalDate date);

    Driver assignLicense(String driver_id, String number);

    boolean assignTransport(Driver driver, Transport transport);

    boolean removeTransport(Driver driver, Transport transport);
}