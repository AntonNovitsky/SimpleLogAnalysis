package by.novitsky.simpleloganalysis.filter;

import by.novitsky.simpleloganalysis.entity.FilterObject;
import by.novitsky.simpleloganalysis.entity.LogLineObject;

public class TrueLineFilter implements LineFilter {

	@Override
	public boolean isInputFilterValid(LogLineObject input, FilterObject filter) {
		return true;
	}

}
