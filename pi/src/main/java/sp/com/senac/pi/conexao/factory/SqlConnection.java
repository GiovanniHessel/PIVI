package sp.com.senac.pi.conexao.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import sp.com.senac.pi.conexao.contratos.DbConnection;
import sp.com.senac.pi.conexao.model.ConexaoPropriedade;

public class SqlConnection implements DbConnection {

	private ConexaoPropriedade prop = new ConexaoPropriedade();
	private Connection connection;
	private boolean isConnected;

	public SqlConnection() {
		this.sqlServer();
		isConnected = false;
	}

	@Override
	public void open() {
		try {
			if (!isConnected) {
				Class.forName(prop.getDrive()).newInstance();
				this.connection = DriverManager.getConnection(prop.getConnectionURL(), prop.getLogin(), prop.getSenha());
				isConnected = true;
			}
		} catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			isConnected = false;
		}
	}

	public void close() {
		try {
			if (isConnected) {
				this.connection.close();
				isConnected = false;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public Connection getConnection() {
		return this.connection;
	}

	public void sqlServer() {
		prop.setPorta("1433");
		prop.setHost("localHost");
		prop.setBase("Integration");
		prop.setLogin("sa");
		prop.setSenha("121212");
		prop.setDrive("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		prop.setConnectionURL("jdbc:sqlserver://localHost:" + prop.getPorta() + ";databaseName=" + prop.getBase());
	}
}
