package pro.paullezin.logisticsservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "driver_license", uniqueConstraints = {@UniqueConstraint(name = "driver_license_uniqe", columnNames = {"number"})})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DriverLicense implements Serializable {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String number;
    private LocalDate expDate;
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "driver_license_categories", joinColumns = @JoinColumn(name = "driver_license_id"), uniqueConstraints = {@UniqueConstraint(columnNames = {"driver_license_id", "category"}, name = "driver_license_category_unique")})
    @Column(name = "category")
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<DriverLicenseCategory> categories;
}