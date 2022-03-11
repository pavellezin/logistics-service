package pro.paullezin.logisticsservice.service;

import pro.paullezin.logisticsservice.model.Transport;

import java.util.List;

public interface TransportService {

    Transport saveTransport(Transport transport);

    Transport getTransport(String regnum);

    List<Transport> getAll();
}