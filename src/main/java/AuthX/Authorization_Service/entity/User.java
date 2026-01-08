package AuthX.Authorization_Service.entity;

import AuthX.Authorization_Service.entity.baseDomain.BaseDomain;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User extends BaseDomain<UUID> {

    @Column(name = "email", nullable = false, length = 30)
    private String email;

    @Column(name = "password", nullable = false, length = 20)
    private String password;

    @Column(name = "username", nullable = false, length = 15)
    private String username;



}
