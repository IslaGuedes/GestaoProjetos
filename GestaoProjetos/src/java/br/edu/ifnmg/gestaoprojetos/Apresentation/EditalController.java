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
@Named(value = "editalController")
@SessionScoped
public class EditalController 
    extends ControllerGenerico<Edital> implements Serializable {

    /**
     * Creates a new instance of EditalController
     */
    public EditalController() {        
        filtro = new Edital();
        entidade = new Edital();        
    }    
    
    @EJB
    EditalRepositorio dao;
    
    @Override
    public void salvar() {
        if(dao.Salvar(entidade)){
            
        } else {
            //mensagem de erro
        }
    }

    @Override
    public String novo(){
        entidade = new Edital();
        return "editarEdital.xhtml";
    }
    
    @Override
    public String abrir() {
        return "editarEdital.xhtml";
    }

    @Override
    public String cancelar() {
        return "listagemEdital.xhtml";
    }

    
    @Override
    public String excluir() {
        if(dao.Apagar(entidade)){
            return "listagemEdital.xhtml";
        } else {
            return "";
        }
    }

    @Override
    public void filtrar() {
        listagem = dao.Buscar(filtro);
    }

    public EditalRepositorio getDao() {
        return dao;
    }

    public void setDao(EditalRepositorio dao) {
        this.dao = dao;
    }
    
    
    

}
