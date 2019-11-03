package vsu.labs.crypto.controllers.algs;

import liquibase.util.file.FilenameUtils;
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
import java.util.Map;

@RestController
@RequestMapping("wavelet")
@AllArgsConstructor
@Slf4j
public class WaveletController extends AbstractAlgController {
    private final String pathToRoot = "src/main/resources/pictures";
    private final WaveletService waveletService;
    private static String extension;

    @PostMapping("upload")
    public Response handleFileUpload(@RequestParam("id") int id,
                                   @RequestParam(required = false) Long eps,
                                   @RequestParam("file") MultipartFile file) throws IOException {
        if (file != null) {
            File dir = new File(pathToRoot);
            if (!dir.exists()) {
                dir.mkdir();
            }
            String extension = "."+FilenameUtils.getExtension(file.getOriginalFilename());
            WaveletController.extension = extension;
            String pathOriginalFile = pathToRoot + "/original" + id + extension;
            String pathTransformedFile = pathToRoot + "/transformed" + id + extension;
            String pathCompressedFile = pathToRoot + "/Compressed" + id + extension;
            File newFile = new File(pathOriginalFile);
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(newFile));
            stream.write(file.getBytes());
            stream.close();
            Wavelet.haarCompression(pathOriginalFile, pathCompressedFile, pathTransformedFile, eps == null ? 0 : eps);
            return Response.success();
        }
        return Response.fail("Файл пуст!");
    }

    @GetMapping("transformed")
    public ResponseEntity<Resource> getTransformed(@RequestParam("id") int id) {
        String path = "transformed" + id + WaveletController.extension;
        Resource file = waveletService.loadAsResource(path);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @GetMapping("compressed")
    public ResponseEntity<Resource> getCompressed(@RequestParam("id") int id) {
        String path = "Compressed" + id + WaveletController.extension;
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
