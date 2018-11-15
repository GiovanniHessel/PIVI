package sp.com.senac.pi.model.dao.producao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import sp.com.senac.pi.conexao.contratos.DbConnection;
import sp.com.senac.pi.conexao.singleton.ConnectionSingleton;
import sp.com.senac.pi.model.pojo.producao.Custo;
import sp.com.senac.pi.model.pojo.producao.Produto;
import sp.com.senac.pi.util.control.Util;

public class CustoDao {
	private DbConnection connection;

    public CustoDao() {
        this.connection = ConnectionSingleton.getNewConnection();
    }
    
    public Custo insert(Custo custo) {
    	
    	custo.setId(0);
    	
        connection.open();
        try {

        	PreparedStatement stmt = this.carregaParametros(custo);
            
            ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				custo.setId(rs.getInt("id"));
			}
			
			rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Insert");
            connection.close();
            return custo;
        }
        connection.close();
        return custo;
    }
    
    public Custo update(Custo custo) {
    	
        connection.open();
        try {

        	PreparedStatement stmt = this.carregaParametros(custo);
            
            ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				custo.setId(rs.getInt("id"));
			}
			
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("update");
            connection.close();
            return custo;
        }
        connection.close();
        return custo;
    }
   
    public List<Custo> insert(List<Custo> custos) {
       
        connection.open();
        try {
        	
        	for (Custo custo : custos) {
        		custo.setId(0);
        		
	            PreparedStatement stmt = this.carregaParametros(custo);
	            
	            ResultSet rs = stmt.executeQuery();
				if (rs.next()) {
					custo.setId(rs.getInt("id"));
				}
				
	            rs.close();
	            
	            stmt.close();
        	}
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Insert List");
            connection.close();
            return custos;
        }
        connection.close();
        return custos;
    }
    
    public List<Custo> insertCustos(Produto produto) {
        
        connection.open();
        try {
        	
        	for (Custo custo : produto.getCustos()) {
        		
        		if (custo.getId() == 0) {
        			custo.setIdProduto(produto.getId());
		            PreparedStatement stmt = this.carregaParametros(custo);
		            ResultSet rs = stmt.executeQuery();
					if (rs.next()) {
						custo.setId(rs.getInt("id"));
					}
					
		            rs.close();
		            stmt.close();
        		}
        	}
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            connection.close();
            return produto.getCustos();
        }
        connection.close();
        return produto.getCustos();
    }
    
    public List<Custo> update(List<Custo> custos) {
        
        connection.open();
        try {
        	
        	for (Custo custo : custos) {
        		custo.setId(0);
        		
	            PreparedStatement stmt = this.carregaParametros(custo);
	            
	            ResultSet rs = stmt.executeQuery();
				if (rs.next()) {
					custo.setId(rs.getInt("id"));
				}
				
	            rs.close();
	            
	            stmt.close();
        	}
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Update List");
            connection.close();
            return custos;
        }
        connection.close();
        return custos;
    }
    
    public Custo delete(Custo custo) {
    	 this.connection.open();
         try {
             PreparedStatement stmt = this.connection.getConnection().prepareStatement("exec Producao.SPD_CUSTO ?");
             stmt.setInt(1, custo.getId());

             ResultSet rs = stmt.executeQuery();
             
             if (rs.next()) {
             	custo.setId(rs.getInt("id"));
             }

             rs.close();
             stmt.close();
             connection.close();
             return custo;

         } catch (SQLException e) {
             connection.close();
             throw new RuntimeException(e);
         }
    }
    public Custo getCusto(int id) {
        this.connection.open();
        try {
            PreparedStatement stmt = this.connection.getConnection().prepareStatement("Select * from Produto.Custo Where id = ?");
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            Custo custo = new Custo();
            
            if (rs.next()) {
            	custo = this.carregaCusto(rs);
            }

            rs.close();
            stmt.close();
            connection.close();
            return custo;

        } catch (SQLException e) {
            connection.close();
            throw new RuntimeException(e);
        }
    }
    
    public List<Custo> getCustos() {
        this.connection.open();
        try {
            PreparedStatement stmt = this.connection.getConnection().prepareStatement("Select * from Produto.Custo");

            ResultSet rs = stmt.executeQuery();
            List<Custo> custos = new ArrayList<Custo>();
            while (rs.next()) {
            	Custo custo = this.carregaCusto(rs);
                custos.add(custo);
            }
            rs.close();
            stmt.close();
            connection.close();
            return custos;
        } catch (SQLException e) {
            connection.close();
            throw new RuntimeException(e);
        }
    }
    
    public List<Custo> getCustos(Produto produto) {
    	List<Custo> custos = new ArrayList<Custo>();
    	
        this.connection.open();
        try {
            PreparedStatement stmt = this.connection.getConnection().prepareStatement("Select * from Producao.Custo where idProduto = ?");
            stmt.setInt(1, produto.getId());
            
            ResultSet rs = stmt.executeQuery();
           
            while (rs.next()) {
            	Custo custo = this.carregaCusto(rs);
                custos.add(custo);
            }
            
            rs.close();
            stmt.close();
            this.connection.close();
            
            return custos;
        } catch (SQLException e) {
            this.connection.close();
            throw new RuntimeException(e);
        }
    }
    
    public List<Custo> alterarCustos(Produto produto){
    	for (int i = 0; i < produto.getCustos().size(); i++) {
    		produto.getCustos().get(i).setIdProduto(produto.getId());
        }
    	
    	this.alterarCustos(this.getCustos(produto), produto.getCustos());
    	return this.getCustos(produto);
    }
 
    
    private void alterarCustos(List<Custo> custosAtuais, List<Custo> custosAlterados){
    	
		for (Custo custoAtual : custosAtuais) {
			int flag = 0;
			
			for (Custo custoAlterado : custosAlterados) {
				flag++;
				
				if (custoAtual.getId() == custoAlterado.getId()) {
					this.update(custoAlterado); 
					break;
				}
				
				if (flag == custosAlterados.size()) {
					this.delete(custoAtual);
				}	
			}
		}
	
		for (Custo custo : custosAlterados) {
			if (custo.getId() == 0) {
				this.insert(custo);
			}
		}
    }
    
    private Custo carregaCusto(ResultSet rs) {
    	Custo custo = new Custo();
    	
        try {
	        
	        custo.setId(rs.getInt("id"));
	        custo.setCusto(rs.getFloat("custo"));
	        custo.setDataCusto(new Util().getStringDate(rs.getTimestamp("dataCusto")));
	        custo.setIdProduto(rs.getInt("idProduto"));

        } catch (SQLException e) {
        	throw new RuntimeException(e);
		}
        
        return custo;
        		
    }
    
    private PreparedStatement carregaParametros(Custo custo) throws SQLException {
    	String sql = "exec Producao.SPIU_CUSTO ?,?,?,?";
    	
    	PreparedStatement stmt = connection.getConnection().prepareStatement(sql);

        stmt.setInt(1, custo.getId());
        stmt.setFloat(2, custo.getCusto());
        stmt.setString(3, custo.getDataCusto());
        
        if (custo.getIdProduto() == null) {
        	stmt.setNull(4, Types.INTEGER);
        }else {
        	 stmt.setInt(4, custo.getIdProduto());
        }
       
        
        return stmt;
    }
}
