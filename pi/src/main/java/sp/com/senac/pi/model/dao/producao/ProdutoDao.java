package sp.com.senac.pi.model.dao.producao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sp.com.senac.pi.conexao.contratos.DbConnection;
import sp.com.senac.pi.conexao.singleton.ConnectionSingleton;
import sp.com.senac.pi.model.pojo.producao.Custo;
import sp.com.senac.pi.model.pojo.producao.Estoque;
import sp.com.senac.pi.model.pojo.producao.Preco;
import sp.com.senac.pi.model.pojo.producao.Produto;
import sp.com.senac.pi.util.control.Util;


public class ProdutoDao {
	private DbConnection connection;
	
	public ProdutoDao() {
		this.connection = ConnectionSingleton.getConnection();
	}

	public Produto insert(Produto produto) {
		produto.setId(0);
		
		this.connection.open();
		try {
	
			PreparedStatement stmt = this.carregaParametros(produto);

			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				produto.setId(rs.getInt("id"));
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			connection.close();
			return produto;
		}
		connection.close();
		
		produto.setPrecos(new PrecoDao().insertPrecos(produto));
		produto.setCustos(new CustoDao().insertCustos(produto));
		produto.setEstoques(new EstoqueDao().estoqueInicial(produto));
		
		return produto;
	}
	
	public Produto update(Produto produto) {

		this.connection.open();
		try {
	
			PreparedStatement stmt = this.carregaParametros(produto);

			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				produto.setId(rs.getInt("id"));
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			connection.close();
			return produto;
		}
		connection.close();

		produto.setPrecos(new PrecoDao().insertPrecos(produto));
		produto.setCustos(new CustoDao().insertCustos(produto));
		
		return produto;
	}
	
	public Produto delete(Produto produto) {

		this.connection.open();
		try {
	
			PreparedStatement stmt = this.connection.getConnection().prepareStatement("exec SPD_PRODUTO ?");
			stmt.setInt(1, produto.getId());

			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				produto.setId(rs.getInt("id"));
			}
			
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			connection.close();
			return produto;
		}
		connection.close();
		
		return produto;
	}
	
	public Produto getProduto(int id) {
		this.connection.open();
		try {
			PreparedStatement stmt = this.connection.getConnection().prepareStatement("select * from Producao.Produto where id = ?");
			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();
			Produto produto = new Produto();

			if (rs.next()) {
				produto = this.carregaProduto(rs);
			}
			rs.close();
			stmt.close();
			connection.close();
			return produto;

		} catch (SQLException e) {
			connection.close();
			throw new RuntimeException(e);
		}
	}
	
	public Produto getProdutoAtual(int id) {
		this.connection.open();
		try {
			PreparedStatement stmt = this.connection.getConnection().prepareStatement("select * from Producao.vwProduto where id = ?");
			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();
			Produto produto = new Produto();

			if (rs.next()) {
				produto = this.carregaProdutoAtual(rs);
			}
			rs.close();
			stmt.close();
			connection.close();
			return produto;

		} catch (SQLException e) {
			connection.close();
			throw new RuntimeException(e);
		}
	}

	public List<Produto> getProdutos() {
		this.connection.open();
		try {
			PreparedStatement stmt = this.connection.getConnection().prepareStatement("select top 100 * from Producao.Produto");

			List<Produto> produtos = this.retornaProdutos(stmt);
			
			stmt.close();
			connection.close();
			return produtos;
		} catch (SQLException e) {
			connection.close();
			throw new RuntimeException(e);
		}
	}
	
	public List<Produto> getProdutosAtuais() {
		this.connection.open();
		try {
			PreparedStatement stmt = this.connection.getConnection().prepareStatement("select top 100 * from Producao.vwProduto");

			List<Produto> produtos = this.retornaProdutosAtuais(stmt);
			
			stmt.close();
			connection.close();
			return produtos;
		} catch (SQLException e) {
			connection.close();
			throw new RuntimeException(e);
		}
	}
	
	public List<Produto> getProdutosNome(String nome) {
		this.connection.open();
		try {
			PreparedStatement stmt = this.connection.getConnection().prepareStatement("select * from Producao.Produto where nome like '%"+nome+"%'");
			
			List<Produto> produtos = this.retornaProdutos(stmt);
			
			stmt.close();
			connection.close();
			return produtos;
		} catch (SQLException e) {
			connection.close();
			throw new RuntimeException(e);
		}
	}
	
	public List<Produto> getProdutosAtuaisNome(String nome) {
		this.connection.open();
		try {
			PreparedStatement stmt = this.connection.getConnection().prepareStatement("select * from Producao.vwProduto where nome like '%"+nome+"%'");
			
			List<Produto> produtos = this.retornaProdutosAtuais(stmt);
			
			stmt.close();
			connection.close();
			return produtos;
		} catch (SQLException e) {
			connection.close();
			throw new RuntimeException(e);
		}
	}
	
	private Produto carregaProduto(ResultSet rs)  {
		Produto produto = new Produto();
    	
		try {
			produto.setId(rs.getInt("id"));
			produto.setNome(rs.getString("nome"));
			produto.setDescricao(rs.getString("descricao"));
			produto.setEan(rs.getString("ean"));
			produto.setMarca(rs.getString("marca"));
			produto.setPeso(rs.getFloat("peso"));
			produto.setLargura(rs.getFloat("largura"));
			produto.setAltura(rs.getFloat("altura"));
			produto.setComprimento(rs.getFloat("comprimento"));
			produto.setInativo(rs.getInt("inativo"));
			
			produto.setPrecos( new PrecoDao().getPrecos(produto));
			produto.setCustos( new CustoDao().getCustos(produto));
			produto.setEstoques( new EstoqueDao().getEstoques(produto));
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return produto;
	}
	
	private Produto carregaProdutoAtual(ResultSet rs)  {
		Produto produto = new Produto();
		Preco preco = new Preco();
		Custo custo = new Custo();
		Estoque estoque = new Estoque();
    	
		try {
			produto.setId(rs.getInt("id"));
			produto.setNome(rs.getString("nome"));
			produto.setDescricao(rs.getString("descricao"));
			produto.setEan(rs.getString("ean"));
			produto.setMarca(rs.getString("marca"));
			produto.setPeso(rs.getFloat("peso"));
			produto.setLargura(rs.getFloat("largura"));
			produto.setAltura(rs.getFloat("altura"));
			produto.setComprimento(rs.getFloat("comprimento"));
			produto.setInativo(rs.getInt("inativo"));
			
			preco.setId(rs.getInt("idPreco"));
			preco.setPreco(rs.getFloat("preco"));
			
			preco.setDataPreco(new Util().getStringDate(rs.getTimestamp("dataPreco")));
			preco.setIdProduto(rs.getInt("id"));
			
			custo.setId(rs.getInt("idCusto"));
			custo.setCusto(rs.getFloat("custo"));
			custo.setDataCusto(new Util().getStringDate(rs.getTimestamp("dataCusto")));
			custo.setIdProduto(rs.getInt("id"));
	        
			estoque.setId(rs.getInt("idEstoque"));
			estoque.setQuantidade(rs.getFloat("quantidade"));
			estoque.setMotivo(rs.getString("motivo"));
			estoque.setDataEstoque(new Util().getStringDate(rs.getTimestamp("dataEstoque")));
			estoque.setIdProduto(rs.getInt("id"));
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		produto.getPrecos().add(preco);
		produto.getCustos().add(custo);
		produto.getEstoques().add(estoque);
		
		return produto;
	}
	
	private PreparedStatement carregaParametros(Produto produto) throws SQLException {
		String sql = "exec Producao.SPIU_PRODUTO ?,?,?,?,?,?,?,?,?,?";
		
		PreparedStatement stmt = connection.getConnection().prepareStatement(sql);
		
		stmt.setInt(1, produto.getId());
		stmt.setString(2, produto.getNome());
		stmt.setString(3, produto.getDescricao());
		stmt.setString(4, produto.getEan());
		stmt.setString(5, produto.getMarca());
		stmt.setFloat(6, produto.getPeso());
		stmt.setFloat(7, produto.getLargura());
		stmt.setFloat(8, produto.getAltura());
		stmt.setFloat(9, produto.getComprimento());
		stmt.setInt(10, produto.getInativo());
		
		return stmt;
	}
	
	private List<Produto> retornaProdutos(PreparedStatement stmt){
		List<Produto> produtos = new ArrayList<Produto>();
		
		try {
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				Produto produto = this.carregaProduto(rs);
				produtos.add(produto);
			}
			rs.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return produtos;
	}
	
	private List<Produto> retornaProdutosAtuais(PreparedStatement stmt){
		List<Produto> produtos = new ArrayList<Produto>();
		
		try {
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				Produto produto = this.carregaProdutoAtual(rs);
				produtos.add(produto);
			}
			rs.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return produtos;
	}
	
}
