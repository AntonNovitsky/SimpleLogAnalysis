package by.novitsky.simpleloganalysis.filter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import by.novitsky.simpleloganalysis.entity.DateGroupingObject;

public class GroupParamFilter {
	
	private static final String HOUR_GROUPING_PARAM = "h";
	private static final String HOUR_NAME_PARAM = "Hour";
	private static final String DAY_GROUPING_PARAM = "d";
	private static final String DAY_NAME_PARAM = "Day";
	private static final String MONTH_GROUPING_PARAM = "m";
	private static final String MONTH_NAME_PARAM = "Month";
	
	private static final Map<String, DateGroupingObject> STRING_TO_DGO;
	
	static {
		Map<String, DateGroupingObject> temp = new HashMap<>();
		
		DateGroupingObject dgo = new DateGroupingObject();
		dgo.setName(HOUR_NAME_PARAM);
		dgo.setF(x -> LocalDateTime.of(x.getDate().getYear(), x.getDate().getMonth(), x.getDate().getDayOfMonth(), x.getDate().getHour(), 0));
		dgo.setFormat(DateTimeFormatter.ofPattern("yyyy-MM-dd/-HH"));
		temp.put(HOUR_GROUPING_PARAM, dgo);
		
		dgo = new DateGroupingObject();
		dgo.setName(DAY_NAME_PARAM);
		dgo.setF(x -> LocalDateTime.of(x.getDate().getYear(), x.getDate().getMonth(), x.getDate().getDayOfMonth(), 0, 0));
		dgo.setFormat(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		temp.put(DAY_GROUPING_PARAM, dgo);
		
		dgo = new DateGroupingObject();
		dgo.setName(MONTH_NAME_PARAM);
		dgo.setF(x -> LocalDateTime.of(x.getDate().getYear(), x.getDate().getMonth(), 1, 0, 0));
		dgo.setFormat(DateTimeFormatter.ofPattern("yyyy-MM"));
		temp.put(MONTH_GROUPING_PARAM, dgo);
		
		STRING_TO_DGO = temp;
		
	}
	
	private GroupParamFilter() {
		throw new RuntimeException("Can't create instance of this class");
	}
	
	public static DateGroupingObject parseDateParam(String input) {
		DateGroupingObject dgo = STRING_TO_DGO.get(input.trim().toLowerCase());
		return dgo;
	}
	
	public static boolean parseNameParam(String input) {
		return Boolean.valueOf(input);
	}
	
}
