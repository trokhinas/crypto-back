package vsu.labs.crypto.controllers.algs;


import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import vsu.labs.crypto.algs.morse.Morse;
import vsu.labs.crypto.controllers.algs.abstr.AbstractAlgController;
import vsu.labs.crypto.dto.algs.MorseRequest;
import vsu.labs.crypto.dto.response.Response;
import vsu.labs.crypto.service.algs.encryption.MorseService;

@RestController
@RequestMapping("morse")
@AllArgsConstructor
public class MorseController extends AbstractAlgController {
    private static final Logger log = LoggerFactory.getLogger(MorseController.class);

    private MorseService morseService;

    @PostMapping("encrypt")
    public Response encrypt(@RequestBody MorseRequest request,
                            @RequestParam(required = false) boolean isStaging) {
        log.info("call encrypt Morse with request = {}, isStaging = {}", request, isStaging);

        if (isStaging)
            return Response.success(Morse.stagingCode(request.getText()));
        return Response.success(Morse.code(request.getText()));
    }

    @PostMapping("decrypt")
    public Response decrypt(@RequestBody MorseRequest request,
                            @RequestParam(required = false) boolean isStaging) {
        log.info("call decrypt Morse with request = {}, isStaging = {}", request, isStaging);

        if (isStaging)
            return Response.success(Morse.stagingDecode(request.getText()));
        return Response.success(Morse.decode(request.getText()));
    }

    @Override
    @GetMapping("blocks")
    public Response getBlocks() {
        return Response.success(morseService.getBlocks());
    }
}
