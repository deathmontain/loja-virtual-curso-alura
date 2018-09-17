package br.com.caelum.jdbc.DAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.caelum.jdbc.DataBase;
import br.com.caelum.jdbc.ProdutosDAO;
import br.com.caelum.jdbc.modelo.Produto;

public class TestaDaoDeProduto {
	public static void main(String[] args) throws SQLException {
		Produto mesa = new Produto("Mesa Azul", "Mesa com tampa de vidro");
		try (Connection connection = new DataBase().getConnection()) {
			ProdutosDAO Dao = new ProdutosDAO(connection);
			Dao.salva(mesa);

			List<Produto> produtos = Dao.lista();
			for (Produto produto : produtos) {
				System.out.println("Existe o produto: " + produto);
			}
		}
	}
}
