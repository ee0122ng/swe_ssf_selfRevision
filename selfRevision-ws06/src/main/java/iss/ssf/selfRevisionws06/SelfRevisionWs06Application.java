package iss.ssf.selfRevisionws06;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import iss.ssf.selfRevisionws06.repo.BoardgameRepo;

@SpringBootApplication
public class SelfRevisionWs06Application implements CommandLineRunner {

	@Autowired
	BoardgameRepo gameRepo;

	public static void main(String[] args) {
		SpringApplication.run(SelfRevisionWs06Application.class, args);
	}

	@Override
	public void run(String... agrs) {

		System.out.printf(">>> all id: %s\n".formatted(gameRepo.getAllId()));
	}

}
