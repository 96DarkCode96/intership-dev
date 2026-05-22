package intervie.zebrih.entities;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter @Setter
public class CurrencyConfig {
    private Integer places;
    private String format;

    public CurrencyConfig() {}
}