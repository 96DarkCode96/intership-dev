package intervie.zebrih.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "currencies")
@Getter @Setter
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Integer permission;
    private String entityName = "Currency";
    private String code01;
    private String code02;
    private String strValue01;
    private Integer sequenceNumber;

    @Embedded
    private CurrencyConfig config;

    public Currency() {}
}