package vsu.labs.crypto.controllers.teacher;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vsu.labs.crypto.dto.response.Response;
import vsu.labs.crypto.service.users.UsersGridService;

@RestController
@RequestMapping("user-grid")
@AllArgsConstructor @Slf4j
public class UsersGridController {

    private UsersGridService usersGridService;

    @GetMapping("marks")
    public Response getMarksGrid() {
        log.info("call method get marks grid");
        return Response.success(usersGridService.getGridData());
    }
}
