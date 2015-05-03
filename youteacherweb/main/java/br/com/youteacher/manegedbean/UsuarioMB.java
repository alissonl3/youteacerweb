package br.com.youteacher.manegedbean;



import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;




import javax.faces.context.FacesContext;


import javax.servlet.http.HttpSession;

import br.com.youteacher.banco.dao.VideoDAO;
import br.com.youteacher.banco.dao.UsuarioDAO;
import br.com.youteacher.viewbean.UsuarioViewBean;
import br.com.youteacherweb.entidades.Usuario;

@ManagedBean
public class UsuarioMB implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private UsuarioDAO dao;
	private VideoDAO videoDAO;

	private UsuarioViewBean viewBean;

	public UsuarioMB(){}
	
	//Formulário
	private boolean habilitarFormulario;
	private boolean habilitarVisualizacaoQuestionario;
	
	
	@PostConstruct
	private void initPage(){
		
		dao = new UsuarioDAO();
		viewBean = new UsuarioViewBean();
		videoDAO = new VideoDAO();
		
	}
	//ADICIONAR VIDEO
	public void inserirVideo(){
		try{
			viewBean.getVideo().setUsuario(viewBean.getUsuarioLogado());
			videoDAO.inserir(viewBean.getVideo());
			
			
		}catch(Exception e){
			mostraMenssagem("ERRO!!", "Erro ao inserir video!");
			System.out.println("Erro video:"+e);
		}
		
		
	}
	
	
	
	//INSERIR UM NOVO ALUNO
	public void inserir(){
		
		try{
			
			dao.inserirUsuario(viewBean.getUsuario());
			mostraMenssagem("SUCESSO", "Usuario inserido com sucesso.");
			
			novoUsuario();
			
		}catch(Exception e){
			
			mostraMenssagem("ERRO", "Houve um erro ao tentar inserir usuario.");
			System.out.println("Erro " + e);
		}
		
		
	}
	
	//DELETAR UM NOVO ALUNO
		public void deletar(){
			
			try{
				
				dao.deletarUsuario(viewBean.getUsuario());
				mostraMenssagem("SUCESSO", "Usuario deletado com sucesso.");
				
				
			}catch(Exception e){
				
				mostraMenssagem("ERRO", "Houve um erro ao tentar deletar usuario.");
				System.out.println("Erro " + e);
			}
			
			
		}
	
	
	//MOSTRAR MENSSAGEM DE NOTIFICAÇÃO
	 public void mostraMenssagem(String titulo, String menssagem){
	        
		 FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage(titulo,  menssagem));
	    
	    
	    }
	 
	 //NOVO USUARIO
	 public void novoUsuario(){
		 viewBean.setUsuario(new Usuario());
	 }

	 //EXECUTAR O LOGIN DO USUARIO
	    public String loginUsuario(){
	        
	        listarCondicao();
	  
	    if(!viewBean.getUsuarios().isEmpty()){
	             
	            viewBean.setUsuarioLogado(viewBean.getUsuarios().get(0)); 
	            viewBean.setNomeUsuario(viewBean.getUsuarios().get(0).getNome());
	          
	            FacesContext fc = FacesContext.getCurrentInstance();
	            HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
	            session.setAttribute("objLogin", "logado");
	        
	            return "logado";
	        
	        }
	         else {
	            
	            mostraMenssagem("LOGIN",  "Senha ou login incorretos!!!");
	            return "inicial";
	        
	        }
	    
	   
	    }
	   
	    
	    
	 //EXECUTAR LOGOFF DO USUARIO
	    public String logOff(){

	        FacesContext fc = FacesContext.getCurrentInstance();
	        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
	        session.removeAttribute("objLogin");
	    
	        return "sair";
	    }
	 
	 //LISTAR CONDICÃO LOGAR USUARIO
	 public void listarCondicao(){
		    
	        viewBean.setUsuarios(dao.listarCondicaoUsuario(" email = '" + viewBean.getUsuario().getEmail() + "' and senha = '" + viewBean.getUsuario().getSenha() + "'"));
	    
	        
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
