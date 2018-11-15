package sp.com.senac.pi.model.pojo.contato;

public class Contato {
	private int id;
    private String ddd;
    private String numero;
    private String email;
    private String site;
    private Integer idPessoa;
    private Integer idEmpresa;

    public Contato() {
        this.id = 0;
        this.ddd = "";
        this.numero = "";
        this.email = "";
        this.site = "";
        this.idPessoa = null;
        this.idEmpresa = null;
    }

    public Contato(int id, String ddd, String numero, String email, String site, Integer idPessoa, Integer idEmpresa) {
        this.id = id;
        this.ddd = ddd;
        this.numero = numero;
        this.email = email;
        this.site = site;
        this.idPessoa = idPessoa;
        this.idEmpresa = idEmpresa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }
    

    public Integer getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(Integer idPessoa) {
		if (idPessoa == 0)
			idPessoa = null;
		
		this.idPessoa = idPessoa;
	}

	public Integer getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Integer idEmpresa) {
		if (idEmpresa == 0)
			idEmpresa = null;
		
		this.idEmpresa = idEmpresa;
	}
}