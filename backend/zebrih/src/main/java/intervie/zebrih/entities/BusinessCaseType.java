package intervie.zebrih.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "business_case_types")
@Getter @Setter
public class BusinessCaseType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer permission;
    private String entityName = "BusinessCaseType";
    private String code01;
    private Integer sequenceNumber;

    public BusinessCaseType() {}
}