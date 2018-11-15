package sp.com.senac.pi.model.pojo.base;

import java.util.ArrayList;
import java.util.List;

import sp.com.senac.pi.model.pojo.contato.Contato;
import sp.com.senac.pi.model.pojo.localizacao.Cidade;

public class Empresa {
    private int idEmpresa;
    private String nomeFantasia;
    private String razaoSocial;
    private String cnpj;
    private String dataDeCriacao;
    private String cep;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private Cidade cidade;
    private List<Contato> contatos;
    
    public Empresa() {
        this.idEmpresa = 0;
        this.nomeFantasia = "";
        this.razaoSocial = "";
        this.cnpj = "";
        this.dataDeCriacao = "";
        this.cep = "";
        this.logradouro = "";
		this.numero = "";
		this.complemento = "";
		this.bairro = "";
		this.cidade = new Cidade();
        this.contatos = new ArrayList<Contato>();;
    }

	public Empresa(int idEmpresa, String nomeFantasia, String razaoSocial, String cnpj, String dataDeCriacao, String cep,
			String logradouro, String numero, String complemento, String bairro, Cidade cidade, List<Contato> contatos) {
		super();
		this.idEmpresa = idEmpresa;
		this.nomeFantasia = nomeFantasia;
		this.razaoSocial = razaoSocial;
		this.cnpj = cnpj;
		this.dataDeCriacao = dataDeCriacao;
		this.cep = "";
		this.logradouro = logradouro;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cidade = cidade;
		this.contatos = contatos;
	}

	public int getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(int idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getDataDeCriacao() {
		return dataDeCriacao;
	}

	public void setDataDeCriacao(String dataDeCriacao) {
		this.dataDeCriacao = dataDeCriacao;
	}
	
	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public List<Contato> getContatos() {
		return contatos;
	}

	public void setContatos(List<Contato> contatos) {
		this.contatos = contatos;
	}

    
}
