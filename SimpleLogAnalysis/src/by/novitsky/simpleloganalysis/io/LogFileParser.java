package by.novitsky.simpleloganalysis.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import by.novitsky.simpleloganalysis.entity.FilterObject;
import by.novitsky.simpleloganalysis.entity.LogLineObject;
import by.novitsky.simpleloganalysis.filter.FilterBuilder;
import by.novitsky.simpleloganalysis.filter.LineFilter;

public class LogFileParser {
		
	private File file;
		
	public LogFileParser(File file) {
		this.file = file;
	}
		
	public List<LogLineObject> parse() {
		String line;
		List<LogLineObject> result = new ArrayList<>();
		ParametersGetterStrategy params = ParametersGetterManager.getManager().getCurrentStrategy();
		FilterObject filter = params.getFilterObject();
		LineFilter lineFilter = new FilterBuilder(filter).getFilter();
		
		try (BufferedReader reader = new BufferedReader(new FileReader(file))){
			while((line = reader.readLine()) != null) {	 
				LogLineObject lineObj = parseLine(line);
				if (lineFilter.isInputFilterValid(lineObj, filter)) {
					result.add(lineObj);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	private static LogLineObject parseLine(String input) {
		ParametersGetterStrategy pgs = ParametersGetterManager.getManager().getCurrentStrategy();
		String separator = pgs.getLogFileSeparator();
		
		String[] parsedStringArray = input.split(separator);
		
		LogLineObject result = new LogLineObject();
		
		result.setUserName(parsedStringArray[0]);
		
		LocalDateTime dateTime = LocalDateTime.parse(parsedStringArray[1]);
		result.setDate(dateTime);
		
		result.setMessage(parsedStringArray[2]);
		
		return result;
	}
	
}
