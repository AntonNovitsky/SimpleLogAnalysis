package by.novitsky.simpleloganalysis.filter;

import java.time.LocalDateTime;

import by.novitsky.simpleloganalysis.entity.FilterObject;

public class FilterBuilder {
	
	private FilterObject filter;

	public FilterBuilder(FilterObject filter) {
		this.filter = filter;
	}
	
	public LineFilter getFilter() {
		LineFilter result = new TrueLineFilter();
		if (!"".equals(filter.getUserName().trim())) {
			result = new UserNameFilter(result);
		}
		if (!filter.getTimeStart().equals(LocalDateTime.MIN) 
			|| !filter.getTimeEnd().equals(LocalDateTime.MAX)){
			result = new TimeFilter(result);
		}
		if (!"".equals(filter.getMessage().trim())) {
			result = new MessageFilter(result);
		}
		return result;
	}
	
	

}
