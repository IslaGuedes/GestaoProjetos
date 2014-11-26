/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.gestaoprojetos.Presentation;

import br.edu.ifnmg.gestaoprojetos.DomainModel.Usuario;
import br.edu.ifnmg.gestaoprojetos.DomainModel.UsuarioRepositorio;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Enumeration;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author HOME
 */
@Named(value = "autenticacaoController")
@SessionScoped
public class AutenticacaoController 
                implements Serializable {

    /**
     * Creates a new instance of AutenticacaoController
     */
    public AutenticacaoController() {
    }
    
    @EJB
    UsuarioRepositorio dao;
    private String nome, senha;
    Usuario usuario;
    
     public void Mensagem(String msg) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(msg));
    }
     
     public void validar() {
        
        try {
            
            usuario = dao.porLogin(nome);
        
        if (usuario == null) {
            Mensagem("Login ou senha não correspondem!");
        } else {
            if (senha.equals(usuario.getSenha())) {

                HttpSession session;

                FacesContext ctx = FacesContext.getCurrentInstance();
                session = (HttpSession) ctx.getExternalContext().getSession(false);
                session.setAttribute("usuarioAutenticado", usuario);

               // AppendLog("Login");

               FacesContext.getCurrentInstance().getExternalContext().redirect("admin/index.xhtml");
               
            } else {
                Mensagem("Login ou senha não correspondem");
            }
        }
        } catch(Exception ex){
            Mensagem("Login ou senha não correspondem");            
        }

    }
    
      public String logout() {
        HttpSession session;

        FacesContext ctx = FacesContext.getCurrentInstance();
        session = (HttpSession) ctx.getExternalContext().getSession(false);
        session.setAttribute("usuarioAutenticado", null);

       // AppendLog("Logout");
        
        Enumeration<String> vals = session.getAttributeNames(); 
        
        while(vals.hasMoreElements()){
            session.removeAttribute(vals.nextElement());
        }

        return "login.xhtml";

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
      
      
   

    
}
