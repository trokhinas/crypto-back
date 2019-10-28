package vsu.labs.crypto.controllers.algs;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vsu.labs.crypto.algs.compression.wavelet.Wavelet;
import vsu.labs.crypto.controllers.algs.abstr.AbstractAlgController;
import vsu.labs.crypto.dto.response.Response;
import vsu.labs.crypto.service.algs.Wavelet.WaveletService;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@RestController
@RequestMapping("wavelet")
@AllArgsConstructor
@Slf4j
public class WaveletController extends AbstractAlgController {
    private final String pathToRoot = "src/main/resources/pictures";
    private final WaveletService waveletService;

    @PostMapping("upload")
    public String handleFileUpload(@RequestParam("id") int id, @RequestParam int eps, @RequestParam("file") MultipartFile file) throws IOException {
        if (file != null) {
            File dir = new File(pathToRoot);
            if (!dir.exists()) {
                dir.mkdir();
            }
            String pathOriginalFile = pathToRoot + "/original" + id + ".jpg";
            String pathTransformedFile = pathToRoot + "/transformed" + id + ".jpg";
            String pathCompressedFile = pathToRoot + "/Compressed" + id + ".jpg";

            File newFile = new File(pathOriginalFile);
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(newFile));
            stream.write(file.getBytes());
            stream.close();
            Wavelet.haarCompression(pathOriginalFile, pathCompressedFile, pathTransformedFile, eps);
        }
        return null;
    }

    @GetMapping("/file")
    public ResponseEntity<Resource> serveFile() {
        String path = "original1.jpg";
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
