package vsu.labs.crypto.controllers.test;


import liquibase.util.file.FilenameUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import vsu.labs.crypto.algs.compression.wavelet.Wavelet;
import vsu.labs.crypto.controllers.algs.WaveletController;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@RestController
@RequestMapping("lecture")
@AllArgsConstructor
@Slf4j
public class LectureController {

    private final String pathToRoot = "src/main/resources/lecture";
    private static String extension;

    @PostMapping("upload")
    public String handleFileUpload(@RequestParam("id") int id, @RequestParam("file") MultipartFile file) throws IOException {
        if (file != null) {
            File dir = new File(pathToRoot);
            if (!dir.exists()) {
                dir.mkdir();
            }
            String extension = "." + FilenameUtils.getExtension(file.getOriginalFilename());
            LectureController.extension = extension;
            String pathOriginalFile = pathToRoot + "/lecture" + id + extension;
            File newFile = new File(pathOriginalFile);
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(newFile));
            stream.write(file.getBytes());
            stream.close();
        }
        return null;
    }
}
