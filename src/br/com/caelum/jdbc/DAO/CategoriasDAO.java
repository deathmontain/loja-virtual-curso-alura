package br.com.caelum.jdbc.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.caelum.jdbc.modelo.Categoria;
import br.com.caelum.jdbc.modelo.Produto;

public class CategoriasDAO {
	private final Connection connection;

	public CategoriasDAO(Connection connection) {
		this.connection = connection;
	}

	public List<Categoria> lista() throws SQLException {
		System.out.println("Executando uma Query!");
		List<Categoria> categorias = new ArrayList<>();

		String sql = "select * from categoria";
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.execute();
			try (ResultSet result = statement.getResultSet()) {
				while (result.next()) {
					int id = result.getInt("id");
					String nome = result.getString("nome");
					Categoria categoria = new Categoria(id, nome);
					categorias.add(categoria);
				}
			}
		}
		return categorias;
	}

	public List<Categoria> listaComProdutos() throws SQLException {
		List<Categoria> categorias = new ArrayList<>();
		Categoria ultima = null;
		String sql = "select c.id as c_id, c.nome as c_nome, p.id as p_id, p.nome as p_nome, p.descricao as p_descricao from categoria as c join produto as p on p.categoria_id = c.id";
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.execute();
			try (ResultSet result = statement.getResultSet()) {
				while (result.next()) {
					int id = result.getInt("c_id");
					String nome = result.getString("c_nome");

					if (ultima == null || !ultima.getNome().equals(nome)) {
						Categoria categoria = new Categoria(id, nome);
						categorias.add(categoria);
						ultima = categoria;
					}
					int idDoProduto = result.getInt("p_id");
					String nomeDoProduto = result.getString("p_nome");
					String descricaoDoProduto = result.getString("p_descricao");
					Produto p = new Produto(nomeDoProduto, descricaoDoProduto);
					p.setId(idDoProduto);
					ultima.adiciona(p);
				}
			}
		}
		return categorias;
	}
}
