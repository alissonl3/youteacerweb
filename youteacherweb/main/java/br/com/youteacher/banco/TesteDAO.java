package br.com.youteacher.banco;


import br.com.youteacherweb.entidades.*;


public class TesteDAO{
	
		
	
	public static void main(String[] args){
		
		
		   Usuario user = new Usuario();
		   user.setId(null);
	       user.setNome("testando2");
	       user.setAdm("0");
	       user.setEmail("testando@gmail.com");
	       user.setSenha("sem senha");
	       UsuarioDAO dao = new UsuarioDAO();
	       dao.inserirUsuario(user);
	     
	         
	         
	      
	         
	}



}
