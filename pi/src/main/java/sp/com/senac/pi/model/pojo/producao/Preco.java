package sp.com.senac.pi.model.pojo.producao;

public class Preco {
	private int id; 
	private float preco; 
	private String dataPreco;
	private Integer idProduto;
	
	public Preco(int id, float preco, String dataPreco, Integer idProduto) {
		this.id = id;
		this.preco = preco;
		this.dataPreco = dataPreco;
		this.idProduto = idProduto;
	}
	
	public Preco() {
		this.id = 0;
		this.preco = 0;
		this.dataPreco = "";
		this.idProduto = 0;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getPreco() {
		return preco;
	}

	public void setPreco(float preco) {
		this.preco = preco;
	}

	public String getDataPreco() {
		return dataPreco;
	}

	public void setDataPreco(String dataPreco) {
		this.dataPreco = dataPreco;
	}

	public Integer getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Integer idProduto) {
		this.idProduto = idProduto;
	}
	
	
}
