package vsu.labs.crypto.controllers.algs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import vsu.labs.crypto.algs.encryption.rot13.Rot13;
import vsu.labs.crypto.dto.algs.Rot13Request;
import vsu.labs.crypto.dto.response.Response;

@RestController
@RequestMapping("rot-13")
public class Rot13RestController {
    private static final Logger log = LoggerFactory.getLogger(Rot13RestController.class);

    @PostMapping("encrypt")
    public Response encrypt(@RequestBody Rot13Request request,
                            @RequestParam(required = false) boolean isStaging) {
        log.info("call encrypt ROT-13 with request = {}, isStaging = {}", request, isStaging);

        if (isStaging)
            return Response.success(Rot13.stagingEncrypt(request.getText()));
        return Response.success(Rot13.encrypt(request.getText()));
    }

    @PostMapping("decrypt")
    public Response decrypt(@RequestBody Rot13Request request,
                            @RequestParam(required = false) boolean isStaging) {
        log.info("call decrypt ROT-13 with request = {}, isStaging = {}", request, isStaging);

        if (isStaging)
            return Response.success(Rot13.stagingDerypt(request.getText()));
        return Response.success(Rot13.decrypt(request.getText()));
    }
}
