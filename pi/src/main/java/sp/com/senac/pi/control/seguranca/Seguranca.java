package sp.com.senac.pi.control.seguranca;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Seguranca {
	
	public Seguranca() {}
	
	public String hash(String dado) {
		return this.sha512(dado);
	}
	
	private String sha512(String dado) {
		String result = null;
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-512");
			digest.reset();
		    digest.update(dado.getBytes(StandardCharsets.UTF_8));
		    result = String.format("%040x", new BigInteger(1,digest.digest() ));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return result;
	}
}
