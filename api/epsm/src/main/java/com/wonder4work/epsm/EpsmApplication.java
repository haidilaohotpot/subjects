package com.wonder4work.epsm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.annotations.ApiIgnore;

@SpringBootApplication
@ApiIgnore
public class EpsmApplication {

    public static void main(String[] args) {
        SpringApplication.run(EpsmApplication.class, args);
    }

}
