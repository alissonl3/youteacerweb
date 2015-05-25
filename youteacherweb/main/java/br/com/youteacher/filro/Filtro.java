package br.com.youteacher.filro;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Filtro implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
	
		   HttpServletRequest req = (HttpServletRequest) request;
	       HttpServletResponse res = (HttpServletResponse) response;
	        
	        String login = (String) req.getSession().getAttribute("objLogin");
	        String url = req.getRequestURI();
	        
	        if(url.contains("master") && (login == null || !login.equals("logado3"))){
	            
	            String contextPath =  ((HttpServletRequest) request).getContextPath();
	            
	            ((HttpServletResponse) response).sendRedirect(contextPath + "/index.html");
	        
	        
	        }
	        else if(url.contains("aluno") && (login == null || !login.equals("logado"))){
	            
	            String contextPath =  ((HttpServletRequest) request).getContextPath();
	            
	            ((HttpServletResponse) response).sendRedirect(contextPath + "/index.html");
	        
	        
	        }
	        else if(url.contains("adm") && (login == null || !login.equals("logado2"))){
	        
	             String contextPath =  ((HttpServletRequest) request).getContextPath();
	            
	            ((HttpServletResponse) response).sendRedirect(contextPath + "/index.html");
	        
	        
	        }
	        
	        else{
	        
	           chain.doFilter(req, res);
	        
	        }
	        
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
