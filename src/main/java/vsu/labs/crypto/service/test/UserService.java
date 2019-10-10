package vsu.labs.crypto.service.test;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vsu.labs.crypto.dto.mapper.TestMapper;
import vsu.labs.crypto.dto.mapper.UserMapper;
import vsu.labs.crypto.dto.test.UserDto;
import vsu.labs.crypto.dto.test.UserTestDto;
import vsu.labs.crypto.entity.JpaRepository.MarkRepository;
import vsu.labs.crypto.entity.JpaRepository.TestRepository;
import vsu.labs.crypto.entity.JpaRepository.UserRepository;
import vsu.labs.crypto.entity.security.UserEntity;
import vsu.labs.crypto.entity.test.MarkEntity;
import vsu.labs.crypto.entity.test.TestEntity;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final MarkRepository markRepository;
    private final TestRepository testRepository;
    private final TestMapper testMapper;


    public boolean addUser(UserDto user) {
        UserEntity userOnSave = userMapper.fromDto(user);
        UserEntity saveEntity = userRepository.save(userOnSave);
        if (saveEntity == null) {
            return false;
        } else return true;
    }

    public void deleteUser(UserDto user) {
        UserEntity userOnSave = userMapper.fromDto(user);
        userRepository.delete(userOnSave);
    }

    public boolean changeUser(UserDto user) {
        UserEntity userOnSave = userMapper.fromDto(user);
        UserEntity saveEntity = userRepository.save(userOnSave);
        if (saveEntity == null) {
            return false;
        } else return true;
    }

    public List<UserTestDto> findAllMarkForTest(Long id) {
        List<MarkEntity> markEntitiesOfUser = markRepository.findByUserId(Math.toIntExact(id));
        List<UserTestDto> userTestDtos = new ArrayList<>();
        List<Integer> idTestOfUser = new ArrayList<>();
        for (int i = 0; i < markEntitiesOfUser.size(); i++) {
            UserTestDto userTestDto = new UserTestDto();
            Integer idL = markEntitiesOfUser.get(i).getTestId();
            idTestOfUser.add(idL);
            TestEntity testEntity = testRepository.findById((long) idL).get();
            userTestDto.setId(idL);
            userTestDto.setTitle(testEntity.getTitle());
            String mark = markEntitiesOfUser.get(i).getCorrectAnswer() + "/" + markEntitiesOfUser.get(i).getAll_question();
            userTestDto.setMark(mark);
            userTestDtos.add(userTestDto);
        }
        List<TestEntity> allTest = testRepository.findAll();
        for (TestEntity test : allTest){
            boolean check = true;
            for (int idTest: idTestOfUser){
                if (test.getId()==idTest){
                    check = false;
                }
            }
            if (check){
                UserTestDto userTest = new UserTestDto();
                userTest.setId(Math.toIntExact(test.getId()));
                userTest.setTitle(test.getTitle());
                userTest.setMark("N/A");
                userTestDtos.add(userTest);
            }
        }
            return userTestDtos;
    }

}
