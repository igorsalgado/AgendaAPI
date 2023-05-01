package com.agendaapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2 // Habilita o Swagger
public class AgendaApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(AgendaApiApplication.class, args);
    }

}
