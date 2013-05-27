/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.filter;

import br.com.entidade.Usuario;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.faces.context.FacesContext;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Murilo
 */
@WebFilter(filterName = "LoginFilter", urlPatterns = {"/*"})
public class LoginFilter implements Filter {
    
    
    FilterConfig fc;
    
    @Override  
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {  
  
        HttpServletRequest req = (HttpServletRequest) request;  
        HttpSession session = req.getSession();  
        String paginaSolicitada = req.getRequestURI();
  
        if (session.getAttribute("logado") != null || paginaSolicitada.contains("login.xhtml") || paginaSolicitada.contains("cadastroUsuario.xhtml") ) {  
            chain.doFilter(request, response);  
        } else {  
            HttpServletResponse res = (HttpServletResponse) response;  
            res.sendRedirect(req.getContextPath()+"/login.xhtml");
        }  
  
    }  
  
    @Override  
    public void init(FilterConfig filterConfig) throws ServletException {  
        fc = filterConfig;
    }  
  
    @Override  
    public void destroy() {  
    }  

    
}
