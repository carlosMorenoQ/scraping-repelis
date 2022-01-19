package com.example.practica1;

import com.example.practica1.usecases.ExtractEventsUseCase;
import com.example.practica1.usecases.ExtractUrlsUseCase;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Practica1Application {

    public static void main(String[] args) {
        SpringApplication.run(Practica1Application.class, args);
        var verificacionProceso = new ExtractUrlsUseCase();
        verificacionProceso.verificacion();

    }

}
