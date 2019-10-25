package vsu.labs.crypto.controllers.algs;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import vsu.labs.crypto.algs.common.ControlPanelBlock;
import vsu.labs.crypto.controllers.algs.abstr.AbstractAlgController;
import vsu.labs.crypto.dto.algs.AlgBlockRequest;
import vsu.labs.crypto.dto.response.Response;
import vsu.labs.crypto.service.algs.encryption.ElGamalService;

import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

@RestController
@RequestMapping("el-gamal")
@AllArgsConstructor @Slf4j
public class ElGamalController extends AbstractAlgController {

    private final ElGamalService elGamalService;

    @Override
    public Response getBlocks() {
        log.info("call get blocks");
        return Response.success(elGamalService.getBlocks());
    }

    @GetMapping("keys")
    public Response generateKeys(@RequestParam(required = false) Boolean isStaging) {
        log.info("call generate keys with isStaging = {}", isStaging);
        Supplier<Object> keysSupplier = isStaging ?
                elGamalService::generateKeyStaging :
                elGamalService::generateKeys;
        return Response.success(keysSupplier.get());
    }

    @PostMapping("encrypt")
    public Response encrypt(@RequestBody AlgBlockRequest request,
                            @RequestParam(required = false) Boolean isStaging) {
        log.info("call encrypt with blocks = {}", request.getBlocks());
        Function<Map<String, ControlPanelBlock>, Object> producer = isStaging ?
                elGamalService::stagingEncrypt :
                elGamalService::encrypt;

        return Response.success(producer.apply(request.getBlocks()));
    }
    @PostMapping("decrypt")
    public Response decrypt(@RequestBody AlgBlockRequest request,
                            @RequestParam(required = false) Boolean isStaging) {
        log.info("call decrypt with blocks = {}", request.getBlocks());
        Function<Map<String, ControlPanelBlock>, Object> producer = isStaging ?
                elGamalService::stagingDecrypt :
                elGamalService::decrypt;

        return Response.success(producer.apply(request.getBlocks()));
    }
}
