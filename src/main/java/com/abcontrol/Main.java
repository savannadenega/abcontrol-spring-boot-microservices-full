package com.abcontrol;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

// com esta anotação eu estou dizendo que este main executa uma aplicação springboot
// reconhece os beans
// reconhece todas as configurações das anotações
@SpringBootApplication
@EntityScan(basePackages = {"com.abcontrol.entity"})
@EnableJpaRepositories(basePackages = {"com.abcontrol.repository"})
public class Main{

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

}