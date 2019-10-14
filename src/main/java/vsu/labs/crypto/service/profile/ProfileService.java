package vsu.labs.crypto.service.profile;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import vsu.labs.crypto.dto.test.UserTestDto;
import vsu.labs.crypto.entity.JpaRepository.MarkRepository;
import vsu.labs.crypto.entity.JpaRepository.TestRepository;
import vsu.labs.crypto.entity.test.MarkEntity;
import vsu.labs.crypto.entity.test.TestEntity;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ProfileService {
    private final MarkRepository markRepository;
    private final TestRepository testRepository;

    public List<UserTestDto> loadStudentProfile(Long userId) {
        List<MarkEntity> marks = markRepository.findByUserId(userId);
        List<UserTestDto> tests = new ArrayList<>();
        List<Long> testIds = new ArrayList<>();

        for (var mark: marks) {
            UserTestDto userTestDto = new UserTestDto();
            Long testId = mark.getTestId();
            testIds.add(testId);

            TestEntity testEntity = testRepository.getOne(testId);
            userTestDto.setId(testId);
            userTestDto.setTitle(testEntity.getTitle());
            String markValue = mark.getCorrectAnswer() + "/" + mark.getAll_question();
            userTestDto.setMark(markValue);
            tests.add(userTestDto);
        }

        List<TestEntity> allTest = testRepository.findAll();
        for (TestEntity test : allTest){
            boolean check = true;
            for (var idTest: testIds){
                if (test.getId().equals(idTest)){
                    check = false;
                }
            }
            if (check){
                UserTestDto userTest = new UserTestDto();
                userTest.setId(test.getId());
                userTest.setTitle(test.getTitle());
                userTest.setMark("N/A");
                tests.add(userTest);
            }
        }
        return tests;
    }
}
