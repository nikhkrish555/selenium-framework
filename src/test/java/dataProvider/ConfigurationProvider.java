package dataProvider;

import java.io.File;
import java.io.IOException;

import org.ini4j.Ini;
import org.ini4j.InvalidFileFormatException;

public class ConfigurationProvider {
	Ini prefs;
	final String DEFAULT_DRIVER_PATH = "./BrowserDrivers/geckodriver-v0.20.1-win64/geckodriver.exe";
	final String DEFAULT_HEADER = "qa";

	public ConfigurationProvider() {
		final File fname = new File("./Configuration/config.ini");
		try {
			prefs = new Ini(fname);
		} catch (InvalidFileFormatException e) {
			System.out.println("Configuration file format is not .ini. " + e.getMessage());
		} catch (IOException e) {
			System.out.println("Configuration file does not exist. Check the path. " + e.getMessage());
		}
	}

	public String getApplicationURL(final String header, final String key, final String dflt) {
		String value;
		try {
			if (prefs.get(header, key).isEmpty()) {
				value = dflt;
			} else {
				value = prefs.get(header, key);
			}
		} catch (NullPointerException e) {
			System.out.println("Caught null pointer exception. Header \"" + header + "\" or key \"" + key
					+ "\" does not exist in config file. Setting to default value. Exception: " + e.getMessage());
			value = dflt;
		}
		return value;
	}

	public String getBrowserDriverPath(final String header, final String key) {
		String value;
		try {
			if (prefs.get(header, key).isEmpty()) {
				value = DEFAULT_DRIVER_PATH;
			} else {
				value = prefs.get(header, key);
			}
		} catch (NullPointerException e) {
			System.out.println("Caught null pointer exception. Header \"" + header + "\" or key \"" + key
					+ "\" does not exist in config file. Setting to default value. Exception: " + e.getMessage());
			value = DEFAULT_DRIVER_PATH;
		}
		return value;
	}

}
