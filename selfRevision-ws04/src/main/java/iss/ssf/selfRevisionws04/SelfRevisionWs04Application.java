package iss.ssf.selfRevisionws04;

import java.io.File;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SelfRevisionWs04Application implements ApplicationRunner {

	public static void main(String[] args) {
		// SpringApplication.run(SelfRevisionWs04Application.class, args);

		// setup a spring application
		SpringApplication app = new SpringApplication(SelfRevisionWs04Application.class);

		// set a port number
		String port = "8080";
		ApplicationArguments cliOpts = new DefaultApplicationArguments(args);

		if (cliOpts.containsOption("port")) {
			port = cliOpts.getOptionValues("port").get(0);
		}
		app.setDefaultProperties(Collections.singletonMap("server.port", port));
		app.run(args);

	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
	}

	/*
		@Override
	 	public void run(ApplicationArguments args) throws Exception {

		Logger logger = Logger.getLogger(SelfRevisionWs04Application.class.getName());

		if (args.containsOption("dirData")) {
			final String dirPath = args.getOptionValues("dirData").get(0);

			File filePath = new File(dirPath);
			if (!filePath.exists()){
				filePath.mkdir();
				logger.log(Level.INFO, ">>> File directory created");
			} else {
				logger.log(Level.INFO, ">>> File directory already exists");
			}
			
		} else {

			logger.log(Level.INFO, ">>> Directory not provided");
			System.exit(0);
		}
	}

	 */


}
