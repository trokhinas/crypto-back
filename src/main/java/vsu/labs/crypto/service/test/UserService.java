package vsu.labs.crypto.service.test;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import vsu.labs.crypto.dto.mapper.TestMapper;
import vsu.labs.crypto.dto.mapper.UserMapper;
import vsu.labs.crypto.dto.test.UserDto;
import vsu.labs.crypto.entity.JpaRepository.MarkRepository;
import vsu.labs.crypto.entity.JpaRepository.TestRepository;
import vsu.labs.crypto.entity.JpaRepository.UserRepository;
import vsu.labs.crypto.entity.security.UserEntity;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public boolean addUser(UserDto user) {
        UserEntity userOnSave = userMapper.fromDto(user);
        UserEntity saveEntity = userRepository.save(userOnSave);
        if (saveEntity == null) {
            return false;
        } else return true;
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    public boolean changeUser(UserDto user) {
        UserEntity userOnSave = userMapper.fromDto(user);
        UserEntity saveEntity = userRepository.save(userOnSave);
        if (saveEntity == null) {
            return false;
        } else return true;
    }
}
