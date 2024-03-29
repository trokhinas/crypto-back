package vsu.labs.crypto.entity.test;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "test")
@Data
public class TestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "test_seq")
    @SequenceGenerator(name = "test_seq", sequenceName = "test_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;


    @OneToMany(cascade = CascadeType.ALL,mappedBy = "test")
    private List<TaskEntity> tasks = new ArrayList<>();
}

