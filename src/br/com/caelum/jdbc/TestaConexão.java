package br.com.caelum.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestaConexão {
	public static void main(String[] args) throws SQLException {
		Connection connection = new DataBase().getConnection();
		System.out.println("Abrindo conexão!");
	    connection.close();
	}

}
