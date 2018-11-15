package sp.com.senac.pi.util.control;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
	
	private DateFormat dateFormat; 
	
	public Util() {
		this.dateFormat = new SimpleDateFormat("yyyyMMdd HH:mm:ss"); 
	}
	
	public String getStringDate(Date data){
		return dateFormat.format(data);
	}
}
