package br.com.youteacher.manegedbean;



import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;




import javax.faces.context.FacesContext;

import br.com.youteacher.banco.BancoDAO;
import br.com.youteacher.viewbean.UsuarioViewBean;

@ManagedBean
public class UsuarioMB implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private BancoDAO dao ;

	private UsuarioViewBean viewBean;

	
	public UsuarioMB(){}
	
	
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
			
			
		}catch(Exception e){
			
			mostraMenssagem("ERRO", "Houve um erro ao tentar inserir usuario.");
			System.out.println("Erro " + e);
		}
		
		
	}
	
	//INSERIR UM NOVO ALUNO
		public void deletar(){
			
			try{
				
				dao.deletar(viewBean.getUsuario());
				mostraMenssagem("SUCESSO", "Usuario deletado com sucesso.");
				
				
			}catch(Exception e){
				
				mostraMenssagem("ERRO", "Houve um erro ao tentar deletar usuario.");
				System.out.println("Erro " + e);
			}
			
			
		}
	
	
	//MOSTRAR MENSSAGEM DE NOTFICAÇÃO
	 public void mostraMenssagem(String titulo, String menssagem){
	        
	        FacesContext fc = FacesContext.getCurrentInstance();
	        FacesMessage men = new FacesMessage(titulo, menssagem);
	        fc.addMessage(null, men);
	    
	    
	    }

	public UsuarioViewBean getViewBean() {
		return viewBean;
	}

	public void setViewBean(UsuarioViewBean viewBean) {
		this.viewBean = viewBean;
	}
	
	
	

}
