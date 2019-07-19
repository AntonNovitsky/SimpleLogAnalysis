package by.novitsky.simpleloganalysis.filter;

import by.novitsky.simpleloganalysis.entity.FilterObject;
import by.novitsky.simpleloganalysis.entity.LogLineObject;

public class TimeFilter implements LineFilter {
	
	private LineFilter lineFilter;
	
	public TimeFilter(LineFilter lineFilter) {
		this.lineFilter = lineFilter;
	}
	@Override
	public boolean isInputFilterValid(LogLineObject input, FilterObject filter) {
		return lineFilter.isInputFilterValid(input, filter) && this.isFiltered(input, filter);
	}
	
	private boolean isFiltered(LogLineObject input, FilterObject filter) {
		if (input.getDate().isAfter(filter.getTimeEnd()) 
				|| input.getDate().isBefore(filter.getTimeStart())) {
			return false;
		}
		return true;
	}

}
