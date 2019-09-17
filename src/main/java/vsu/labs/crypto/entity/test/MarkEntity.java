package vsu.labs.crypto.entity.test;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
// TODO добавить связи
public class MarkEntity {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "mark")
    private Integer mark;
}
