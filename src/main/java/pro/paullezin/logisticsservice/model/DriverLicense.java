package pro.paullezin.logisticsservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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
    @NotEmpty
    private String number;
    @Future
    @NotNull
    private LocalDate expDate;
    @NotEmpty
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "driver_license_categories", joinColumns = @JoinColumn(name = "driver_license_id"), uniqueConstraints = {@UniqueConstraint(columnNames = {"driver_license_id", "category"}, name = "driver_license_category_unique")})
    @Column(name = "category")
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Category> categories;
}