package vsu.labs.crypto.controllers.algs;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vsu.labs.crypto.controllers.algs.abstr.AbstractAlgController;
import vsu.labs.crypto.dto.response.Response;
import vsu.labs.crypto.service.algs.Wavelet.WaveletService;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

@RestController
@RequestMapping("wavelet")
@AllArgsConstructor @Slf4j
public class WaveletController extends AbstractAlgController {
    private final String pathToRoot = "src/main/resources/pictures";
    private final WaveletService waveletService;
//@RequestParam("id") int id,
    @PostMapping("upload")
    public String handleFileUpload( @RequestParam("file") MultipartFile file) throws IOException {

        if (file != null) {
            File dir = new File(pathToRoot);
            if (!dir.exists()) {
                dir.mkdir();
            }
            String nameOfPicture = file.getOriginalFilename();
            file.transferTo(dir);
            //todo wavelet

        }
        return null;
    }

    @GetMapping("file")
    @ResponseBody
    public ResponseEntity<Resource> serveFile() throws MalformedURLException, URISyntaxException {
        String path = "1true.png";
        Resource file = waveletService.loadAsResource(path);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @Override
    public Response getBlocks() {
        log.info("call get blocks");
        return Response.success(waveletService.getBlocks());
    }
}
