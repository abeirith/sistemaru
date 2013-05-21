/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.entidade;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Murilo
 */
public class ListaUsuarios implements java.io.Serializable{
    
    private List<Usuario> usuarios;

    public ListaUsuarios() {
    }

    
    
    public List<Usuario> getUsuarios() {
        if(usuarios == null){
            usuarios = new ArrayList<Usuario>();
        }
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
    
    
    
}
