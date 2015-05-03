package br.com.youteacher.viewbean;

import java.util.ArrayList;
import java.util.List;

import br.com.youteacher.banco.dao.VideoDAO;
import br.com.youteacherweb.entidades.Formulario;
import br.com.youteacherweb.entidades.Usuario;
import br.com.youteacherweb.entidades.Video;

public class UsuarioViewBean {

	private Usuario usuario;
	private List<Usuario> usuarios;
	private Usuario usuarioLogado;
	private Video video;
	private Formulario formulario;
	private List<Video> videos;
	private List<Video> videosRecente;

	// pagina
	private boolean habilitarVisualizacaoIdCadastro;
	private String tituloVideo;
	private String urlVideo;
	private String nomeUsuario;

	// pagina cadastro video
	private boolean habilitarVisualizacaoQuestionario;

	public UsuarioViewBean() {

		initValues();

	}

	private void initValues() {
		usuario = new Usuario();
		usuarioLogado = new Usuario();
		video = new Video();

		// pagina
		habilitarVisualizacaoIdCadastro = false;
		tituloVideo = "YouTeacher";
		urlVideo = "https://www.youtube.com/v/1OH3ISC-vfk";
		habilitarVisualizacaoQuestionario = false;
	}

	public boolean isHabilitarVisualizacaoQuestionario() {
		return habilitarVisualizacaoQuestionario;
	}

	public void setHabilitarVisualizacaoQuestionario(
			boolean habilitarVisualizacaoQuestionario) {
		this.habilitarVisualizacaoQuestionario = habilitarVisualizacaoQuestionario;
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

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public Video getVideo() {
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}

	public Formulario getFormulario() {
		return formulario;
	}

	public void setFormulario(Formulario formulario) {
		this.formulario = formulario;
	}

	public List<Video> getVideos() {
		return videos;
	}

	public void setVideos(List<Video> videos) {
		this.videos = videos;
	}

	public List<Video> getVideosRecente() {
		return videosRecente;
	}

	public void setVideosRecente(List<Video> videosRecente) {
		this.videosRecente = videosRecente;
	}

}
