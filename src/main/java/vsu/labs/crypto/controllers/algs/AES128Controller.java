package vsu.labs.crypto.controllers.algs;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import vsu.labs.crypto.algs.common.ControlPanelBlock;
import vsu.labs.crypto.algs.digSignature.aes128.AES128;
import vsu.labs.crypto.controllers.algs.abstr.AbstractAlgController;
import vsu.labs.crypto.dto.algs.AlgBlockRequest;
import vsu.labs.crypto.dto.response.Response;
import vsu.labs.crypto.service.algs.aes128.Aes128Service;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

@RestController
@RequestMapping("aes")
@AllArgsConstructor @Slf4j
public class AES128Controller extends AbstractAlgController {
    private final Aes128Service aes128Service;

    @FunctionalInterface
    private interface Action {
        Object act(Map<String, ControlPanelBlock> blocks) throws NoSuchPaddingException, BadPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, UnsupportedEncodingException, InvalidKeyException;
    }
    @Override
    public Response getBlocks() {
        log.info("call get blocks");
        return Response.success(aes128Service.getBlocks());
    }

    @PostMapping("encrypt")
    public Response encrypt(@RequestBody AlgBlockRequest request,
                         @RequestParam(required = false) Boolean isStaging) throws NoSuchPaddingException, InvalidKeyException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        log.info("call encrypt with blocks = {}", request.getBlocks());
        Action producer = isStaging ? aes128Service::stagingEncode : aes128Service::encode;
        return Response.success(producer.act(request.getBlocks()));
    }
    @PostMapping("decrypt")
    public Response decrypt(@RequestBody AlgBlockRequest request,
                         @RequestParam(required = false) Boolean isStaging) throws NoSuchPaddingException, InvalidKeyException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        log.info("call encrypt with blocks = {}", request.getBlocks());
        Action producer = isStaging ? aes128Service::stagingDecode : aes128Service::decode;
        return Response.success(producer.act(request.getBlocks()));
    }
}
