package vsu.labs.crypto.controllers.algs;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import vsu.labs.crypto.algs.common.ControlPanelBlock;
import vsu.labs.crypto.controllers.algs.abstr.AbstractAlgController;
import vsu.labs.crypto.dto.algs.AlgBlockRequest;
import vsu.labs.crypto.dto.response.Response;
import vsu.labs.crypto.service.algs.hash.Sha1Service;

import java.util.Map;

@RestController
@RequestMapping("sha-1")
@AllArgsConstructor @Slf4j
public class Sha1RestController extends AbstractAlgController {

    private final Sha1Service sha1Service;

    @FunctionalInterface
    private interface Action {
        Object act(Map<String, ControlPanelBlock> blocks);
    }

    @Override
    public Response getBlocks() {
        log.info("call get blocks");
        return Response.success(sha1Service.getBlocks());
    }

    @PostMapping("hash")
    public Response hash(@RequestBody AlgBlockRequest request,
                         @RequestParam(required = false) Boolean isStaging) {
        log.info("call hash with blocks = {}", request.getBlocks());
        Action producer = isStaging ? sha1Service::stagingHash : sha1Service::hash;
        return Response.success(producer.act(request.getBlocks()));
    }
}
