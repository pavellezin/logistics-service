package pro.paullezin.logisticsservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pro.paullezin.logisticsservice.model.DriverLicense;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface DriverLicenseRepo extends JpaRepository<DriverLicense, String> {
    Optional<DriverLicense> findByNumber(String number);

    @Query("SELECT l FROM DriverLicense l WHERE l.expDate <= :date")
    List<DriverLicense> findExpired(LocalDate date);
}