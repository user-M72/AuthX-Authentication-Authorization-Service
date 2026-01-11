package AuthX.Authorization_Service.entity.baseDomain;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.UuidGenerator;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@ToString
@MappedSuperclass
public abstract class BaseDomain implements Serializable {
    @Id
    @UuidGenerator
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;
}
