package pro.paullezin.logisticsservice.service;

import pro.paullezin.logisticsservice.model.Action;
import pro.paullezin.logisticsservice.model.DriverTransport;

import java.util.List;

public interface DriverTransportService {

    DriverTransport assignTransport(String driver_id, String regnum);

    DriverTransport removeTransport(String driver_id, String regnum, Action action);

    int removeAllTransport(String driver_id, Action action);

    List<DriverTransport> findAllDriverTransport(String driver_id);

    List<DriverTransport> findAllDriverTransportAssignment();

}
