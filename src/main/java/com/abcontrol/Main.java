package com.abcontrol;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


// com esta anotação eu estou dizendo que este main executa uma aplicação springboot
// reconhece os beans
// reconhece todas as configurações das anotações
@SpringBootApplication
public class Main{

    public static void main(String[] args) {

        // passando alguns argumentos (args) da linha de comando
        // rodando a partir da classe Main.class
        // para testar local abrir no navegador por exemplo localhost:8080/tarefas assim ele chama o método trazendo todas as tarefas
        SpringApplication.run(Main.class, args);

    }

}
