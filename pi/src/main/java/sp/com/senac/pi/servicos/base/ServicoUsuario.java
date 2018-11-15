package sp.com.senac.pi.servicos.base;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import sp.com.senac.pi.model.dao.base.UsuarioDao;
import sp.com.senac.pi.model.pojo.base.Usuario;

@Path("/usuario")
public class ServicoUsuario {
	
	
	@GET
	@Path("lista")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Usuario> getUsuarios() {
		
		return new UsuarioDao().getUsuarios();
	}
	
	@POST
	@Path("id")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Usuario getUsuario(Usuario usuario) {
		
		return new UsuarioDao().getUsuario(usuario.getId());
	}
	

	@POST
	@Path("signin")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Usuario validar(Usuario usuario) {
		
		return new UsuarioDao().getUsuario(usuario.getLogin(), usuario.getChave());
	}
	
	@POST
	@Path("inserir")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Usuario inserir(Usuario usuario) {
		
		return new UsuarioDao().insert(usuario);
	}
	
	@PUT
	@Path("alterar")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Usuario alterar(Usuario usuario) {
		
		return new UsuarioDao().update(usuario);
	}
	
	@DELETE
	@Path("deletar")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Usuario deletar(Usuario usuario) {
		
		return new UsuarioDao().delete(usuario);
	}
	
}
