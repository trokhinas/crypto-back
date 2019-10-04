package vsu.labs.crypto.controllers.algs;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import vsu.labs.crypto.algs.common.ControlPanelBlock;
import vsu.labs.crypto.algs.encryption.transposition.TableTransposition;
import vsu.labs.crypto.dto.algs.TableTranspositionRequest;
import vsu.labs.crypto.dto.response.Response;
import vsu.labs.crypto.service.algs.encryption.TableTranspositionService;

import java.util.List;

@RestController
@RequestMapping("table-transposition")
@AllArgsConstructor
public class TableTranspositionRestController {
    private static final Logger log = LoggerFactory.getLogger(TableTranspositionRestController.class);

    private final TableTranspositionService tableTranspositionService;

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

    @PostMapping("whatever")
    public Response whatever(@RequestBody List<ControlPanelBlock> blocks) {
        return Response.success(tableTranspositionService.startAlgorithm(blocks));
    }
}
