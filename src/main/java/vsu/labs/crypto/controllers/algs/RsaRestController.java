package vsu.labs.crypto.controllers.algs;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vsu.labs.crypto.controllers.algs.abstr.AbstractAlgController;
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
}
