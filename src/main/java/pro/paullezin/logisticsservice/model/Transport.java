package pro.paullezin.logisticsservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "transport", uniqueConstraints = {@UniqueConstraint(name = "transport_regnum_uniqe", columnNames = {"regnum"})})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transport implements Serializable {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    @NotEmpty
    private String model;
    @NotEmpty
    private String regnum;
    @NotNull
    @Enumerated(EnumType.STRING)
    private Category category;
}