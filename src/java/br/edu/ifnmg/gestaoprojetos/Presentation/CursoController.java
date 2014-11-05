/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.gestaoprojetos.Presentation;

import br.edu.ifnmg.gestaoprojetos.DomainModel.Campus;
import br.edu.ifnmg.gestaoprojetos.DomainModel.Curso;
import br.edu.ifnmg.gestaoprojetos.DomainModel.CursoRepositorio;
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
@Named(value = "cursoController")
@SessionScoped
public class CursoController
   extends ControllerGenerico<Curso> implements Serializable {

    /**
     * Creates a new instance of CursoController
     */
    public CursoController() {
        filtro = new Curso();
        entidade = new Curso();
    }
    
    @EJB
    CursoRepositorio dao;
    
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
        entidade = new Curso();
        return "editarCurso.xhtml";
    }
    
    @Override
    public String abrir() {
        return "editarCurso.xhtml";
    }

    @Override
    public String cancelar() {
        return "listagemCurso.xhtml";
    }

    
    @Override
    public String excluir() {
        if(dao.Apagar(entidade)){
            listagem = null;
            return "listagemCurso.xhtml";
        } else {
            return "";
        }
    }

    @Override
    public void filtrar() {
        listagem = dao.Buscar(filtro);
    }

    public CursoRepositorio getDao() {
        return dao;
    }

    public void setDao(CursoRepositorio dao) {
        this.dao = dao;
    }
    
    public void validaNome(FacesContext context, UIComponent component, Object value) throws ValidatorException{
       
        Curso tmp = dao.Abrir(value.toString());
        
        if (tmp != null){
            FacesMessage msg
                    = new FacesMessage("Curso já cadastrado!");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
            
        }
        
    }   
    

}
