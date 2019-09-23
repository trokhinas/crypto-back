package vsu.labs.crypto.entity.test;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "course_themes")
@Data
public class CourseThemesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "course_themes_seq")
    @SequenceGenerator(name = "course_themes_seq", sequenceName = "course_themes_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "reference")
    private String reference;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private CourseEntity course;
    
}
