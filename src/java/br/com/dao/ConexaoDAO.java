/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author Murilo
 */
public class ConexaoDAO {
    
    public Connection getConexao() throws NamingException, SQLException{
        String conexao = "jdbc/sistemaru";
        DataSource ds = null;

        try {
        InitialContext ctx = new InitialContext();
        ds = (DataSource) ctx.lookup(conexao);
        
        } catch (NamingException ex) {
            Logger.getLogger(ConexaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ds.getConnection();
        
    }
    
}
