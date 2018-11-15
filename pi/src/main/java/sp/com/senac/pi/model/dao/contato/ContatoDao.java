package sp.com.senac.pi.model.dao.contato;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import sp.com.senac.pi.conexao.contratos.DbConnection;
import sp.com.senac.pi.conexao.singleton.ConnectionSingleton;
import sp.com.senac.pi.model.pojo.base.Empresa;
import sp.com.senac.pi.model.pojo.base.Pessoa;
import sp.com.senac.pi.model.pojo.contato.Contato;

public class ContatoDao {
	private DbConnection connection;

    public ContatoDao() {
        this.connection = ConnectionSingleton.getNewConnection();
    }
    
    public Contato insert(Contato contato) {
    	
    	contato.setId(0);
    	
        connection.open();
        try {

        	PreparedStatement stmt = this.carregaParametros(contato);
            
            ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				contato.setId(rs.getInt("id"));
			}
			
			rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Insert");
            connection.close();
            return contato;
        }
        connection.close();
        return contato;
    }
    
    public Contato update(Contato contato) {
    	
        connection.open();
        try {

        	PreparedStatement stmt = this.carregaParametros(contato);
            
            ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				contato.setId(rs.getInt("id"));
			}
			
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("update");
            connection.close();
            return contato;
        }
        connection.close();
        return contato;
    }
   
    public List<Contato> insert(List<Contato> contatos) {
       
        connection.open();
        try {
        	
        	for (Contato contato : contatos) {
        		contato.setId(0);
        		
	            PreparedStatement stmt = this.carregaParametros(contato);
	            
	            stmt.execute();
	            stmt.close();
        	}
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Insert List");
            connection.close();
            return contatos;
        }
        connection.close();
        return contatos;
    }
    
    public List<Contato> update(List<Contato> contatos) {
        
        connection.open();
        try {
        	
        	for (Contato contato : contatos) {
        		contato.setId(0);
        		
	            PreparedStatement stmt = this.carregaParametros(contato);
	            
	            stmt.execute();
	            stmt.close();
        	}
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Update List");
            connection.close();
            return contatos;
        }
        connection.close();
        return contatos;
    }
    
    public Contato delete(Contato contato) {
    	 this.connection.open();
         try {
             PreparedStatement stmt = this.connection.getConnection().prepareStatement("exec SPD_CONTATO ?");
             stmt.setInt(1, contato.getId());

             ResultSet rs = stmt.executeQuery();
             
             if (rs.next()) {
             	contato.setId(rs.getInt("id"));
             }

             rs.close();
             stmt.close();
             connection.close();
             return contato;

         } catch (SQLException e) {
             connection.close();
             throw new RuntimeException(e);
         }
    }
    public Contato getContato(int id) {
        this.connection.open();
        try {
            PreparedStatement stmt = this.connection.getConnection().prepareStatement("Select * from Contato Where id = ?");
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            Contato contato = new Contato();
            
            if (rs.next()) {
            	contato = this.carregaContato(rs);
            }

            rs.close();
            stmt.close();
            connection.close();
            return contato;

        } catch (SQLException e) {
            connection.close();
            throw new RuntimeException(e);
        }
    }
    
    public List<Contato> getContatos() {
        this.connection.open();
        try {
            PreparedStatement stmt = this.connection.getConnection().prepareStatement("Select * from contato");

            ResultSet rs = stmt.executeQuery();
            List<Contato> contatos = new ArrayList<Contato>();
            while (rs.next()) {
            	Contato contato = this.carregaContato(rs);
                contatos.add(contato);
            }
            rs.close();
            stmt.close();
            connection.close();
            return contatos;
        } catch (SQLException e) {
            connection.close();
            throw new RuntimeException(e);
        }
    }
    
    public List<Contato> getContatos(Pessoa pessoa) {
    	List<Contato> contatos = new ArrayList<Contato>();
    	
        this.connection.open();
        try {
            PreparedStatement stmt = this.connection.getConnection().prepareStatement("Select * from contato where idPessoa = ?");
            stmt.setInt(1, pessoa.getIdPessoa());
            
            ResultSet rs = stmt.executeQuery();
           
            while (rs.next()) {
            	Contato contato = this.carregaContato(rs);
                contatos.add(contato);
            }
            
            rs.close();
            stmt.close();
            this.connection.close();
            
            return contatos;
        } catch (SQLException e) {
            this.connection.close();
            throw new RuntimeException(e);
        }
    }
    
    public List<Contato> getContatos(Empresa empresa) {
    	List<Contato> contatos = new ArrayList<Contato>();
        this.connection.open();
        try {
            PreparedStatement stmt = this.connection.getConnection().prepareStatement("Select * from contato where idEmpresa = ?");
            stmt.setInt(1, empresa.getIdEmpresa());
            
            ResultSet rs = stmt.executeQuery();
           
            while (rs.next()) {
            	Contato contato = this.carregaContato(rs);
                contatos.add(contato);
            }
            
            rs.close();
            stmt.close();
            connection.close();
            return contatos;
        } catch (SQLException e) {
            connection.close();
            throw new RuntimeException(e);
        }
    }
    
    public List<Contato> alterarContatos(Pessoa pessoa){
    	for (int i = 0; i < pessoa.getContatos().size(); i++) {
    		pessoa.getContatos().get(i).setIdPessoa(pessoa.getIdPessoa());
    		pessoa.getContatos().get(i).setIdEmpresa(0);
    		
        }
    	
    	this.alterarContatos(this.getContatos(pessoa), pessoa.getContatos());
    	return this.getContatos(pessoa);
    }
    
    public List<Contato> alterarContatos(Empresa empresa){
    	for (int i = 0; i < empresa.getContatos().size(); i++) {
    		empresa.getContatos().get(i).setIdEmpresa(empresa.getIdEmpresa());
    		empresa.getContatos().get(i).setIdPessoa(0);;
        }
    	
    	this.alterarContatos(this.getContatos(empresa), empresa.getContatos());
    	return this.getContatos(empresa);
    }
    
    private void alterarContatos(List<Contato> contatosAtuais, List<Contato> contatosAlterados){
    	
		for (Contato contatoAtual : contatosAtuais) {
			int flag = 0;
			
			for (Contato contatoAlterado : contatosAlterados) {
				flag++;
				
				if (contatoAtual.getId() == contatoAlterado.getId()) {
					this.update(contatoAlterado); 
					break;
				}
				
				if (flag == contatosAlterados.size()) {
					this.delete(contatoAtual);
				}	
			}
		}
	
		for (Contato contato : contatosAlterados) {
			if (contato.getId() == 0) {
				this.insert(contato);
			}
		}
    }
    
    private Contato carregaContato(ResultSet rs) {
    	Contato contato = new Contato();
    	
        try {
	        
	        contato.setId(rs.getInt("id"));
	        contato.setDdd(rs.getString("ddd"));
	        contato.setNumero(rs.getString("numero"));
	        contato.setEmail(rs.getString("email"));
	        contato.setSite(rs.getString("site"));
	        contato.setIdPessoa(rs.getInt("idPessoa"));
	        contato.setIdEmpresa(rs.getInt("idEmpresa"));
	       
        } catch (SQLException e) {
        	throw new RuntimeException(e);
		}
        
        return contato;
        		
    }
    
    private PreparedStatement carregaParametros(Contato contato) throws SQLException {
    	String sql = "exec SPIU_CONTATO ?,?,?,?,?,?,?";
    	
    	PreparedStatement stmt = connection.getConnection().prepareStatement(sql);

        stmt.setInt(1, contato.getId());
        stmt.setString(2, contato.getDdd());
        stmt.setString(3, contato.getNumero());
        stmt.setString(4, contato.getEmail());
        stmt.setString(5, contato.getSite());
        
        if (contato.getIdPessoa() == null) {
        	stmt.setNull(6, Types.INTEGER);
        }else {
        	 stmt.setInt(6, contato.getIdPessoa());
        }
       
        if (contato.getIdEmpresa() == null) {
        	stmt.setNull(7, Types.INTEGER);
        }else {
        	 stmt.setInt(7, contato.getIdEmpresa());
        }
        
        return stmt;
    }
}
