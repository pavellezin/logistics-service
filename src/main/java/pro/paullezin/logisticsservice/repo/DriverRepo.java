package pro.paullezin.logisticsservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pro.paullezin.logisticsservice.model.Driver;

import java.time.LocalDate;
import java.util.List;

public interface DriverRepo extends JpaRepository<Driver, String> {

    @Query("SELECT d FROM Driver d WHERE d.name LIKE CONCAT('%',:name,'%')")
    List<Driver> findByPartName(String name);

    @Query("SELECT d FROM Driver d JOIN FETCH d.license WHERE d.license.expDate <= :date")
    List<Driver> findWithExpiredLicense(LocalDate date);

}