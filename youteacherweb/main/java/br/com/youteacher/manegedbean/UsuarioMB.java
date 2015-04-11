package br.com.youteacher.manegedbean;



import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;




import br.com.youteacher.banco.BancoDAO;
import br.com.youteacher.viewbean.UsuarioViewBean;

@ManagedBean
public class UsuarioMB implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private BancoDAO dao = new BancoDAO();

	private UsuarioViewBean viewBean;

	
	public UsuarioMB(){}
	
	
	@PostConstruct
	private void initPage(){
		
		
		viewBean = new UsuarioViewBean();
		
	}
	
	//INSERIR UM NOVO ALUNO
	public void inserir(){
		
		
	}

	public UsuarioViewBean getViewBean() {
		return viewBean;
	}

	public void setViewBean(UsuarioViewBean viewBean) {
		this.viewBean = viewBean;
	}
	
	
	

}
