package vsu.labs.crypto.entity.security;

import lombok.Data;
import vsu.labs.crypto.enums.RoleType;

import javax.persistence.*;

@Entity(name = "role")
@Data
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_seq")
    @SequenceGenerator(name = "role_seq", sequenceName = "role_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    @Enumerated(EnumType.STRING)
    private RoleType name;
}
