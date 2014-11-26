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
import br.edu.ifnmg.gestaoprojetos.DomainModel.Usuario;
import br.edu.ifnmg.gestaoprojetos.DomainModel.UsuarioRepositorio;
import br.edu.ifnmg.gestaoprojetos.DomainModel.ValidadorCPF;
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
    UsuarioRepositorio daoUsuario;
    
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

    public UsuarioRepositorio getDaoUsuario() {
        return daoUsuario;
    }

    public void setDaoUsuario(UsuarioRepositorio daoUsuario) {
        this.daoUsuario = daoUsuario;
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
   
   
   
   
    public void validaRG(FacesContext context, UIComponent component, Object value) throws ValidatorException {

        if (entidade.getId() == null || entidade.getId() == 0L) {

            Usuario tmp = daoUsuario.AbrirRG(value.toString());

            if (tmp != null) {
                FacesMessage msg = new FacesMessage("RG já cadastrado!");
                msg.setSeverity(FacesMessage.SEVERITY_ERROR);
                throw new ValidatorException(msg);

            }
        }
    }
    
  
    
     public void validaCPF(FacesContext context, UIComponent component, Object value) throws ValidatorException {

        if (entidade.getId() == null || entidade.getId() == 0L) {

            if (value == null || value.toString() == "") {
                FacesMessage msg = new FacesMessage("CPF vazio!");
                msg.setSeverity(FacesMessage.SEVERITY_ERROR);
                throw new ValidatorException(msg);
            }

            Usuario tmp = daoUsuario.AbrirPorCPF(value.toString());

            if (tmp != null && !tmp.equals(entidade)) {
                FacesMessage msg = new FacesMessage("CPF já cadastrado!");
                msg.setSeverity(FacesMessage.SEVERITY_ERROR);
                throw new ValidatorException(msg);
            }

            if (!ValidadorCPF.validaCPF(value.toString())) {
                FacesMessage msg = new FacesMessage("CPF Inválido!");
                msg.setSeverity(FacesMessage.SEVERITY_ERROR);
                throw new ValidatorException(msg);
            }
        }
    }
    
     
    

}