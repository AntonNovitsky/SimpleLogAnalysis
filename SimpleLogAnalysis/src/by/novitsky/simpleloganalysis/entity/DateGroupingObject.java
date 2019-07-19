package by.novitsky.simpleloganalysis.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Function;

public class DateGroupingObject {
	
	private Function<LogLineObject, LocalDateTime> f;
	private String name;
	private DateTimeFormatter format;
	
	public DateGroupingObject() {
		
	}
	
	public DateGroupingObject(Function<LogLineObject, LocalDateTime> f, String name, DateTimeFormatter format) {
		this.f = f;
		this.name = name;
		this.format = format;
	}



	public Function<LogLineObject, LocalDateTime> getF() {
		return f;
	}

	public void setF(Function<LogLineObject, LocalDateTime> f) {
		this.f = f;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public DateTimeFormatter getFormat() {
		return format;
	}

	public void setFormat(DateTimeFormatter format) {
		this.format = format;
	}
	
	

}
