package sp.com.senac.pi.servicos.producao;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import sp.com.senac.pi.model.dao.producao.ProdutoDao;
import sp.com.senac.pi.model.pojo.producao.Produto;

@Path("/produto")
public class ServicoProduto {
	
	@POST
	@Path("id")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Produto getProdutoId(Produto produto) {
		
		return new ProdutoDao().getProduto(produto.getId());
	}
	
	@POST
	@Path("idAtual")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Produto getProdutoIdAtual(Produto produto) {
		
		return new ProdutoDao().getProdutoAtual(produto.getId());
	}
	
	@POST
	@Path("nomesAtuais")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Produto> getProdutosNomeAtuais(Produto produto) {
		
		return new ProdutoDao().getProdutosAtuaisNome(produto.getNome());
	}
	
	@POST
	@Path("nomes")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Produto> getProdutosNome(Produto produto) {
		
		return new ProdutoDao().getProdutosNome(produto.getNome());
	}
	
	@GET
	@Path("listaAtual")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Produto> getProdutosAtuais() {
		
		return new ProdutoDao().getProdutosAtuais();
	}
	
	@GET
	@Path("lista")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Produto> getProdutos() {
		
		return new ProdutoDao().getProdutos();
	}
	
	@POST
	@Path("inserir")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Produto insert(Produto produto) {
		
		return new ProdutoDao().insert(produto);
	}
	
	@PUT
	@Path("alterar")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Produto update(Produto produto) {
		
		return new ProdutoDao().update(produto);
	}
	
	//Necessário alterar a procedure para deletar todos os itens relacionados.
	@DELETE
	@Path("deletar")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Produto delete(Produto produto) {
		
		return new ProdutoDao().delete(produto);
	}
}
