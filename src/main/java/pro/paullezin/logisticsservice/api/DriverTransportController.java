package pro.paullezin.logisticsservice.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.paullezin.logisticsservice.model.Action;
import pro.paullezin.logisticsservice.model.DriverTransport;
import pro.paullezin.logisticsservice.service.DriverTransportService;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class DriverTransportController {
    private final DriverTransportService driverTransportService;

    @GetMapping("/driver/{id}/assign-transport")
    public ResponseEntity<DriverTransport> assignTransport(@PathVariable String id, @RequestParam(value = "regnum") String regnum) {
        return ResponseEntity.ok().body(driverTransportService.assignTransport(id, regnum));
    }

    @GetMapping("/driver/{id}/remove-transport")
    public ResponseEntity<DriverTransport> removeTransport(@PathVariable String id, @RequestParam(value = "regnum") String regnum) {
        return ResponseEntity.ok().body(driverTransportService.removeTransport(id, regnum, Action.REMOVE));
    }

    @GetMapping("/driver/{id}/remove-all-transport")
    public ResponseEntity<String> removeAllTransport(@PathVariable String id) {
        return ResponseEntity.ok().body("Remove " + driverTransportService.removeAllTransport(id, Action.EXPIRE) + " transports for driver [" + id + "]");
    }
}