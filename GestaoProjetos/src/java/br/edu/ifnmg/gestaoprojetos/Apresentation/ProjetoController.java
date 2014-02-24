/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.gestaoprojetos.Apresentation;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import br.edu.ifnmg.gestaoprojetos.DomainModel.*;
import javax.ejb.EJB;

/**
 *
 * @author Isla Guedes
 */
@Named(value = "projetoController")
@SessionScoped
public class ProjetoController 
    extends ControllerGenerico<Projeto> implements Serializable {

    /**
     * Creates a new instance of ProjetoController
     */
    public ProjetoController() {
      
        filtro = new Projeto();
        entidade = new Projeto();  
        
    }
    
    @EJB
    ProjetoRepositorio dao;
    
     @Override
    public void salvar() {
        if(dao.Salvar(entidade)){
            
        } else {
            //mensagem de erro
        }
    }

    @Override
    public String novo(){
        entidade = new Projeto();
        return "editarProjeto.xhtml";
    }
    
    @Override
    public String abrir() {
        return "editarProjeto.xhtml";
    }

    @Override
    public String cancelar() {
        return "listagemProjeto.xhtml";
    }

    
    @Override
    public String excluir() {
        if(dao.Apagar(entidade)){
            return "listagemProjeto.xhtml";
        } else {
            return "";
        }
    }

    @Override
    public void filtrar() {
        listagem = dao.Buscar(filtro);
    }

    public ProjetoRepositorio getDao() {
        return dao;
    }

    public void setDao(ProjetoRepositorio dao) {
        this.dao = dao;
    }
    
    

}
