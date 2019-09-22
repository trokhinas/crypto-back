package vsu.labs.crypto.controllers.algs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import vsu.labs.crypto.algs.encryption.transposition.TableTransposition;
import vsu.labs.crypto.dto.algs.TableTranspositionRequest;
import vsu.labs.crypto.dto.response.Response;

@RestController
@RequestMapping("table-transposition")
public class TableTranspositionRestController {
    private static final Logger log = LoggerFactory.getLogger(TableTranspositionRestController.class);

    @PostMapping("encrypt")
    public Response encrypt(@RequestBody TableTranspositionRequest request,
                            @RequestParam(required = false) boolean isStaging) {
        log.info("call encrypt TableTransposition with request = {}, isStaging = {}", request, isStaging);

        if (isStaging)
            return Response.success(TableTransposition.encrypt(request.getText(), request.getKey()));
        return Response.success(TableTransposition.encrypt(request.getText(), request.getKey()));
    }

    @PostMapping("decrypt")
    public Response decrypt(@RequestBody TableTranspositionRequest request,
                            @RequestParam(required = false) boolean isStaging) {
        log.info("call encrypt TableTransposition with request = {}, isStaging = {}", request, isStaging);

        if (isStaging)
            return Response.success(TableTransposition.decrypt(request.getText(), request.getKey()));
        return Response.success(TableTransposition.decrypt(request.getText(), request.getKey()));
    }
}
