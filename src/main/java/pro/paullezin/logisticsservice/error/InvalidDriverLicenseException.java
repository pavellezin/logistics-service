package pro.paullezin.logisticsservice.error;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.http.HttpStatus;

import static org.springframework.boot.web.error.ErrorAttributeOptions.Include.MESSAGE;

public class InvalidDriverLicenseException extends AppException {
    public InvalidDriverLicenseException(String msg) {
        super(HttpStatus.NOT_ACCEPTABLE, msg, ErrorAttributeOptions.of(MESSAGE));
    }
}