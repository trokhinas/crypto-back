package vsu.labs.crypto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import vsu.labs.crypto.entity.JpaRepository.UserRepository;

@SpringBootApplication
public class CryptoApplication {
    public static void main(String[] args) {
        SpringApplication.run(CryptoApplication.class, args);

    }

}
