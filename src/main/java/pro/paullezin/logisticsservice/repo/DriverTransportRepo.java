package pro.paullezin.logisticsservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pro.paullezin.logisticsservice.model.DriverTransport;

import java.util.List;
import java.util.Optional;

public interface DriverTransportRepo extends JpaRepository<DriverTransport, String> {
    @Query("SELECT dt FROM DriverTransport dt WHERE dt.driver.id = :driver_id AND dt.transport.regnum = :regnum AND dt.status = 'ACTIVE'")
    Optional<DriverTransport> findActiveDriverTransport(String driver_id, String regnum);

    @Query("SELECT dt FROM DriverTransport dt WHERE dt.driver.id = :driver_id AND dt.status = 'ACTIVE'")
    List<DriverTransport> findAllActiveDriverTransport(String driver_id);

    @Query("SELECT dt FROM DriverTransport dt WHERE dt.driver.id = :driver_id")
    List<DriverTransport> findAllDriverTransport(String driver_id);
}
