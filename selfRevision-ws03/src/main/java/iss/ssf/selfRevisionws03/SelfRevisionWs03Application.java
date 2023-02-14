package iss.ssf.selfRevisionws03;

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
public class SelfRevisionWs03Application implements ApplicationRunner {

	public static void main(String[] args) {
		// SpringApplication.run(SelfRevisionWs03Application.class, args);

		SpringApplication app = new SpringApplication(SelfRevisionWs03Application.class);

		String port = "8080";
		ApplicationArguments cliOpts = new DefaultApplicationArguments(args);
		if(cliOpts.containsOption("port")) {
			port = cliOpts.getOptionValues("port").get(0);
		}

		app.setDefaultProperties(Collections.singletonMap("server.port", port));
		app.run(args);
		
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		String dataDir = "";
		Logger logger = Logger.getLogger(SelfRevisionWs03Application.class.getName());

		if(args.containsOption("dataDir")) {
			dataDir = args.getOptionValues("dataDir").get(0);
			
			// check if the directory exists
			File dir = new File(dataDir);
			if (dir.exists()) {
				logger.log(Level.INFO, ">>> File path already exists: " + dir.getAbsolutePath());
			} else {
				dir.mkdir();
				logger.log(Level.INFO, ">>> File path created");
			}

		} else {
			System.out.println(">>> System killed.\n File directory not provided");
			System.exit(0);
		}

	}
	

}
