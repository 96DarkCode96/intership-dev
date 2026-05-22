package intervie.zebrih.entities; 

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "people")
@Getter @Setter
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer permission;
    private Integer version;
    private String entityName = "Person";
    private String gender;
    private String fullName;
    private String fullNameWithoutTitles;
    private String firstName;
    private String lastName;
    private String titleBefore;
    private String notice;
    private String titleAfter;

    @ManyToOne
    @JoinColumn(name = "photo_id")
    private FileRef photo;

    private String salutation;

    @Column(name = "contact_info_tel1")
    private String contactInfoTel1;
    @Column(name = "contact_info_tel1_type")
    private String contactInfoTel1Type;
    @Column(name = "contact_info_tel2")
    private String contactInfoTel2;
    @Column(name = "contact_info_tel2_type")
    private String contactInfoTel2Type;
    @Column(name = "contact_info_email")
    private String contactInfoEmail;
    @Column(name = "contact_info_email2")
    private String contactInfoEmail2;
    @Column(name = "contact_info_www")
    private String contactInfoWww;
    @Column(name = "private_address_country_code")
    private String privateAddressCountryCode;

    private boolean activeUserAccount;

    @ManyToOne
    @JoinColumn(name = "security_level_id")
    private SecurityLevel securityLevel;

    public Person() {}
}