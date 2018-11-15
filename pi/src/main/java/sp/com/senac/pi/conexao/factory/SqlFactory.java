package sp.com.senac.pi.conexao.factory;

import sp.com.senac.pi.conexao.contratos.DbConnection;
import sp.com.senac.pi.conexao.contratos.DbFactory;

public class SqlFactory implements DbFactory{

	@Override
	public DbConnection createConnection() {
		return new SqlConnection();
	}

}
