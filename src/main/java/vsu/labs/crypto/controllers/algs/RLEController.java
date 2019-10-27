package vsu.labs.crypto.controllers.algs;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import vsu.labs.crypto.algs.common.ControlPanelBlock;
import vsu.labs.crypto.controllers.algs.abstr.AbstractAlgController;
import vsu.labs.crypto.dto.algs.AlgBlockRequest;
import vsu.labs.crypto.dto.response.Response;
import vsu.labs.crypto.service.algs.rle.RLEService;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

@RestController
@RequestMapping("rle")
@AllArgsConstructor
@Slf4j
public class RLEController extends AbstractAlgController {
    private final RLEService rleService;

    @FunctionalInterface
    private interface Action {
        Object act(Map<String, ControlPanelBlock> blocks) throws NoSuchPaddingException, BadPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, UnsupportedEncodingException, InvalidKeyException;
    }

    @Override
    public Response getBlocks() {
        log.info("call get blocks");
        return Response.success(rleService.getBlocks());
    }

    @PostMapping("encrypt")
    public Response encrypt(@RequestBody AlgBlockRequest request,
                            @RequestParam(required = false) Boolean isStaging) throws NoSuchPaddingException, InvalidKeyException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        log.info("call encrypt with blocks = {}", request.getBlocks());
        Action producer = isStaging ? rleService::stagingEncode : rleService::encode;
        return Response.success(producer.act(request.getBlocks()));
    }

    @PostMapping("decrypt")
    public Response decrypt(@RequestBody AlgBlockRequest request,
                            @RequestParam(required = false) Boolean isStaging) throws NoSuchPaddingException, InvalidKeyException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        log.info("call encrypt with blocks = {}", request.getBlocks());
        Action producer = isStaging ? rleService::stagingDecode : rleService::decode;
        return Response.success(producer.act(request.getBlocks()));
    }
}
