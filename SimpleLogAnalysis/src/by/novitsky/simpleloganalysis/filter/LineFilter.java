package by.novitsky.simpleloganalysis.filter;

import by.novitsky.simpleloganalysis.entity.FilterObject;
import by.novitsky.simpleloganalysis.entity.LogLineObject;

public interface LineFilter {

	boolean isInputFilterValid(LogLineObject input, FilterObject filter);
	
}
