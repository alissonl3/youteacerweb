package br.com.youteacher.viewbean;

import java.util.ArrayList;
import java.util.Date;
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
	
	//tabela data model
	private Usuario usuarioSelecionadoTabela;
	private Video videoSelecionadoTabela;
	
	
	//Dados de alteracao do perfil
	private String senhaAlterada;
	private String nomeAlterado;
	private Date dataAlterada;
	private String senhaAtual;
	
	
	
	//envio de email
	private String emailEnviar;
	


	// pagina
	private boolean habilitarVisualizacaoIdCadastro;
	private String tituloVideo;
	private String urlVideo;
	private String nomeUsuario;

	// pagina cadastro video
	private boolean habilitarVisualizacaoQuestionario;

	// visualização do video
	private Video videoSelecionado;

	// FORMULÁRIOS
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
	
	//RESPOSTA USUARIO
	private String resposta1;
	private String resposta2;
	private String resposta3;
	private String resposta4;
	private String resposta5;
	private String resposta6;
	private String resposta7;
	private String resposta8;
	private String resposta9;
	private String resposta10;
	

	
	public UsuarioViewBean() {

		initValues();

	}

	public void initValues() {
		usuario = new Usuario();
		usuarioLogado = new Usuario();
		video = new Video();
		videoSelecionado = new Video();
		senhaAlterada="";
		nomeAlterado = "";
		dataAlterada = new Date();
		
		videoSelecionadoTabela = new Video();

		// formulários
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
		
		//Respostas
		resposta1 = new String();
		resposta2 = new String();
		resposta3 = new String();
		resposta4 = new String();
		resposta5 = new String();
		resposta6 = new String();
		resposta7 = new String();
		resposta8 = new String();
		resposta9 = new String();
		resposta10 = new String();
		

		// pagina
		habilitarVisualizacaoIdCadastro = false;
		tituloVideo = "YouTeacher";
		urlVideo = "https://www.youtube.com/v/aKDugIvMw5M";
		habilitarVisualizacaoQuestionario = false;
	}

	

	public Video getVideoSelecionadoTabela() {
		return videoSelecionadoTabela;
	}

	public void setVideoSelecionadoTabela(Video videoSelecionadoTabela) {
		this.videoSelecionadoTabela = videoSelecionadoTabela;
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

	public String getEmailEnviar() {
		return emailEnviar;
	}

	public void setEmailEnviar(String emailEnviar) {
		this.emailEnviar = emailEnviar;
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

	public Usuario getUsuarioSelecionadoTabela() {
		return usuarioSelecionadoTabela;
	}

	public void setUsuarioSelecionadoTabela(Usuario usuarioSelecionadoTabela) {
		System.out.println("ID:"+usuarioSelecionadoTabela.getId());
		this.usuarioSelecionadoTabela = usuarioSelecionadoTabela;
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
	public String getResposta1() {
		return resposta1;
	}

	public void setResposta1(String resposta1) {
		this.resposta1 = resposta1;
	}

	public String getResposta2() {
		return resposta2;
	}

	public void setResposta2(String resposta2) {
		this.resposta2 = resposta2;
	}

	public String getResposta3() {
		return resposta3;
	}

	public void setResposta3(String resposta3) {
		this.resposta3 = resposta3;
	}

	public String getResposta4() {
		return resposta4;
	}

	public void setResposta4(String resposta4) {
		this.resposta4 = resposta4;
	}

	public String getResposta5() {
		return resposta5;
	}

	public void setResposta5(String resposta5) {
		this.resposta5 = resposta5;
	}

	public String getResposta6() {
		return resposta6;
	}

	public void setResposta6(String resposta6) {
		this.resposta6 = resposta6;
	}

	public String getResposta7() {
		return resposta7;
	}

	public void setResposta7(String resposta7) {
		this.resposta7 = resposta7;
	}

	public String getResposta8() {
		return resposta8;
	}

	public void setResposta8(String resposta8) {
		this.resposta8 = resposta8;
	}

	public String getResposta9() {
		return resposta9;
	}

	public void setResposta9(String resposta9) {
		this.resposta9 = resposta9;
	}

	public String getResposta10() {
		return resposta10;
	}

	public void setResposta10(String resposta10) {
		this.resposta10 = resposta10;
	}
	public String getSenhaAlterada() {
		return senhaAlterada;
	}

	public void setSenhaAlterada(String senhaAlterada) {
		this.senhaAlterada = senhaAlterada;
	}

	public String getNomeAlterado() {
		return nomeAlterado;
	}

	public void setNomeAlterado(String nomeAlterado) {
		this.nomeAlterado = nomeAlterado;
	}

	public Date getDataAlterada() {
		return dataAlterada;
	}

	public void setDataAlterada(Date dataAlterada) {
		this.dataAlterada = dataAlterada;
	}
	public String getSenhaAtual() {
		return senhaAtual;
	}

	public void setSenhaAtual(String senhaAtual) {
		this.senhaAtual = senhaAtual;
	}


}
