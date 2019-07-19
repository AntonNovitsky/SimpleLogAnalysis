package by.novitsky.simpleloganalysis.io;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import by.novitsky.simpleloganalysis.entity.LogLineObject;

public class LogDirectoryParser {
	
	private static int MULTITHREADING_WAIT_TIME_SECONDS = 600;

	public LogDirectoryParser() {
		
	}
	
	public List<LogLineObject> parse(int numberOfThreads){
		File[] logFiles = getDirectoryFiles();
		List<LogLineObject> result = new ArrayList<>();
		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(numberOfThreads);
		
		for (File logFile : logFiles) {
			executor.execute(()-> {
				LogFileParser lfp = new LogFileParser(logFile);
				List<LogLineObject> fileParsingResult = lfp.parse();
				synchronized (result) {
					result.addAll(fileParsingResult);
				}
			});
		}
		
		executor.shutdown();
        try {
			executor.awaitTermination(MULTITHREADING_WAIT_TIME_SECONDS, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        
		return result;
	}
	
	public List<LogLineObject> parse(){
		ParametersGetterStrategy pgs = ParametersGetterManager.getManager().getCurrentStrategy();
		int numberOfThreads = pgs.getNumberOfThreads();
		return parse(numberOfThreads);
	}

	public File[] getDirectoryFiles() {
		ParametersGetterStrategy pr = ParametersGetterManager.getManager().getCurrentStrategy();
		String directory = pr.getLogDirectory();
		return getDirectoryFiles(directory);
	}
	
	public File[] getDirectoryFiles(String input) {
		File dirFile = new File(input);
		return getDirectoryFiles(dirFile);
	}
	
	public File[] getDirectoryFiles(File input) {
		File[] result = input.listFiles();
		return result;
	}
	
	
}
