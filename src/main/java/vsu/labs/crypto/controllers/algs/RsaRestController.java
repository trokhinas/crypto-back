package vsu.labs.crypto.controllers.algs;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import vsu.labs.crypto.controllers.algs.abstr.AbstractAlgController;
import vsu.labs.crypto.dto.algs.AlgBlockRequest;
import vsu.labs.crypto.dto.response.Response;
import vsu.labs.crypto.service.algs.sign.RsaService;


@RestController
@RequestMapping("rsa")
@AllArgsConstructor @Slf4j
public class RsaRestController extends AbstractAlgController {

    private final RsaService rsaService;

    @Override
    public Response getBlocks() {
        log.info("call get blocks");
        return Response.success(rsaService.getBlocks());
    }

    @GetMapping("keys")
    public Response getKeys(@RequestParam(required = false) Boolean isStaging) {
        if (isStaging)
            return Response.success(rsaService.stagingGenerateKeys());
        return Response.success(rsaService.generateKeys());
    }

    @PostMapping("check-sign")
    public Response checkSign(
            @RequestBody AlgBlockRequest algBlockRequest,
            @RequestParam(required = false) Boolean isStaging) {
        if (isStaging)
            return Response.success(rsaService.stagingCheckMessage(algBlockRequest.getBlocks()));
        return Response.success(rsaService.checkSign(algBlockRequest.getBlocks()));
    }

    @PostMapping("sign-message")
    public Response stagingGenerateMessage(@RequestBody AlgBlockRequest algBlockRequest,
                                           @RequestParam(required = false) Boolean isStaging) {
        if (isStaging)
            return Response.success(rsaService.stagingGenerateMessage(algBlockRequest.getBlocks()));
        return Response.success(rsaService.generateMessage(algBlockRequest.getBlocks()));
    }
}
