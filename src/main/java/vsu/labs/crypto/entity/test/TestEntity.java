package vsu.labs.crypto.entity.test;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class TestEntity {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;
}

