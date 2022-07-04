package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
	public static Connection con = null;
	private static Connect instance = new Connect();

	public static Connect getInstance() {
		return instance;
	}

	public static void connect() {
		String url = "jdbc:sqlserver://localhost:1433;databaseName=QUANLYLAODONG";
		String user = "sa";
		String password = "sapassword";
		try {
			con = DriverManager.getConnection(url, user, password);
		} catch (SQLException s) {
			s.printStackTrace();
		}
	}

	public static void disconnect() {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static Connection getConnection() {
		connect();

		return con;
	}

}
