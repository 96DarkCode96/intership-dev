package intervie.zebrih.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "contact_sources")
@Getter @Setter
public class ContactSource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer permission;
    private String entityName = "ContactSource";
    private String code01;
    private Integer sequenceNumber;

    public ContactSource() {}
}