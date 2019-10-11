package vsu.labs.crypto.dto.test;

import lombok.Data;

import java.util.List;

@Data
public class TestDto {
    private Long testId;
    private String title;
    private List<TaskDto> tasks;
}
