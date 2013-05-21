package br.com.beans;

import br.com.entidade.ListaUsuarios;
import br.com.entidade.Usuario;
import br.com.persistencia.Persistencia;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.naming.AuthenticationException;

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
    
    private String login;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
    
    @Inject
    Persistencia persistencia;

    public LoginBean() {
    }

    public Usuario getUsuario() {
        if(usuario == null){
            usuario = new Usuario();
        }
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

 
    public String checkLogin() {
        ArrayList<Usuario> usuarios = persistencia.abrirArquivo("C:\\DSO\\usuarios.txt");
        for (Iterator<Usuario> it = usuarios.iterator(); it.hasNext();) {
            Usuario usuarioCadastrado = it.next();
            if ((usuarioCadastrado.getLogin().equals(getUsuario().getLogin()) && (usuarioCadastrado.getSenha().equals(getUsuario().getSenha())))) {
                return "/index";
            }
        }
        return "/login";
    }

    public String cadastrarUsuario() throws Exception {
         ListaUsuarios lista = null;
         lista.setUsuarios(persistencia.abrirArquivo("C:\\DSO\\usuarios.txt"));
                 
                 
        if (getUsuario() != null && getUsuario().getLogin() != null && getUsuario().getSenha() != null) {
            lista.getUsuarios().add(getUsuario());
            persistencia.registrarEmArquivo(lista.getUsuarios());
            return "/login";
        } else {
            throw new Exception("Impossivel cadastrar");
        }

    }
}
