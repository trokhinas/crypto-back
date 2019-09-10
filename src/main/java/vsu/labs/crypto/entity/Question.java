package vsu.labs.crypto.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Question {
    private int id;
    private String name;
    private Task taskByTaskId;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return id == question.id &&
                Objects.equals(name, question.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @ManyToOne
    @JoinColumn(name = "task_id", referencedColumnName = "id")
    public Task getTaskByTaskId() {
        return taskByTaskId;
    }

    public void setTaskByTaskId(Task taskByTaskId) {
        this.taskByTaskId = taskByTaskId;
    }
}
