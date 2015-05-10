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
	private List<Video> videos;
	private List<Video> videosRecente;

	// pagina
	private boolean habilitarVisualizacaoIdCadastro;
	private String tituloVideo;
	private String urlVideo;
	private String nomeUsuario;

	// pagina cadastro video
	private boolean habilitarVisualizacaoQuestionario;
	
	//visualização do video
	private Video videoSelecionado;
	
	//FORMULÁRIOS
	private Formulario formulario1;
	private Formulario formulario2;
	private Formulario formulario3;
	private Formulario formulario4;
	private Formulario formulario5;
	private Formulario formulario6;
	private Formulario formulario7;
	private Formulario formulario8;
	private Formulario formulario9;
	private Formulario formulario10;
	

	public UsuarioViewBean() {

		initValues();

	}

	private void initValues() {
		usuario = new Usuario();
		usuarioLogado = new Usuario();
		video = new Video();
		videoSelecionado = new Video();
		
		//formulários
		formulario1 = new Formulario();
		formulario2 = new Formulario();
		formulario3 = new Formulario();
		formulario4 = new Formulario();
		formulario5 = new Formulario();
		formulario6 = new Formulario();
		formulario7 = new Formulario();
		formulario8 = new Formulario();
		formulario9 = new Formulario();
		formulario10 = new Formulario();
		

		// pagina
		habilitarVisualizacaoIdCadastro = false;
		tituloVideo = "YouTeacher";
		urlVideo = "https://www.youtube.com/v/1OH3ISC-vfk";
		habilitarVisualizacaoQuestionario = false;
	}

	
	
	public Video getVideoSelecionado() {
		return videoSelecionado;
	}

	public void setVideoSelecionado(Video videoSelecionado) {
		this.videoSelecionado = videoSelecionado;
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

	

	public Formulario getFormulario1() {
		return formulario1;
	}

	public void setFormulario1(Formulario formulario1) {
		this.formulario1 = formulario1;
	}

	public Formulario getFormulario2() {
		return formulario2;
	}

	public void setFormulario2(Formulario formulario2) {
		this.formulario2 = formulario2;
	}

	public Formulario getFormulario3() {
		return formulario3;
	}

	public void setFormulario3(Formulario formulario3) {
		this.formulario3 = formulario3;
	}

	public Formulario getFormulario4() {
		return formulario4;
	}

	public void setFormulario4(Formulario formulario4) {
		this.formulario4 = formulario4;
	}

	public Formulario getFormulario5() {
		return formulario5;
	}

	public void setFormulario5(Formulario formulario5) {
		this.formulario5 = formulario5;
	}

	public Formulario getFormulario6() {
		return formulario6;
	}

	public void setFormulario6(Formulario formulario6) {
		this.formulario6 = formulario6;
	}

	public Formulario getFormulario7() {
		return formulario7;
	}

	public void setFormulario7(Formulario formulario7) {
		this.formulario7 = formulario7;
	}

	public Formulario getFormulario8() {
		return formulario8;
	}

	public void setFormulario8(Formulario formulario8) {
		this.formulario8 = formulario8;
	}

	public Formulario getFormulario9() {
		return formulario9;
	}

	public void setFormulario9(Formulario formulario9) {
		this.formulario9 = formulario9;
	}

	public Formulario getFormulario10() {
		return formulario10;
	}

	public void setFormulario10(Formulario formulario10) {
		this.formulario10 = formulario10;
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
