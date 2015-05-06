package br.com.youteacher.manegedbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtextarea.InputTextarea;

import br.com.youteacher.banco.dao.VideoDAO;
import br.com.youteacher.banco.dao.UsuarioDAO;
import br.com.youteacher.viewbean.UsuarioViewBean;
import br.com.youteacherweb.entidades.*;

@ManagedBean
public class UsuarioMB implements Serializable {
	private static final long serialVersionUID = 1L;

	private UsuarioDAO dao;
	private VideoDAO videoDAO;

	private UsuarioViewBean viewBean;

	public UsuarioMB() {
	}

	private List<Video> videos;

	// Formulário
	private boolean habilitarFormulario;
	private boolean habilitarVisualizacaoQuestionario;

	// campos dinâmicos
	private HtmlPanelGrid grid;
	private int componenteCount = 1;
	List<InputTextarea> iptsPerguntas = new ArrayList<InputTextarea>();

	// video
	private Video videoSelecionado;

	@PostConstruct
	private void initPage() {

		dao = new UsuarioDAO();
		viewBean = new UsuarioViewBean();
		videoDAO = new VideoDAO();
		videoSelecionado = new Video();
		limparGrid();

	}

	// SETAR VIDEO SELECIONADO
	public void setarVideoSelecionado(Video video) {
		viewBean.setVideoSelecionado(video);
	}

	// ADICIONAR VIDEO
	public void inserirVideo() {
		try {
			String novoUrl = viewBean.getVideo().getUrl()
					.replace("watch?v=", "v/");
			viewBean.getVideo().setUrl(novoUrl);
			viewBean.getVideo().setUsuario(viewBean.getUsuarioLogado());
			videoDAO.inserir(viewBean.getVideo());

			novoVideo();

			mostraMenssagem("SUCESSO", "Video inserido com sucesso!");

		} catch (Exception e) {
			mostraMenssagem("ERRO!!", "Erro ao inserir video!");
			System.out.println("Erro video:" + e);
		}

	}

	// SELECIONADO
	public void selecionado() {
		System.out.println("SELECIONADO:" + viewBean.getVideoSelecionado());

	}

	// INSERIR UM NOVO ALUNO
	public void inserir() {

		try {

			dao.inserirUsuario(viewBean.getUsuario());
			mostraMenssagem("SUCESSO", "Usuario inserido com sucesso.");

			novoUsuario();

		} catch (Exception e) {

			mostraMenssagem("ERRO", "Houve um erro ao tentar inserir usuario.");
			System.out.println("Erro " + e);
		}

	}

	// DELETAR UM NOVO ALUNO
	public void deletar() {

		try {

			dao.deletarUsuario(viewBean.getUsuario());
			mostraMenssagem("SUCESSO", "Usuario deletado com sucesso.");

		} catch (Exception e) {

			mostraMenssagem("ERRO", "Houve um erro ao tentar deletar usuario.");
			System.out.println("Erro " + e);
		}

	}

	// GERAR CAMPOS DINAMICOS DO FORMULARIO
	public void gerarCampo() {

		int id = ++componenteCount;
		System.out.println("Valor id " + id);

		// INPUT PARA A PERGUNTA
		HtmlOutputText ol = new HtmlOutputText();
		ol.setId("oplPergunta" + id);
		ol.setValue("Pergunta " + id);
		grid.getChildren().add(ol);

		// INPUT PARA O CAMPO EM BRANCO
		// HtmlOutputText ol2 = new HtmlOutputText();
		// ol.setId("oplPerguntaBranco" + componenteCount);
		// ol.setValue("Valor Nove");
		// grid.getChildren().add(ol2);

		// INPUT TEXT AREA PARA A PERGUNTA
		// InputTextarea ipa = new InputTextarea();
		// ipa.setId("iptPergunta" + componenteCount);
		// ipa.setStyle("min-width: 100px;");
		// ipa.setValue("Valor");
		// ipa.setMaxlength(50);
		// ipa.setCols(40);
		// ipa.setRows(2);
		// grid.getChildren().add(ipa);

		// BOTÃO PARA ADICIONAR ALTERNATIVAS
		// CommandButton bt = new CommandButton();
		// bt.setId("bntAddAlternativa" + componenteCount);
		// bt.setValue(" ");
		// bt.setStyle("width: 35px;height:35px ; margin-top:5px; background: url(../resources/imagens/add-icon.png) no-repeat; border-color: white; font:18px 'Ruda',sans-serif;");
		// bt.setUpdate("@form");
		// bt.setAjax(false);
		// grid.getChildren().add(bt);

		// ADICIONAR OS INPUT AREAS
		// iptsPerguntas.add(ipa);
	}

	// RESETAR O GRID PARA UM NOVO CADASTRO DE FORMULÁRIO
	public void limparGrid() {
		iptsPerguntas = new ArrayList<InputTextarea>();

		if (grid != null) {
			grid.getChildren().clear();
		}
	}

	// MOSTRAR MENSSAGEM DE NOTIFICAÇÃO
	public void mostraMenssagem(String titulo, String menssagem) {

		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(titulo, menssagem));

	}

	// NOVO USUARIO
	public void novoUsuario() {
		viewBean.setUsuario(new Usuario());
	}

	// NOVO VIDEO
	public void novoVideo() {
		viewBean.setVideo(new Video());
	}

	// ATUALIZAR LISTA VIDEOS
	public void atualizarListaVideo() {
		viewBean.setVideos(videoDAO.listarTodos());
		addRecente(viewBean.getVideos());
	}

	// ADICIONADOS RECENTEMENTE
	public void addRecente(List<Video> videos) {
		if (videos.size() > 3) {
			List<Video> recentes = new ArrayList<Video>();
			int tamanho = videos.size();
			for (int i = (tamanho - 3); i < tamanho; i++) {
				recentes.add(videos.get(i));
			}
			viewBean.setVideosRecente(recentes);
		}

	}

	// EXECUTAR O LOGIN DO USUARIO
	public String loginUsuario() {

		listarCondicao();

		if (!viewBean.getUsuarios().isEmpty()) {

			viewBean.setUsuarioLogado(viewBean.getUsuarios().get(0));
			viewBean.setNomeUsuario(viewBean.getUsuarios().get(0).getNome());
			atualizarListaVideo();

			FacesContext fc = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession) fc.getExternalContext()
					.getSession(false);
			session.setAttribute("objLogin", "logado");

			return "logado";

		} else {

			mostraMenssagem("LOGIN", "Senha ou login incorretos!!!");
			return "inicial";

		}

	}

	// EXECUTAR LOGOFF DO USUARIO
	public String logOff() {

		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(
				false);
		session.removeAttribute("objLogin");

		return "sair";
	}

	// LISTAR CONDICÃO LOGAR USUARIO
	public void listarCondicao() {

		viewBean.setUsuarios(dao.listarCondicaoUsuario(" email = '"
				+ viewBean.getUsuario().getEmail() + "' and senha = '"
				+ viewBean.getUsuario().getSenha() + "'"));

	}

	public Video getVideoSelecionado() {
		return videoSelecionado;
	}

	public void setVideoSelecionado(Video videoSelecionado) {
		this.videoSelecionado = videoSelecionado;
	}

	public int getComponenteCount() {
		return componenteCount;
	}

	public void setComponenteCount(int componenteCount) {
		this.componenteCount = componenteCount;
	}

	public HtmlPanelGrid getGrid() {
		if (grid == null) {
			grid = new HtmlPanelGrid();
		}
		return grid;
	}

	public void setGrid(HtmlPanelGrid grid) {
		this.grid = grid;
	}

	public boolean isHabilitarVisualizacaoQuestionario() {
		return habilitarVisualizacaoQuestionario;
	}

	public void setHabilitarVisualizacaoQuestionario(
			boolean habilitarVisualizacaoQuestionario) {
		this.habilitarVisualizacaoQuestionario = habilitarVisualizacaoQuestionario;
	}

	public boolean isHabilitarFormulario() {
		return habilitarFormulario;
	}

	public void setHabilitarFormulario(boolean habilitarFormulario) {
		this.habilitarFormulario = habilitarFormulario;
	}

	public UsuarioViewBean getViewBean() {
		return viewBean;
	}

	public void setViewBean(UsuarioViewBean viewBean) {
		this.viewBean = viewBean;
	}

}
