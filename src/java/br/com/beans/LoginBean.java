package br.com.beans;

import br.com.dao.UsuarioDAO;
import br.com.entidade.Usuario;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Murilo
 */
@ManagedBean(name = "loginBean")
@SessionScoped
public class LoginBean {

    private Usuario usuario;
    @Inject
    private UsuarioDAO usuarioDAO;
    private String login;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public LoginBean() {
    }

    public Usuario getUsuario() {
        if (usuario == null) {
            usuario = new Usuario();
        }
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String checkLogin() throws Exception {
        
        if (getUsuario() != null) {
            Usuario usuarioRegistrado = usuarioDAO.buscarUsuario(getUsuario());
            if (usuarioRegistrado != null && usuarioRegistrado.getSenha().equals(getUsuario().getSenha())) {
               HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);  
               session.setAttribute("logado", true);  
                return "/index";
            }
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Login ou senha inexistente"));

        return "";
    }

    public String cadastrarUsuario() throws Exception {


        List<Usuario> usuarios = usuarioDAO.listarUsuarios();

        for (Usuario usuario : usuarios) {
            System.out.print("teste " + usuario.getLogin() + " - " + usuario.getSenha());
        }

        if (getUsuario() != null) {
            usuarioDAO.inserir(getUsuario());
        }

        return "/login";

    }

    public String doLogout() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession httpSession = (HttpSession) facesContext.getExternalContext().getSession(false);
        httpSession.invalidate();
        return "/login?faces-redirect=true";
    }
}
