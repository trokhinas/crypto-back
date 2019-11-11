package vsu.labs.crypto.service.test;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import vsu.labs.crypto.algs.common.BlocksResponse;
import vsu.labs.crypto.dto.mapper.LectureMapper;
import vsu.labs.crypto.dto.test.LectureDto;
import vsu.labs.crypto.entity.JpaRepository.LectureRepository;
import vsu.labs.crypto.entity.JpaRepository.UserRepository;
import vsu.labs.crypto.entity.test.LectureEntity;
import vsu.labs.crypto.exceptions.algs.encryption.transposition.StorageFileNotFoundException;

import java.io.File;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

@Service
public class LectureService {
    private final LectureRepository lectureRepository;
    private final UserRepository userRepository;
    private final LectureMapper lectureMapper;
    private final Path rootLocation;

    @Autowired
    public LectureService(LectureRepository lectureRepository, UserRepository userRepository, LectureMapper lectureMapper) {
        this.lectureRepository = lectureRepository;
        this.userRepository = userRepository;
        this.lectureMapper = lectureMapper;
        this.rootLocation = Paths.get("");
    }

    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }

    public Resource loadAsResource(String filename) {
        try {
            String root = System.getProperty("user.dir");
            String fullPathToFile = root + "\\src\\main\\resources\\lecture\\" + filename;
            Path file = load(fullPathToFile);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new StorageFileNotFoundException("Could not read file: " + filename);

            }
        } catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read file: " + filename);
        }
    }

    public List<LectureDto> getAll() {
        return lectureMapper.toDto(lectureRepository.findAll());
    }

    public BlocksResponse getBlocks() {
        return BlocksResponse.withFileCompression(Collections.emptyMap());
    }

    public Boolean addLecture(Long userId, String pathToFile, String name) throws Exception {
        LectureEntity newLecture = new LectureEntity();
        if (userRepository.findById(userId).get() == null)
            throw new Exception("Пользователя добавляющего лекцию не существует");
        newLecture.setAuthor(userRepository.findById(userId).get());
        newLecture.setReference(pathToFile);
        newLecture.setName(name);
        lectureRepository.save(newLecture);
        return true;
    }

    public String findLectureById(Long id) throws Exception {
        LectureEntity foundedLecture = lectureRepository.findById(id).get();
        if (foundedLecture == null)
            throw new Exception("Лекции с id:" + id + "не существует");
        return foundedLecture.getReference();
    }

    public boolean delete(Long id) throws Exception {
        LectureEntity lectureEntity = lectureRepository.findById(id).get();
        String pathToFile = lectureEntity.getReference();
        lectureRepository.delete(lectureEntity);
        File fileForDelete = new File(pathToFile);
        
        return true;
    }

}
