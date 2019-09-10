package vsu.labs.crypto.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Task {
    private int id;
    private String type;
    private String name;
    private Test testByTestId;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
        Task task = (Task) o;
        return id == task.id &&
                Objects.equals(type, task.type) &&
                Objects.equals(name, task.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, name);
    }

    @ManyToOne
    @JoinColumn(name = "test_id", referencedColumnName = "id")
    public Test getTestByTestId() {
        return testByTestId;
    }

    public void setTestByTestId(Test testByTestId) {
        this.testByTestId = testByTestId;
    }
}
