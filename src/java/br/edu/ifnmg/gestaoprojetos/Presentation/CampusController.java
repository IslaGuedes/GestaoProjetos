/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.gestaoprojetos.Presentation;

import br.edu.ifnmg.gestaoprojetos.DomainModel.AgenciaFinanciadora;
import br.edu.ifnmg.gestaoprojetos.DomainModel.Campus;
import br.edu.ifnmg.gestaoprojetos.DomainModel.CampusRepositorio;
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
@Named(value = "campusController")
@SessionScoped
public class CampusController
    extends ControllerGenerico<Campus> implements Serializable {

    /**
     * Creates a new instance of CampusController
     */
    public CampusController() {        
        filtro = new Campus();
        entidade = new Campus();        
    }
    
    @EJB
    CampusRepositorio dao;
    
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
        entidade = new Campus();
        return "editarCampus.xhtml";
    }
    
    @Override
    public String abrir() {
        return "editarCampus.xhtml";
    }

    @Override
    public String cancelar() {
        return "listagemCampus.xhtml";
    }

    
    @Override
    public String excluir() {
        if(dao.Apagar(entidade)){
            listagem = null;
            return "listagemCampus.xhtml";
        } else {
            return "";
        }
    }

    @Override
    public void filtrar() {
        listagem = dao.Buscar(filtro);
    }

    public CampusRepositorio getDao() {
        return dao;
    }

    public void setDao(CampusRepositorio dao) {
        this.dao = dao;
    }
    
   public void validaNome(FacesContext context, UIComponent component, Object value) throws ValidatorException{
       
      if (entidade.getId() == null || entidade.getId() == 0L) {

        Campus tmp = dao.Abrir(value.toString());
        
        if (tmp != null){
            FacesMessage msg
                    = new FacesMessage("Campus já cadastrado!");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
            
        }
        
     }  
    
   }
}

