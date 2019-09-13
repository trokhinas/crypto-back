package vsu.labs.crypto.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Mark {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "mark")
    private int mark;
}
