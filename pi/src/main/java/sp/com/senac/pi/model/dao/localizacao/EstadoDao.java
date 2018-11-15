package sp.com.senac.pi.model.dao.localizacao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sp.com.senac.pi.conexao.contratos.DbConnection;
import sp.com.senac.pi.conexao.singleton.ConnectionSingleton;
import sp.com.senac.pi.model.pojo.localizacao.Estado;
import sp.com.senac.pi.model.pojo.localizacao.Pais;

public class EstadoDao {
	private DbConnection connection;

    public EstadoDao() {
        this.connection = ConnectionSingleton.getConnection();
    }
    
    public boolean insert(Estado estado) {
        String sql = "exec spiu_estado (?,?,?,?)";
        connection.open();
        try {

            PreparedStatement stmt = connection.getConnection().prepareStatement(sql);

            stmt.setInt(1, estado.getId());
            stmt.setString(2, estado.getEstado());
            stmt.setString(3, estado.getSigla());
            stmt.setInt(4, estado.getPais().getId());

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

 
    public Estado getEstado(int id) {
        this.connection.open();
        try {
            PreparedStatement stmt = this.connection.getConnection().prepareStatement("Select * from vwEstado Where id = ?");
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            Estado estado = new Estado();
            
            if (rs.next()) {
            	estado = this.carregaEstado(rs);
            }

            rs.close();
            stmt.close();
            connection.close();
            return estado;

        } catch (SQLException e) {
            connection.close();
            throw new RuntimeException(e);
        }
    }
    
    public List<Estado> getEstados() {
        this.connection.open();
        try {
            PreparedStatement stmt = this.connection.getConnection().prepareStatement("Select * from vwEstado");

            ResultSet rs = stmt.executeQuery();
            List<Estado> estados = new ArrayList<Estado>();
            while (rs.next()) {
            	Estado estado = this.carregaEstado(rs);
                estados.add(estado);
            }
            rs.close();
            stmt.close();
            connection.close();
            return estados;
        } catch (SQLException e) {
            connection.close();
            throw new RuntimeException(e);
        }
    }

    public List<Estado> getEstados(Pais pais) {
        this.connection.open();
        try {
            PreparedStatement stmt = this.connection.getConnection().prepareStatement("Select * from vwEstado where idPais = ?");
            stmt.setInt(1, pais.getId());

            ResultSet rs = stmt.executeQuery();
            List<Estado> estados = new ArrayList<Estado>();
            while (rs.next()) {
                Estado estado = this.carregaEstado(rs);
                estados.add(estado);
            }
            rs.close();
            stmt.close();
            connection.close();
            return estados;
        } catch (SQLException e) {
            connection.close();
            throw new RuntimeException(e);
        }
    }
    
    
    private Estado carregaEstado(ResultSet rs) {
    
    	Estado estado = new Estado();
    	Pais pais = new Pais();
    	
        try {

			estado.setId(rs.getInt("id"));
	        estado.setEstado(rs.getString("estado"));
	        estado.setSigla(rs.getString("siglaEstado"));
	        
	        pais.setId(rs.getInt("idPais"));
	        pais.setPais(rs.getString("pais"));
	        pais.setSigla(rs.getString("siglaPais"));
        
        } catch (SQLException e) {
        	throw new RuntimeException(e);
		}
        
        estado.setPais(pais);
        
        return estado;
        		
    }
}
