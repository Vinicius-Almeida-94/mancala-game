package com.vinicius_almeida.mancala;

import com.vinicius_almeida.mancala.web.GameController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackageClasses = GameController.class)
public class MancalaGameApplication {

    public static void main(String[] args) {
        SpringApplication.run(MancalaGameApplication.class, args);
    }

}
