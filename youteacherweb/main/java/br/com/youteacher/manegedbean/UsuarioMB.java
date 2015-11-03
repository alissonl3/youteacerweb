package br.com.youteacher.manegedbean;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.crypto.EncryptedPrivateKeyInfo;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;

import br.com.youteacher.banco.dao.FormularioDAO;
import br.com.youteacher.banco.dao.SenhaEncripty;
import br.com.youteacher.banco.dao.VideoDAO;
import br.com.youteacher.banco.dao.UsuarioDAO;
import br.com.youteacher.email.Carteiro;
import br.com.youteacher.email.Menssagem;
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
	private Usuario usuarioGerenciado;
	private Usuario usuarioGerenciadoApagar;
	
	public UsuarioMB() {
	}

	// menu bar
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
	private boolean habilitarVisualizacaoRemover;
	private boolean habilitarVisualizacaoPraticar;

	// video
	private Video videoSelecionado;
	private int listaTamanho;

	private Video videoSelecionadoFormulario;

	// popUp rendimento
	private String imagem;
	private Double rendimento;
	private int tamanhoFormulario;

	// pagina gerenciar
	private boolean habilitarVisualizacaoDadosUsuario;

	// opções render botão principal
	private boolean habilitarVisualizacaoBtInicio;
	
	private boolean retorno;

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
	
	//VALIDAR INJECTION SQL
	public boolean validarSQL(String string){
		 String[] lixo= {"select" , "drop" ,  ";" , "--" , "insert" , "delete" ,  "xp_", "'","update"};
		this.retorno = false;
	        for(int i=0;i<lixo.length;i++){
	            // string = string.replace(lixo[i], "");
	            boolean validar = string.contains(lixo[i]);
	            if(validar){
	            	this.retorno = validar;
	            }
	        }
	       return this.retorno;
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

			videoCondicao = videoDAO.listarCondicaoVideo("titulo = '"
					+ viewBean.getVideo().getTitulo() + "'");
			videosMesmoURL = videoDAO.listarCondicaoVideo("url = '" + novoUrl
					+ "'");
			// VERIFICAR SE EXISTE UM VIDEO COM O MESMO TITULO
			if (videoCondicao.isEmpty() && videosMesmoURL.isEmpty()) {
				viewBean.getVideo().setUrl(novoUrl);
				viewBean.getVideo().setUsuario(viewBean.getUsuarioLogado());
				videoDAO.inserir(viewBean.getVideo());
				atualizarListaVideo();

				// passar video inseido para o formulario
				videoSelecionadoFormulario = viewBean.getVideo();

				novoVideo();

				mostraMenssagem("SUCESSO", "Video inserido com sucesso!");

				RequestContext.getCurrentInstance().execute(
						"PF('dlgCriacaoForm').show();");

			} else {

				mostraMenssagem("ATENÇÃO",
						"Já existe um video com esse titulo ou com esse url!.");

			}

		} catch (Exception e) {

			mostraMenssagem("ERRO!!", "Erro ao inserir video!");
			System.out.println("Erro video:" + e);
		}

	}

	// DIRECIONAR PARA A PAGINA DE ADDFORMULARIO
	public String toAddFormulario() {

		// controle da vizualização
		habilitarVisualizacaoPergunta2 = false;
		habilitarVisualizacaoPergunta3 = false;
		habilitarVisualizacaoPergunta4 = false;
		habilitarVisualizacaoPergunta5 = false;
		habilitarVisualizacaoPergunta6 = false;
		habilitarVisualizacaoPergunta7 = false;
		habilitarVisualizacaoPergunta8 = false;
		habilitarVisualizacaoPergunta9 = false;
		habilitarVisualizacaoPergunta10 = false;

		// limpar todos os formularios
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
	public void inserirFormulario() {

		int count = 0;
		//String retorno = null;

		if (viewBean.getFormulario1() != null) {
			if(!viewBean.getFormulario1().getPergunta().trim().equals("")){
			viewBean.getFormulario1().setVideo(videoSelecionadoFormulario);

			formularioDAO.inserir(viewBean.getFormulario1());
			count++;

			limparFormulario1();
			}
		}

		if (habilitarVisualizacaoPergunta2 == true) {
			if (viewBean.getFormulario2() != null) {
				if(!viewBean.getFormulario2().getPergunta().trim().equals("")){

				viewBean.getFormulario2().setVideo(videoSelecionadoFormulario);

				formularioDAO.inserir(viewBean.getFormulario2());
				count++;

				limparFormulario2();
				}
			}
		}

		if (habilitarVisualizacaoPergunta3 == true) {
			if (viewBean.getFormulario3() != null) {
				if(!viewBean.getFormulario3().getPergunta().trim().equals("")){

				viewBean.getFormulario3().setVideo(videoSelecionadoFormulario);
				formularioDAO.inserir(viewBean.getFormulario3());
				count++;

				limparFormulario3();
				}
			}
		}

		if (habilitarVisualizacaoPergunta4 == true) {
			if (viewBean.getFormulario4() != null) {
				if(!viewBean.getFormulario4().getPergunta().trim().equals("")){

				viewBean.getFormulario4().setVideo(videoSelecionadoFormulario);
				formularioDAO.inserir(viewBean.getFormulario4());
				count++;

				limparFormulario4();
				}
			}
		}

		if (habilitarVisualizacaoPergunta5 == true) {
			if (viewBean.getFormulario5() != null) {
				if(!viewBean.getFormulario5().getPergunta().trim().equals("")){

				viewBean.getFormulario5().setVideo(videoSelecionadoFormulario);
				formularioDAO.inserir(viewBean.getFormulario6());
				count++;

				limparFormulario5();
				}
			}
		}

		if (habilitarVisualizacaoPergunta6 == true) {
			if (viewBean.getFormulario6() != null) {
				if(!viewBean.getFormulario6().getPergunta().trim().equals("")){

				viewBean.getFormulario6().setVideo(videoSelecionadoFormulario);
				formularioDAO.inserir(viewBean.getFormulario6());
				count++;

				limparFormulario6();
				}
			}
		}

		if (habilitarVisualizacaoPergunta7 == true) {
			if (viewBean.getFormulario7() != null) {
				if(!viewBean.getFormulario7().getPergunta().trim().equals("")){

				viewBean.getFormulario7().setVideo(videoSelecionadoFormulario);
				formularioDAO.inserir(viewBean.getFormulario7());
				count++;

				limparFormulario7();
				}
			}
		}

		if (habilitarVisualizacaoPergunta8 == true) {
			if (viewBean.getFormulario8() != null) {
				if(!viewBean.getFormulario8().getPergunta().trim().equals("")){

				viewBean.getFormulario8().setVideo(videoSelecionadoFormulario);
				formularioDAO.inserir(viewBean.getFormulario8());
				count++;

				limparFormulario8();
				}
			}
		}

		if (habilitarVisualizacaoPergunta9 == true) {
			if (viewBean.getFormulario9() != null) {
				if(!viewBean.getFormulario9().getPergunta().trim().equals("")){

				viewBean.getFormulario9().setVideo(videoSelecionadoFormulario);
				formularioDAO.inserir(viewBean.getFormulario9());
				count++;

				limparFormulario9();
				}
			}
		}

		if (habilitarVisualizacaoPergunta10 == true) {
			if (viewBean.getFormulario10() != null) {
				if(!viewBean.getFormulario10().getPergunta().trim().equals("")){

				viewBean.getFormulario10().setVideo(videoSelecionadoFormulario);
				formularioDAO.inserir(viewBean.getFormulario10());
				count++;

				limparFormulario10();
				}
			}
		}

		if (count > 0) {
			atualizarListaVideo();
			mostraMenssagem("SUCESSO", "Formulário adicionado com sucesso!");
			
			RequestContext.getCurrentInstance().update("frmDlgTemplate");
			RequestContext.getCurrentInstance().execute("PF('dlgAddForm').show();");
			
			//retorno = "inicio";
			
		} else {
			atualizarListaVideo();
			mostraMenssagem("ATENÇÃO", "Não foi adicionado nenhum formulário!");
			//retorno = "inicio";
		}

		//return retorno;

	}

	// TONAR UM USUARIO EM ADMINISTRADOR
	public void tornarAdministrador() {

		if (viewBean.getUsuarioSelecionadoTabela() != null) {
			viewBean.getUsuarioSelecionadoTabela().setAdm("sim");

			try {
				dao.alterar(viewBean.getUsuarioSelecionadoTabela());
				mostraMenssagem("SUCESSO",
						"O usuario se tornou um administrador");

				RequestContext.getCurrentInstance().update("frmAddAdm");
				RequestContext.getCurrentInstance().update("frmGerenciar");

			} catch (Exception e) {
				System.out.println("Erro tornar adm: " + e);
				mostraMenssagem("ERRO", "Houve um erro");

			}

		}

	}

	// Metodo para preencher os campos quando o usuario clicar em
	// "VAMOS PRATICAR"
	public void mostrarFormulario() {
		List<Formulario> formularios = new ArrayList<Formulario>();

		try {

			formularios = formularioDAO.pesquisarPorVideo(viewBean
					.getVideoSelecionado().getId());
			tamanhoFormulario = formularios.size();


			if (tamanhoFormulario >= 1) {
				viewBean.setFormulario1(formularios.get(0));
				setHabilitarVisualizacaoPergunta1(true);
			}
			if (tamanhoFormulario >= 2) {
				viewBean.setFormulario2(formularios.get(1));
				setHabilitarVisualizacaoPergunta2(true);
			}
			if (tamanhoFormulario >= 3) {
				viewBean.setFormulario3(formularios.get(2));
				setHabilitarVisualizacaoPergunta3(true);
			}
			if (tamanhoFormulario >= 4) {
				viewBean.setFormulario4(formularios.get(3));
				setHabilitarVisualizacaoPergunta4(true);
			}
			if (tamanhoFormulario >= 5) {
				viewBean.setFormulario5(formularios.get(4));
				setHabilitarVisualizacaoPergunta5(true);
			}
			if (tamanhoFormulario >= 6) {
				viewBean.setFormulario6(formularios.get(5));
				setHabilitarVisualizacaoPergunta6(true);
			}
			if (tamanhoFormulario >= 7) {
				viewBean.setFormulario7(formularios.get(6));
				setHabilitarVisualizacaoPergunta7(true);
			}
			if (tamanhoFormulario >= 8) {
				viewBean.setFormulario8(formularios.get(7));
				setHabilitarVisualizacaoPergunta8(true);
			}
			if (tamanhoFormulario >= 9) {
				viewBean.setFormulario9(formularios.get(8));
				setHabilitarVisualizacaoPergunta9(true);
			}
			if (tamanhoFormulario >= 10) {
				viewBean.setFormulario10(formularios.get(9));
				setHabilitarVisualizacaoPergunta10(true);
			}

			RequestContext.getCurrentInstance().update(":frmPraticar");

		} catch (Exception e) {
			mostraMenssagem("ERRO!", "Não existe formulário para o Vídeo");
		}

	}

	// REDIRECIONAR PARA ADDADM
	public String irParaAddAdm() {

		// usuarioDataModel = dao.listarTodos();
		usuarioDataModel = dao.listarCondicaoUsuario(" email != '"
				+ viewBean.getUsuarioLogado().getEmail() + "'");
		return "adm";
	}

	// atulizar dialog
	public void atualizarDialogAddAdm() {

		RequestContext.getCurrentInstance().update("frmDlgTemplate");
		RequestContext.getCurrentInstance().execute("PF('dlgAddAdm').show();");

	}

	// Metodo para corrigir as respostas do Usuario
	public void corrigir() {

		try {
			double acertos = 0;

			if (viewBean.getResposta1().equals(
					viewBean.getFormulario1().getResposta_certa()) && viewBean.getResposta1() != null) {
				acertos++;

			}
		
			if (viewBean.getResposta2().equals(
					viewBean.getFormulario2().getResposta_certa()) && viewBean.getResposta2() != null) {
				acertos++;

			}
	
			if (viewBean.getResposta3().equals(
					viewBean.getFormulario3().getResposta_certa()) && viewBean.getResposta3() != null) {
				acertos++;

			}
	
			if (viewBean.getResposta4().equals(
					viewBean.getFormulario4().getResposta_certa()) && viewBean.getResposta4() != null) {
				acertos++;

			}
		
			if (viewBean.getResposta5().equals(
					viewBean.getFormulario5().getResposta_certa()) && viewBean.getResposta5() != null) {
				acertos++;

			}

			if (viewBean.getResposta6().equals(
					viewBean.getFormulario6().getResposta_certa()) && viewBean.getResposta6() != null) {
				acertos++;

			}
	
			if (viewBean.getResposta7().equals(
					viewBean.getFormulario7().getResposta_certa()) && viewBean.getResposta7() != null) {
				acertos++;

			}
			
			if (viewBean.getResposta8().equals(
					viewBean.getFormulario8().getResposta_certa()) && viewBean.getResposta8() != null) {
				acertos++;

			}
		
			if (viewBean.getResposta9().equals(
					viewBean.getFormulario9().getResposta_certa()) && viewBean.getResposta9() != null) {
				acertos++;

			}
		
			if (viewBean.getResposta10().equals(
					viewBean.getFormulario10().getResposta_certa()) && viewBean.getResposta10() != null) {
				acertos++;

			}

			setRendimento((acertos / tamanhoFormulario) * 100);

			System.out.println("Total de acertos: " + acertos);
			System.out.println("Seu rendimento foi de: " + getRendimento());

			// DEFINIR A IMAGEM DE BOM OU RUIM
			if (rendimento > 50) {

				setImagem("../resources/imagens/acima50.png");

			} else {

				setImagem("../resources/imagens/abaixo50.png");

			}

			RequestContext.getCurrentInstance().update(":frmDlgTemplate");
			// RequestContext.getCurrentInstance().execute("PF('dlgRendimento').show();");

		} catch (Exception e) {

			System.out.println("Ocorreu um erro: " + e);

		}

	}

	// ENVIO DE MAIL
	public void enviarEmail() {

		try {

			List<Usuario> listaEmail = dao.listarCondicaoUsuario("email = '"
					+ viewBean.getEmailEnviar() + "'");

			if (listaEmail.size() > 0) {

				// gerando nova senha
				Usuario user = new Usuario();
				user = listaEmail.get(0);
				String novaSenha = gerarSenha();
				user.setSenha(SenhaEncripty.md5(novaSenha));

				dao.alterar(user);
				System.out.println("Alterado: nova senha >  " + novaSenha);

				Menssagem men = new Menssagem(viewBean.getEmailEnviar(),
						"Recuperar senha", "Olá " + user.getNome()
								+ ", sua nova senha é: " + novaSenha + ". "
								+ "Faça o login com sua nova senha! \n "
								+ "Link: http://localhost:8080/youteacherweb ");

				Carteiro carta = new Carteiro();

				if (carta.enviarMensagem(men)) {
					System.out.println("Enviado com sucesso!");
					RequestContext.getCurrentInstance()
							.update("frmDlgTemplate");
					RequestContext.getCurrentInstance().execute(
							"PF('dlgEmailEnviar').show();");

				} else {
					System.out.println("enviado sem sucesso!");
					mostraMenssagem("ERRO",
							"Houve um erro ao tentar enviar a menssagem");

				}
			} else {

				mostraMenssagem("ERRO", "Não existe um usuário com esse email");
			}

		} catch (Exception e) {

			System.out.println("Erro no enviarEmail " + e.getMessage());

		}

	}

	// GERAR NOVA SENHA
	public String gerarSenha() {
		char[] chart = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a',
				'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
				'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y',
				'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
				'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
				'X', 'Y', 'Z' };

		char[] senha = new char[5];

		int chartLenght = chart.length;
		Random rdm = new Random();

		for (int x = 0; x < 5; x++)
			senha[x] = chart[rdm.nextInt(chartLenght)];

		return new String(senha);
	}

	// REDIRECIONAR PARA PAGINA DE GERENCIAMENTO
	public String irParaGerenciar() {

		// usuarioDataModel = dao.listarTodos();
		usuarioDataModel = dao.listarCondicaoUsuario(" email != '"
				+ viewBean.getUsuarioLogado().getEmail() + "'");

		return "gerenciar";
	}

	// DELETAR VIDEO
	public String deletarVideo() {
		String url = "";
		try {
			if (viewBean.getVideoSelecionado() != null) {
		
				List<Formulario> formularioExistente = new ArrayList<Formulario>();

				formularioExistente = formularioDAO.pesquisarPorVideo(viewBean
						.getVideoSelecionado().getId());

				if (formularioExistente.size() >= 1) {
	
					for (int i = 0; i < formularioExistente.size(); i++) {
						formularioDAO.remover(formularioExistente.get(i));
			
					}
				}

				videoDAO.remover(viewBean.getVideoSelecionado());

				mostraMenssagem("SUCESSO", "Video deletado com sucesso");


				atualizarListaVideo();

				RequestContext.getCurrentInstance().update("frmGerenciar");

				url = "inicio";

			}
		} catch (Exception e) {
			System.out.println("Erro ao deletar video " + e);
		}

		return url;

	}

	// DELETAR VIDEO
	public void deletarVideoTabela() {

		try {

			Usuario user = new Usuario();

			if (usuarioGerenciado != null) {
				user = usuarioGerenciado;
			}

			if (viewBean.getVideoSelecionadoTabela() != null) {

				List<Formulario> formularioExistente = new ArrayList<Formulario>();

				formularioExistente = formularioDAO.pesquisarPorVideo(viewBean
						.getVideoSelecionadoTabela().getId());

				if (formularioExistente.size() >= 1) {

					for (int i = 0; i < formularioExistente.size(); i++) {
						formularioDAO.remover(formularioExistente.get(i));

					}
				}

				videoDAO.remover(viewBean.getVideoSelecionadoTabela());

				mostraMenssagem("SUCESSO", "Video deletado com sucesso");
				atualizarTabelaVideoDataModel(user);

				RequestContext.getCurrentInstance().update("frmGerenciar");

			}
		} catch (Exception e) {
			System.out.println("Erro ao deletar videoTabela " + e);
		}

	}

	// ATUALIZAR A TABELA DE VIDEO DATA MODEL DE ACORDO COM O USUARIO
	// SELECIONADO
	public void atualizarTabelaVideoDataModel(Usuario user) {

		// RequestContext.getCurrentInstance().update("frmGerenciar");
		RequestContext.getCurrentInstance().update(
				"frmGerenciar:pnlOcultoDados:dtUsuarioVideo");

		if (user != null) {
			this.videoDataModel = videoDAO.pesquisarPorUsuario(user.getId());
		}

		RequestContext.getCurrentInstance().update(
				"frmGerenciar:pnlOcultoDados");

	}

	// VERIFICAR EXISTENCIA DE FORMULÁRIO DO VIDEO
	public void verificarFormularioVideo() {

		// controle da vizualização
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

		habilitarVisualizacaoAdicao = false;
		habilitarVisualizacaoRemover = false;
		habilitarVisualizacaoPraticar = false;

		List<Formulario> formularioExistente = new ArrayList<Formulario>();

		try {


			setVideoSelecionadoFormulario(getVideoSelecionado());
			formularioExistente = formularioDAO.pesquisarPorVideo(viewBean
					.getVideoSelecionado().getId());

			// Se o usuario Logado tiver um video e não for adm
			if (viewBean.getVideoSelecionado().getUsuario().getId() == viewBean
					.getUsuarioLogado().getId()
					&& viewBean.getUsuarioLogado().getAdm() == null) {
				habilitarVisualizacaoRemover = true;
				if (formularioExistente.size() > 0) {
					habilitarVisualizacaoPraticar = true;
					habilitarVisualizacaoAdicao = false;
				} else {
					habilitarVisualizacaoAdicao = true;
					habilitarVisualizacaoPraticar = false;
				}

			}
			// Se o usuario Logado não for Adm e o video selecionado não for
			// dele
			else if (viewBean.getVideoSelecionado().getUsuario().getId() != viewBean
					.getUsuarioLogado().getId()
					&& formularioExistente.size() > 0
					&& viewBean.getUsuarioLogado().getAdm() == null) {
				habilitarVisualizacaoRemover = false;
				habilitarVisualizacaoAdicao = false;
				habilitarVisualizacaoPraticar = true;
			}

			// Se o usuario Logado for Adm e o video Selecionado não for dele
			else if (viewBean.getVideoSelecionado().getUsuario().getId() != viewBean
					.getUsuarioLogado().getId()
					&& viewBean.getUsuarioLogado().getAdm() != null) {
				habilitarVisualizacaoRemover = true;
				habilitarVisualizacaoAdicao = false;
				if (formularioExistente.size() > 0) {
					habilitarVisualizacaoPraticar = true;
				}

			}
			// Se o usuario Logado for Adm e o video Selecionado for dele
			else if (viewBean.getVideoSelecionado().getUsuario().getId() == viewBean
					.getUsuarioLogado().getId()
					&& viewBean.getUsuarioLogado().getAdm() != null) {
				habilitarVisualizacaoRemover = true;
				if (formularioExistente.size() > 0) {
					habilitarVisualizacaoPraticar = true;
					habilitarVisualizacaoAdicao = false;
				} else {
					habilitarVisualizacaoAdicao = true;
					habilitarVisualizacaoPraticar = false;
				}

			}

			else {
				habilitarVisualizacaoAdicao = false;
				habilitarVisualizacaoRemover = false;
				habilitarVisualizacaoPraticar = false;
			}

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
//	public String inserirUsuario() {
//
//		String retorno = "";
//
//		try {
//			if (dao.listarCondicaoUsuario(
//					"email = '" + viewBean.getUsuario().getEmail() + "'")
//					.size() > 0) {
//				mostraMenssagem("ERRO", "Já existe um usúario com este Email");
//
//				retorno = "inicial";
//
//			} else {
//
//				habilitarVisualizacaoBtnPerfil = true;
//
//				dao.inserir(viewBean.getUsuario());
//				mostraMenssagem("SUCESSO", "Usuario inserido com sucesso.");
//
//				viewBean.setUsuarioLogado(viewBean.getUsuario());
//				viewBean.setNomeUsuario(viewBean.getUsuario().getNome());
//				viewBean.setNomeAlterado(viewBean.getUsuario().getNome());
//				viewBean.setDataAlterada(viewBean.getUsuario()
//						.getDataNascimento());
//				// viewBean.setUsuarioEditado(viewBean.getUsuarios().get(0));
//				atualizarListaVideo();
//
//				FacesContext fc = FacesContext.getCurrentInstance();
//				HttpSession session = (HttpSession) fc.getExternalContext()
//						.getSession(false);
//				session.setAttribute("objLogin", "logado");
//				retorno = "logado";
//
//				novoUsuario();
//			}
//
//		} catch (Exception e) {
//
//			mostraMenssagem("ERRO", "Houve um erro ao tentar inserir usuario.");
//			System.out.println("Erro " + e);
//
//			retorno = "inicial";
//
//		}
//
//		return retorno;
//
//	}
	
	
	//INSERIR ALUNO NOVO
	public void inserirUsuario() {


		try {
			if (dao.listarCondicaoUsuario(
					"email = '" + viewBean.getUsuario().getEmail() + "'")
					.size() > 0) {
				mostraMenssagem("ERRO", "Já existe um usúario com este Email");
			} else {
				habilitarVisualizacaoBtnPerfil = true;
				
				String novaSenha = 	gerarSenha();
				
				if(viewBean.getUsuario() != null){
				viewBean.getUsuario().setSenha(novaSenha);								
				dao.inserir(viewBean.getUsuario());
				}
				
				Menssagem men = new Menssagem(viewBean.getUsuario().getEmail(),
						"YouTeacher", "Olá " + viewBean.getUsuario().getNome()
								+ ", seja Bem Vindo ao YouTeacher. Sua nova senha é: " + novaSenha + ". "
								+ "Faça o login com essa senha! Você poderá muda-lá no seu perfil. \n "
								+ "Link: http://localhost:8080/youteacherweb ");
				
				Carteiro carta = new Carteiro();

				if (carta.enviarMensagem(men)) {
					RequestContext.getCurrentInstance()
							.update("frmDlgTemplate");
					//TODO
					
					RequestContext.getCurrentInstance().execute(
							"PF('dlgEmailEnviarCadastrar').show();");

				} else {

					mostraMenssagem("ERRO",
							"Houve um erro ao tentar enviar a menssagem");

				}
				
				//mostraMenssagem("SUCESSO", "Usuario inserido com sucesso.");

				novoUsuario();
			}

		} catch (Exception e) {

			mostraMenssagem("ERRO", "Houve um erro ao tentar inserir usuario.");
			System.out.println("Erro " + e);


		}
	}

	// Editar usuario
	public void alterarUsuario() {
		try {
				boolean alterado = false;
				
				// Caso a senha seja alterada
				if(SenhaEncripty.md5(viewBean.getSenhaAtual()).equals(viewBean.getUsuarioLogado().getSenha())){
					System.out.println("Entrou");
				if (!viewBean.getSenhaAlterada().equals("")) {
					viewBean.getUsuarioLogado().setSenha(SenhaEncripty.md5(viewBean.getSenhaAlterada()));
					alterado = true;
				}
				}
				
				// Caso o nome seja alterado
				if (!viewBean.getNomeAlterado().equals(
						viewBean.getUsuarioLogado().getNome())) {
					viewBean.getUsuarioLogado().setNome(
							viewBean.getNomeAlterado());
					alterado = true;
				}
				if (viewBean.getDataAlterada() != viewBean.getUsuarioLogado()
						.getDataNascimento()) {
					viewBean.getUsuarioLogado().setDataNascimento(
							viewBean.getDataAlterada());
					alterado = true;
				}

				if(alterado){
				dao.alterar(viewBean.getUsuarioLogado());
				mostraMenssagem("SUCESSO", "Usuario alterado com sucesso.");
				}
			
			

			viewBean.setNomeUsuario(viewBean.getUsuarioLogado().getNome());
			viewBean.setNomeAlterado(viewBean.getUsuarioLogado().getNome());
			viewBean.setDataAlterada(viewBean.getUsuarioLogado()
					.getDataNascimento());

		} catch (Exception e) {
			mostraMenssagem("ERRO", "Houve um erro ao tentar alterar o usuario");
			System.out.println("Erro " + e);
		}

	}

	// DELETAR UM ALUNO
	public void deletarUsuario(Usuario user) {

		try {

			if (user != null) {
				apagarVideosUsuario(user);
				dao.remover(user);

				usuarioDataModel = dao.listarTodos();
				atualizarListaVideo();

				RequestContext.getCurrentInstance().update(
						"frmAddAdm:dtUsuario");
				RequestContext.getCurrentInstance().update("frmGerenciar");

				mostraMenssagem("SUCESSO", "Usuario deletado com sucesso.");
			} else {
				System.out.println("usuario selecionado null");
			}

		} catch (Exception e) {

			mostraMenssagem("ERRO", "Houve um erro ao tentar deletar usuario.");
			System.out.println("Erro " + e);
		}

	}

	// Apagar todos os videos daquele usuario
	public void apagarVideosUsuario(Usuario usuario) {
		try {
			List<Video> videosRemovidos = videoDAO.pesquisarPorUsuario(usuario
					.getId());
			if (videosRemovidos.size() > 0) {
				for (Video video : videosRemovidos) {
					viewBean.setVideoSelecionadoTabela(video);
					// deletarVideoTabela();
					if (viewBean.getVideoSelecionadoTabela() != null) {
	
						List<Formulario> formularioExistente = new ArrayList<Formulario>();

						formularioExistente = formularioDAO
								.pesquisarPorVideo(viewBean
										.getVideoSelecionadoTabela().getId());

						if (formularioExistente.size() >= 1) {

							for (int i = 0; i < formularioExistente.size(); i++) {
								formularioDAO.remover(formularioExistente
										.get(i));
	
							}
						}

						videoDAO.remover(viewBean.getVideoSelecionadoTabela());
					}

				}

			}
		} catch (Exception e) {
			mostraMenssagem("ERRO", "Houve um erro ao tentar deletar video");
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
			if (tamanho > 6) {
				int inicio = tamanho;
				inicio -= 6;
				for (int i = (tamanho - 1); i >= inicio; i--) {
					recentes.add(videos.get(i));
				}
			} else {
				for (int i = (tamanho - 1); i >= 0; i--) {
					recentes.add(videos.get(i));
				}
			}
			viewBean.setVideosRecente(recentes);
		}

	}

	// EXECUTAR O LOGIN DO USUARIO
	public String loginUsuario() throws NoSuchAlgorithmException {
		if(!validarSQL(viewBean.getUsuario().getSenha())){
		atualizarListaVideo();
		// Logando como root
		if (viewBean.getUsuario().getEmail().equals("ifpr@gmail.com")
				&& viewBean.getUsuario().getSenha().equals("root")) {
			System.out.println("Entrou no master!");

			habilitarVisualizacaoBtnPerfil = false;

			viewBean.setNomeUsuario("Usuario Master");

			FacesContext fc = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession) fc.getExternalContext()
					.getSession(false);
			session.setAttribute("objLogin", "logado3");
			return "logado3";

		} else {

			habilitarVisualizacaoBtnPerfil = true;

			List<Usuario> usuarioAdm = new ArrayList<Usuario>();
			List<Usuario> usuarioNormal = new ArrayList<Usuario>();

			usuarioAdm = listarCondicaoAdministrador();

			if (usuarioAdm.isEmpty()) {

				usuarioNormal = listarCondicao();
			}

			if (!usuarioAdm.isEmpty()) {

				habilitarVisualizacaoRemover = true;

				viewBean.setUsuarios(usuarioAdm);

				viewBean.setUsuarioLogado(viewBean.getUsuarios().get(0));
				viewBean.setNomeUsuario(viewBean.getUsuarios().get(0).getNome());
				viewBean.setNomeAlterado(viewBean.getUsuarios().get(0)
						.getNome());
				viewBean.setDataAlterada(viewBean.getUsuarios().get(0)
						.getDataNascimento());
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
				// Inserção dos dados do usuario
				viewBean.setUsuarioLogado(viewBean.getUsuarios().get(0));
				viewBean.setNomeUsuario(viewBean.getUsuarios().get(0).getNome());
				viewBean.setNomeAlterado(viewBean.getUsuarios().get(0)
						.getNome());
				viewBean.setDataAlterada(viewBean.getUsuarios().get(0)
						.getDataNascimento());
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
		}else{
			mostraMenssagem("ERRO!", "Tentativa de SQL INJECTION!!");
			return "inicial";
		}

	}

	// EXECUTAR LOGOFF DO USUARIO
	public String logOff() {
		viewBean = new UsuarioViewBean();
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(
				false);
		session.removeAttribute("objLogin");

		return "sair";
	}

	// LISTAR CONDICÃO LOGAR USUARIO
	public List<Usuario> listarCondicao() throws NoSuchAlgorithmException {

		return dao.listarCondicaoUsuario(" email = '"
				+ viewBean.getUsuario().getEmail() + "' and senha = '"
				+ SenhaEncripty.md5(viewBean.getUsuario().getSenha()) + "'");

	}

	// LISTAR CONDICÃO LOGAR ADMINISTRADOR
	public List<Usuario> listarCondicaoAdministrador()
			throws NoSuchAlgorithmException {

		return dao.listarCondicaoUsuario(" email = '"
				+ viewBean.getUsuario().getEmail() + "' and senha = '"
				+ SenhaEncripty.md5(viewBean.getUsuario().getSenha())
				+ "' and adm = 'sim' ");

	}

	// ATUALIZAR RESPOSTAS FORMULARIOS
	public void atualizarFormulario() {
		// Respostas
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
		atualizarListaVideo();

		org.primefaces.context.RequestContext.getCurrentInstance().update(
				":frmPraticar");
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

	public boolean isHabilitarVisualizacaoRemover() {
		return habilitarVisualizacaoRemover;
	}

	public void setHabilitarVisualizacaoRemover(
			boolean habilitarVisualizacaoRemover) {
		this.habilitarVisualizacaoRemover = habilitarVisualizacaoRemover;
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

	public boolean isHabilitarVisualizacaoBtInicio() {
		return habilitarVisualizacaoBtInicio;
	}

	public void setHabilitarVisualizacaoBtInicio(
			boolean habilitarVisualizacaoBtInicio) {
		this.habilitarVisualizacaoBtInicio = habilitarVisualizacaoBtInicio;
	}

	public Usuario getUsuarioGerenciado() {
		return usuarioGerenciado;
	}

	public void setUsuarioGerenciado(Usuario usuarioGerenciado) {
		if (usuarioGerenciado != null)
			atualizarTabelaVideoDataModel(usuarioGerenciado);
		this.usuarioGerenciado = usuarioGerenciado;
	}

	public Usuario getUsuarioGerenciadoApagar() {
		return usuarioGerenciadoApagar;
	}

	public void setUsuarioGerenciadoApagar(Usuario usuarioGerenciadoApagar) {
		if (usuarioGerenciadoApagar != null) {
			deletarUsuario(usuarioGerenciadoApagar);
			this.usuarioGerenciadoApagar = usuarioGerenciadoApagar;
		}
	}

}






