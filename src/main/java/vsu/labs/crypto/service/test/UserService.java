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
        for (int i = 0; i < markEntitiesOfUser.size(); i++){
            UserTestDto userTestDto = new UserTestDto();
            int idL =  markEntitiesOfUser.get(i).getTestId();
            userTestDto.setTestId(idL);
            userTestDto.setAllQuestion(markEntitiesOfUser.get(i).getAll_question());
            userTestDto.setCorrectAnswer(markEntitiesOfUser.get(i).getCorrectAnswer());
            userTestDtos.add(userTestDto);
        }
        return userTestDtos;
    }

}
