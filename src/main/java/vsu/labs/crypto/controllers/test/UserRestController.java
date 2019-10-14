package vsu.labs.crypto.controllers.test;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import vsu.labs.crypto.dto.test.UserDto;
import vsu.labs.crypto.service.test.UserService;


@RestController
@RequestMapping("user")
@AllArgsConstructor
public class UserRestController {
    private final UserService userService;

    @PostMapping()
    public boolean addUser(@RequestBody UserDto userDto) {
        return userService.addUser(userDto);
    }

    @PutMapping()
    public boolean changeUser(@RequestBody UserDto userDto) {
        return userService.changeUser(userDto);
    }

    @DeleteMapping()
    public void deleteUser(@RequestBody UserDto userDto) {
        userService.deleteUser(userDto);
    }
}
