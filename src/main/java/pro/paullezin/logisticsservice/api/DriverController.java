package pro.paullezin.logisticsservice.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pro.paullezin.logisticsservice.model.Driver;
import pro.paullezin.logisticsservice.service.DriverService;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class DriverController {
    private final DriverService driverService;

    @GetMapping("/driver")
    public ResponseEntity<List<Driver>> getDrivers() {
        return ResponseEntity.ok().body(driverService.getAll());
    }

    @GetMapping("/driver/find/by-name")
    public ResponseEntity<List<Driver>> getByName(@RequestParam(value = "name") String name) {
        return ResponseEntity.ok().body(driverService.getByName(name));
    }

    @GetMapping("/driver/{id}")
    public ResponseEntity<Driver> getById(@PathVariable String id) {
        return ResponseEntity.ok().body(driverService.getById(id));
    }

    @GetMapping("/driver/find/expired")
    public ResponseEntity<List<Driver>> getWithExpiredLicense(@RequestParam(value = "date") String date) {
        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return ResponseEntity.ok().body(driverService.getWithExpiredLicense(localDate));
    }

    @PostMapping(value = "/driver/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Driver> addDriver(@Valid @RequestBody Driver driver) {
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/driver")
                .build().toUri();
        return ResponseEntity.created(uri).body(driverService.saveDriver(driver));
    }

    @GetMapping("/driver/{id}/assign-license")
    public ResponseEntity<Driver> assignLicense(@PathVariable String id, @RequestParam(value = "number") String number){
        return ResponseEntity.ok().body(driverService.assignLicense(id, number));
    }
}