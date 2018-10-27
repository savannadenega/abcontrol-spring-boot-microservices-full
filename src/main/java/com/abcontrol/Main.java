package com.abcontrol;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// com esta anotação eu estou dizendo que este main executa uma aplicação springboot
// reconhece os beans
// reconhece todas as configurações das anotações
@SpringBootApplication


public class Main{

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);

    }

}
