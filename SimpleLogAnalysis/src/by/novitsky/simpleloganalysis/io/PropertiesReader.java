package by.novitsky.simpleloganalysis.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Properties;

import by.novitsky.simpleloganalysis.entity.FilterObject;
import by.novitsky.simpleloganalysis.entity.GroupingParametersObject;
import by.novitsky.simpleloganalysis.filter.GroupParamFilter;

public class PropertiesReader implements ParametersGetterStrategy {
	
	private static final String DEFAULT_PROP_PATH = "resources" + File.separator + "config.properties";
	private static final String LOG_DIRECTORY_PARAM = "log_directory";
	private static final String USERNAME_FILTER_PARAM = "username_filter";
	private static final String TIMEPERIOD_FILTER_PARAM = "timeperiod_filter";
	private static final String MESSAGE_FILTER_PARAM = "message_filter";
	private static final String USERNAME_GROUP_PARAM = "username_group";
	private static final String TIMEUNIT_GROUP_PARAM = "timeunit_group";
	private static final String NUMBER_OF_THREADS = "number_of_threads";
	private static final String TIMEPERIOD_SEPARATOR = "->";
	private static final String OUTPUT_DIRECTORY = "output_directory";
	private static final String LOG_FILE_SEPARATOR = "log_file_separator";
	
	private String path;
	private Properties prop;
	
	public PropertiesReader(){
		path = DEFAULT_PROP_PATH;
		loadProp();
	}
	
	public PropertiesReader(String path) {
		this.path = path;
		loadProp();
	}

	public void loadProp() {
		File file = new File(path);
		prop = new Properties();
		
		try (FileInputStream inputFileStream = new FileInputStream(file)){
			prop.load(inputFileStream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getLogDirectory() {
		return prop.getProperty(LOG_DIRECTORY_PARAM);
	}
	
	public String getOutputDirectory() {
		return prop.getProperty(OUTPUT_DIRECTORY);
	}
	
	public FilterObject getFilterObject() {
		FilterObject result = new FilterObject();
		result.setUserName(prop.getProperty(USERNAME_FILTER_PARAM,""));
		result.setMessage(prop.getProperty(MESSAGE_FILTER_PARAM,""));
		
		String timeString = prop.getProperty(TIMEPERIOD_FILTER_PARAM,"");
		parseAndSetTime(timeString, result);
		
		return result;
	}
	
	private static void parseAndSetTime(String time, FilterObject filter) {
		if (!"".equals(time.trim())) {
			String [] timePeriodString = time.split(TIMEPERIOD_SEPARATOR);
			LocalDateTime start = LocalDateTime.parse(timePeriodString[0]);
			LocalDateTime end = LocalDateTime.parse(timePeriodString[1]);
			setTime(start, end, filter);
		}
	}
	
	private static void setTime(LocalDateTime start, LocalDateTime end, FilterObject filter) {
		if (start.isAfter(end)) {
			setTime(end, start, filter);
		}
		filter.setTimeStart(start);
		filter.setTimeEnd(end);
	}
	
	public String getLogFileSeparator() {
		return prop.getProperty(LOG_FILE_SEPARATOR);
	}
	
	public int getNumberOfThreads() {
		String resultString = prop.getProperty(NUMBER_OF_THREADS,"");
		int result;
		try {
			result = Math.abs(Integer.valueOf(resultString));
		} catch (NumberFormatException e) {
			result = 1;
		}
		return result;
	}
	
	public GroupingParametersObject getGroupingParameters() {
		
		GroupingParametersObject result = new GroupingParametersObject();
		
		String userName = prop.getProperty(USERNAME_GROUP_PARAM,"");
		result.setUserName(GroupParamFilter.parseNameParam(userName));
		
		String date = prop.getProperty(TIMEUNIT_GROUP_PARAM,"");
		result.setDgo(GroupParamFilter.parseDateParam(date));
		
		return result;
	}

}
