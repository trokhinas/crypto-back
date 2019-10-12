package vsu.labs.crypto.controllers.test;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import vsu.labs.crypto.dto.response.Response;
import vsu.labs.crypto.dto.test.TestDto;
import vsu.labs.crypto.service.test.TestService;

@RestController
@RequestMapping("tests")
@AllArgsConstructor
@Slf4j
public class TestRestController {
    private final TestService testService;

    @PostMapping("check")
    public Response check(@RequestBody TestDto test) {
        log.info("call check for test {}", test);
        return Response.success();
    }

    @GetMapping("all")
    public Response getAll() {
        log.info("call getAll");
        return Response.success(testService.getAll());
    }

    @GetMapping()
    public Response getById(@RequestParam Long id) {
        log.info("call getById");
        return Response.success(testService.getById(id));
    }


    @PostMapping("addTest")
    public boolean addTest(@RequestBody TestDto test) {
        return testService.addTest(test);
    }
}
