package vsu.labs.crypto.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;


@Entity
@Data
public class Task {

    @Id
    @Column(name = "id")
    private Long id;

    @Basic
    @Column(name = "type")
    private String type;

    @Basic
    @Column(name = "name")
    private String name;


    @OneToMany(mappedBy = "task")
    private List<Question> questionList;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "test_id")
    private Test test;

}
