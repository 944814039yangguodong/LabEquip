package com.university.labequip;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan
public class LabequipApplication {

	public static void main(String[] args) {
		SpringApplication.run(LabequipApplication.class, args);
	}

}
