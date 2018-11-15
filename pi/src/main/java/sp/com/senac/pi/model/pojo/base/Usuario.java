package sp.com.senac.pi.model.pojo.base;

public class Usuario extends Pessoa {
	private int id;
	private String login;
	private String chave;
	private int inativo;
	
	public Usuario() {
		this.id = 0;
		this.login = "";
		this.chave = "";
		this.inativo = 0;
	}
	
	public Usuario(int id, String login, String chave, int inativo) {
		this.id = id;
		this.login = login;
		this.chave = chave;
		this.inativo = inativo;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getChave() {
		return chave;
	}
	
	public void setChave(String chave) {
		this.chave = chave;
	}
	
	public int getInativo() {
		return inativo;
	}
	
	public void setInativo(int inativo) {
		this.inativo = inativo;
	}
	
	
	
}
