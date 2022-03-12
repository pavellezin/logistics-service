package pro.paullezin.logisticsservice.service;

public interface TransportControlService {

    boolean assignTransport(String driver_id, String regnum);

    boolean removeTransport(String driver_id, String regnum);

    boolean removeAllTransport(String driver_id);

}
