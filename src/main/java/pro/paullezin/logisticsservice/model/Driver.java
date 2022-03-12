package pro.paullezin.logisticsservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Entity
@Table(name = "drivers", uniqueConstraints = {@UniqueConstraint(name = "driver_uniqe", columnNames = {"license_id"})})
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Driver implements Serializable {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    @NotEmpty
    private String name;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "license_id", referencedColumnName = "id")
    private DriverLicense license;
}