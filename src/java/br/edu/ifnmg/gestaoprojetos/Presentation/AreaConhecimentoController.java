/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.gestaoprojetos.Presentation;

import br.edu.ifnmg.gestaoprojetos.DomainModel.AgenciaFinanciadora;
import br.edu.ifnmg.gestaoprojetos.DomainModel.AreaConhecimento;
import br.edu.ifnmg.gestaoprojetos.DomainModel.AreaConhecimentoRepositorio;
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
@Named(value = "areaConhecimentoController")
@SessionScoped
public class AreaConhecimentoController
    extends ControllerGenerico<AreaConhecimento> implements Serializable {

    /**
     * Creates a new instance of AreaConhecimentoController
     */
    public AreaConhecimentoController() {
        filtro = new AreaConhecimento();
        entidade = new AreaConhecimento(); 
    }
    
    @EJB
    AreaConhecimentoRepositorio dao;
    
    public void exibirMensagem(String msg) {
       FacesContext context = FacesContext.getCurrentInstance();
       context.addMessage(null, new FacesMessage(msg));
    }
    
    @Override
    public void salvar() {
        if(dao.Salvar(entidade)){
            
         if (entidade.getNome().trim().length() == 0) {
           exibirMensagem("preencha o campo Nome com caracteres diferentes de espaço!");
           return;
         }
            
          listagem = null; 
          exibirMensagem("Operação realizada com Sucesso!");
        } 
    }

    @Override
    public String novo(){
        entidade = new AreaConhecimento();
        return "editarAreaConhecimento.xhtml";
    }
    
    @Override
    public String abrir() {
        return "editarAreaConhecimento.xhtml";
    }

    @Override
    public String cancelar() {
        return "listagemAreaConhecimento.xhtml";
    }

    
    @Override
    public String excluir() {
        if(dao.Apagar(entidade)){
            listagem = null;  
            exibirMensagem("Apagado com sucesso!");
        } 
     return "listagemAreaConhecimento.xhtml";
    }

    @Override
    public void filtrar() {
        listagem = dao.Buscar(filtro);
    }

    public AreaConhecimentoRepositorio getDao() {
        return dao;
    }

    public void setDao(AreaConhecimentoRepositorio dao) {
        this.dao = dao;
    }
    
     public void validaNome(FacesContext context, UIComponent component, Object value) throws ValidatorException{
     
        
       
        AreaConhecimento tmp = dao.Abrir(value.toString());
        
         if (tmp != null){
            FacesMessage msg
                    = new FacesMessage("Área de Conhecimento já cadastrada!");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
            
          }
        
     }   
    
}

