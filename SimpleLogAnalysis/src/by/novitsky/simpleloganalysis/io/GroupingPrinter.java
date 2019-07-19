package by.novitsky.simpleloganalysis.io;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.TreeMap;

import by.novitsky.simpleloganalysis.grouping.GroupingManager;

public class GroupingPrinter {
	
	private GroupingManager gm;
	private static final String USER_GROUPING_HEADER = "User - Count of records";
	private static final String LINE_DIVIDER = "----------";
	
	public GroupingPrinter(GroupingManager gm) {
		this.gm = gm;
	}

	public void printUserGrouping() {
		Map<String, Long> result = gm.groupByUserNameSorted();
		if (!result.isEmpty()) {
			print(result,USER_GROUPING_HEADER);
		}
	}
	
	public void printDateGrouping() {
		Map<LocalDateTime, Long> result = gm.groupByDate();
		
		if (!result.isEmpty()) {
			String DateGroupingHeader = gm.getGroupingParams().getDgo().getName();
			Map<String, Long> formatedResult = new TreeMap<>();
			DateTimeFormatter format = gm.getGroupingParams().getDgo().getFormat();
			for(LocalDateTime temp : result.keySet()) {
				formatedResult.put(temp.format(format), result.get(temp));
			}
			print(formatedResult,DateGroupingHeader + " - Count of records");
		}
	}
	
	private static void print(Map<?,?> input, String header) {
		System.out.println(LINE_DIVIDER);
		System.out.println(header);
		for(Map.Entry<?, ?> item : input.entrySet()) {
			System.out.println(item.getKey() + " - " + item.getValue());
		}
		System.out.println(LINE_DIVIDER);
		System.out.println();
	}

}
