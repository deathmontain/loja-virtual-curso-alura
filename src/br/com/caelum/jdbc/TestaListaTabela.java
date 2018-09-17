package br.com.caelum.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaListaTabela {
	public static void main(String[] args)  throws SQLException{
		DataBase dataBase = new DataBase();
		
		for (int i = 0; i < 100; i++) {
			Connection connection = dataBase.getConnection();
			Statement statement = connection.createStatement();
			boolean resultado = statement.execute("select * from contatos");
			ResultSet resultSet = statement.getResultSet();
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String nome = resultSet.getString("nome");
				String endereco = resultSet.getString("endereco");
				String dataNascimento = resultSet.getString("dataNascimento");
				System.out.printf("ID: " + id);
				System.out.println("Nome: " + nome);
				System.out.println("Endereço: " + endereco);
				System.out.println("Data de Nascimento: " + dataNascimento);
			}
			resultSet.close();
			statement.close();
			connection.close();
		}	
	}
}
