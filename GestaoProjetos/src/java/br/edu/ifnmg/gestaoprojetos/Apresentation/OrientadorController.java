/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.gestaoprojetos.Apresentation;

import br.edu.ifnmg.gestaoprojetos.DomainModel.*;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;

/**
 *
 * @author Isla Guedes
 */
@Named(value = "orientadorController")
@SessionScoped
public class OrientadorController
    extends ControllerGenerico<Orientador> implements Serializable {

    /**
     * Creates a new instance of OrientadorController
     */
    public OrientadorController() {
        filtro = new Orientador();
        entidade = new Orientador();
    }
    
    @EJB
    OrientadorRepositorio dao;
    
    @Override
    public void salvar() {
        if(dao.Salvar(entidade)){
            
        } else {
            //mensagem de erro
        }
    }

    @Override
    public String novo(){
        entidade = new Orientador();
        return "editarOrientador.xhtml";
    }
    
    @Override
    public String abrir() {
        return "editarOrientador.xhtml";
    }

    @Override
    public String cancelar() {
        return "listagemOrientador.xhtml";
    }

    
    @Override
    public String excluir() {
        if(dao.Apagar(entidade)){
            return "listagemOrientador.xhtml";
        } else {
            return "";
        }
    }

    @Override
    public void filtrar() {
        listagem = dao.Buscar(filtro);
    }

    public OrientadorRepositorio getDao() {
        return dao;
    }

    public void setDao(OrientadorRepositorio dao) {
        this.dao = dao;
    }
    
    

}
