package vsu.labs.crypto.entity.test;

import lombok.Data;
import vsu.labs.crypto.enums.TaskType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "task_type")
@Data
public class TaskTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task_type_seq")
    @SequenceGenerator(name = "task_type_seq", sequenceName = "task_type_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "tittle")
    private String tittle;


    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private TaskType taskType;


    @OneToMany(mappedBy = "taskType")
    private List<TaskEntity> taskList = new ArrayList<>();

}
