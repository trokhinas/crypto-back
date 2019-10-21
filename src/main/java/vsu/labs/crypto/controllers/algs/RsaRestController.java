package vsu.labs.crypto.controllers.algs;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import vsu.labs.crypto.algs.common.ControlPanelBlock;
import vsu.labs.crypto.controllers.algs.abstr.AbstractAlgController;
import vsu.labs.crypto.dto.crypto.PartitionAlgData;
import vsu.labs.crypto.dto.response.Response;
import vsu.labs.crypto.service.algs.sign.RsaService;

import java.math.BigInteger;
import java.util.Map;

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
    @GetMapping("/generateKeys")
    public String getKeys(){
        return rsaService.generateKeys();
    }
    @PutMapping("/checkSign")
    public String checkSign(@RequestBody Map<String, ControlPanelBlock> blocks){
        return rsaService.checkSign(blocks);
    }
    @GetMapping("/stagingGenerateKeys")
    public PartitionAlgData stagingGenerateKeys(){
        return rsaService.stagingGenerateKeys();
    }
    @PostMapping("/stagingGenerateMessage")
    public PartitionAlgData stagingGenerateMessage(@RequestBody BigInteger d,@RequestBody BigInteger n,@RequestBody String message){
        return rsaService.stagingGenerateMessage(d,n,message);
    }
    @PostMapping("/stagingCheckMessage")
    public PartitionAlgData stagingCheckMessage(@RequestBody Map<String, ControlPanelBlock> blocks){
        return rsaService.stagingCheckMessage(blocks);
    }


}
