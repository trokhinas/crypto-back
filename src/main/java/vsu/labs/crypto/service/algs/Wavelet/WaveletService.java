package vsu.labs.crypto.service.algs.Wavelet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import vsu.labs.crypto.algs.common.BlocksResponse;
import vsu.labs.crypto.exceptions.algs.encryption.transposition.StorageFileNotFoundException;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;

@Service
public class WaveletService {

    private final Path rootLocation;

    @Autowired
    public WaveletService() {
        this.rootLocation = Paths.get("");
    }

    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }

    public Resource loadAsResource(String filename) {
        try {
            String root = System.getProperty("user.dir");
            System.out.println(root);
            String fullPathToFile = root+"\\src\\main\\resources\\pictures\\"+filename;
            System.out.println(fullPathToFile);
            Path file = load(fullPathToFile);
            Resource resource = new UrlResource(file.toUri());
            if(resource.exists() || resource.isReadable()) {
                return resource;
            }
            else {
                throw new StorageFileNotFoundException("Could not read file: " + filename);

            }
        } catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read file: " + filename, e);
        }
    }


    public BlocksResponse getBlocks() {
        return BlocksResponse.withFileCompression(Collections.emptyMap());
    }


}
