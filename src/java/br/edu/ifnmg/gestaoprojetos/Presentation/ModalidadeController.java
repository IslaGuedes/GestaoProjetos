/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.gestaoprojetos.Presentation;

import br.edu.ifnmg.gestaoprojetos.DomainModel.AgenciaFinanciadora;
import br.edu.ifnmg.gestaoprojetos.DomainModel.Modalidade;
import br.edu.ifnmg.gestaoprojetos.DomainModel.ModalidadeRepositorio;
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
 * @author HOME
 */
@Named(value = "modalidadeController")
@SessionScoped
public class ModalidadeController 
    extends ControllerGenerico<Modalidade> implements Serializable {

    /**
     * Creates a new instance of ModalidadeController
     */
    public ModalidadeController() {
        filtro = new Modalidade();
        entidade = new Modalidade(); 
    }
    
    @EJB
    ModalidadeRepositorio dao;
    
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
        entidade = new Modalidade();
        return "editarModalidade.xhtml";
    }
    
    @Override
    public String abrir() {
        return "editarModalidade.xhtml";
    }

    @Override
    public String cancelar() {
        return "listagemModalidade.xhtml";
    }

    
    @Override
    public String excluir() {
        if(dao.Apagar(entidade)){
            listagem = null;
            return "listagemModalidade.xhtml";
        } else {
            return "";
        }
    }

    @Override
    public void filtrar() {
        listagem = dao.Buscar(filtro);
    }

    public ModalidadeRepositorio getDao() {
        return dao;
    }

    public void setDao(ModalidadeRepositorio dao) {
        this.dao = dao;
    }
    
     public void validaSigla(FacesContext context, UIComponent component, Object value) throws ValidatorException{
       
        Modalidade tmp = dao.Abrir(value.toString());
        
        if (tmp != null){
            FacesMessage msg
                    = new FacesMessage("Sigla já cadastrada!");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
            
        }
        
    }
}
