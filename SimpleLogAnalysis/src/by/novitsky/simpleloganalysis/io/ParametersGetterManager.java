package by.novitsky.simpleloganalysis.io;

public class ParametersGetterManager {
	
	private ParametersGetterStrategy currentStrategy;
	private static final ParametersGetterManager manager = new ParametersGetterManager();
		
	private ParametersGetterManager() {
		currentStrategy = new PropertiesReader();
	}
	
	public ParametersGetterStrategy getCurrentStrategy() {
		return currentStrategy;
	}
	
	public void setStrategy(ParametersGetterStrategy input) {
		currentStrategy = input;
	}
	
	public static ParametersGetterManager getManager() {
		return manager;
	}

}
