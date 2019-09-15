package vsu.labs.crypto.dto.test;

import lombok.Data;

import java.util.List;

@Data
public class Test {
    private Long testId;
    private List<Task> tasks;
    private Integer mark;
}
