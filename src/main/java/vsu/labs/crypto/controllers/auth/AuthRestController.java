package vsu.labs.crypto.controllers.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vsu.labs.crypto.dto.auth.LoginRequest;
import vsu.labs.crypto.dto.response.Response;
import vsu.labs.crypto.service.auth.AuthService;


@RestController
@RequestMapping("auth")
public class AuthRestController {
    private static final Logger log = LoggerFactory.getLogger(AuthRestController.class);

    @Autowired
    private AuthService authService;

    @PostMapping("auth")
    public Response auth(@RequestBody LoginRequest request) {
        log.info("call auth");
        return Response.success(authService.authenticate(request));
    }

    @PostMapping("fakeAuth")
    public Response fakeAuth() {
        log.info("call fakeAuth");
        return Response.success(authService.fakeAuth());
    }
}
