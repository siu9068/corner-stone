package kr.cornerstone;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableEncryptableProperties
@EnableJpaAuditing
@SpringBootApplication
public class CornerStoneApplication {

    public static void main(String[] args) {
        SpringApplication.run(CornerStoneApplication.class, args);

    }

}
