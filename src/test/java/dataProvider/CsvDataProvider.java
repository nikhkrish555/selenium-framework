package dataProvider;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;

public class CsvDataProvider {
	String applicationTestDataPath;
	File fname;

	public CsvDataProvider() {
		applicationTestDataPath = System.getProperty("user.dir") + File.separator + "ApplicationTestData";
		fname = new File(applicationTestDataPath + File.separator + "AppData.csv");
	}

	public String getTestData(final String key) {
		String value = null;
		try {
			if (fname.exists() && !fname.isDirectory()) {
				Class.forName("org.relique.jdbc.csv.CsvDriver");
				final Connection repoCon = DriverManager.getConnection("jdbc:relique:csv:" + applicationTestDataPath);
				final Statement repoStmt = repoCon.createStatement();
				final ResultSet repoRslt = repoStmt.executeQuery(
						"SELECT value FROM AppData WHERE LOWER(key) = '" + key.toLowerCase(Locale.US) + "'");
				try {
					if (repoRslt.next()) {
						value = repoRslt.getString("value");
					} else {
						System.out.println("Key does not exists!");
					}
				} finally {
					repoRslt.close();
					repoStmt.close();
					repoCon.close();
				}
			} else {
				System.out.println("FileName does not exists or name is a directory!");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Value: " + value);
		return value;
	}
}
