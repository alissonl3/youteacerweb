package br.com.youteacher.manegedbean;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;

import br.com.youteacher.banco.dao.FormularioDAO;
import br.com.youteacher.banco.dao.SenhaEncripty;
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
	private List<Video> videos;
	
	private List<Usuario> usuarioDataModel;
	private List<Video> videoDataModel;

	public UsuarioMB() {
	}
	
	//menu bar
	private boolean habilitarVisualizacaoBtnPerfil;

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

	// opções video
	private boolean habilitarVisualizacaoAdicao;
	private boolean hbilitarVisualizacaoEdicao;
	private boolean habilitarVisualizacaoPraticar;

	// video
	private Video videoSelecionado;
	private int listaTamanho;

	private Video videoSelecionadoFormulario;
	
	//popUp rendimento
	private String imagem;
	private Double rendimento;
	private int tamanhoFormulario;
	
	//pagina gerenciar
	private boolean habilitarVisualizacaoDadosUsuario;

	@PostConstruct
	private void initPage() {

		dao = new UsuarioDAO();
		formularioDAO = new FormularioDAO();
		
		usuarioDataModel = new ArrayList<Usuario>();
		videoDataModel = new ArrayList<Video>();

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
			
			List<Video> videoCondicao = new ArrayList<Video>();
			List<Video> videosMesmoURL = new ArrayList<Video>();
		
		try {
			String novoUrl = viewBean.getVideo().getUrl()
					.replace("watch?v=", "v/");
			
			videoCondicao = videoDAO.listarCondicaoVideo("titulo = '" + viewBean.getVideo().getTitulo() + "'");
			videosMesmoURL = videoDAO.listarCondicaoVideo("url = '"+novoUrl+"'");
			//VERIFICAR SE EXISTE UM VIDEO COM O MESMO TITULO
			if(videoCondicao.isEmpty() && videosMesmoURL.isEmpty()){
			viewBean.getVideo().setUrl(novoUrl);
			viewBean.getVideo().setUsuario(viewBean.getUsuarioLogado());
			videoDAO.inserir(viewBean.getVideo());
			atualizarListaVideo();

			// passar video inseido para o formulario
			videoSelecionadoFormulario = viewBean.getVideo();

			novoVideo();
			
			mostraMenssagem("SUCESSO", "Video inserido com sucesso!");
			
			RequestContext.getCurrentInstance().execute("PF('dlgCriacaoForm').show();");
			
			}else{
				
			mostraMenssagem("ATENÇÃO", "Já existe um video com esse titulo ou com esse url!.");
			
			}

		} catch (Exception e) {
			
			mostraMenssagem("ERRO!!", "Erro ao inserir video!");
			System.out.println("Erro video:" + e);
		}

	}
	
	//DIRECIONAR PARA A PAGINA DE ADDFORMULARIO
	public String toAddFormulario(){
		
		
		//controle da vizualização
		habilitarVisualizacaoPergunta2 = false;
		habilitarVisualizacaoPergunta3 = false;
		habilitarVisualizacaoPergunta4 = false;
		habilitarVisualizacaoPergunta5 = false;
		habilitarVisualizacaoPergunta6 = false;
		habilitarVisualizacaoPergunta7 = false;
		habilitarVisualizacaoPergunta8 = false;
		habilitarVisualizacaoPergunta9 = false;
		habilitarVisualizacaoPergunta10 = false;
		
		
		//limpar todos os formularios
		limparFormulario1();
		limparFormulario2();
		limparFormulario3();
		limparFormulario4();
		limparFormulario5();
		limparFormulario6();
		limparFormulario7();
		limparFormulario8();
		limparFormulario9();
		limparFormulario10();
		
		
		return "formulario";
	}

	// ADICIONAR FORMULÁRIO
	public String inserirFormulario() {

		int count = 0;
		String retorno = null;

		if (viewBean.getFormulario1() != null) {

			
			viewBean.getFormulario1().setVideo(videoSelecionadoFormulario);
			
			formularioDAO.inserir(viewBean.getFormulario1());
			count++;
			
			limparFormulario1();
		}

		if (habilitarVisualizacaoPergunta2 == true) {
			if (viewBean.getFormulario2() != null) {

				viewBean.getFormulario2().setVideo(videoSelecionadoFormulario);
				
				formularioDAO.inserir(viewBean.getFormulario2());
				count++;
				
				limparFormulario2();
			}
		}

		if (habilitarVisualizacaoPergunta3 == true) {
			if (viewBean.getFormulario3() != null) {

				viewBean.getFormulario3().setVideo(videoSelecionadoFormulario);
				formularioDAO.inserir(viewBean.getFormulario3());
				count++;
				
				limparFormulario3();
			}
		}

		if (habilitarVisualizacaoPergunta4 == true) {
			if (viewBean.getFormulario4() != null) {

				viewBean.getFormulario4().setVideo(videoSelecionadoFormulario);
				formularioDAO.inserir(viewBean.getFormulario4());
				count++;
				
				limparFormulario4();
			}
		}

		if (habilitarVisualizacaoPergunta5 == true) {
			if (viewBean.getFormulario5() != null) {

				viewBean.getFormulario5().setVideo(videoSelecionadoFormulario);
				formularioDAO.inserir(viewBean.getFormulario6());
				count++;
				
				limparFormulario5();
			}
		}

		if (habilitarVisualizacaoPergunta6 == true) {
			if (viewBean.getFormulario6() != null) {

				viewBean.getFormulario6().setVideo(videoSelecionadoFormulario);
				formularioDAO.inserir(viewBean.getFormulario6());
				count++;
				
				limparFormulario6();
			}
		}

		if (habilitarVisualizacaoPergunta7 == true) {
			if (viewBean.getFormulario7() != null) {

				viewBean.getFormulario7().setVideo(videoSelecionadoFormulario);
				formularioDAO.inserir(viewBean.getFormulario7());
				count++;
				
				limparFormulario7();
			}
		}

		if (habilitarVisualizacaoPergunta8 == true) {
			if (viewBean.getFormulario8() != null) {

				viewBean.getFormulario8().setVideo(videoSelecionadoFormulario);
				formularioDAO.inserir(viewBean.getFormulario8());
				count++;
				
				limparFormulario8();
			}
		}

		if (habilitarVisualizacaoPergunta9 == true) {
			if (viewBean.getFormulario9() != null) {

				viewBean.getFormulario9().setVideo(videoSelecionadoFormulario);
				formularioDAO.inserir(viewBean.getFormulario9());
				count++;
				
				limparFormulario9();
			}
		}

		if (habilitarVisualizacaoPergunta10 == true) {
			if (viewBean.getFormulario10() != null) {

				viewBean.getFormulario10().setVideo(videoSelecionadoFormulario);
				formularioDAO.inserir(viewBean.getFormulario10());
				count++;
				
				limparFormulario10();
			}
		}

		if (count > 0) {

			mostraMenssagem("SUCESSO", "Formulário adicionado com sucesso!");
			retorno = "inicio";
		}
		else
		{
			mostraMenssagem("ATENÇÃO", "Não foi adicionado nenhum formulário!");
			retorno = "inicio";
		}	
		
		return retorno;

	}
	
	//TONAR UM USUARIO EM ADMINISTRADOR
	public void tornarAdministrador(){
		
		if(viewBean.getUsuarioSelecionadoTabela() != null){
			System.out.println("Entrou em tornar adm");
			viewBean.getUsuarioSelecionadoTabela().setAdm("sim");
			
			try{
			dao.alterar(viewBean.getUsuarioSelecionadoTabela());
			mostraMenssagem("SUCESSO", "O usuario se tornou um administrador");
			
			RequestContext.getCurrentInstance().update("frmAddAdm");
			RequestContext.getCurrentInstance().update("frmGerenciar");
			
			}catch(Exception e){
				System.out.println("Erro tornar adm: " + e);
				mostraMenssagem("ERRO", "Houve um erro");
				
			}
			
		}
		
		
	}
	
	
	
	
	
	//Metodo para preencher os campos quando o usuario clicar em "VAMOS PRATICAR"
	public void mostrarFormulario(){
		List<Formulario> formularios = new ArrayList<Formulario>();
		
		try{
			
			
		 System.out.println("Entrou no praticar:");
		 formularios = formularioDAO.pesquisarPorVideo(viewBean.getVideoSelecionado().getId());
		 tamanhoFormulario = formularios.size();
		 System.out.println("Tamanho da lista de formulario:"+formularios.size());
		
		if(tamanhoFormulario>=1 ){
			viewBean.setFormulario1(formularios.get(0));
			setHabilitarVisualizacaoPergunta1(true);
		}
		if(tamanhoFormulario>=2 ){
			viewBean.setFormulario2(formularios.get(1));
			setHabilitarVisualizacaoPergunta2(true);
				}
		if(tamanhoFormulario>=3 ){
			viewBean.setFormulario3(formularios.get(2));
			setHabilitarVisualizacaoPergunta3(true);
		}
		if(tamanhoFormulario>=4 ){
			viewBean.setFormulario4(formularios.get(3));
			setHabilitarVisualizacaoPergunta4(true);
		}
		if(tamanhoFormulario>=5 ){
			viewBean.setFormulario5(formularios.get(4));
			setHabilitarVisualizacaoPergunta5(true);
		}
		if(tamanhoFormulario>=6 ){
			viewBean.setFormulario6(formularios.get(5));
			setHabilitarVisualizacaoPergunta6(true);
		}
		if(tamanhoFormulario>=7 ){
			viewBean.setFormulario7(formularios.get(6));
			setHabilitarVisualizacaoPergunta7(true);
		}
		if(tamanhoFormulario>=8 ){
			viewBean.setFormulario8(formularios.get(7));
			setHabilitarVisualizacaoPergunta8(true);
		}
		if(tamanhoFormulario>=9 ){
			viewBean.setFormulario9(formularios.get(8));
			setHabilitarVisualizacaoPergunta9(true);
		}
		if(tamanhoFormulario>=10 ){
			viewBean.setFormulario10(formularios.get(9));
			setHabilitarVisualizacaoPergunta10(true);
		}
		
		RequestContext.getCurrentInstance().update(":frmPraticar");
		
		
		}
		catch(Exception e){
			mostraMenssagem("ERRO!", "Não existe formulário para o Vídeo");
		}
		
	}
	
	//REDIRECIONAR PARA ADDADM
	public String irParaAddAdm(){
		
		usuarioDataModel = dao.listarTodos();
		
		return "adm";
	}
	
	//atulizar dialog
	public void atualizarDialogAddAdm(){
		
		RequestContext.getCurrentInstance().update("frmDlgTemplate");
		RequestContext.getCurrentInstance().execute("PF('dlgAddAdm').show();");
		
	}
	
	//Metodo para corrigir as respostas do Usuario
	public void corrigir(){
		
		
		try{
			double acertos = 0;
			if(viewBean.getResposta1().equals(viewBean.getFormulario1().getResposta_certa()) ){
				acertos++;
				
			}
			if(viewBean.getResposta2().equals(viewBean.getFormulario2().getResposta_certa())){
				acertos++;
				
			}
			if(viewBean.getResposta3().equals(viewBean.getFormulario3().getResposta_certa())){
				acertos++;
				
			}
			if(viewBean.getResposta4().equals(viewBean.getFormulario4().getResposta_certa())){
				acertos++;
				
			}
			if(viewBean.getResposta5().equals(viewBean.getFormulario5().getResposta_certa())){
				acertos++;
				
			}
			if(viewBean.getResposta6().equals(viewBean.getFormulario6().getResposta_certa())){
				acertos++;
				
			}
			if(viewBean.getResposta7().equals(viewBean.getFormulario7().getResposta_certa())){
				acertos++;
				
			}
			if(viewBean.getResposta8().equals(viewBean.getFormulario8().getResposta_certa())){
				acertos++;
				
			}
			if(viewBean.getResposta9().equals(viewBean.getFormulario9().getResposta_certa())){
				acertos++;
				
			}
			if(viewBean.getResposta10().equals(viewBean.getFormulario10().getResposta_certa())){
				acertos++;
				
			}		
			
			setRendimento((acertos / tamanhoFormulario) * 100);
			
			System.out.println("Total de acertos: "+acertos);
			System.out.println("Seu rendimento foi de: " + getRendimento());
			
			//DEFINIR A IMAGEM DE BOM OU RUIM
			if(rendimento > 50){
				
				setImagem("../resources/imagens/acima50.png");
				
			}else{
				
				setImagem("../resources/imagens/abaixo50.png");
				
			}
			
			
				RequestContext.getCurrentInstance().update(":frmDlgTemplate");
			//RequestContext.getCurrentInstance().execute("PF('dlgRendimento').show();");
			
			
		}catch(Exception e){
			
			System.out.println("Ocorreu um erro: " + e.getMessage());
			
		}
		
	}
	
	// REDIRECIONAR PARA PAGINA DE GERENCIAMENTO
		public String irParaGerenciar() {

			usuarioDataModel = dao.listarTodos();

			return "gerenciar";
		}
	
	
	
		// DELETAR VIDEO
				public String deletarVideo() {
					String url = "";
					try {
						if (viewBean.getVideoSelecionado() != null) {
							System.out.println("Video Selecionado diferente de null");
							List<Formulario> formularioExistente = new ArrayList<Formulario>();

							formularioExistente = formularioDAO.pesquisarPorVideo(viewBean
									.getVideoSelecionado().getId());			

							if (formularioExistente.size() >= 1) {
								System.out
										.println("Existe Formulario vinculado ao videoSelecionado");
								for (int i = 0; i < formularioExistente.size(); i++) {
									formularioDAO.remover(formularioExistente.get(i));
									System.out.println("Removeu Formulario");
								}
							}
							
							videoDAO.remover(viewBean.getVideoSelecionado());

							mostraMenssagem("SUCESSO", "Video deletado com sucesso");
							System.out.println("Removeu video");

							atualizarListaVideo();
							
							RequestContext.getCurrentInstance().update("frmGerenciar");
							

							url = "inicio";

						}
					} catch (Exception e) {
						System.out.println("Erro ao deletar video " + e);
					}

					return url;

					// RequestContext.getCurrentInstance().update("frmDlgTemplate");
					// RequestContext.getCurrentInstance().execute("PF('dlgDeletarVideo').show();");

				}
				
				
				//ATUALIZAR A TABELA DE VIDEO DATA MODEL DE ACORDO COM O USUARIO SELECIONADO 
				public void atualizarTabelaVideoDataModel(){
					
					//RequestContext.getCurrentInstance().update("frmGerenciar");
					RequestContext.getCurrentInstance().update("frmGerenciar:pnlOcultoDados:dtUsuarioVideo");
					
					if(viewBean.getUsuarioSelecionadoTabela() != null){
						System.out.println("Usuario selecionado diferente de null");
					this.videoDataModel = videoDAO.pesquisarPorUsuario(viewBean
							.getUsuarioSelecionadoTabela().getId());
					}
					else{
						System.out.println("Usuario selecionado n diferente de null");
					}
					
					
					RequestContext.getCurrentInstance().update("frmGerenciar:pnlOcultoDados");
					
				}
	

	// VERIFICAR EXISTENCIA DE FORMULÁRIO DO VIDEO
	public void verificarFormularioVideo() {
		
				//controle da vizualização
				habilitarVisualizacaoPergunta1 = false;
				habilitarVisualizacaoPergunta2 = false;
				habilitarVisualizacaoPergunta3 = false;
				habilitarVisualizacaoPergunta4 = false;
				habilitarVisualizacaoPergunta5 = false;
				habilitarVisualizacaoPergunta6 = false;
				habilitarVisualizacaoPergunta7 = false;
				habilitarVisualizacaoPergunta8 = false;
				habilitarVisualizacaoPergunta9 = false;
				habilitarVisualizacaoPergunta10 = false;
				
		

		List<Formulario> formularioExistente = new ArrayList<Formulario>();
		List<Video> videoRelacionados = new ArrayList<Video>();

		boolean verificarRelacaoVideoFormulario = false;
		boolean verificarRelacaoVideoUsuario = false;

		try {
			
			setVideoSelecionadoFormulario(getVideoSelecionado());
			
			formularioExistente = formularioDAO.pesquisarPorVideo(viewBean
					.getVideoSelecionado().getId());

			videoRelacionados = videoDAO.pesquisarPorUsuario(viewBean
					.getUsuarioLogado().getId());

			

			// VERIFICAR SE USUARIO É DONO DO VIDEO
			if(!videoRelacionados.isEmpty()){	
				//SE EXISTE FORMULARIO DAQUELE VIDEO
			if (!formularioExistente.isEmpty()) {
				for (Video v : videoRelacionados) {
					for (Formulario f : formularioExistente) {
						if (v.getId() == f.getVideo().getId()) {
							verificarRelacaoVideoFormulario = true;
						}
					}
				}
			}
			//SE NÃO EXISTE FORMULARIO DAQUELE VIDEO
			if(formularioExistente.isEmpty()){
				for(Video v : videoRelacionados){
					if(v.getUsuario().getId() == viewBean.getUsuarioLogado().getId()){
						verificarRelacaoVideoUsuario = true;
					}
					
				}	
			}	
			}
			

			// VERIFICAÇÃO DA VISUALIZAÇÃO DAS OPÇOES
			//SE EXISTE FORMULARIO E SE O VIDEO É DO USUARIO
			if (!formularioExistente.isEmpty() && !videoRelacionados.isEmpty() && verificarRelacaoVideoFormulario == true) {
				habilitarVisualizacaoAdicao = false;
				hbilitarVisualizacaoEdicao = true;
				habilitarVisualizacaoPraticar = true;
			}
			//SE EXISTE O FORMULARIO E O VIDEO FOR DO USUARIO
			else if (!formularioExistente.isEmpty()
					&& videoRelacionados.isEmpty() && verificarRelacaoVideoUsuario == false) {
				habilitarVisualizacaoAdicao = false;
				hbilitarVisualizacaoEdicao = false;
				habilitarVisualizacaoPraticar = true;
			}
			//SE NÃO EXISTE O FORMULARIO E O VIDEO FOR DO USUARIO
			else if (formularioExistente.isEmpty()
					&& !videoRelacionados.isEmpty() && verificarRelacaoVideoUsuario == true) {
				habilitarVisualizacaoAdicao = true;
				hbilitarVisualizacaoEdicao = true;
				habilitarVisualizacaoPraticar = false;
			}
			//SE NÃO EXISTE O FORMULÁRIO E O VIDEO NÃO FOR DO USUARIO
			else if (formularioExistente.isEmpty()
					&& videoRelacionados.isEmpty() && verificarRelacaoVideoUsuario == false) {
				habilitarVisualizacaoAdicao = false;
				hbilitarVisualizacaoEdicao = false;
				habilitarVisualizacaoPraticar = false;
			} 
			
			System.out.println("Titulo Video Formulário " + getVideoSelecionadoFormulario());
			

		} catch (Exception e) {

			System.out.println("Houve um erro " + e);

		}

	}
	

	// GERAR NOVOS FORMULARIOS
	public void limparFormulario1() {

		viewBean.setFormulario1(new Formulario());
	}

	public void limparFormulario2() {
		if (habilitarVisualizacaoPergunta2 == true) {

			viewBean.setFormulario2(new Formulario());

		}
	}

	public void limparFormulario3() {
		if (habilitarVisualizacaoPergunta3 == true) {

			viewBean.setFormulario3(new Formulario());

		}
	}

	public void limparFormulario4() {
		if (habilitarVisualizacaoPergunta4 == true) {

			viewBean.setFormulario4(new Formulario());

		}
	}

	public void limparFormulario5() {
		if (habilitarVisualizacaoPergunta5 == true) {

			viewBean.setFormulario5(new Formulario());

		}
	}

	public void limparFormulario6() {
		if (habilitarVisualizacaoPergunta6 == true) {

			viewBean.setFormulario6(new Formulario());

		}
	}

	public void limparFormulario7() {
		if (habilitarVisualizacaoPergunta7 == true) {

			viewBean.setFormulario7(new Formulario());

		}
	}

	public void limparFormulario8() {
		if (habilitarVisualizacaoPergunta8 == true) {

			viewBean.setFormulario8(new Formulario());

		}
	}

	public void limparFormulario9() {
		if (habilitarVisualizacaoPergunta9 == true) {

			viewBean.setFormulario9(new Formulario());

		}
	}

	public void limparFormulario10() {
		if (habilitarVisualizacaoPergunta10 == true) {

			viewBean.setFormulario10(new Formulario());

		}
	}

	// INSERIR UM NOVO ALUNO
	public String inserirUsuario() {
		
		String retorno = "";

		try {
			if (dao.listarCondicaoUsuario(
					"email = '" + viewBean.getUsuario().getEmail() + "'")
					.size() > 0) {
				mostraMenssagem("ERRO", "Já existe um usúario com este Email");
				
				retorno = "inicial";
				
			} else {
				
				habilitarVisualizacaoBtnPerfil = true;
				
				dao.inserir(viewBean.getUsuario());
				mostraMenssagem("SUCESSO", "Usuario inserido com sucesso.");
				
				viewBean.setUsuarioLogado(viewBean.getUsuario());
				viewBean.setNomeUsuario(viewBean.getUsuario().getNome());
				viewBean.setEmailAlterado(viewBean.getUsuario().getEmail());
				viewBean.setNomeAlterado(viewBean.getUsuario().getNome());
				viewBean.setDataAlterada(viewBean.getUsuario().getDataNascimento());
				// viewBean.setUsuarioEditado(viewBean.getUsuarios().get(0));
				atualizarListaVideo();

				FacesContext fc = FacesContext.getCurrentInstance();
				HttpSession session = (HttpSession) fc.getExternalContext()
						.getSession(false);
				session.setAttribute("objLogin", "logado");
				retorno = "logado";

				novoUsuario();
			}
			
		

		} catch (Exception e) {

			mostraMenssagem("ERRO", "Houve um erro ao tentar inserir usuario.");
			System.out.println("Erro " + e);
			
			retorno = "inicial";
		
		}
		
		return retorno;

	}

	// Editar usuario
	public void alterarUsuario() {
		try {
			
			//Caso o Email seja alterado e o mesmo não existe no banco
			if((!viewBean.getEmailAlterado().equals(viewBean.getUsuarioLogado().getEmail())
					&& dao.listarCondicaoUsuario(
							"email = '" + viewBean.getEmailAlterado() + "'")
							.size() == 0) || viewBean.getEmailAlterado().equals(viewBean.getUsuarioLogado().getEmail())
					){
				viewBean.getUsuarioLogado().setEmail(viewBean.getEmailAlterado());
				//Caso  a senha seja alterada
				if(!viewBean.getSenhaAlterada().equals("")){
					viewBean.getUsuarioLogado().setSenha(SenhaEncripty.md5(viewBean.getSenhaAlterada()));
				}
				//Caso o nome seja alterado
				if(!viewBean.getNomeAlterado().equals(viewBean.getUsuarioLogado().getNome())){
					viewBean.getUsuarioLogado().setNome(viewBean.getNomeAlterado());
				}
				if(viewBean.getDataAlterada()!=viewBean.getUsuarioLogado().getDataNascimento()){
					viewBean.getUsuarioLogado().setDataNascimento(viewBean.getDataAlterada());
				}
				
				dao.alterar(viewBean.getUsuarioLogado());
				mostraMenssagem("SUCESSO", "Usuario alterado com sucesso.");
			
			}
			//caso o email alterado já exista no banco
			else{
				mostraMenssagem("Erro", "Já existe um usuario com este Email!!");
				
				
			}
		
			viewBean.setNomeUsuario(viewBean.getUsuarioLogado().getNome());
			viewBean.setNomeAlterado(viewBean.getUsuarioLogado().getNome());
			viewBean.setEmailAlterado(viewBean.getUsuarioLogado().getEmail());
			viewBean.setDataAlterada(viewBean.getUsuarioLogado().getDataNascimento());

		} catch (Exception e) {
			mostraMenssagem("ERRO", "Houve um erro ao tentar alterar o usuario");
			System.out.println("Erro " + e);
		}

	}

	// DELETAR UM ALUNO
	public void deletarUsuario() {

		try {
			
			if(viewBean.getUsuarioSelecionadoTabela() != null){
			dao.remover(viewBean.getUsuarioSelecionadoTabela());
			}
			else{
				System.out.println("usuario selecionado null");
			}
			
			usuarioDataModel = dao.listarTodos();
			
			RequestContext.getCurrentInstance().update("frmAddAdm:dtUsuario");
			RequestContext.getCurrentInstance().update("frmGerenciar");
			
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
		setListaTamanho(viewBean.getVideos().size() - 1);
	}

	// ADICIONADOS RECENTEMENTE
		public void addRecente(List<Video> videos) {
			if (videos.size() > 0) {
				List<Video> recentes = new ArrayList<Video>();
				int tamanho = videos.size();
				if(tamanho>6){
					int inicio =tamanho;
					inicio -= 6; 
					for (int i = (tamanho-1); i >=inicio ; i--) {
						recentes.add(videos.get(i));
					}
				}
				else{
					for (int i = (tamanho-1); i >= 0; i--) {
						recentes.add(videos.get(i));
					}
				}
				viewBean.setVideosRecente(recentes);
			}

		}

	// EXECUTAR O LOGIN DO USUARIO
	public String loginUsuario() throws NoSuchAlgorithmException {
		
		//Logando como root
		if(viewBean.getUsuario().getEmail().equals("ifpr@gmail.com") && viewBean.getUsuario().getSenha().equals("root")){
			System.out.println("Entrou no master!");
			
			habilitarVisualizacaoBtnPerfil = false;
			
			viewBean.setNomeUsuario("Usuario Master");
			
			FacesContext fc = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession) fc.getExternalContext()
					.getSession(false);
			session.setAttribute("objLogin", "logado3");
			return "logado3";
			
		}
		else{
			
		habilitarVisualizacaoBtnPerfil = true;
			
		List<Usuario> usuarioAdm = new ArrayList<Usuario>();
		List<Usuario> usuarioNormal = new ArrayList<Usuario>();
	
		usuarioAdm = listarCondicaoAdministrador();
	
		if(usuarioAdm.isEmpty()){
		
			usuarioNormal = listarCondicao();
		}
			
		if (!usuarioAdm.isEmpty()) {
			
			hbilitarVisualizacaoEdicao = true;
		
			viewBean.setUsuarios(usuarioAdm);

			viewBean.setUsuarioLogado(viewBean.getUsuarios().get(0));
			viewBean.setNomeUsuario(viewBean.getUsuarios().get(0).getNome());
			// viewBean.setUsuarioEditado(viewBean.getUsuarios().get(0));
			atualizarListaVideo();

			FacesContext fc = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession) fc.getExternalContext()
					.getSession(false);
			session.setAttribute("objLogin", "logado2");
			return "logado2";

		}

		else if (!usuarioNormal.isEmpty()) {
			
			viewBean.setUsuarios(usuarioNormal);
			//Inserção dos dados do usuario
			viewBean.setUsuarioLogado(viewBean.getUsuarios().get(0));
			viewBean.setNomeUsuario(viewBean.getUsuarios().get(0).getNome());
			viewBean.setEmailAlterado(viewBean.getUsuarios().get(0).getEmail());
			viewBean.setNomeAlterado(viewBean.getUsuarios().get(0).getNome());
			viewBean.setDataAlterada(viewBean.getUsuarios().get(0).getDataNascimento());
			// viewBean.setUsuarioEditado(viewBean.getUsuarios().get(0));
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
	public List<Usuario> listarCondicao() throws NoSuchAlgorithmException {
		

				return	dao.listarCondicaoUsuario(" email = '"
				+ viewBean.getUsuario().getEmail() + "' and senha = '"
				+ SenhaEncripty.md5(viewBean.getUsuario().getSenha()) + "'");

	}
	
	// LISTAR CONDICÃO LOGAR ADMINISTRADOR
		public List<Usuario> listarCondicaoAdministrador() throws NoSuchAlgorithmException {

			return dao.listarCondicaoUsuario(" email = '"
					+ viewBean.getUsuario().getEmail() + "' and senha = '"
					+ SenhaEncripty.md5(viewBean.getUsuario().getSenha()) + "' and adm = 'sim' ");

		}
	
		//ATUALIZAR RESPOSTAS FORMULARIOS
		public void atualizarFormulario(){
			//Respostas
			viewBean.setResposta1(new String());
			viewBean.setResposta2(new String());
			viewBean.setResposta3(new String());
			viewBean.setResposta4(new String());
			viewBean.setResposta5(new String());
			viewBean.setResposta6(new String());
			viewBean.setResposta7(new String());
			viewBean.setResposta8(new String());
			viewBean.setResposta9(new String());
			viewBean.setResposta10(new String());
			
			org.primefaces.context.RequestContext.getCurrentInstance().update(":frmPraticar");
		}
		
	

	public int getTamanhoFormulario() {
		return tamanhoFormulario;
	}

	public void setTamanhoFormulario(int tamanhoFormulario) {
		this.tamanhoFormulario = tamanhoFormulario;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public Double getRendimento() {
		return rendimento;
	}

	public void setRendimento(Double rendimento) {
		this.rendimento = rendimento;
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

	public boolean isHabilitarVisualizacaoBtnPerfil() {
		return habilitarVisualizacaoBtnPerfil;
	}

	public void setHabilitarVisualizacaoBtnPerfil(
			boolean habilitarVisualizacaoBtnPerfil) {
		this.habilitarVisualizacaoBtnPerfil = habilitarVisualizacaoBtnPerfil;
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

	public int getListaTamanho() {
		return listaTamanho;
	}

	public void setListaTamanho(int listaTamanho) {
		this.listaTamanho = listaTamanho;
	}

	public Video getVideoSelecionadoFormulario() {
		return videoSelecionadoFormulario;
	}

	public void setVideoSelecionadoFormulario(Video videoSelecionadoFormulario) {
		this.videoSelecionadoFormulario = videoSelecionadoFormulario;
	}

	public boolean isHabilitarVisualizacaoAdicao() {
		return habilitarVisualizacaoAdicao;
	}

	public void setHabilitarVisualizacaoAdicao(
			boolean habilitarVisualizacaoAdicao) {
		this.habilitarVisualizacaoAdicao = habilitarVisualizacaoAdicao;
	}

	public boolean isHbilitarVisualizacaoEdicao() {
		return hbilitarVisualizacaoEdicao;
	}

	public void setHbilitarVisualizacaoEdicao(boolean hbilitarVisualizacaoEdicao) {
		this.hbilitarVisualizacaoEdicao = hbilitarVisualizacaoEdicao;
	}

	public boolean isHabilitarVisualizacaoPraticar() {
		return habilitarVisualizacaoPraticar;
	}

	public void setHabilitarVisualizacaoPraticar(
			boolean habilitarVisualizacaoPraticar) {
		this.habilitarVisualizacaoPraticar = habilitarVisualizacaoPraticar;
	}

	public List<Usuario> getUsuarioDataModel() {
		return usuarioDataModel;
	}

	public void setUsuarioDataModel(List<Usuario> usuarioDataModel) {
		this.usuarioDataModel = usuarioDataModel;
	}

	public List<Video> getVideoDataModel() {
		return videoDataModel;
	}

	public void setVideoDataModel(List<Video> videoDataModel) {
		this.videoDataModel = videoDataModel;
	}

	public boolean isHabilitarVisualizacaoDadosUsuario() {
		return habilitarVisualizacaoDadosUsuario;
	}

	public void setHabilitarVisualizacaoDadosUsuario(
			boolean habilitarVisualizacaoDadosUsuario) {
		this.habilitarVisualizacaoDadosUsuario = habilitarVisualizacaoDadosUsuario;
	}
	
	
	

}
