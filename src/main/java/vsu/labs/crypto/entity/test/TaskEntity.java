package vsu.labs.crypto.entity.test;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "task")
@Data
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task_seq")
    @SequenceGenerator(name = "task_seq", sequenceName = "task_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Basic
    @Column(name = "type")
    private String type;

    @Basic
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "task")
    private List<QuestionEntity> questionList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "test_id")
    private TestEntity test;
}
