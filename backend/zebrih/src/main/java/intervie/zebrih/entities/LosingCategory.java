package intervie.zebrih.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "losing_categories")
@Getter @Setter
public class LosingCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer permission;
    private String entityName = "LosingCategory";
    private String code01;
    @Column(name = "is_primary")
    private boolean primary;
    private Integer sequenceNumber;

    public LosingCategory() {}
}