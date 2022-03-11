package pro.paullezin.logisticsservice.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pro.paullezin.logisticsservice.model.Transport;
import pro.paullezin.logisticsservice.service.TransportService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TransportController {
    private final TransportService transportService;

    @GetMapping("/transport")
    public ResponseEntity<List<Transport>> getTransports() {
        return ResponseEntity.ok().body(transportService.getAll());
    }

    @GetMapping("/transport/find/by-regnum")
    public ResponseEntity<Transport> getTransport(@RequestParam(value = "regnum") String regnum) {
        return ResponseEntity.ok().body(transportService.getTransport(regnum));
    }

    @PostMapping(value = "/transport/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Transport> addTransport(@Valid @RequestBody Transport transport) {
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/transport")
                .build().toUri();
        return ResponseEntity.created(uri).body(transportService.saveTransport(transport));
    }
}