package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String USER = "lim";
    private static final String PASSWORD = "1234";

    public static Connection getConnection() throws SQLException {
    	Connection conn = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver"); // Oracle 드라이버 로딩
             conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return conn;
    }
    
    public static void close(Connection conn) {
        if (conn != null) {
            try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
        }
    }
}