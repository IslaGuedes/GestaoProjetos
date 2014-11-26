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
    @EJB
    UsuarioRepositorio dao;

    public void Mensagem(String msg) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(msg));
    }

    public String novaSenha() {

        return "novaSenha.xhtml";
    }

    @Override
    public void salvar() {
        if (dao.Salvar(entidade)) {
            listagem = null;
            Mensagem("Operação realizada com Sucesso!");
        }
    }

    @Override
    public String novo() {
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
        if (dao.Apagar(entidade)) {
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

   
   
}
