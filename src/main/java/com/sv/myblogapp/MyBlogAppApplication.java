package com.sv.myblogapp;

import com.sv.myblogapp.model.Post;
import com.sv.myblogapp.dao.*;
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
	@Bean
	public CommandLineRunner commandLineRunner(PostDAO postDAO){
		return runner->{
			findAllPost(postDAO);
		};
	}

	private void findAllPost(PostDAO postDAO) {
		List<Post> posts=postDAO.showAllPosts();

		for(Post post:posts){
			System.out.println(post);
		}
	}

}
