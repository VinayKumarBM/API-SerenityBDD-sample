package com.serenityAPI.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StudentApplication {
	private Logger log = LoggerFactory.getLogger(StudentApplication.class);
	private final String applicationFolder = System.getProperty("user.dir")+"\\src\\test\\resources\\studentApp\\";
	private final String logFilePath = applicationFolder+"output.txt";
	
	public void deleteLogFile() {
		File file = new File(logFilePath);
		log.info("Stundent Application log file was deleted: "+file.delete());
	}

	public void start() throws IOException, InterruptedException {
		deleteLogFile();

		Runtime runtime = Runtime.getRuntime();
		runtime.exec("cmd /c start start_application.bat", null, new File(applicationFolder));

		boolean hasAppStarted = false;
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.SECOND, 45);
		long stopTime = calendar.getTimeInMillis();
		System.out.print("Waiting for application to start");
		while(System.currentTimeMillis() < stopTime) {
			if(new File(logFilePath).exists()) {
				// Read from output file and check if the node is registered to hub
				BufferedReader reader = new BufferedReader(new FileReader(logFilePath));
				String currentLine = reader.readLine();
				while(currentLine!=null) {
					if(currentLine.contains("Started RestApplication")) {
						log.info(currentLine);
						hasAppStarted = true;
						break;
					}
					currentLine = reader.readLine(); 
				}
				reader.close();				
				Thread.sleep(2000);
				System.out.print(".");
			}
			if(hasAppStarted) {
				log.info("Stundent Application has started: "+hasAppStarted);
				break;
			}
		}
	}

	public void shutdown() throws IOException, InterruptedException {
		log.info("Closing the Student Application");
		Runtime.getRuntime().exec("taskkill /IM cmd.exe");
		Thread.sleep(5000);
	}
}
