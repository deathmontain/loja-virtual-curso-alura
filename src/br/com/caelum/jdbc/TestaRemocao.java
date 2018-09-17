package br.com.caelum.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaRemocao {
	public static void main(String[] args) throws SQLException{
		Connection connection = new DataBase().getConnection();
		Statement statement = connection.createStatement();
		boolean resultado = statement.execute("delete from contatos where id > 4");
		int count = statement.getUpdateCount();
		System.out.println(count + " número de linha atualizadas.");
		connection.close();
	}
}
