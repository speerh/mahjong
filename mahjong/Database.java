package mahjong;

import java.util.ArrayList;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;

public class Database

{
	// Private data fields for storing the file streams.
	FileInputStream fis;
	FileOutputStream fos;

	private Connection conn;

	// Add any other data fields you like – at least a Connection object is
	// mandatory
	public Database() {
		// Add your code here
		// Read the database file.
		// Read properties file
		Properties prop = new Properties();
		try {
			FileInputStream fis = new FileInputStream("mahjong/db.properties");
			prop.load(fis);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String url = prop.getProperty("url");
		String user = prop.getProperty("user");
		String pass = prop.getProperty("password");

		try {
			conn = DriverManager.getConnection(url, user, pass);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList<String> query(String query) {
		// Add your code here
		int i = 0;
		int rowCount = 0; // detect empty result set
		ArrayList<String> array = new ArrayList<String>();

		// create a statement from the connection
		Statement stmt;
		try {
			stmt = conn.createStatement();

			// run the query
			ResultSet rs = stmt.executeQuery(query);

			// get metadata
			ResultSetMetaData rmd = rs.getMetaData();
			int noColumns = rmd.getColumnCount();
			while (rs.next()) {
				String record = "";

				for (i = 0; i < noColumns; i++) {
					String value = rs.getString(i + 1);
					record += value + ",";
					array.add(record);
					rowCount++;
				}
			}

			if (rowCount > 0)
				return array;
			else
				return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			return null;
		}

	}

	public void executeDML(String dml) throws SQLException {
		// Add your code here
		Statement stmt = conn.createStatement();
		stmt.execute(dml);
	}

}