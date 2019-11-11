package vsu.labs.crypto.service.test;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import vsu.labs.crypto.algs.sha_1.Sha1;
import vsu.labs.crypto.dto.mapper.TestMapper;
import vsu.labs.crypto.dto.mapper.UserMapper;
import vsu.labs.crypto.dto.test.UserDto;
import vsu.labs.crypto.entity.JpaRepository.MarkRepository;
import vsu.labs.crypto.entity.JpaRepository.TestRepository;
import vsu.labs.crypto.entity.JpaRepository.UserRepository;
import vsu.labs.crypto.entity.security.UserEntity;
import vsu.labs.crypto.exceptions.LogicException;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public boolean changeOrAddUser(UserDto user) {
        UserEntity userOnSave = userMapper.fromDto(user);
        boolean exist = user.getId() != null;
        if (!exist && user.getPassword()==null)// новый пользователь
            throw new LogicException("При создании нового пользователя поле пароля пустое");
        if (exist && user.getPassword()==null){// обовление пользователя, не обновляя пароль
            userOnSave.setPassword(userRepository.findById(userOnSave.getId()).get().getPassword());
        }
        if (!exist)// если новый пользователь, надо захэшировать пароль
            userOnSave.setPassword(Sha1.hash(userOnSave.getPassword()));
        UserEntity saveEntity = userRepository.save(userOnSave);
        if (saveEntity == null) {
            return false;
        } else return true;
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

}
