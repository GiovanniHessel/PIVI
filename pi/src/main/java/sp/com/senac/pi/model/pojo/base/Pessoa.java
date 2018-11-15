package sp.com.senac.pi.model.pojo.base;

import java.util.ArrayList;
import java.util.List;

import sp.com.senac.pi.model.pojo.contato.Contato;
import sp.com.senac.pi.model.pojo.localizacao.Cidade;

public class Pessoa {
	private int idPessoa;
    private String nome;
    private String sobrenome;
    private String cpf;
    private String dataDeNascimento;
    private String sexo;
    private String cep;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private Cidade cidade;
    private List<Contato> contatos;

    public Pessoa() {
        this.idPessoa = 0;
        this.nome = "";
        this.sobrenome = "";
        this.cpf = "";
        this.dataDeNascimento = "";
        this.sexo = "";
        this.cep = "";
        this.logradouro = "";
		this.numero = "";
		this.complemento = "";
		this.bairro = "";
		this.cidade = new Cidade();
		this.contatos = new ArrayList<Contato>();
    }
    
	public Pessoa(int idPessoa, String nome, String sobrenome, String cpf, String dataDeNascimento, String sexo, String cep, String logradouro,
			String numero, String complemento, String bairro, Cidade cidade, List<Contato> contatos) {
		this.idPessoa = idPessoa;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.cpf = cpf;
		this.dataDeNascimento = dataDeNascimento;
		this.sexo = sexo;
		this.cep = cep;
		this.logradouro = logradouro;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cidade = cidade;
		this.contatos = contatos;
	}

	public int getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(int id) {
		this.idPessoa = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getCPF() {
		return cpf;
	}

	public void setCPF(String cpf) {
		this.cpf = cpf;
	}

	public String getDataDeNascimento() {
		return dataDeNascimento;
	}

	public void setDataDeNascimento(String dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}
	
	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public List<Contato> getContatos() {
		return contatos;
	}

	public void setContatos(List<Contato> contatos) {
		this.contatos = contatos;
	}

   
}
