package intervie.zebrih.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "security_levels")
@Getter @Setter
public class SecurityLevel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer permission;
    private String entityName = "SecurityLevel";
    private String name;
    private boolean locked;

    public SecurityLevel() {}
}