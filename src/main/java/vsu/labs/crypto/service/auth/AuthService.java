package vsu.labs.crypto.service.auth;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import vsu.labs.crypto.algs.sha_1.Sha1;
import vsu.labs.crypto.dto.auth.AuthResponse;
import vsu.labs.crypto.dto.auth.LoginRequest;
import vsu.labs.crypto.entity.JpaRepository.UserRepository;
import vsu.labs.crypto.entity.security.UserEntity;
import vsu.labs.crypto.enums.RoleType;
import vsu.labs.crypto.exceptions.LogicException;

@Service @AllArgsConstructor
public class AuthService {
    private static final Logger log = LoggerFactory.getLogger(AuthService.class);

    private static final String NO_AUTH = "Incorrect username or password";

    private final UserRepository userRepository;

    public AuthResponse authenticate(LoginRequest request) {
        log.info("start method authenticate for request - {}", request);
        if (request.getPassword() == null || request.getLogin() == null)
            throw new IllegalStateException();

        UserEntity user = userRepository.findByLogin(request.getLogin());
        if (user != null && user.getPassword().equalsIgnoreCase(getHash(request.getPassword()))) {
            return new AuthResponse(user, RoleType.byId(user.getRoleId()));
        }
        throw new LogicException(NO_AUTH);
    }

    public AuthResponse fakeAuth() {
        log.info("start method fakeAuth");
        UserEntity user = userRepository.findAll().get(0);
        return new AuthResponse(user, RoleType.ADMIN);
    }

    private String getHash(String password) {
        return Sha1.hash(password);
    }
}
