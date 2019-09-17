package vsu.labs.crypto.entity.security;

import lombok.Data;
import vsu.labs.crypto.enums.RoleType;

import javax.persistence.*;

@Entity(name = "role")
@Data
public class RoleEntity {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    @Enumerated(EnumType.STRING)
    private RoleType name;
}
