package vsu.labs.crypto.controllers.algs;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import vsu.labs.crypto.controllers.algs.abstr.AbstractAlgController;
import vsu.labs.crypto.dto.algs.AlgBlockRequest;
import vsu.labs.crypto.dto.response.Response;
import vsu.labs.crypto.service.algs.encryption.Rot13Service;

@RestController
@RequestMapping("rot-13")
@AllArgsConstructor @Slf4j
public class Rot13RestController extends AbstractAlgController {

    private final Rot13Service rot13Service;

    @PostMapping("encrypt")
    public Response encrypt(@RequestBody AlgBlockRequest request,
                            @RequestParam(required = false) boolean isStaging) {
        log.info("call encrypt ROT-13 with request = {}, isStaging = {}", request, isStaging);

        if (isStaging)
            return Response.success(rot13Service.stagingEncrypt(request.getBlocks()));
        return Response.success(rot13Service.encrypt(request.getBlocks()));
    }

    @PostMapping("decrypt")
    public Response decrypt(@RequestBody AlgBlockRequest request,
                            @RequestParam(required = false) boolean isStaging) {
        log.info("call decrypt ROT-13 with request = {}, isStaging = {}", request, isStaging);

        if (isStaging)
            return Response.success(rot13Service.stagingDecrypt(request.getBlocks()));
        return Response.success(rot13Service.decrypt(request.getBlocks()));
    }

    @GetMapping("blocks")
    public Response getBlocks() {
        log.info("call get blocks");
        return Response.success(rot13Service.getBlocks());
    }
}
