package iss.ssf.selfRevisionws06;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import iss.ssf.selfRevisionws06.service.GameService;

@SpringBootApplication
public class SelfRevisionWs06Application implements ApplicationRunner {

	@Autowired
	GameService gameSvc;

	private Logger logger = Logger.getLogger(SelfRevisionWs06Application.class.getName());

	public static void main(String[] args) {
		SpringApplication.run(SelfRevisionWs06Application.class, args);
	}

	public void run(ApplicationArguments args) {

		// // check if port is given
		// if (args.containsOption("port")) {
		// 	SpringApplication app = new SpringApplication(SelfRevisionWs06Application.class);

		// 	final String port = args.getOptionValues("port").get(0);
		// 	app.setDefaultProperties(Collections.singletonMap("server.port", port));
			
		// }
		
		// // check if specific data is retrieved
		// if (args.containsOption("gid")) {
		// 	Integer gid = Integer.valueOf(args.getOptionValues("gid").get(0));
		// }

		if (args.containsOption("dataFile")) {
			final String fileName = args.getOptionValues("dataFile").get(0);

			logger.log(Level.INFO, ">>> data file: %s".formatted(fileName));

			// check if the file has been uploaded
			File file = new File("./data/" + fileName + ".json");

			if (file.exists()) {
				logger.log(Level.INFO, ">>> file exists");
				
				try {
					gameSvc.readJsonFile(fileName, file);
				} catch (IOException ex) {
					ex.getStackTrace();
				}
				
				
			} else {
				logger.log(Level.INFO, ">>> file not exists");
				System.exit(0);
			}
		}
	}

}
