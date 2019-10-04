package vsu.labs.crypto.controllers.algs;


import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import vsu.labs.crypto.algs.common.ControlPanelBlock;
import vsu.labs.crypto.algs.morse.Morse;
import vsu.labs.crypto.controllers.algs.abstr.AbstractAlgController;
import vsu.labs.crypto.dto.algs.AlgBlockRequest;
import vsu.labs.crypto.dto.algs.MorseRequest;
import vsu.labs.crypto.dto.response.Response;
import vsu.labs.crypto.service.algs.encryption.MorseService;

import java.util.List;

@RestController
@RequestMapping("morse")
@AllArgsConstructor
public class MorseController extends AbstractAlgController {
    private static final Logger log = LoggerFactory.getLogger(MorseController.class);

    private MorseService morseService;

    @PostMapping("encode")
    public Response encode(@RequestBody AlgBlockRequest request,
                           @RequestParam(required = false) boolean isStaging) {
        log.info("call encode Morse with request = {}, isStaging = {}", request, isStaging);

        if (isStaging)
            return Response.success(morseService.stagingEncode(request.getBlocks()));
        return Response.success(morseService.encode(request.getBlocks()));
    }

    @PostMapping("decode")
    public Response decode(@RequestBody AlgBlockRequest request,
                            @RequestParam(required = false) boolean isStaging) {
        log.info("call decode Morse with request = {}, isStaging = {}", request, isStaging);

        if (isStaging)
            return Response.success(morseService.stagingDecode(request.getBlocks()));
        return Response.success(morseService.decode(request.getBlocks()));
    }

    @Override
    @GetMapping("blocks")
    public Response getBlocks() {
        return Response.success(morseService.getBlocks());
    }
}
