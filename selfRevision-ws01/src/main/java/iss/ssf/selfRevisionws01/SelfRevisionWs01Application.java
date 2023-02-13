package iss.ssf.selfRevisionws01;

import java.util.Collections;
import java.util.logging.Logger;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SelfRevisionWs01Application {

	public static void main(String[] args) {

		// SpringApplication.run(SelfRevisionWs01Application.class, args);

		// create a new spring application to read the command line
		SpringApplication app = new SpringApplication(SelfRevisionWs01Application.class);

		// check if the command line contains a port value
		// else set port to default value 8080
		String port = "3000";

		ApplicationArguments cliOpts = new DefaultApplicationArguments(args);
		if (cliOpts.containsOption("port")) {
			port = cliOpts.getOptionValues("port").get(0);
		}

		// set the port number to default property
		app.setDefaultProperties(Collections.singletonMap("server.port", port));

		app.run(args);

	}

}
