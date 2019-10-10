package vsu.labs.crypto.controllers.profile;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vsu.labs.crypto.dto.response.Response;
import vsu.labs.crypto.service.profile.ProfileService;

@RestController
@RequestMapping("profile")
@AllArgsConstructor @Slf4j
public class ProfileRestController {
    private final ProfileService profileService;

    @GetMapping("data")
    public Response loadStudentProfile(@RequestParam Long userId) {
        log.info("call loadStudentProfile with userId = {}", userId);
        return Response.success(profileService.loadStudentProfile(userId));
    }
}
