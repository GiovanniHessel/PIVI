package sp.com.senac.pi.model.dao.base;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sp.com.senac.pi.conexao.contratos.DbConnection;
import sp.com.senac.pi.conexao.singleton.ConnectionSingleton;
import sp.com.senac.pi.model.dao.contato.ContatoDao;
import sp.com.senac.pi.model.pojo.base.Pessoa;
import sp.com.senac.pi.model.pojo.localizacao.Cidade;
import sp.com.senac.pi.model.pojo.localizacao.Estado;
import sp.com.senac.pi.model.pojo.localizacao.Pais;
import sp.com.senac.pi.util.control.Util;

public class PessoaDao {
	private DbConnection connection;

	public PessoaDao() {
		this.connection = ConnectionSingleton.getConnection();
	}

	public Pessoa insert(Pessoa pessoa) {
		
		pessoa.setIdPessoa(0);
		return this.sendDB(pessoa);
	}
	
	public Pessoa update(Pessoa pessoa) {

		return  this.sendDB(pessoa);
	}
	
	public Pessoa getPessoa(int id) {
		this.connection.open();
		try {
			PreparedStatement stmt = this.connection.getConnection().prepareStatement("select * from vwPessoa where id = ?");
			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();
			Pessoa pessoa = new Pessoa();

			if (rs.next()) {
				pessoa = this.carregaPessoa(rs);
			}
			rs.close();
			stmt.close();
			connection.close();
			return pessoa;

		} catch (SQLException e) {
			connection.close();
			throw new RuntimeException(e);
		}
	}

	public Pessoa getPessoa(String cpf) {
		this.connection.open();
		try {
			PreparedStatement stmt = this.connection.getConnection().prepareStatement("select * from vwPessoa where CPF = ?");
			stmt.setString(1, cpf);

			ResultSet rs = stmt.executeQuery();
			Pessoa pessoa = new Pessoa();
			
			if (rs.next()) {
				pessoa = this.carregaPessoa(rs);
			}
			rs.close();
			stmt.close();
			connection.close();
			return pessoa;

		} catch (SQLException e) {
			connection.close();
			throw new RuntimeException(e);
		}
	}

	public List<Pessoa> getPessoas() {
		this.connection.open();
		try {
			PreparedStatement stmt = this.connection.getConnection().prepareStatement("select * from vwPessoa");

			ResultSet rs = stmt.executeQuery();
			List<Pessoa> pessoas = new ArrayList<Pessoa>();
			
			while (rs.next()) {
				Pessoa pessoa = this.carregaPessoa(rs);
				pessoas.add(pessoa);
			}
			rs.close();
			stmt.close();
			connection.close();
			return pessoas;
		} catch (SQLException e) {
			connection.close();
			throw new RuntimeException(e);
		}
	}

	private Pessoa carregaPessoa(ResultSet rs)  {
		Pessoa pessoa = new Pessoa();
		Cidade cidade = new Cidade();
		Estado estado = new Estado();
    	Pais pais = new Pais();
    	
		try {
			pessoa.setIdPessoa(rs.getInt("id"));
			pessoa.setNome(rs.getString("nome"));
			pessoa.setSobrenome(rs.getString("sobreNome"));
			pessoa.setCPF(rs.getString("CPF"));
			
			pessoa.setDataDeNascimento(new Util().getStringDate(rs.getTimestamp("dataDeNascimento")));
			pessoa.setSexo(rs.getString("sexo"));
			pessoa.setCep(rs.getString("cep"));
			pessoa.setLogradouro(rs.getString("logradouro"));
			pessoa.setNumero(rs.getString("numero"));
			pessoa.setComplemento(rs.getString("complemento"));
			pessoa.setBairro(rs.getString("bairro"));
			
			pessoa.setContatos(new ContatoDao().getContatos(pessoa));
			
			cidade.setId(rs.getInt("idCidade"));
			cidade.setCidade(rs.getString("cidade"));
            cidade.setSigla(rs.getString("siglaCidade"));
            
			estado.setId(rs.getInt("idEstado"));
	        estado.setEstado(rs.getString("estado"));
	        estado.setSigla(rs.getString("siglaEstado"));
	        
	        pais.setId(rs.getInt("idPais"));
	        pais.setPais(rs.getString("pais"));
	        pais.setSigla(rs.getString("siglaPais"));
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		pessoa.setCidade(cidade);
		
		return pessoa;
	}
	
	private Pessoa sendDB(Pessoa pessoa)  {
		
		String sql = "exec SPIU_PESSOA ?,?,?,?,?,?,?,?,?,?,?,?";
		
		this.connection.open();
		try {
	
			PreparedStatement stmt = connection.getConnection().prepareStatement(sql);
			
			stmt.setInt(1, pessoa.getIdPessoa());
			stmt.setString(2, pessoa.getNome());
			stmt.setString(3, pessoa.getSobrenome());
			stmt.setString(4, pessoa.getCPF());
			stmt.setString(5, pessoa.getDataDeNascimento());
			stmt.setString(6, pessoa.getSexo());
			stmt.setString(7, pessoa.getCep());
			stmt.setString(8, pessoa.getLogradouro());
			stmt.setString(9, pessoa.getNumero());
			stmt.setString(10, pessoa.getComplemento());
			stmt.setString(11, pessoa.getBairro());
			stmt.setInt(12, pessoa.getCidade().getId());

			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				pessoa.setIdPessoa(rs.getInt("id"));
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			connection.close();
			return pessoa;
		}
		connection.close();
		pessoa.setContatos(new ContatoDao().alterarContatos(pessoa));
		
		return pessoa;
		
	}
}
