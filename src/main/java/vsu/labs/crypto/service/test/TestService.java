package vsu.labs.crypto.service.test;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import vsu.labs.crypto.dto.mapper.TestMapper;
import vsu.labs.crypto.dto.test.TestDto;
import vsu.labs.crypto.entity.JpaRepository.TestRepository;
import vsu.labs.crypto.entity.test.TestEntity;

import java.util.List;

@Service
@AllArgsConstructor @Slf4j
public class TestService {
    private final TestRepository testRepository;
    private final TestMapper testMapper;

    public boolean addTest(TestDto testDto) {
        TestEntity testOnSave = testMapper.fromDto(testDto);
        TestEntity saveTest = testRepository.save(testOnSave);
        return saveTest != null;
    }

    public List<TestDto> getAll() {
        List<TestEntity> testEntities = testRepository.findAll();
        return testMapper.toDto(testEntities);
    }
    public TestDto getById(Long id){
        TestEntity testEntity = testRepository.findById(id).get();
        return testMapper.toDto(testEntity);
    }
}
