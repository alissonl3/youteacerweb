package br.com.youteacher.manegedbean;



import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;




import javax.faces.context.FacesContext;


import javax.servlet.http.HttpSession;

import br.com.youteacher.banco.BancoDAO;
import br.com.youteacher.viewbean.UsuarioViewBean;
import br.com.youteacherweb.entidades.Usuario;

@ManagedBean
public class UsuarioMB implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private BancoDAO dao ;

	private UsuarioViewBean viewBean;

	
	public UsuarioMB(){}
	
	//Formulário
	private boolean habilitarFormulario;
	
	
	@PostConstruct
	private void initPage(){
		
		dao = new BancoDAO();
		viewBean = new UsuarioViewBean();
		
	}
	
	//INSERIR UM NOVO ALUNO
	public void inserir(){
		
		try{
			
			dao.inserir(viewBean.getUsuario());
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
				
				dao.deletar(viewBean.getUsuario());
				mostraMenssagem("SUCESSO", "Usuario deletado com sucesso.");
				
				
			}catch(Exception e){
				
				mostraMenssagem("ERRO", "Houve um erro ao tentar deletar usuario.");
				System.out.println("Erro " + e);
			}
			
			
		}
	
	
	//MOSTRAR MENSSAGEM DE NOTIFICAÇÃO
	 public void mostraMenssagem(String titulo, String menssagem){
	        
	        FacesContext fc = FacesContext.getCurrentInstance();
	        FacesMessage men = new FacesMessage(titulo, menssagem);
	        fc.addMessage(null, men);
	    
	    
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
	            
	            FacesContext fc = FacesContext.getCurrentInstance();
	            HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
	            session.setAttribute("objLogin", "logado");
	        
	            return "/aluno/home.jsf?faces-redirect=true";
	        
	        }
	         else {
	            
	            mostraMenssagem("LOGIN",  "Senha ou login incorretos!!!");
	            return "index";
	        
	        }
	    
	   
	    }
	    
	 //EXECUTAR LOGOFF DO USUARIO
	    public String logOff(){

	        FacesContext fc = FacesContext.getCurrentInstance();
	        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
	        session.removeAttribute("objLogin");
	    
	        return "/index.html?faces-redirect=true";
	    }
	 
	 //LISTAR CONDICÃO LOGAR USUARIO
	 public void listarCondicao(){
		    
	        viewBean.setUsuarios(dao.listarCondicao(Usuario.class, " email = '" + viewBean.getUsuario().getEmail() + "' and senha = '" + viewBean.getUsuario().getSenha() + "'"));
	    
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
