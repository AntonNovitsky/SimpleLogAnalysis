package by.novitsky.simpleloganalysis.io;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileNameGenerator {
	
	private FileNameGenerator() {
		throw new RuntimeException("Can't create instance of this class");
	}
	
	public static String generateFileName() {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
		String formatDateTime = now.format(formatter);
		return ("logfile - " + formatDateTime + ".txt");
	}

}
