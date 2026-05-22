package intervie.zebrih.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "business_cases")
@Getter @Setter
public class BusinessCase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer permission;
    private Integer version;
    private String entityName = "BusinessCase";
    private String code;
    private String name;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Person owner;

    @ManyToOne
    @JoinColumn(name = "currency_id")
    private Currency currency;

    private String validFrom;
    private String validTill;
    private String scheduledEnd;
    
    private Double totalAmount;
    private Double totalAmountInDefaultCurrency;
    private Double taxAmount;
    private Double totalAmountWithTax;
    private Double baseAmount;
    private Double estimatedValue;
    private Double tradingProfit;
    
    private String status; // "B_ACTIVE" | "E_WIN" | "F_LOST"
    private Double probability;
    private Double exchangeRate;

    @ManyToOne
    @JoinColumn(name = "business_case_type_id")
    private BusinessCaseType businessCaseType;

    @ManyToOne
    @JoinColumn(name = "business_case_phase_id")
    private BusinessCasePhase businessCasePhase;

    @ManyToOne
    @JoinColumn(name = "contact_source_id")
    private ContactSource source;

    private String losingReason;

    @ManyToOne
    @JoinColumn(name = "losing_category_id")
    private LosingCategory losingCategory;

    @Column(name = "row_info_created_at")
    private String rowInfoCreatedAt;
    @Column(name = "row_info_created_by")
    private String rowInfoCreatedBy;
    @Column(name = "row_info_updated_at")
    private String rowInfoUpdatedAt;
    @Column(name = "row_info_updated_by")
    private String rowInfoUpdatedBy;
    @Column(name = "row_info_last_modified_at")
    private String rowInfoLastModifiedAt;
    @Column(name = "row_info_last_modified_by")
    private String rowInfoLastModifiedBy;

    @Column(length = 1000)
    private String description;

    @ManyToOne
    @JoinColumn(name = "security_level_id")
    private SecurityLevel securityLevel;

    private String prevActivity;
    private String nextActivity;
    private String lastPhaseChange;
    private Integer lastPhaseChangeDays;

    public BusinessCase() {}
}