package sp.com.senac.pi.model.pojo.localizacao;

public class Cidade {
	private int id;
    private String cidade;
    private String sigla;    
    private Estado estado;

    public Cidade() {
        this.id = 0;
        this.cidade = "";
        this.sigla = "";
        this.estado = new Estado();
    }
    
    public Cidade(int id, String cidade, String sigla, Estado estado) {
        this.id = id;
        this.cidade = cidade;
        this.sigla = sigla;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

}
