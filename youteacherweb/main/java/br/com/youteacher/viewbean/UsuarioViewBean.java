package br.com.youteacher.viewbean;



import java.util.List;

import br.com.youteacherweb.entidades.Usuario;

public class UsuarioViewBean {
	
private Usuario usuario;
private List<Usuario> usuarios;
private Usuario usuarioLogado;

//pagina
private boolean habilitarVisualizacaoIdCadastro;
private String tituloVideo;
private String urlVideo;
	
	public UsuarioViewBean(){
		
		initValues();

	}
	
	private void initValues(){
		usuario = new Usuario();
		usuarioLogado = new Usuario();
		
		//pagina
		habilitarVisualizacaoIdCadastro = false;
		tituloVideo = "YouTeacher";
		urlVideo = "https://www.youtube.com/v/1OH3ISC-vfk";
	}
	

	
	public String getUrlVideo() {
		return urlVideo;
	}

	public void setUrlVideo(String urlVideo) {
		this.urlVideo = urlVideo;
	}

	public String getTituloVideo() {
		return tituloVideo;
	}

	public void setTituloVideo(String tituloVideo) {
		this.tituloVideo = tituloVideo;
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
