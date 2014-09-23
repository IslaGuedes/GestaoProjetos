/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.gestaoprojetos.Presentation;

import br.edu.ifnmg.gestaoprojetos.DomainModel.AgenciaFinanciadora;
import br.edu.ifnmg.gestaoprojetos.DomainModel.Aluno;
import br.edu.ifnmg.gestaoprojetos.DomainModel.AlunoRepositorio;
import br.edu.ifnmg.gestaoprojetos.DomainModel.Email;
import br.edu.ifnmg.gestaoprojetos.DomainModel.Endereco;
import br.edu.ifnmg.gestaoprojetos.DomainModel.Telefone;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.GregorianCalendar;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import sun.util.calendar.CalendarDate;

/**
 *
 * @author Isla Guedes
 */
@Named(value = "alunoController")
@SessionScoped
public class AlunoController 
    extends ControllerGenerico<Aluno> implements Serializable {
    
     Email email;
     Telefone telefone;
     Endereco endereco;

    /**
     * Creates a new instance of AlunoController
     */
    public AlunoController() {
        
        filtro = new Aluno();
        entidade = new Aluno();
        email= new Email();
        telefone = new Telefone();
        endereco = new Endereco();
        
    }
    
    @EJB
    AlunoRepositorio dao;
    
    public void exibirMensagem(String msg) {
       FacesContext context = FacesContext.getCurrentInstance();
       context.addMessage(null, new FacesMessage(msg));
    }
     
    @Override
    public void salvar() {
       GregorianCalendar datahoje = new GregorianCalendar();
       
        if(dao.Salvar(entidade)){           
            
            if (entidade.getNome().trim().length() == 0) {
                exibirMensagem("preencha o campo Nome Completo com caracteres diferentes de espaço!");
              return;
            }
            
            if (entidade.getMatricula() == 0) {
                exibirMensagem("preencha o campo Matrícula com caracteres diferentes de espaço!");
              return;
            }
            
            if (entidade.getDataNascimento() != null) {
             if (entidade.getDataNascimento().after(datahoje.getTime())) {
                exibirMensagem("Data de Nascimento não pode ser maior que a Data de hoje!");
                 return;
             }
            }
            
            if (entidade.getNaturalidadeUF().trim().length() == 0) {
                exibirMensagem("preencha o campo Naturalidade-UF com caracteres diferentes de espaço!");
              return;
            }
            
             if (entidade.getNacionalidade().trim().length() == 0) {
                exibirMensagem("preencha o campo Nacionaliade com caracteres diferentes de espaço!");
              return;
            }
             
            if (entidade.getEstado().trim().length() == 0) {
                exibirMensagem("preencha o campo Estado com caracteres diferentes de espaço!");
              return;
            }
            
        listagem = null;
        exibirMensagem("Operação realizada com Sucesso!");
  
            
        }
    }

    @Override
    public String novo(){
        entidade = new Aluno();
        return "editarAluno.xhtml";
    }
    
    @Override
    public String abrir() {
        return "editarAluno.xhtml";
    }

    @Override
    public String cancelar() {
        return "listagemAluno.xhtml";
    }

    
    @Override
    public String excluir() {
        if(dao.Apagar(entidade)){
            listagem = null;
            exibirMensagem("Apagado com sucesso!");
        }
      return "listagemAluno.xhtml";
    }

    @Override
    public void filtrar() {
        listagem = dao.Buscar(filtro);
    }

    
    
   //getter e setter
    
    public AlunoRepositorio getDao() {
        return dao;
    }

    public void setDao(AlunoRepositorio dao) {
        this.dao = dao;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public Telefone getTelefone() {
        return telefone;
    }

    public void setTelefone(Telefone telefone) {
        this.telefone = telefone;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
    
    
    
    //Metodos
    
    
    public void addTelefone(){
        //entidade = dao.Refresh(entidade.getId());
        entidade.addTelefone(telefone);
        dao.Salvar(entidade);
        telefone = new Telefone();
    }
    
    public void addEndereco(){
        entidade.addEndereco(endereco);
        dao.Salvar(entidade);
        endereco = new Endereco();
    }
    
    public void addEmail(){
        entidade.addEmail(email);
        dao.Salvar(entidade);
        email = new Email();
    }
    
    public void removeEndereco(){
        entidade.removeEndereco(endereco);
        dao.Salvar(entidade);
        endereco = new Endereco();
    }
    
    public void removeTelefone(){
        entidade.removeTelefone(telefone);
        dao.Salvar(entidade);
        telefone = new Telefone();
    }
     
   public void removeEmail(){
        entidade.removeEmail(email);
        dao.Salvar(entidade);
        email = new Email();
   }
   
   
   public void validaMatricula(FacesContext context, UIComponent component, Object value) throws ValidatorException{
       
       if (entidade.getId() == null || entidade.getId() == 0L ){
       
        Aluno tmp = dao.Abrir(Integer.parseInt(value.toString()));
        
        if (tmp != null){
            FacesMessage msg
                    = new FacesMessage("Matrícula já cadastrada!");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
            
        }
       }
    }
    

}