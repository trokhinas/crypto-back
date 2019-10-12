package vsu.labs.crypto.entity.test;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "answer")
@Data
public class AnswerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "answer_seq")
    @SequenceGenerator(name = "answer_seq", sequenceName = "answer_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Basic
    @Column(name = "name")
    private String name;

    @Basic
    @Column(name = "is_correct")
    private Boolean isCorrect;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private QuestionEntity question;


}
