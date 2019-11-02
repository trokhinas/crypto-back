package vsu.labs.crypto.service.users;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import vsu.labs.crypto.dto.users.GridColumn;
import vsu.labs.crypto.dto.users.GridResponse;
import vsu.labs.crypto.entity.JpaRepository.MarkRepository;
import vsu.labs.crypto.entity.JpaRepository.TestRepository;
import vsu.labs.crypto.entity.JpaRepository.UserRepository;
import vsu.labs.crypto.entity.security.UserEntity;
import vsu.labs.crypto.entity.test.MarkEntity;
import vsu.labs.crypto.entity.test.TestEntity;
import vsu.labs.crypto.enums.RoleType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor @Slf4j
public class UsersMarksGridService {

    private final UserRepository userRepository;
    private final TestRepository testRepository;
    private final MarkRepository markRepository;

    public GridResponse getGridData() {
        List<GridColumn> columns = buildColumns();
        List<UserEntity> students = userRepository.findByRoleId(RoleType.STUDENT.getId());
        List<TestEntity> tests = testRepository.findAll();

        List<Map<String, Object>> data = new ArrayList<>();
        students.stream().map(student -> buildStudentRow(student, tests)).forEach(data::add);

        return new GridResponse(columns, data);
    }

    private Map<String, Object> buildStudentRow(UserEntity userEntity, List<TestEntity> tests) {
        Map<String, Object> userRow = new HashMap<>();
        userRow.put("userName", userEntity.getName());

        Long userId = userEntity.getId();
        tests.forEach(test -> {
            String key = generateKey(test);
            String mark = getMark(test.getId(), userId);

            userRow.put(key, mark);
        });
        return userRow;
    }

    private List<GridColumn> buildColumns() {
        List<GridColumn> columns = new ArrayList<>();
        columns.add(DefaultColumns.userNameColumns);

        testRepository.findAll().forEach(testEntity -> {
            GridColumn gridColumn = new GridColumn();
            gridColumn.setKey(generateKey(testEntity));
            gridColumn.setHeader(testEntity.getTitle());

            columns.add(gridColumn);
        });
        return columns;
    }

    private String generateKey(TestEntity testEntity) {
        return "test" + testEntity.getId();
    }

    private String getMark(Long testId, Long userId) {
        MarkEntity mark = markRepository.findByUserIdAndTestId(userId, testId);
        if (mark == null)
            return "N/A";

        return mark.getCorrectAnswer() + "/" + mark.getAll_question();
    }
}
