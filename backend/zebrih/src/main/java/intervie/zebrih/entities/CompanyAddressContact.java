package intervie.zebrih.entities; 

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "company_addresses_contact")
@Getter @Setter
public class CompanyAddressContact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer permission;
    private Integer version;
    private String entityName = "CompanyAddress";

    @Column(name = "address_name")
    private String addressName;
    @Column(name = "address_street")
    private String addressStreet;
    @Column(name = "address_province")
    private String addressProvince;
    @Column(name = "address_zip_code")
    private String addressZipCode;
    @Column(name = "address_city")
    private String addressCity;
    @Column(name = "address_country_code")
    private String addressCountryCode;
    @Column(name = "address_country_name")
    private String addressCountryName;
    @Column(name = "address_lat")
    private Double addressLat;
    @Column(name = "address_lng")
    private Double addressLng;

    @ManyToOne
    @JoinColumn(name = "territory_id")
    private Territory territory;

    @Column(name = "contact_info_tel1")
    private String contactInfoTel1;
    @Column(name = "contact_info_tel1_normalized")
    private String contactInfoTel1Normalized;
    @Column(name = "contact_info_tel1_type")
    private String contactInfoTel1Type;
    @Column(name = "contact_info_tel2")
    private String contactInfoTel2;
    @Column(name = "contact_info_tel2_normalized")
    private String contactInfoTel2Normalized;
    @Column(name = "contact_info_tel2_type")
    private String contactInfoTel2Type;
    @Column(name = "contact_info_email")
    private String contactInfoEmail;
    @Column(name = "contact_info_email2")
    private String contactInfoEmail2;
    @Column(name = "contact_info_www")
    private String contactInfoWww;
    @Column(name = "contact_info_fax")
    private String contactInfoFax;
    @Column(name = "contact_info_do_not_send_mm")
    private boolean contactInfoDoNotSendMM;
    @Column(name = "contact_info_other_contact")
    private String contactInfoOtherContact;

    @Column(name = "is_primary")
    private boolean primary;
    private boolean contactAddress;

    public CompanyAddressContact() {}
}