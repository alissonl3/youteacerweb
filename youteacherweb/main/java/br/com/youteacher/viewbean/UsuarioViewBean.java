package br.com.youteacher.viewbean;



import java.util.List;

import br.com.youteacherweb.entidades.Usuario;

public class UsuarioViewBean {
	
private Usuario usuario;
private List<Usuario> usuarios;
private Usuario usuarioLogado;

private boolean habilitarVisualizacaoIdCadastro;
	
	public UsuarioViewBean(){
		
		initValues();

	}
	
	private void initValues(){
		usuario = new Usuario();
		usuarioLogado = new Usuario();
		
		habilitarVisualizacaoIdCadastro = false;
	}


	public boolean isHabilitarVisualizacaoIdCadastro() {
		return habilitarVisualizacaoIdCadastro;
	}

	public void setHabilitarVisualizacaoIdCadastro(
			boolean habilitarVisualizacaoIdCadastro) {
		this.habilitarVisualizacaoIdCadastro = habilitarVisualizacaoIdCadastro;
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
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
