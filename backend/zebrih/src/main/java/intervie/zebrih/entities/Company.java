package intervie.zebrih.entities; 

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "companies")
@Getter @Setter
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer permission;
    private Integer version;
    private String entityName = "Company";
    private String name;
    private String regNumber;
    private String taxNumber;
    private String taxNumber2;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "primary_address_id")
    private CompanyAddressPrimary primaryAddress;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contact_address_id")
    private CompanyAddressContact contactAddress;

    @ManyToOne
    @JoinColumn(name = "logo_id")
    private FileRef logo;

    private boolean person;
    private String firstName;
    private String lastName;
    private String titleBefore;
    private String titleAfter;

    public Company() {}
}