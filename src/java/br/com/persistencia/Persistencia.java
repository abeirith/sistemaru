package br.com.persistencia;

import br.com.entidade.Usuario;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Persistencia {

    public void registrarEmArquivo(List<Usuario> usuario) {

        serializarUsuario(usuario, "C:\\DSO\\usuarios.txt");

    }

    public ArrayList<Usuario> abrirArquivo(String arquivo) {
        ArrayList<Usuario> listaUsuarios = deserializarListaUsuarios(arquivo);
        return listaUsuarios;
    }

    private static void serializarUsuario(List<Usuario> usuario, String arquivo) {

        FileOutputStream arq = null;
        ObjectOutputStream out = null;

        try {

            arq = new FileOutputStream(arquivo);
            out = new ObjectOutputStream(arq);
            out.writeObject(usuario);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                arq.close();
                out.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private static ArrayList<Usuario> deserializarListaUsuarios(String arquivo) {
        FileInputStream arqLeitura = null;
        ObjectInputStream in = null;
        ArrayList<Usuario> lista = null;

        try {

            arqLeitura = new FileInputStream(arquivo);
            in = new ObjectInputStream(arqLeitura);
            lista = (ArrayList<Usuario>) in.readObject();
            
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                arqLeitura.close();
                in.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        return lista;
    }
}