package vsu.labs.crypto.controllers.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vsu.labs.crypto.dto.test.UserDto;
import vsu.labs.crypto.service.test.UserService;

@RestController("/user")
public class UserRestController {
    private final UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

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
