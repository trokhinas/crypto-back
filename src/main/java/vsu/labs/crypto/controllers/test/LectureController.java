package vsu.labs.crypto.controllers.test;


import liquibase.util.file.FilenameUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vsu.labs.crypto.algs.compression.wavelet.Wavelet;
import vsu.labs.crypto.controllers.algs.WaveletController;
import vsu.labs.crypto.dto.response.Response;
import vsu.labs.crypto.dto.test.LectureDto;
import vsu.labs.crypto.service.algs.Wavelet.WaveletService;
import vsu.labs.crypto.service.test.LectureService;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("lecture")
@AllArgsConstructor
@Slf4j
public class LectureController {

    private final String pathToRoot = "src/main/resources/lecture";
    private final LectureService lectureService;

    @PostMapping("upload")
    public Response handleFileUpload(@RequestParam Long userId, @RequestParam("file") MultipartFile file) throws Exception {
        if (file != null) {
            File dir = new File(pathToRoot);
            if (!dir.exists()) {
                dir.mkdir();
            }
            String pathOriginalFile = pathToRoot +"/"+ file.getOriginalFilename();
            File newFile = new File(pathOriginalFile);
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(newFile));
            stream.write(file.getBytes());
            stream.close();
            lectureService.addLecture(userId, file.getOriginalFilename(), file.getOriginalFilename());
        }
        return Response.success();
    }

    @GetMapping("find")
    public ResponseEntity<Resource> getTransformed(@RequestParam("id") Long id) throws Exception {

        String path = lectureService.findLectureById(id);
        Resource file = lectureService.loadAsResource(path);
        return ResponseEntity.ok().header(
                HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @GetMapping("all")
    public Response getAll(){
        return Response.success(lectureService.getAll());
    }
}
