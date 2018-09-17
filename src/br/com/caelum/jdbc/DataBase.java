package br.com.caelum.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class DataBase {
	private MysqlDataSource dataSource;

	public DataBase(){
		MysqlDataSource pool = new MysqlDataSource();
		pool.setURL("jdbc:mysql://localhost/estudos_schema");
		pool.setUser("root");
		pool.setPassword("CronoCross@123");
		this.dataSource = pool;
	}
	
	public Connection getConnection() throws SQLException {
		/*Connection connection = DriverManager.getConnection(
				"jdbc:mysql://localhost/estudos_schema", "root", "CronoCross@123");*/
		Connection connection = dataSource.getConnection();
		return connection;
	}

}
