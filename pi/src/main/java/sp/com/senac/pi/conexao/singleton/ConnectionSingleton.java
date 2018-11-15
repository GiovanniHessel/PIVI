package sp.com.senac.pi.conexao.singleton;

import sp.com.senac.pi.conexao.contratos.DbConnection;
import sp.com.senac.pi.conexao.factory.SqlFactory;

public class ConnectionSingleton {
	private static DbConnection connection = new SqlFactory().createConnection();
	 
    private ConnectionSingleton() {
    }
 
    public static DbConnection getConnection() {
        return connection;
    }
    
    public static DbConnection getNewConnection() {
        return new SqlFactory().createConnection();
    }
    
}
