package sp.com.senac.pi.model.dao.producao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import sp.com.senac.pi.conexao.contratos.DbConnection;
import sp.com.senac.pi.conexao.singleton.ConnectionSingleton;
import sp.com.senac.pi.model.pojo.producao.Estoque;
import sp.com.senac.pi.model.pojo.producao.Produto;
import sp.com.senac.pi.util.control.Util;

public class EstoqueDao {
	private DbConnection connection;

    public EstoqueDao() {
        this.connection = ConnectionSingleton.getNewConnection();
    }
    
    public Estoque insert(Estoque estoque) {
    	
    	estoque.setId(0);
    	
        connection.open();
        try {

        	PreparedStatement stmt = this.carregaParametros(estoque);
            
            ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				estoque.setId(rs.getInt("id"));
			}
			
			rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Insert");
            connection.close();
            return estoque;
        }
        connection.close();
        return estoque;
    }
    
    public Estoque update(Estoque estoque) {
    	
        connection.open();
        try {

        	PreparedStatement stmt = this.carregaParametros(estoque);
            
            ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				estoque.setId(rs.getInt("id"));
			}
			
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("update");
            connection.close();
            return estoque;
        }
        connection.close();
        return estoque;
    }
   
    public List<Estoque> insert(List<Estoque> estoques) {
       
        connection.open();
        try {
        	
        	for (Estoque estoque : estoques) {
        		estoque.setId(0);
        		
	            PreparedStatement stmt = this.carregaParametros(estoque);
	            
	            stmt.execute();
	            stmt.close();
        	}
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Insert List");
            connection.close();
            return estoques;
        }
        connection.close();
        return estoques;
    }
    
    public List<Estoque> insertEstoques(List<Estoque> estoques) {
        
        connection.open();
        try {
        	
        	for (Estoque estoque : estoques) {
        		
        		if (estoque.getId() == 0) {
		            PreparedStatement stmt = this.carregaParametros(estoque);
		            stmt.execute();
		            stmt.close();
        		}
        	}
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Insert List");
            connection.close();
            return estoques;
        }
        connection.close();
        return estoques;
    }
    
    public List<Estoque> estoqueInicial(Produto produto) {
    	List<Estoque> estoques = new ArrayList<Estoque>();
    	Estoque estoque = new Estoque();
        connection.open();
        try {
           	
        	estoque.setQuantidade(0);
        	estoque.setIdProduto(produto.getId());
        	estoque.setDataEstoque(new Util().getStringDate(new Date()));
        	estoque.setMotivo("Estoque Inicial");
        	
        	PreparedStatement stmt = this.carregaParametros(estoque);
        	
        	
            ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				estoque.setId(rs.getInt("id"));
			}
			
			estoques.add(estoque);
			rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Insert");
            connection.close();
            return estoques;
        }
        connection.close();
        return estoques;
    }
    
    public List<Estoque> update(List<Estoque> estoques) {
        
        connection.open();
        try {
        	
        	for (Estoque estoque : estoques) {
        		estoque.setId(0);
        		
	            PreparedStatement stmt = this.carregaParametros(estoque);
	            
	            stmt.execute();
	            stmt.close();
        	}
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Update List");
            connection.close();
            return estoques;
        }
        connection.close();
        return estoques;
    }
    
    public Estoque delete(Estoque estoque) {
    	 this.connection.open();
         try {
             PreparedStatement stmt = this.connection.getConnection().prepareStatement("exec Producao.SPD_ESTOQUE ?");
             stmt.setInt(1, estoque.getId());

             ResultSet rs = stmt.executeQuery();
             
             if (rs.next()) {
             	estoque.setId(rs.getInt("id"));
             }

             rs.close();
             stmt.close();
             connection.close();
             return estoque;

         } catch (SQLException e) {
             connection.close();
             throw new RuntimeException(e);
         }
    }
    public Estoque getEstoque(int id) {
        this.connection.open();
        try {
            PreparedStatement stmt = this.connection.getConnection().prepareStatement("Select * from Produto.Estoque Where id = ?");
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            Estoque estoque = new Estoque();
            
            if (rs.next()) {
            	estoque = this.carregaEstoque(rs);
            }

            rs.close();
            stmt.close();
            connection.close();
            return estoque;

        } catch (SQLException e) {
            connection.close();
            throw new RuntimeException(e);
        }
    }
    
    public List<Estoque> getEstoques() {
        this.connection.open();
        try {
            PreparedStatement stmt = this.connection.getConnection().prepareStatement("Select * from Produto.Estoque");

            ResultSet rs = stmt.executeQuery();
            List<Estoque> estoques = new ArrayList<Estoque>();
            while (rs.next()) {
            	Estoque estoque = this.carregaEstoque(rs);
                estoques.add(estoque);
            }
            rs.close();
            stmt.close();
            connection.close();
            return estoques;
        } catch (SQLException e) {
            connection.close();
            throw new RuntimeException(e);
        }
    }
    
    public List<Estoque> getEstoques(Produto produto) {
    	List<Estoque> estoques = new ArrayList<Estoque>();
    	
        this.connection.open();
        try {
            PreparedStatement stmt = this.connection.getConnection().prepareStatement("Select * from Producao.Estoque where idProduto = ?");
            stmt.setInt(1, produto.getId());
            
            ResultSet rs = stmt.executeQuery();
           
            while (rs.next()) {
            	Estoque estoque = this.carregaEstoque(rs);
                estoques.add(estoque);
            }
            
            rs.close();
            stmt.close();
            this.connection.close();
            
            return estoques;
        } catch (SQLException e) {
            this.connection.close();
            throw new RuntimeException(e);
        }
    }
    
    public List<Estoque> alterarEstoques(Produto produto){
    	for (int i = 0; i < produto.getEstoques().size(); i++) {
    		produto.getEstoques().get(i).setIdProduto(produto.getId());
        }
    	
    	this.alterarEstoques(this.getEstoques(produto), produto.getEstoques());
    	return this.getEstoques(produto);
    }
 
    
    private void alterarEstoques(List<Estoque> estoquesAtuais, List<Estoque> estoquesAlterados){
    	
		for (Estoque estoqueAtual : estoquesAtuais) {
			int flag = 0;
			
			for (Estoque estoqueAlterado : estoquesAlterados) {
				flag++;
				
				if (estoqueAtual.getId() == estoqueAlterado.getId()) {
					this.update(estoqueAlterado); 
					break;
				}
				
				if (flag == estoquesAlterados.size()) {
					this.delete(estoqueAtual);
				}	
			}
		}
	
		for (Estoque estoque : estoquesAlterados) {
			if (estoque.getId() == 0) {
				this.insert(estoque);
			}
		}
    }
    
    private Estoque carregaEstoque(ResultSet rs) {
    	Estoque estoque = new Estoque();
    	
        try {
	        
	        estoque.setId(rs.getInt("id"));
	        estoque.setQuantidade(rs.getFloat("quantidade"));
	        estoque.setMotivo(rs.getString("motivo"));
	        estoque.setDataEstoque(new Util().getStringDate(rs.getTimestamp("dataEstoque")));
	        estoque.setIdProduto(rs.getInt("idProduto"));

	       
        } catch (SQLException e) {
        	throw new RuntimeException(e);
		}
        
        return estoque;
        		
    }
    
    private PreparedStatement carregaParametros(Estoque estoque) throws SQLException {
    	String sql = "exec Producao.SPIU_ESTOQUE ?,?,?,?,?";
    	
    	PreparedStatement stmt = connection.getConnection().prepareStatement(sql);

        stmt.setInt(1, estoque.getId());
        stmt.setFloat(2, estoque.getQuantidade());
        stmt.setString(3, estoque.getMotivo());
        stmt.setString(4, estoque.getDataEstoque());
        
        if (estoque.getIdProduto() == null) {
        	stmt.setNull(5, Types.INTEGER);
        }else {
        	 stmt.setInt(5, estoque.getIdProduto());
        }
       
        
        return stmt;
    }
}
