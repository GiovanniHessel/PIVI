package sp.com.senac.pi.model.dao.producao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import sp.com.senac.pi.conexao.contratos.DbConnection;
import sp.com.senac.pi.conexao.singleton.ConnectionSingleton;
import sp.com.senac.pi.model.pojo.producao.Preco;
import sp.com.senac.pi.model.pojo.producao.Produto;
import sp.com.senac.pi.util.control.Util;

public class PrecoDao {
	private DbConnection connection;

    public PrecoDao() {
        this.connection = ConnectionSingleton.getNewConnection();
    }
    
    public Preco insert(Preco preco) {
    	
    	preco.setId(0);
    	
        connection.open();
        try {

        	PreparedStatement stmt = this.carregaParametros(preco);
            
            ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				preco.setId(rs.getInt("id"));
			}
			
			rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Insert");
            connection.close();
            return preco;
        }
        connection.close();
        return preco;
    }
    
    public Preco update(Preco preco) {
    	
        connection.open();
        try {

        	PreparedStatement stmt = this.carregaParametros(preco);
            
            ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				preco.setId(rs.getInt("id"));
			}
			
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("update");
            connection.close();
            return preco;
        }
        connection.close();
        return preco;
    }
   
    public List<Preco> insert(List<Preco> precos) {
       
        connection.open();
        try {
        	
        	for (Preco preco : precos) {
        		preco.setId(0);
        		
	            PreparedStatement stmt = this.carregaParametros(preco);
	           
	            ResultSet rs = stmt.executeQuery();
				if (rs.next()) {
					preco.setId(rs.getInt("id"));
				}
				
	            rs.close();
	            stmt.close();
        	}
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Insert List");
            connection.close();
            return precos;
        }
        connection.close();
        return precos;
    }
    
    public List<Preco> insertPrecos(Produto produto) {
        
        connection.open();
        try {
        	
        	for (Preco preco : produto.getPrecos()) {
        		
        		if (preco.getId() == 0) {
        			preco.setIdProduto(produto.getId());
		            PreparedStatement stmt = this.carregaParametros(preco);
		            ResultSet rs = stmt.executeQuery();
					if (rs.next()) {
						preco.setId(rs.getInt("id"));
					}
					
		            rs.close();
		            
		            stmt.close();
        		}
        	}
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Insert List");
            connection.close();
            return produto.getPrecos();
        }
        connection.close();
        return produto.getPrecos();
    }
    
    public List<Preco> update(List<Preco> precos) {
        
        connection.open();
        try {
        	
        	for (Preco preco : precos) {
        		preco.setId(0);
        		
	            PreparedStatement stmt = this.carregaParametros(preco);
	            
	            ResultSet rs = stmt.executeQuery();
				if (rs.next()) {
					preco.setId(rs.getInt("id"));
				}
				
	            rs.close();
	            
	            stmt.close();
        	}
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Update List");
            connection.close();
            return precos;
        }
        connection.close();
        return precos;
    }
    
    public Preco delete(Preco preco) {
    	 this.connection.open();
         try {
             PreparedStatement stmt = this.connection.getConnection().prepareStatement("exec Producao.SPD_PRECO ?");
             stmt.setInt(1, preco.getId());

             ResultSet rs = stmt.executeQuery();
             
             if (rs.next()) {
             	preco.setId(rs.getInt("id"));
             }

             rs.close();
             stmt.close();
             connection.close();
             return preco;

         } catch (SQLException e) {
             connection.close();
             throw new RuntimeException(e);
         }
    }
    public Preco getPreco(int id) {
        this.connection.open();
        try {
            PreparedStatement stmt = this.connection.getConnection().prepareStatement("Select * from Produto.Preco Where id = ?");
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            Preco preco = new Preco();
            
            if (rs.next()) {
            	preco = this.carregaPreco(rs);
            }

            rs.close();
            stmt.close();
            connection.close();
            return preco;

        } catch (SQLException e) {
            connection.close();
            throw new RuntimeException(e);
        }
    }
    
    public List<Preco> getPrecos() {
        this.connection.open();
        try {
            PreparedStatement stmt = this.connection.getConnection().prepareStatement("Select * from Produto.Preco");

            ResultSet rs = stmt.executeQuery();
            List<Preco> precos = new ArrayList<Preco>();
            while (rs.next()) {
            	Preco preco = this.carregaPreco(rs);
                precos.add(preco);
            }
            rs.close();
            stmt.close();
            connection.close();
            return precos;
        } catch (SQLException e) {
            connection.close();
            throw new RuntimeException(e);
        }
    }
    
    public List<Preco> getPrecos(Produto produto) {
    	List<Preco> precos = new ArrayList<Preco>();
    	
        this.connection.open();
        try {
            PreparedStatement stmt = this.connection.getConnection().prepareStatement("Select * from Producao.Preco where idProduto = ?");
            stmt.setInt(1, produto.getId());
            
            ResultSet rs = stmt.executeQuery();
           
            while (rs.next()) {
            	Preco preco = this.carregaPreco(rs);
                precos.add(preco);
            }
            
            rs.close();
            stmt.close();
            this.connection.close();
            
            return precos;
        } catch (SQLException e) {
            this.connection.close();
            throw new RuntimeException(e);
        }
    }
    
    public List<Preco> alterarPrecos(Produto produto){
    	for (int i = 0; i < produto.getPrecos().size(); i++) {
    		produto.getPrecos().get(i).setIdProduto(produto.getId());
        }
    	
    	this.alterarPrecos(this.getPrecos(produto), produto.getPrecos());
    	return this.getPrecos(produto);
    }
 
    
    private void alterarPrecos(List<Preco> precosAtuais, List<Preco> precosAlterados){
    	
		for (Preco precoAtual : precosAtuais) {
			int flag = 0;
			
			for (Preco precoAlterado : precosAlterados) {
				flag++;
				
				if (precoAtual.getId() == precoAlterado.getId()) {
					this.update(precoAlterado); 
					break;
				}
				
				if (flag == precosAlterados.size()) {
					this.delete(precoAtual);
				}	
			}
		}
	
		for (Preco preco : precosAlterados) {
			if (preco.getId() == 0) {
				this.insert(preco);
			}
		}
    }
    
    private Preco carregaPreco(ResultSet rs) {
    	Preco preco = new Preco();
    	
        try {
	        
	        preco.setId(rs.getInt("id"));
	        preco.setPreco(rs.getFloat("preco"));
	        preco.setDataPreco(new Util().getStringDate(rs.getTimestamp("dataPreco")));
	        preco.setIdProduto(rs.getInt("idProduto"));

	       
        } catch (SQLException e) {
        	throw new RuntimeException(e);
		}
        
        return preco;
        		
    }
    
    private PreparedStatement carregaParametros(Preco preco) throws SQLException {
    	String sql = "exec Producao.SPIU_PRECO ?,?,?,?";
    	
    	PreparedStatement stmt = connection.getConnection().prepareStatement(sql);

        stmt.setInt(1, preco.getId());
        stmt.setFloat(2, preco.getPreco());
        stmt.setString(3, preco.getDataPreco());
        
        if (preco.getIdProduto() == null) {
        	stmt.setNull(4, Types.INTEGER);
        }else {
        	 stmt.setInt(4, preco.getIdProduto());
        }
       
        
        return stmt;
    }
}
