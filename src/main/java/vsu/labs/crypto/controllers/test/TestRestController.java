package vsu.labs.crypto.controllers.test;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import vsu.labs.crypto.dto.response.Response;
import vsu.labs.crypto.dto.test.Test;
import vsu.labs.crypto.service.test.TestService;

@RestController
@RequestMapping("tests")
@AllArgsConstructor
public class TestRestController {
    private static final Logger log = LoggerFactory.getLogger(TestRestController.class);

    private final TestService testService;

    @PostMapping("check")
    public Response check(@RequestBody Test test) {
        log.info("call check for test {}", test);
        return Response.success();
    }

    @GetMapping("{id}")
    public Response getTestById(@PathVariable Long id) {
        log.info("call getTestById, with id = {}", id);
        return Response.success();
    }

    @PutMapping
    public Response updateTest(@RequestBody Test test) {
        log.info("call update test");
        return Response.success();
    }

    @PostMapping
    public Response createTest(@RequestBody Test test) {
        log.info("call update test");
        testService.createTest(test);
        return Response.success();
    }

    @DeleteMapping("{id}")
    public Response deleteTest(@PathVariable Long id) {
        log.info("call delete test with id = {}", id);
        return Response.success();
    }
}
