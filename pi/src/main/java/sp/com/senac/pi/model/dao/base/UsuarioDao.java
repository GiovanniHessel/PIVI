package sp.com.senac.pi.model.dao.base;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sp.com.senac.pi.conexao.contratos.DbConnection;
import sp.com.senac.pi.conexao.singleton.ConnectionSingleton;
import sp.com.senac.pi.control.seguranca.Seguranca;
import sp.com.senac.pi.model.dao.contato.ContatoDao;
import sp.com.senac.pi.model.pojo.base.Pessoa;
import sp.com.senac.pi.model.pojo.base.Usuario;
import sp.com.senac.pi.model.pojo.localizacao.Cidade;
import sp.com.senac.pi.model.pojo.localizacao.Estado;
import sp.com.senac.pi.model.pojo.localizacao.Pais;
import sp.com.senac.pi.util.control.Util;

public class UsuarioDao {
	private DbConnection connection;

	public UsuarioDao() {
		this.connection = ConnectionSingleton.getConnection();
	}

	public Usuario insert(Usuario usuario) {
		usuario.setId(0);
		
		Pessoa pessoa = new  PessoaDao().insert(usuario);
		usuario.setIdPessoa(pessoa.getIdPessoa());
		
		if (usuario.getIdPessoa() == 0) {
			return usuario;
		}
		
		return this.sendDB(usuario);
	}
	
	public Usuario update(Usuario usuario) {
		
		Pessoa pessoa = new  PessoaDao().update(usuario);
		usuario.setIdPessoa(pessoa.getIdPessoa());
		
		if (usuario.getIdPessoa() == 0) {
			usuario.setId(0);
			return usuario;
		}
		
		return this.sendDB(usuario);
	}
	
	public Usuario delete(Usuario usuario) {
		this.connection.open();
		try {
			PreparedStatement stmt = this.connection.getConnection()
					.prepareStatement("SPD_USUARIO ?");
			stmt.setInt(1, usuario.getId());

			ResultSet rs = stmt.executeQuery();
	
			if (rs.next()) {
				usuario.setId(rs.getInt("id"));
			}

			rs.close();
			stmt.close();
			connection.close();
			return usuario;

		} catch (SQLException e) {
			connection.close();
			throw new RuntimeException(e);
		}
	}
	
	public Usuario getUsuario(int id) {
		this.connection.open();
		try {
			PreparedStatement stmt = this.connection.getConnection()
					.prepareStatement("select * from vwUsuario where id = ?");
			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();
			Usuario usuario = new Usuario();
	
			if (rs.next()) {
				usuario = this.carregaUsuario(rs);
			}

			rs.close();
			stmt.close();
			connection.close();
			return usuario;

		} catch (SQLException e) {
			connection.close();
			throw new RuntimeException(e);
		}
	}

	public Usuario getUsuario(String login, String chave) {
		this.connection.open();
		try {
			PreparedStatement stmt = this.connection.getConnection().prepareStatement("select * from vwUsuario where login = ? and chave = ?");
			stmt.setString(1, login);
			stmt.setString(2, new Seguranca().hash(chave));

			ResultSet rs = stmt.executeQuery();
			Usuario usuario = new Usuario();
		
			if (rs.next()) {
				usuario = this.carregaUsuario(rs);
			}

			rs.close();
			stmt.close();
			connection.close();
			return usuario;

		} catch (SQLException e) {
			connection.close();
			throw new RuntimeException(e);
		}
	}
	
	public List<Usuario> getUsuarios() {
		this.connection.open();
		try {
			PreparedStatement stmt = this.connection.getConnection().prepareStatement("select * from vwUsuario");
			List<Usuario> usuarios = new ArrayList<Usuario>();
			ResultSet rs = stmt.executeQuery();
			
		
			while (rs.next()) {
				
				Usuario usuario = this.carregaUsuario(rs);
				usuarios.add(usuario);
			}

			rs.close();
			stmt.close();
			connection.close();
			return usuarios;

		} catch (SQLException e) {
			connection.close();
			throw new RuntimeException(e);
		}
	}
	
	private Usuario carregaUsuario(ResultSet rs) {
		Usuario usuario = new Usuario();
		Cidade cidade = new Cidade();
		Estado estado = new Estado();
    	Pais pais = new Pais();
    	
		try {
			usuario.setId(rs.getInt("id"));
			usuario.setLogin(rs.getString("login"));
			usuario.setChave(rs.getString("chave"));
			usuario.setInativo(rs.getInt("inativo"));
			
			usuario.setIdPessoa(rs.getInt("idPessoa"));
			usuario.setNome(rs.getString("nome"));
			usuario.setSobrenome(rs.getString("sobreNome"));
			usuario.setCPF(rs.getString("CPF"));
			usuario.setDataDeNascimento(new Util().getStringDate(rs.getTimestamp("dataDeNascimento")));
			
			usuario.setSexo(rs.getString("sexo"));
			usuario.setLogradouro(rs.getString("logradouro"));
			usuario.setNumero(rs.getString("numero"));
			usuario.setComplemento(rs.getString("complemento"));
			usuario.setBairro(rs.getString("bairro"));
			usuario.setContatos(new ContatoDao().getContatos(usuario));
			
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
		
		estado.setPais(pais);
        cidade.setEstado(estado);
        usuario.setCidade(cidade);

        return usuario;
	}
	
	private Usuario sendDB(Usuario usuario)  {
		
		String sql = "exec SPIU_USUARIO ?,?,?,?,?";
		
		this.connection.open();
		try {
			
			PreparedStatement stmt = connection.getConnection().prepareStatement(sql);
			
			stmt.setInt(1, usuario.getId());
			stmt.setInt(2, usuario.getIdPessoa());
			stmt.setString(3, usuario.getLogin());
			stmt.setString(4, new Seguranca().hash(usuario.getChave()));
			stmt.setInt(5, usuario.getInativo());
			
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				usuario.setId(rs.getInt("id"));
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			connection.close();
			return usuario;
		}
		connection.close();
		return usuario;
	
	}
	
}
