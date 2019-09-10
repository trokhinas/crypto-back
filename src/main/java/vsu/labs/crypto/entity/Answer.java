package vsu.labs.crypto.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Answer {
    private int id;
    private String vname;
    private Boolean isCorrect;
    private int column5;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "vname")
    public String getVname() {
        return vname;
    }

    public void setVname(String vname) {
        this.vname = vname;
    }

    @Basic
    @Column(name = "is_correct")
    public Boolean getCorrect() {
        return isCorrect;
    }

    public void setCorrect(Boolean correct) {
        isCorrect = correct;
    }

    @Basic
    @Column(name = "column_5")
    public int getColumn5() {
        return column5;
    }

    public void setColumn5(int column5) {
        this.column5 = column5;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Answer answer = (Answer) o;
        return id == answer.id &&
                column5 == answer.column5 &&
                Objects.equals(vname, answer.vname) &&
                Objects.equals(isCorrect, answer.isCorrect);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, vname, isCorrect, column5);
    }
}
