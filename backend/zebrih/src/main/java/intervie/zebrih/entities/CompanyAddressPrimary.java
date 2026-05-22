package intervie.zebrih.entities; 

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "company_addresses_primary")
@Getter @Setter
public class CompanyAddressPrimary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer permission;
    private Integer version;
    private String entityName = "CompanyAddress";

    @Column(name = "address_city")
    private String addressCity;

    @Column(name = "contact_info_email")
    private String contactInfoEmail;

    @Column(name = "contact_info_email2")
    private String contactInfoEmail2;

    @ManyToOne
    @JoinColumn(name = "territory_id")
    private Territory territory;

    @Column(name = "contact_info_tel1")
    private String contactInfoTel1;

    @Column(name = "address_country_code")
    private String addressCountryCode;

    @Column(name = "address_country_name")
    private String addressCountryName;

    public CompanyAddressPrimary() {}
}