package com.example.ppm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class PpmApplication {
	@Bean
    PasswordEncoder bCryptPasswordEncoder(){
//        return new BCryptPasswordEncoder();
		PasswordEncoder passwordEncoder =PasswordEncoderFactories.createDelegatingPasswordEncoder();
        return passwordEncoder;
	}

	public static void main(String[] args) {
		SpringApplication.run(PpmApplication.class, args);
	}

}
