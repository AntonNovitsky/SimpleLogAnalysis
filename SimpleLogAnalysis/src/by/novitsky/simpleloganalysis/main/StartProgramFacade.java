package by.novitsky.simpleloganalysis.main;

import java.util.List;

import by.novitsky.simpleloganalysis.entity.GroupingParametersObject;
import by.novitsky.simpleloganalysis.entity.LogLineObject;
import by.novitsky.simpleloganalysis.grouping.GroupingManager;
import by.novitsky.simpleloganalysis.io.GroupingPrinter;
import by.novitsky.simpleloganalysis.io.LogDirectoryParser;
import by.novitsky.simpleloganalysis.io.OutputFileWriter;
import by.novitsky.simpleloganalysis.io.ParametersGetterManager;
import by.novitsky.simpleloganalysis.loggenerator.RandomLogWriter;

public class StartProgramFacade {
	
	private StartProgramFacade() {
		throw new RuntimeException("Can't create instance of this class");
	}
	
	public static void startProgram() {
		List<LogLineObject> result = new LogDirectoryParser().parse();
		new OutputFileWriter().CreateOutputFile(result);
		GroupingParametersObject groupingParams = ParametersGetterManager.getManager().getCurrentStrategy().getGroupingParameters();
		GroupingManager gm = new GroupingManager(groupingParams, result);
		GroupingPrinter gp = new GroupingPrinter(gm);
		gp.printUserGrouping();
		gp.printDateGrouping();
	}
	
	public static void startProgram(int input) {
		generateRandomLog(input);
		startProgram();
	}
	
	public static void generateRandomLog() {
		RandomLogWriter writer = new RandomLogWriter();
		writer.writeRandomLog();
	}
	
	public static void generateRandomLog(int input) {
		for (int i = 1; i <= input; i++) {
			generateRandomLog();
		}
	}

}
