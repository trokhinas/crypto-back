package vsu.labs.crypto.controllers.algs;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vsu.labs.crypto.algs.sha_1.Sha1;
import vsu.labs.crypto.dto.algs.Sha1Request;

@RestController
@RequestMapping("sha1")
public class Sha1Controller {
    @GetMapping
    public String getHash(@RequestBody Sha1Request sha1Request){
        return Sha1.sha1(sha1Request.getText());
    }
}
