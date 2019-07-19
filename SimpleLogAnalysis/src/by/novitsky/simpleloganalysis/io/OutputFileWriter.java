package by.novitsky.simpleloganalysis.io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import by.novitsky.simpleloganalysis.entity.LogLineObject;

public class OutputFileWriter {
	
	public OutputFileWriter() {
		
	}
	
	public void CreateOutputFile(List<LogLineObject> inputList, String outputFileName) {
		ParametersGetterStrategy pgs = ParametersGetterManager.getManager().getCurrentStrategy();
		String outputDirectoryString = pgs.getOutputDirectory();
		CreateOutputFile(inputList, outputDirectoryString, outputFileName);
		
	}
	
	public void CreateOutputFile(List<LogLineObject> inputList, String outputDirectoryString, String outputFileName) {
		File outputFile = new File(outputDirectoryString, outputFileName);
		try {
			outputFile.createNewFile();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try (FileWriter writer = new FileWriter(outputFile)){
			for(LogLineObject line : inputList) {
				writer.write(asLogLine(line) + System.getProperty("line.separator"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void CreateOutputFile(List<LogLineObject> inputList) {
		String outputFileName = FileNameGenerator.generateFileName();
		CreateOutputFile(inputList, outputFileName);
	}
	
	private String asLogLine(LogLineObject line) {
		ParametersGetterStrategy pgs = ParametersGetterManager.getManager().getCurrentStrategy();
		String separator = pgs.getLogFileSeparator();
		return line.getUserName() + separator +
				line.getDate() + separator +
				line.getMessage();
	}
}
