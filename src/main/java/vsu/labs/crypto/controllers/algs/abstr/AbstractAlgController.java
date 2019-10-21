package vsu.labs.crypto.controllers.algs.abstr;

import org.springframework.web.bind.annotation.GetMapping;
import vsu.labs.crypto.dto.response.Response;

public abstract class AbstractAlgController {
    public static final String BLOCKS_REQUEST = "blocks";

    @GetMapping(BLOCKS_REQUEST)
    public abstract Response getBlocks();
}
