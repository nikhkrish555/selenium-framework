package factory;

import dataProvider.ConfigurationProvider;

public class DataProviderFactory {
	public static ConfigurationProvider getConfig() {
		ConfigurationProvider config = new ConfigurationProvider();
		return config;
	}

}
