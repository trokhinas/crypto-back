package vsu.labs.crypto.controllers.test;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vsu.labs.crypto.dto.response.Response;
import vsu.labs.crypto.dto.test.TestDto;
import vsu.labs.crypto.service.test.TestService;

@RestController
@RequestMapping("tests")
@AllArgsConstructor
public class TestRestController {
    private static final Logger log = LoggerFactory.getLogger(TestRestController.class);
    @Autowired
    private final TestService testService;

    @PostMapping("check")
    public Response check(@RequestBody TestDto test) {
        log.info("call check for test {}", test);
        return Response.success();
    }

    @GetMapping("hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("helloResponse")
    public Response helloResponse() {
        return Response.success("hello");
    }

    @PostMapping("addTest")
    public boolean addTest(@RequestBody TestDto test){
        return testService.addTest(test);
    }
}
