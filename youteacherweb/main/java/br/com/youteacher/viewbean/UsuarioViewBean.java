package br.com.youteacher.viewbean;



import java.util.List;

import br.com.youteacherweb.entidades.Usuario;

public class UsuarioViewBean {
	
private Usuario usuario;
private List<Usuario> usuarios;
	
	public UsuarioViewBean(){
		
		initValues();

	}
	
	private void initValues(){
		usuario = new Usuario();
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	

}
