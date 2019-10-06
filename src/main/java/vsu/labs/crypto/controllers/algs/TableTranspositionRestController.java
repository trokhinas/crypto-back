package vsu.labs.crypto.controllers.algs;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import vsu.labs.crypto.controllers.algs.abstr.AbstractAlgController;
import vsu.labs.crypto.dto.algs.AlgBlockRequest;
import vsu.labs.crypto.dto.response.Response;
import vsu.labs.crypto.service.algs.encryption.TableTranspositionService;

@RestController
@RequestMapping("table-transposition")
@AllArgsConstructor
public class TableTranspositionRestController extends AbstractAlgController {
    private static final Logger log = LoggerFactory.getLogger(TableTranspositionRestController.class);

    private final TableTranspositionService tableTranspositionService;

    @PostMapping("encrypt")
    public Response encrypt(@RequestBody AlgBlockRequest request,
                            @RequestParam(required = false) boolean isStaging) {
        log.info("call encrypt TableTransposition with request = {}, isStaging = {}", request, isStaging);

        if (isStaging)
            return Response.success(tableTranspositionService.stagingEncrypt(request.getBlocks()));
        return Response.success(tableTranspositionService.encrypt(request.getBlocks()));
    }

    @PostMapping("decrypt")
    public Response decrypt(@RequestBody AlgBlockRequest request,
                            @RequestParam(required = false) boolean isStaging) {
        log.info("call encrypt TableTransposition with request = {}, isStaging = {}", request, isStaging);

        if (isStaging)
            return Response.success(tableTranspositionService.stagingDecrypt(request.getBlocks()));
        return Response.success(tableTranspositionService.decrypt(request.getBlocks()));
    }

    @GetMapping("blocks")
    public Response getBlocks() {
        return Response.success(tableTranspositionService.getAlgorithmBlocks());
    }
}
