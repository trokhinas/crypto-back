package vsu.labs.crypto.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class MarkEntity {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "mark")
    private int mark;
}
