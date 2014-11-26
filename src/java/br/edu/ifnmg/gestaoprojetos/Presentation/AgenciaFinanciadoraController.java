/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.gestaoprojetos.Presentation;

import br.edu.ifnmg.gestaoprojetos.DomainModel.AgenciaFinanciadora;
import br.edu.ifnmg.gestaoprojetos.DomainModel.AgenciaFinanciadoraRepositorio;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author Isla Guedes
 */
@Named(value = "agenciaFinanciadoraController")
@SessionScoped
public class AgenciaFinanciadoraController 
    extends ControllerGenerico<AgenciaFinanciadora> implements Serializable {
 
   
    
    /**
     * Creates a new instance of AgenciaFinanciadoraController
     */
    
    public AgenciaFinanciadoraController() {
        filtro = new AgenciaFinanciadora();
        entidade = new AgenciaFinanciadora();
        
    }

    @EJB
    AgenciaFinanciadoraRepositorio dao;
    
    public void exibirMensagem(String msg) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(msg));
    }

    @Override
    public void salvar() {
        
      if(dao.Salvar(entidade)){      
                       
        listagem = null;
        exibirMensagem("Operação realizada com Sucesso!");

      }
    }

    @Override
    public String novo(){
        entidade = new AgenciaFinanciadora();
        return "editarAgenciaFinanciadora.xhtml";
    }
    
    @Override
    public String abrir() {
        return "editarAgenciaFinanciadora.xhtml";
    }

    @Override
    public String cancelar() {
        return "listagemAgenciaFinanciadora.xhtml";
    }

    
    @Override
    public String excluir() {
        
        if(dao.Apagar(entidade)){
            listagem = null;
            exibirMensagem("Apagado com sucesso!");
            
        } 
        return "listagemAgenciaFinanciadora.xhtml";
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
    
    public void validaSigla(FacesContext context, UIComponent component, Object value) throws ValidatorException{
       
      if (entidade.getId() == null || entidade.getId() == 0L) {

          
        AgenciaFinanciadora tmp = dao.Abrir(value.toString());
        
        if (tmp != null){
            FacesMessage msg
                    = new FacesMessage("Sigla já cadastrada!");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
            
        }
        
      }
    }
}
