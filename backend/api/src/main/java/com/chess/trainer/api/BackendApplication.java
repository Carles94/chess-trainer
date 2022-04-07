package com.chess.trainer.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = { "com.chess.trainer.persistence", "com.chess.trainer.domain",
        "com.chess.trainer.api" })
@EnableJpaRepositories("com.chess.trainer.persistence")
@EntityScan("com.chess.trainer.persistence")
public class BackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

}
