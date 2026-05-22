package intervie.zebrih.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "business_case_phases")
@Getter @Setter
public class BusinessCasePhase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer permission;
    private String entityName = "BusinessCasePhase";
    private String code01;
    private String color;
    private String status;
    private Double probability;
    private boolean locked;
    private Integer sequenceNumber;
    private Integer totalCount;
    private Integer phaseSequenceNumber;

    @ManyToOne
    @JoinColumn(name = "business_case_type_id")
    private BusinessCaseType businessCaseType;

    public BusinessCasePhase() {}
}