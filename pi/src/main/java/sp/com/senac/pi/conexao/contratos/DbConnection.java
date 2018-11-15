package sp.com.senac.pi.conexao.contratos;

import java.sql.Connection;

public interface DbConnection {
	public void open();
	public void close();
	public Connection getConnection();
}
