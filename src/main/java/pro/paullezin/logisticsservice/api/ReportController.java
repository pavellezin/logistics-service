package pro.paullezin.logisticsservice.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.paullezin.logisticsservice.model.DriverTransport;
import pro.paullezin.logisticsservice.service.DriverTransportService;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ReportController {
    private final DriverTransportService driverTransportService;

    @GetMapping("/driver/{id}/history")
    public ResponseEntity<List<DriverTransport>> getDriverTransportHistory (@PathVariable String id){
        return ResponseEntity.ok().body(driverTransportService.findAllDriverTransport(id));
    }

    @GetMapping("/driver/history")
    public ResponseEntity<List<DriverTransport>> getAllDriverTransportHistory (){
        return ResponseEntity.ok().body(driverTransportService.findAllDriverTransportAssignment());
    }
}
