package sp.com.senac.pi.model.dao.localizacao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sp.com.senac.pi.conexao.contratos.DbConnection;
import sp.com.senac.pi.conexao.singleton.ConnectionSingleton;
import sp.com.senac.pi.model.pojo.localizacao.Cidade;
import sp.com.senac.pi.model.pojo.localizacao.Estado;
import sp.com.senac.pi.model.pojo.localizacao.Pais;

public class CidadeDao {
	private DbConnection connection;

    public CidadeDao() {
        this.connection = ConnectionSingleton.getConnection();
    }
    
    public boolean insert(Cidade cidade) {
        String sql = "insert into Cidades"
                + "(cidade, sigla, idEstado)"
                + " values (?,?,?)";
        connection.open();
        try {

            PreparedStatement stmt = connection.getConnection().prepareStatement(sql);

            stmt.setString(1, cidade.getCidade());
            stmt.setString(2, cidade.getSigla());
            stmt.setInt(3, cidade.getEstado().getId());

            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            connection.close();
            return false;
        }
        connection.close();
        return true;
    }

 
    public Cidade getCidade(int id) {
        this.connection.open();
        try {
            PreparedStatement stmt = this.connection.getConnection().prepareStatement("Select * from vwCidade Where id = ?");
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            Cidade cidade = new Cidade();
            
            if (rs.next()) {
            	cidade = this.carregaCidade(rs);
            }

            rs.close();
            stmt.close();
            connection.close();
            return cidade;

        } catch (SQLException e) {
            connection.close();
            throw new RuntimeException(e);
        }
    }
    
    public List<Cidade> getCidades() {
        this.connection.open();
        try {
            PreparedStatement stmt = this.connection.getConnection().prepareStatement("Select * from vwCidade");

            ResultSet rs = stmt.executeQuery();
            List<Cidade> cidades = new ArrayList<Cidade>();
            while (rs.next()) {
            	Cidade cidade = this.carregaCidade(rs);
                cidades.add(cidade);
            }
            rs.close();
            stmt.close();
            connection.close();
            return cidades;
        } catch (SQLException e) {
            connection.close();
            throw new RuntimeException(e);
        }
    }

    public List<Cidade> getCidades(Estado estado) {
        this.connection.open();
        try {
            PreparedStatement stmt = this.connection.getConnection().prepareStatement("Select * from vwCidade where idEstado = ?");
            stmt.setInt(1, estado.getId());

            ResultSet rs = stmt.executeQuery();
            List<Cidade> cidades = new ArrayList<Cidade>();
            while (rs.next()) {
                Cidade cidade = this.carregaCidade(rs);
                cidades.add(cidade);
            }
            rs.close();
            stmt.close();
            connection.close();
            return cidades;
        } catch (SQLException e) {
            connection.close();
            throw new RuntimeException(e);
        }
    }
    
    
    private Cidade carregaCidade(ResultSet rs) {
    	Cidade cidade = new Cidade();
    	Estado estado = new Estado();
    	Pais pais = new Pais();
    	
        try {
        	
        	cidade.setId(rs.getInt("id"));
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
        
        return cidade;
        		
    }
    
}
