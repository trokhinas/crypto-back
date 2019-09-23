package vsu.labs.crypto.entity.test;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "mark")
@Data
// TODO добавить связи
public class MarkEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mark_seq")
    @SequenceGenerator(name = "mark_seq", sequenceName = "mark_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "mark")
    private Integer mark;
}
