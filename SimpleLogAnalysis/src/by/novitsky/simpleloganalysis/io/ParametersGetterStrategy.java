package by.novitsky.simpleloganalysis.io;

import by.novitsky.simpleloganalysis.entity.FilterObject;
import by.novitsky.simpleloganalysis.entity.GroupingParametersObject;

public interface ParametersGetterStrategy {
	
	String getLogDirectory();
	FilterObject getFilterObject();
	String getOutputDirectory();
	String getLogFileSeparator();
	int getNumberOfThreads();
	GroupingParametersObject getGroupingParameters();
}
