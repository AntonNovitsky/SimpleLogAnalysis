package by.novitsky.simpleloganalysis.filter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import by.novitsky.simpleloganalysis.entity.FilterObject;
import by.novitsky.simpleloganalysis.entity.LogLineObject;

public class MessageFilter implements LineFilter {

	private LineFilter lineFilter;
	
	public MessageFilter(LineFilter lineFilter) {
		this.lineFilter = lineFilter;
	}

	@Override
	public boolean isInputFilterValid(LogLineObject input, FilterObject filter) {
		return lineFilter.isInputFilterValid(input, filter) && this.isFiltered(input, filter);
	}
	
	private boolean isFiltered(LogLineObject input, FilterObject filter) {
		try {
			Pattern p = Pattern.compile(filter.getMessage());
			Matcher m = p.matcher(input.getMessage());
			return m.matches();
		} catch (PatternSyntaxException e) {
			return true;
		}
		
	}

}
