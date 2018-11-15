package sp.com.senac.pi.model.pojo.localizacao;

public class Pais {
	private int id;
    private String pais;
    private String sigla;

    public Pais() {
        this.id = 0;
        this.pais = "";
        this.sigla = "";
    }

    public Pais(int id, String pais, String sigla) {
        this.id = id;
        this.pais = pais;
        this.sigla = sigla;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }
}
