package iss.ssf.selfRevisionws02;

import java.util.Collections;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SelfRevisionWs02Application {

	public static void main(String[] args) {
		// SpringApplication.run(SelfRevisionWs02Application.class, args);

		SpringApplication app = new SpringApplication(SelfRevisionWs02Application.class);

		String port = "8080";
		ApplicationArguments cliOpts = new DefaultApplicationArguments(args);
		if (cliOpts.containsOption("port")) {
			port = cliOpts.getOptionValues("port").get(0);
		}

		app.setDefaultProperties(Collections.singletonMap("server.port", port));

		app.run(args);
	}

}
