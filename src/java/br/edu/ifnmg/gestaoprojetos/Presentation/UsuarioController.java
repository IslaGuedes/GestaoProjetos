/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.gestaoprojetos.Presentation;

import br.edu.ifnmg.gestaoprojetos.DomainModel.AgenciaFinanciadora;
import br.edu.ifnmg.gestaoprojetos.DomainModel.Usuario;
import br.edu.ifnmg.gestaoprojetos.DomainModel.UsuarioRepositorio;
import br.edu.ifnmg.gestaoprojetos.DomainModel.ValidadorCPF;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Enumeration;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Isla Guedes
 */
@Named(value = "usuarioController")
@SessionScoped
public class UsuarioController
    extends ControllerGenerico<Usuario> implements Serializable {

    /**
     * Creates a new instance of UsuarioController
     */
    public UsuarioController() {
        filtro = new Usuario();
        entidade = new Usuario(); 
    }
    private String nome, senha;
    @EJB
    UsuarioRepositorio dao;
    
    public void Mensagem(String msg) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(msg));
    }
    
    public String validar() {
        
        try {
            
        Usuario tmp = dao.porLogin(nome);
        
        if (tmp == null) {
            Mensagem("Login ou senha não correspondem!");
            return "login.xhtml";
        } else {
            if (senha.equals(tmp.getSenha())) {

                HttpSession session;

                FacesContext ctx = FacesContext.getCurrentInstance();
                session = (HttpSession) ctx.getExternalContext().getSession(false);
                session.setAttribute("usuarioAutenticado", tmp);

               // AppendLog("Login");

                return "index.xhtml";
            } else {
                Mensagem("Login ou senha não correspondem");
                return "login.xhtml";
            }
        }
        } catch(Exception ex){
            Mensagem("Login ou senha não correspondem");
            return "login.xhtml";
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

    
    
    public String novaSenha(){
        
        return "novaSenha.xhtml";
    }
    
    
    @Override
    public void salvar() {
        if(dao.Salvar(entidade)){
         listagem = null; 
         Mensagem("Operação realizada com Sucesso!");
        }
    }

    @Override
    public String novo(){
        entidade = new Usuario();
        return "cadastrarUsuario.xhtml";
    }
    
    @Override
    public String abrir() {
        return "cadastrarUsuario.xhtml";
    }

    @Override
    public String cancelar() {
        return "login.xhtml";
    }

    
    @Override
    public String excluir() {
        if(dao.Apagar(entidade)){
            listagem = null; 
            return "";
        } else {
            return "";
        }
    }

    @Override
    public void filtrar() {
        listagem = dao.Buscar(filtro);
    }

    public UsuarioRepositorio getDao() {
        return dao;
    }

    public void setDao(UsuarioRepositorio dao) {
        this.dao = dao;
    }
    
    public void validaRG(FacesContext context, UIComponent component, Object value) throws ValidatorException{
      
        if (entidade.getId() == null || entidade.getId() == 0L ){
           
         Usuario tmp = dao.Abrir(value.toString());
        
            if (tmp != null){
            FacesMessage msg
                    = new FacesMessage("RG já cadastrado!");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
            
            }
       } 
    }
    
       
    public void validaEmail(FacesContext context, UIComponent component, Object value) throws ValidatorException{
      
      if (entidade.getId() == null || entidade.getId() == 0L ){
          
        Usuario tmp = dao.AbrirEmail(value.toString());
        
        if (tmp != null){
            FacesMessage msg
                    = new FacesMessage("E-mail já cadastrado!");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
            
        }
      } 
    }
    
    
     public void validaCPF(FacesContext context, UIComponent component, Object value) throws ValidatorException {
      
       if (entidade.getId() == null || entidade.getId() == 0L ){
           
          if (value == null || value.toString() == "" ){
              FacesMessage msg
                    = new FacesMessage("CPF vazio!");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
          }
        
        Usuario tmp = dao.AbrirPorCPF(value.toString());  

        if (tmp != null && !tmp.equals(entidade)) {
            FacesMessage msg
                    = new FacesMessage("CPF já cadastrado!");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }

        if (!ValidadorCPF.validaCPF(value.toString())) {
            FacesMessage msg
                    = new FacesMessage("CPF Inválido!");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
       }
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
     
}

