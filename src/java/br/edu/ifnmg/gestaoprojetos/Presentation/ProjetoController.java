/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.gestaoprojetos.Presentation;

import br.edu.ifnmg.gestaoprojetos.DomainModel.AreaConhecimento;
import br.edu.ifnmg.gestaoprojetos.DomainModel.Projeto;
import br.edu.ifnmg.gestaoprojetos.DomainModel.ProjetoRepositorio;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;

/**
 *
 * @author Isla Guedes
 */
@Named(value = "projetoController")
@SessionScoped
public class ProjetoController 
    extends ControllerGenerico<Projeto> implements Serializable {
    
    AreaConhecimento areaConhecimento;

    /**
     * Creates a new instance of ProjetoController
     */
    
    public ProjetoController() {
      
        filtro = new Projeto();
        entidade = new Projeto();  
        areaConhecimento = new AreaConhecimento();
    }
    
    @EJB
    ProjetoRepositorio dao;
    
     @Override
    public void salvar() {
        if(dao.Salvar(entidade)){
            listagem = null; 
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
            listagem = null; 
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

    public AreaConhecimento getAreaConhecimento() {
        return areaConhecimento;
    }

    public void setAreaConhecimento(AreaConhecimento areaConhecimento) {
        this.areaConhecimento = areaConhecimento;
    }
    
    //Metodos
    
      public void addAreaConhecimento(){
        entidade.addAreaConhecimento(areaConhecimento);
        dao.Salvar(entidade);
        areaConhecimento = new AreaConhecimento();
     }
      
      public void removeAreaConhecimento(){
        entidade.removeAreaConhecimento(areaConhecimento);
        dao.Salvar(entidade);
        areaConhecimento = new AreaConhecimento();
      } 
    
}

