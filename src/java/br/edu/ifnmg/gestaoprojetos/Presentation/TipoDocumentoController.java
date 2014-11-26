/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.gestaoprojetos.Presentation;

import br.edu.ifnmg.gestaoprojetos.DomainModel.AreaConhecimento;
import br.edu.ifnmg.gestaoprojetos.DomainModel.TipoDocumento;
import br.edu.ifnmg.gestaoprojetos.DomainModel.TipoDocumentoRepositorio;
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
@Named(value = "tipoDocumentoController")
@SessionScoped
public class TipoDocumentoController 
    extends ControllerGenerico<TipoDocumento> implements Serializable {

    /**
     * Creates a new instance of TipoDocumentoController
     */
    public TipoDocumentoController() {
        filtro = new TipoDocumento();
        entidade = new TipoDocumento();
    }
    
    @EJB
    TipoDocumentoRepositorio dao;
    
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
        entidade = new TipoDocumento();
        return "editarTipoDocumento.xhtml";
    }
    
    @Override
    public String abrir() {
        return "editarTipoDocumento.xhtml";
    }

    @Override
    public String cancelar() {
        return "listagemTipoDocumento.xhtml";
    }

    
    @Override
    public String excluir() {
        if(dao.Apagar(entidade)){
            listagem = null;  
            return "listagemTipoDocumento.xhtml";
        } else {
            return "";
        }
    }

    @Override
    public void filtrar() {
        listagem = dao.Buscar(filtro);
    }

    public TipoDocumentoRepositorio getDao() {
        return dao;
    }

    public void setDao(TipoDocumentoRepositorio dao) {
        this.dao = dao;
    }
    
    public void validaNome(FacesContext context, UIComponent component, Object value) throws ValidatorException{
       
      if (entidade.getId() == null || entidade.getId() == 0L) {

        TipoDocumento tmp = dao.Abrir(value.toString());
        
        if (tmp != null){
            FacesMessage msg
                    = new FacesMessage("Tipo de Documento já cadastrado!");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
            
        }
      }   
    }

    
}
