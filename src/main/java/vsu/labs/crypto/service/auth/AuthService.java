package vsu.labs.crypto.service.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vsu.labs.crypto.dto.auth.AuthResponse;
import vsu.labs.crypto.dto.auth.LoginRequest;
import vsu.labs.crypto.entity.JpaRepository.RoleRepository;
import vsu.labs.crypto.entity.JpaRepository.UserRepository;
import vsu.labs.crypto.entity.security.UserEntity;
import vsu.labs.crypto.entity.security.RoleEntity;
import vsu.labs.crypto.enums.RoleType;
import vsu.labs.crypto.exceptions.LogicException;

@Service
public class AuthService {
    private static final Logger log = LoggerFactory.getLogger(AuthService.class);

    private static final String NO_AUTH = "Incorrect username or password";

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    public AuthResponse authenticate(LoginRequest request) {
        log.info("start method authenticate for request - {}", request);
        if (request.getPassword() == null || request.getLogin() == null)
            throw new IllegalStateException();

        UserEntity user = userRepository.findByLogin(request.getLogin());
        if (user != null && user.getPassword().equals(getHash(request.getPassword()))) {
            // TODO реализовать получение RoleType по roleId из класса User
            RoleEntity role = roleRepository.findById(user.getRoleId()).get();
            return new AuthResponse(user, RoleType.byId(role.getId()));
        }
        throw new LogicException(NO_AUTH);
    }

    public AuthResponse fakeAuth() {
        log.info("start method fakeAuth");
        UserEntity user = userRepository.findAll().get(0);
        return new AuthResponse(user, RoleType.ADMIN);
    }

    // TODO реализовать функцию получению хэша от пароля
    private String getHash(String password) {

        return password;
    }
}
