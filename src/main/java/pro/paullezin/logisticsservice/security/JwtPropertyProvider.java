package pro.paullezin.logisticsservice.security;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:jwt.properties")
@Getter
public class JwtPropertyProvider {
    private final String secret;

    public JwtPropertyProvider(@Value("${auth.jwt.secret}") String secret) {
        this.secret = secret;
    }
}