package pro.paullezin.logisticsservice.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pro.paullezin.logisticsservice.model.DriverLicense;
import pro.paullezin.logisticsservice.service.DriverLicenseService;

import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class DriverLicenseController {
    private final DriverLicenseService driverLicenseService;

    @GetMapping("/license")
    public ResponseEntity<List<DriverLicense>> getLicenses() {
        return ResponseEntity.ok().body(driverLicenseService.getAll());
    }

    @GetMapping("/license/find/by-number")
    public ResponseEntity<DriverLicense> getLicense(@RequestParam(value = "number") String number){
        return ResponseEntity.ok().body(driverLicenseService.getDriverLicense(number));
    }

    @GetMapping("/license/find/expired")
    public ResponseEntity<List<DriverLicense>> getExpired(@RequestParam(value = "date") String date){
        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return ResponseEntity.ok().body(driverLicenseService.getExpired(localDate));
    }

    @PostMapping(value = "/license/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<DriverLicense> addLicense(@RequestBody DriverLicense license) {
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/account")
                .build().toUri();
        return ResponseEntity.created(uri).body(driverLicenseService.saveDriverLicense(license));
    }
}