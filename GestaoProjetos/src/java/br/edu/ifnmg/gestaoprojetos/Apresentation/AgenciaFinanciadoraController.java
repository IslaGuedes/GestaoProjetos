/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.gestaoprojetos.Apresentation;


import br.edu.ifnmg.gestaoprojetos.DomainModel.*;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;

import javax.inject.Named;



/**
 *
 * @author Isla Guedes
 */
@Named(value = "agenciaFinanciadoraController")
@SessionScoped
public class AgenciaFinanciadoraController 
    extends ControllerGenerico<AgenciaFinanciadora> implements Serializable {
 
    //List<AgenciaFinanciadora> listagem;
   
    /**
     * Creates a new instance of AgenciaFinanciadoraController
     */
    
    public AgenciaFinanciadoraController() {
        filtro = new AgenciaFinanciadora();
        entidade = new AgenciaFinanciadora();       
    }

    @EJB
    AgenciaFinanciadoraRepositorio dao;

    @Override
    public void salvar() {
        if(dao.Salvar(entidade)){
            
        } else {
            //mensagem de erro
        }
    }

    @Override
    public String novo(){
        entidade = new AgenciaFinanciadora();
        return "admin/editarAgenciaFinanciadora.xhtml";
    }
    
    @Override
    public String abrir() {
        return "admin/editarAgenciaFinanciadora.xhtml";
    }

    @Override
    public String cancelar() {
        return "admin/listagemAgenciaFinanciadora.xhtml";
    }

    
    @Override
    public String excluir() {
        if(dao.Apagar(entidade)){
            return "admin/listagemAgenciaFinanciadora.xhtml";
        } else {
            return "";
        }
    }

    @Override
    public void filtrar() {
        listagem = dao.Buscar(filtro);
    }

    public AgenciaFinanciadoraRepositorio getDao() {
        return dao;
    }

    public void setDao(AgenciaFinanciadoraRepositorio dao) {
        this.dao = dao;
    }
    
  
}
