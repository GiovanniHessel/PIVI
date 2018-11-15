package sp.com.senac.pi.model.pojo.producao;

public class Custo {
	private int id;
	private float custo;
	private String dataCusto; 
	private Integer idProduto;
	
	public Custo(int id, float custo, String dataCusto, Integer idProduto) {
		this.id = id;
		this.custo = custo;
		this.dataCusto = dataCusto;
		this.idProduto = idProduto;
	}
	
	public Custo() {
		this.id = 0;
		this.custo = 0;
		this.dataCusto = "";
		this.idProduto = 0;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getCusto() {
		return custo;
	}

	public void setCusto(float custo) {
		this.custo = custo;
	}

	public String getDataCusto() {
		return dataCusto;
	}

	public void setDataCusto(String dataCusto) {
		this.dataCusto = dataCusto;
	}

	public Integer getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Integer idProduto) {
		this.idProduto = idProduto;
	}
	
	
}
