package br.com.caelum.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.caelum.jdbc.modelo.Categoria;
import br.com.caelum.jdbc.modelo.Produto;

public class ProdutosDAO {

	private Connection connection;

	public ProdutosDAO(Connection connection) {
		this.connection = connection;
	}
	
	public void salva(Produto produto) throws SQLException {
		String sql = "insert into produto (nome, descricao) values (?,?)";
		try (PreparedStatement statement = connection.prepareStatement(sql,
				Statement.RETURN_GENERATED_KEYS)){
			statement.setString(1, produto.getNome());
			statement.setString(2, produto.getDescricao());
			statement.execute();
			try (ResultSet result = statement.getGeneratedKeys()){
				while (result.next()) {
					int id = result.getInt("GENERATED_KEY");
					produto.setId(id);;
				}
			}
		}
	}

	public List<Produto> lista() throws SQLException {
		List<Produto> produtos = new ArrayList<>();
		String sql = "select * from produto";
		try (PreparedStatement statement = connection.prepareStatement(sql)){
			statement.execute();	
			transformaResultadoEmProdutos(produtos, statement);
		}
		return produtos;
	}

	private void transformaResultadoEmProdutos(List<Produto> produtos, PreparedStatement statement)
			throws SQLException {
		try (ResultSet result = statement.getResultSet()){
			while(result.next()){
				int id = result.getInt("id");
				String nome = result.getString("nome");
				String descricao = result.getString("descricao");
				Produto produto = new Produto(nome, descricao);
				produto.setId(id);
				produtos.add(produto);
			}
		}
	}

	public List<Produto> busca(Categoria categoria) throws SQLException {
		System.out.println("Executando uma Query!");
		List<Produto> produtos = new ArrayList<>();
		String sql = "select * from produto where categoria_id = ?";
		try (PreparedStatement statement = connection.prepareStatement(sql)){
			statement.setInt(1, categoria.getId());
			statement.execute();	
			transformaResultadoEmProdutos(produtos, statement);
		}
		return produtos;
	}
}
