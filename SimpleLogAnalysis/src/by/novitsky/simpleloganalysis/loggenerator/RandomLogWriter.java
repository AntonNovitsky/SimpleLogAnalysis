package by.novitsky.simpleloganalysis.loggenerator;

import java.util.List;

import by.novitsky.simpleloganalysis.entity.LogLineObject;
import by.novitsky.simpleloganalysis.io.FileNameGenerator;
import by.novitsky.simpleloganalysis.io.OutputFileWriter;
import by.novitsky.simpleloganalysis.io.ParametersGetterManager;

public class RandomLogWriter {
	
	private List<LogLineObject> data;
	
	public RandomLogWriter(){
		data = RandomLogGenerator.getRandomLog();
	}
	
	public void writeRandomLog() {
		String filePath = ParametersGetterManager.getManager().getCurrentStrategy().getLogDirectory();
		String fileName = RandomLogGenerator.randomMessage() + " " + FileNameGenerator.generateFileName();
		OutputFileWriter writer = new OutputFileWriter();
		writer.CreateOutputFile(data, filePath, fileName);
	}
	
}
