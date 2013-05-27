/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dao.impl;

import br.com.dao.ConexaoDAO;
import br.com.dao.UsuarioDAO;
import br.com.entidade.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Murilo
 */
public class UsuarioDAOImpl implements UsuarioDAO {
    
    
    public void inserir(Usuario usuario){
		
		ConexaoDAO conexaoDAO = new ConexaoDAO();
		//Estabelecendo conex�o com o banco
		Connection con = null;
		
		try{
			// Criando o PreparedStatement para envio da senten�a insert SQL 
                        con = conexaoDAO.getConexao();
			PreparedStatement pstm = con.prepareStatement("INSERT INTO USUARIO(LOGIN, SENHA) VALUES(?,?)");
			
			// Populando os parametros da sentenca SQL
			pstm.setString(1, usuario.getLogin());
			pstm.setString(2, usuario.getSenha());
			
			//Executando o PreparedStatement
			pstm.executeUpdate();
			
			System.out.println("Usuario inserido com sucesso!");
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		
	}
    
    public List<Usuario> listarUsuarios(){
		
		ConexaoDAO conexaoDAO = new ConexaoDAO();
		//Estabelecendo conex�o com o banco

		ResultSet resultado = null;
                List<Usuario> usuarios = null;
		
		try{
			// Criando o PreparedStatement para envio da senten�a insert SQL 
                        Connection con = conexaoDAO.getConexao();
                        usuarios = new ArrayList<Usuario>();
                        String sqlConsulta = "SELECT * FROM USUARIO";
			PreparedStatement pstm = con.prepareStatement(sqlConsulta);
			resultado = pstm.executeQuery();
                        
                        while(resultado.next()){
                            
                            Usuario usuario = new Usuario();
                            usuario.setLogin(resultado.getString("LOGIN"));
                            usuario.setSenha(resultado.getString("SENHA"));
                            usuarios.add(usuario);
                            
                        }
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			
		}
		
		
		return usuarios;
	}
    
    public Usuario buscarUsuario(Usuario usuario){
		
		Usuario objUsuario  = null;
		
		ConexaoDAO conexaoDAO = new ConexaoDAO();
                Connection con = null;
		
		try{
                        String sqlConsulta= "SELECT * FROM USUARIO WHERE LOGIN = ?";
                        con = conexaoDAO.getConexao();
			PreparedStatement pstm = con.prepareStatement(sqlConsulta);
			pstm.setString(1, usuario.getLogin());
			ResultSet rs =  pstm.executeQuery();
			
			if(rs.next()){
			//populando o objeto
			objUsuario = new Usuario();
			objUsuario.setLogin(rs.getString("LOGIN"));
			objUsuario.setSenha(rs.getString("SENHA"));
                        }
						
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return objUsuario;
		
		
    }

    
}
