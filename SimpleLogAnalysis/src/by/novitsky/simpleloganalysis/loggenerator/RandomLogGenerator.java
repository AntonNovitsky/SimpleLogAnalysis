package by.novitsky.simpleloganalysis.loggenerator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import by.novitsky.simpleloganalysis.entity.LogLineObject;

public class RandomLogGenerator {
	
	private static final String[] NAMES = {"Kevin", "David", "Oliver", "Amelia", "Mia", "Sophia"};
	
	
	public static LocalDateTime randomDate() {
		LocalDateTime now = LocalDateTime.now();
		int year = 60 * 60 * 24 * 365;
		return now.plusSeconds((long) (- year * Math.random()));
	}
	
	public static String randomName() {
		return NAMES[new Random().nextInt(NAMES.length)];
	}
	
	public static String randomMessage() {
		int leftLimit = 97; 
		int rightLimit = 122; 
		int targetStringLength = (int) (Math.random() * 10 + 10);
		Random random = new Random();
		StringBuilder buffer = new StringBuilder(targetStringLength);
		for (int i = 0; i < targetStringLength; i++) {
			int randomLimitedInt = leftLimit + (int) 
			(random.nextFloat() * (rightLimit - leftLimit + 1));
			buffer.append((char) randomLimitedInt);
		}
		String generatedString = buffer.toString();
		return generatedString;
	}
	
	public static List<LogLineObject> getRandomLog(int minWidth, int width) {
		List<LogLineObject> result = new ArrayList<LogLineObject>();
		int size = (int) (Math.random() * width + minWidth);
		for (int i = 0; i < size; i++) {
			String name = randomName();
			LocalDateTime date = randomDate();
			String message = randomMessage();
			LogLineObject temp = new LogLineObject(name, date, message);
			result.add(temp);
		}
		return result;
	}
	
	public static List<LogLineObject> getRandomLog(){
		return getRandomLog(1500, 1000);
	}

}
