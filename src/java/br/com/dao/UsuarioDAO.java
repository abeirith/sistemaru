/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dao;

import br.com.entidade.Usuario;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author Murilo
 */
public interface UsuarioDAO {
    
    
    public Usuario buscarUsuario(Usuario usuario);
    
    public List<Usuario> listarUsuarios();
    
    public void inserir(Usuario usuario);
    
}
