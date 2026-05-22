package intervie.zebrih.entities; 

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "files")
@Getter @Setter
public class FileRef {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer permission;
    private Integer version;
    private String entityName = "File";
    private String contentType;
    private String fileName;
    private Integer iconSmallSize;
    private String iconSmallUuid;
    private Long size;
    private boolean preview;
    private String uuid;
    private String iconMediumUuid;
    private Integer iconMediumSize;
    private String iconLargeUuid;
    private Integer iconLargeSize;

    public FileRef() {}
}