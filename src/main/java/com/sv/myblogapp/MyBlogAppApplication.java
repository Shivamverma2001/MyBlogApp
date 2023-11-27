package com.sv.myblogapp;

import com.sv.myblogapp.entity.Post;
import com.sv.myblogapp.repository.PostRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class MyBlogAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyBlogAppApplication.class, args);
	}

}
