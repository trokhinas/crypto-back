package vsu.labs.crypto.service.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vsu.labs.crypto.dto.mapper.UserMapper;
import vsu.labs.crypto.dto.test.UserDto;
import vsu.labs.crypto.entity.JpaRepository.UserRepository;
import vsu.labs.crypto.entity.security.UserEntity;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }
    public boolean addUser(UserDto user){
        UserEntity userOnSave = userMapper.fromDto(user);
        UserEntity saveEntity = userRepository.save(userOnSave);
        if (saveEntity == null){
            return false;
        }
        else return true;
    }
    public void deleteUser(UserDto user){
        UserEntity userOnSave = userMapper.fromDto(user);
        userRepository.delete(userOnSave);
    }

    public boolean changeUser(UserDto user){
        UserEntity userOnSave = userMapper.fromDto(user);
        UserEntity saveEntity = userRepository.save(userOnSave);
        if (saveEntity == null){
            return false;
        }
        else return true;
    }

}
