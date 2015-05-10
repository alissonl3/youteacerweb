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

import com.mysql.jdbc.StringUtils;

import br.com.youteacher.banco.dao.FormularioDAO;
import br.com.youteacher.banco.dao.VideoDAO;
import br.com.youteacher.banco.dao.UsuarioDAO;
import br.com.youteacher.viewbean.UsuarioViewBean;
import br.com.youteacherweb.entidades.*;

@ManagedBean
public class UsuarioMB implements Serializable {
	private static final long serialVersionUID = 1L;

	private UsuarioDAO dao;
	private VideoDAO videoDAO;
	private FormularioDAO formularioDAO;

	private UsuarioViewBean viewBean;

	public UsuarioMB() {
	}

	private List<Video> videos;

	// Formulário
	private boolean habilitarFormulario;
	private boolean habilitarVisualizacaoQuestionario;

	// Perguntas
	private boolean habilitarVisualizacaoPergunta1;
	private boolean habilitarVisualizacaoPergunta2;
	private boolean habilitarVisualizacaoPergunta3;
	private boolean habilitarVisualizacaoPergunta4;
	private boolean habilitarVisualizacaoPergunta5;
	private boolean habilitarVisualizacaoPergunta6;
	private boolean habilitarVisualizacaoPergunta7;
	private boolean habilitarVisualizacaoPergunta8;
	private boolean habilitarVisualizacaoPergunta9;
	private boolean habilitarVisualizacaoPergunta10;

	// video
	private Video videoSelecionado;

	@PostConstruct
	private void initPage() {

		dao = new UsuarioDAO();
		formularioDAO = new FormularioDAO();

		viewBean = new UsuarioViewBean();
		videoDAO = new VideoDAO();
		videoSelecionado = new Video();

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



	// ADICIONAR FORMULÁRIO
	public void inserirFormulario() {
		
		int count = 0;
		
		if(viewBean.getFormulario1() != null){
			formularioDAO.inserir(viewBean.getFormulario1());
			count++;
		}
		
		if(habilitarVisualizacaoPergunta2 == true){
			if(viewBean.getFormulario2() != null){
				formularioDAO.inserir(viewBean.getFormulario2());
				count++;
			}
		}
		
		if(habilitarVisualizacaoPergunta3 == true){
			if(viewBean.getFormulario3() != null){
				formularioDAO.inserir(viewBean.getFormulario3());
				count++;
			}
		}
		
		if(habilitarVisualizacaoPergunta4 == true){
			if(viewBean.getFormulario4() != null){
				formularioDAO.inserir(viewBean.getFormulario4());
				count++;
			}
		}
		
		if(habilitarVisualizacaoPergunta5 == true){
			if(viewBean.getFormulario5() != null){
				formularioDAO.inserir(viewBean.getFormulario6());
				count++;
			}
		}
		
		if(habilitarVisualizacaoPergunta6 == true){
			if(viewBean.getFormulario6() != null){
				formularioDAO.inserir(viewBean.getFormulario6());
				count++;
			}
		}
		
		if(habilitarVisualizacaoPergunta7 == true){
			if(viewBean.getFormulario7() != null){
				formularioDAO.inserir(viewBean.getFormulario7());
				count++;
			}
		}
		
		if(habilitarVisualizacaoPergunta8 == true){
			if(viewBean.getFormulario8() != null){
				formularioDAO.inserir(viewBean.getFormulario8());
				count++;
			}
		}
		
		if(habilitarVisualizacaoPergunta9 == true){
			if(viewBean.getFormulario9() != null){
				formularioDAO.inserir(viewBean.getFormulario9());
				count++;
			}
		}
		
		if(habilitarVisualizacaoPergunta10 == true){
			if(viewBean.getFormulario10() != null){
				formularioDAO.inserir(viewBean.getFormulario10());
				count++;
			}
		}
		
		if(count > 0){
			
			mostraMenssagem("SUCESSO", "Formulário adicionado com sucesso!");
			
		}

		
	}

	// GERAR NOVOS FORMULARIOS
	public void novoFormulario1() {	
		
			viewBean.setFormulario1(new Formulario());
	}
	public void novoFormulario2() {		
		if (habilitarVisualizacaoPergunta2 == true) {
			 
			viewBean.setFormulario2(new Formulario());

		}
	}
	public void novoFormulario3() {		
		if (habilitarVisualizacaoPergunta3 == true) {
			 
			viewBean.setFormulario3(new Formulario());

		}
	}
	public void novoFormulario4() {		
		if (habilitarVisualizacaoPergunta4 == true) {
			 
			viewBean.setFormulario4(new Formulario());

		}
	}
	public void novoFormulario5() {		
		if (habilitarVisualizacaoPergunta5 == true) {
			 
			viewBean.setFormulario5(new Formulario());

		}
	}
	public void novoFormulario6() {		
		if (habilitarVisualizacaoPergunta6 == true) {
			 
			viewBean.setFormulario6(new Formulario());

		}
	}
	public void novoFormulario7() {		
		if (habilitarVisualizacaoPergunta7 == true) {
			 
			viewBean.setFormulario7(new Formulario());

		}
	}
	public void novoFormulario8() {		
		if (habilitarVisualizacaoPergunta8 == true) {
			 
			viewBean.setFormulario8(new Formulario());

		}
	}
	public void novoFormulario9() {		
		if (habilitarVisualizacaoPergunta9 == true) {
			 
			viewBean.setFormulario9(new Formulario());

		}
	}
	public void novoFormulario10() {		
		if (habilitarVisualizacaoPergunta10 == true) {
			 
			viewBean.setFormulario10(new Formulario());

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

	public boolean isHabilitarVisualizacaoPergunta1() {
		return habilitarVisualizacaoPergunta1;
	}

	public void setHabilitarVisualizacaoPergunta1(
			boolean habilitarVisualizacaoPergunta1) {
		this.habilitarVisualizacaoPergunta1 = habilitarVisualizacaoPergunta1;
	}

	public boolean isHabilitarVisualizacaoPergunta2() {
		return habilitarVisualizacaoPergunta2;
	}

	public void setHabilitarVisualizacaoPergunta2(
			boolean habilitarVisualizacaoPergunta2) {
		this.habilitarVisualizacaoPergunta2 = habilitarVisualizacaoPergunta2;
	}

	public boolean isHabilitarVisualizacaoPergunta3() {
		return habilitarVisualizacaoPergunta3;
	}

	public void setHabilitarVisualizacaoPergunta3(
			boolean habilitarVisualizacaoPergunta3) {
		this.habilitarVisualizacaoPergunta3 = habilitarVisualizacaoPergunta3;
	}

	public boolean isHabilitarVisualizacaoPergunta4() {
		return habilitarVisualizacaoPergunta4;
	}

	public void setHabilitarVisualizacaoPergunta4(
			boolean habilitarVisualizacaoPergunta4) {
		this.habilitarVisualizacaoPergunta4 = habilitarVisualizacaoPergunta4;
	}

	public boolean isHabilitarVisualizacaoPergunta5() {
		return habilitarVisualizacaoPergunta5;
	}

	public void setHabilitarVisualizacaoPergunta5(
			boolean habilitarVisualizacaoPergunta5) {
		this.habilitarVisualizacaoPergunta5 = habilitarVisualizacaoPergunta5;
	}

	public boolean isHabilitarVisualizacaoPergunta6() {
		return habilitarVisualizacaoPergunta6;
	}

	public void setHabilitarVisualizacaoPergunta6(
			boolean habilitarVisualizacaoPergunta6) {
		this.habilitarVisualizacaoPergunta6 = habilitarVisualizacaoPergunta6;
	}

	public boolean isHabilitarVisualizacaoPergunta7() {
		return habilitarVisualizacaoPergunta7;
	}

	public void setHabilitarVisualizacaoPergunta7(
			boolean habilitarVisualizacaoPergunta7) {
		this.habilitarVisualizacaoPergunta7 = habilitarVisualizacaoPergunta7;
	}

	public boolean isHabilitarVisualizacaoPergunta8() {
		return habilitarVisualizacaoPergunta8;
	}

	public void setHabilitarVisualizacaoPergunta8(
			boolean habilitarVisualizacaoPergunta8) {
		this.habilitarVisualizacaoPergunta8 = habilitarVisualizacaoPergunta8;
	}

	public boolean isHabilitarVisualizacaoPergunta9() {
		return habilitarVisualizacaoPergunta9;
	}

	public void setHabilitarVisualizacaoPergunta9(
			boolean habilitarVisualizacaoPergunta9) {
		this.habilitarVisualizacaoPergunta9 = habilitarVisualizacaoPergunta9;
	}

	public boolean isHabilitarVisualizacaoPergunta10() {
		return habilitarVisualizacaoPergunta10;
	}

	public void setHabilitarVisualizacaoPergunta10(
			boolean habilitarVisualizacaoPergunta10) {
		this.habilitarVisualizacaoPergunta10 = habilitarVisualizacaoPergunta10;
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
