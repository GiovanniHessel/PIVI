package sp.com.senac.pi.model.pojo.producao;

import java.util.ArrayList;
import java.util.List;

public class Produto {
	private int id; 
	private String nome;
	private String descricao;
	private String ean;
	private String marca;
	private float peso;
	private float largura;
	private float altura;
	private float comprimento;
	private List<Preco> precos;
	private List<Custo> custos;
	private List<Estoque> estoques;
	private int inativo;
	
	public Produto(int id, String nome, String descricao, String ean, String marca, float peso, float largura, float altura,
			float comprimento, List<Preco> precos, List<Custo> custos, List<Estoque>  estoques, int inativo) {
		
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.ean = ean;
		this.marca = marca;
		this.peso = peso;
		this.largura = largura;
		this.altura = altura;
		this.comprimento = comprimento;
		this.precos = precos;
		this.custos = custos;
		this.estoques = estoques;
		this.inativo = inativo;
	}
	
	public Produto() {
		
		this.id = 0;
		this.nome = "";
		this.descricao = "";
		this.ean = "";
		this.marca = "";
		this.peso = 0;
		this.largura = 0;
		this.altura = 0;
		this.comprimento = 0;
		this.precos = new ArrayList<Preco>();
		this.custos =  new ArrayList<Custo>();
		this.estoques =  new ArrayList<Estoque>();
		this.inativo = 0;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getEan() {
		return ean;
	}

	public void setEan(String ean) {
		this.ean = ean;
	}
	

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public float getPeso() {
		return peso;
	}

	public void setPeso(float peso) {
		this.peso = peso;
	}

	public float getLargura() {
		return largura;
	}

	public void setLargura(float largura) {
		this.largura = largura;
	}

	public float getAltura() {
		return altura;
	}

	public void setAltura(float altura) {
		this.altura = altura;
	}

	public float getComprimento() {
		return comprimento;
	}

	public void setComprimento(float comprimento) {
		this.comprimento = comprimento;
	}

	public List<Preco> getPrecos() {
		return precos;
	}

	public void setPrecos(List<Preco> precos) {
		this.precos = precos;
	}

	public List<Custo> getCustos() {
		return custos;
	}

	public void setCustos(List<Custo> custos) {
		this.custos = custos;
	}

	public List<Estoque> getEstoques() {
		return estoques;
	}

	public void setEstoques(List<Estoque> estoques) {
		this.estoques = estoques;
	}

	public int getInativo() {
		return inativo;
	}

	public void setInativo(int inativo) {
		this.inativo = inativo;
	}
	
	
	
}
