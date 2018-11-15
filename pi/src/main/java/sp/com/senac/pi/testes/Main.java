package sp.com.senac.pi.testes;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import sp.com.senac.pi.conexao.contratos.DbConnection;
import sp.com.senac.pi.conexao.singleton.ConnectionSingleton;
import sp.com.senac.pi.util.control.Util;

public class Main {

	public static void main(String args[]) throws ParseException {
		DbConnection connection = ConnectionSingleton.getNewConnection();
		connection.open();
		
        try {

        	//String sql = "insert into testeData (data) values (?)";
        	
        	String sql = "select data from testeData order by data desc";
        	
        	PreparedStatement stmt = connection.getConnection().prepareStatement(sql);
        	//Date data = new java.sql.Date( new java.util.Date().getTime() );
        	DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd HH:mm:ss"); 
        	Date date = new Date(); 
        	
        	
        	//String data = dateFormat.format(date); 
        	
        	//System.out.println(dateFormat.parse("2018-11-11 00:55:04"));
        	//System.out.println(data);
            //stmt.setString(1, data);
            
            ResultSet rs = stmt.executeQuery();
		
            while (rs.next()) {
            	String d = new Util().getStringDate(rs.getTimestamp("data")) ;
            	System.out.println(d);
            }
			rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Insert");
            connection.close();
        }
        connection.close();
	}
	
  
}
