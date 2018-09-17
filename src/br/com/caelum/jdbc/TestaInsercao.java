package br.com.caelum.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class TestaInsercao {
	public static void main(String[] args) throws SQLException {
		try (Connection connection = new DataBase().getConnection();){ 
			connection.setAutoCommit(false);
			try {
				String nome = "João' i5";
				String endereco = "'rua Alternada'";
				Date dataNascimento = new Date(1950, 05, 12); /*1950-05-12;*/ 
		
				String sql = "insert into contatos (nome, endereco,"
						+ " dataNascimento) values(?, ?, ?)";
		
				PreparedStatement statement = connection.prepareStatement(sql,
						Statement.RETURN_GENERATED_KEYS);
		
				adiciona(nome, endereco, dataNascimento, statement);
				adiciona("Justus", "rua Prontera", new Date(1985, 05, 12), statement);
				connection.commit();
				statement.close();
			} catch (Exception ex){
				ex.printStackTrace();
				connection.rollback();
				System.out.println("Rollback Efetuado");	
			}
		}
	}

	private static void adiciona(String nome, String endereco, Date dataNascimento, PreparedStatement statement)
			throws SQLException {
		if (nome.equals("Justus")) {
			throw new IllegalArgumentException("Não pode haver Justus");
		}
		
		statement.setString(1, nome);
		statement.setString(2, endereco);
		statement.setDate(3, dataNascimento);
		
		boolean resultado = statement.execute();
		System.out.println(resultado);

		ResultSet resultSet = statement.getGeneratedKeys();
		while (resultSet.next()) {
			String id = resultSet.getString("GENERATED_KEY");
			System.out.println(id + " gerado");
		}
		
		resultSet.close();
	}
}
