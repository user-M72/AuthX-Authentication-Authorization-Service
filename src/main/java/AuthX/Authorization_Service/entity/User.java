package AuthX.Authorization_Service.entity;

import AuthX.Authorization_Service.entity.baseDomain.BaseDomain;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User extends BaseDomain {

    @Column(name = "username", nullable = false, length = 25)
    private  String username;

    @Column(name = "email", nullable = false, length = 30)
    private String email;

    @Column(name = "password", nullable = false, length = 60)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false, length = 20)
    private Role role;

}
