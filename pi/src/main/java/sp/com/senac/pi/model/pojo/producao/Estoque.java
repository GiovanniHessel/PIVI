package sp.com.senac.pi.model.pojo.producao;

public class Estoque {
	private int id;
	private float quantidade;
	private String motivo;
	private String dataEstoque;
	private Integer idProduto;
	
	public Estoque(int id, float quantidade, String motivo, String dataEstoque, Integer idProduto) {
		this.id = id;
		this.quantidade = quantidade;
		this.motivo = motivo;
		this.dataEstoque = dataEstoque;
		this.idProduto = idProduto;
	}
	
	public Estoque() {
		this.id = 0;
		this.quantidade = 0;
		this.motivo = "";
		this.dataEstoque = "";
		this.idProduto = 0;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(float quantidade) {
		this.quantidade = quantidade;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public String getDataEstoque() {
		return dataEstoque;
	}

	public void setDataEstoque(String dataEstoque) {
		this.dataEstoque = dataEstoque;
	}

	public Integer getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Integer idProduto) {
		this.idProduto = idProduto;
	}
	
	
}
