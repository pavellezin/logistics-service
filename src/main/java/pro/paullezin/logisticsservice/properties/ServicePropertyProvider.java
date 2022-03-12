package pro.paullezin.logisticsservice.properties;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:service.properties")
@Getter
public class ServicePropertyProvider {
    private final String secret;
    private final String transportLimit;

    public ServicePropertyProvider(@Value("${auth.jwt.secret}") String secret, @Value("${transport.limit}") String transportLimit) {
        this.secret = secret;
        this.transportLimit = transportLimit;
    }
}