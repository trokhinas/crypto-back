package vsu.labs.crypto.controllers.auth;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import vsu.labs.crypto.dto.auth.LoginRequest;
import vsu.labs.crypto.dto.response.Response;
import vsu.labs.crypto.service.auth.AuthService;


@RestController @AllArgsConstructor
@RequestMapping("auth")
public class AuthRestController {
    private static final Logger log = LoggerFactory.getLogger(AuthRestController.class);

    private final AuthService authService;

    @PostMapping("login")
    public Response auth(@RequestBody LoginRequest request) {
        log.info("call auth");
        return Response.success(authService.authenticate(request));
    }

    @PostMapping("fake-login")
    public Response fakeAuth() {
        log.info("call fakeAuth");
        return Response.success(authService.fakeAuth());
    }
}
