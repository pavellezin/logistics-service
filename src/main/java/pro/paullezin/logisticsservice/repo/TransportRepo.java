package pro.paullezin.logisticsservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pro.paullezin.logisticsservice.model.DriverLicense;
import pro.paullezin.logisticsservice.model.Transport;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TransportRepo extends JpaRepository<Transport, String> {
    Optional<Transport> findByRegnum(String regnum);
}