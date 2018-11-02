package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Map;

import com.mysql.jdbc.Driver;


public class DBUtil {
	public static Connection getConnection() throws Exception{
		return getConnection("mysql");
	}

	private static Connection getConnection(String name) throws Exception{
		Map map = ProxyXML.readDao(name);
		Class.forName(map.get("driver").toString());
		Connection connection = DriverManager.getConnection(map.get("url").toString(), map.get("name").toString(), map.get("pass").toString());
		return connection;
	}

}
