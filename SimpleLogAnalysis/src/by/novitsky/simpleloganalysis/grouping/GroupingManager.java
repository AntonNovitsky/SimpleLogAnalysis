package by.novitsky.simpleloganalysis.grouping;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import by.novitsky.simpleloganalysis.entity.GroupingParametersObject;
import by.novitsky.simpleloganalysis.entity.LogLineObject;

public class GroupingManager {
	
	private GroupingParametersObject groupingParams;
	private List<LogLineObject> inputList;
	
	public GroupingParametersObject getGroupingParams() {
		return groupingParams;
	}

	public GroupingManager(GroupingParametersObject groupingParams, List<LogLineObject> inputList) {
		this.groupingParams = groupingParams;
		this.inputList = inputList;
	}

	public Map<String, Long> groupByUserName() {
		Map<String, Long> result = new HashMap<>();
		if (groupingParams.isUserName()) {
			Stream<LogLineObject> resultStream = inputList.stream();
			result = resultStream.collect(
					Collectors.groupingBy(LogLineObject::getUserName, Collectors.counting()));
		}
		return result;
	}
	
	public Map<LocalDateTime, Long> groupByDate() {
		Map<LocalDateTime, Long> resultByDate = new HashMap<>();
		if (groupingParams.getDgo() != null) {
			Stream<LogLineObject> resultStream = inputList.stream();
			Function <LogLineObject, LocalDateTime> f = groupingParams.getDgo().getF();
			resultByDate = resultStream.collect(
					Collectors.groupingBy(x -> f.apply(x), Collectors.counting()));
		}
		return resultByDate;
	}
	
	public Map<String, Long> groupByUserNameSorted(){
		Map<String, Long> result = new TreeMap<>();
		Map<String, Long> input =  groupByUserName();
		result.putAll(input);
		return result;
	}
}
