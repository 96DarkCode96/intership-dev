package intervie.zebrih.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "territories")
@Getter @Setter
public class Territory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer permission;
    private String entityName = "Territory";
    private String code01;
    private String strValue01;
    private Integer sequenceNumber;

    public Territory() {}
}