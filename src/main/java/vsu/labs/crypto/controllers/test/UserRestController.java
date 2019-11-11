package vsu.labs.crypto.controllers.test;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import vsu.labs.crypto.dto.response.Response;
import vsu.labs.crypto.dto.test.UserDto;
import vsu.labs.crypto.service.test.UserService;


@RestController
@RequestMapping("user")
@AllArgsConstructor
public class UserRestController {
    private final UserService userService;

    @PostMapping()
    public Response addUser(@RequestBody UserDto user) {
        return Response.success(userService.changeOrAddUser(user));
    }


    @DeleteMapping()
    public Response deleteUser(@RequestParam Long userId) {
        userService.deleteUser(userId);
        return Response.success();
    }
}
