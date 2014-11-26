/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.gestaoprojetos.DataAcess;

import br.edu.ifnmg.gestaoprojetos.DomainModel.Email;
import br.edu.ifnmg.gestaoprojetos.DomainModel.Usuario;
import br.edu.ifnmg.gestaoprojetos.DomainModel.UsuarioRepositorio;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author Isla Guedes
 */
@Stateless(name="UsuarioRepositorio")
public class UsuarioDAO

    extends DAOGenerico<Usuario>
    implements UsuarioRepositorio{
    
     public UsuarioDAO() {
        super(Usuario.class);
    }
    
     @Override
    public List<Usuario> Buscar(Usuario obj) {
        String sql = "select u from Usuario u";
        
        String filtros = "";
        
        if(obj != null ){
            if(obj.getId() != null && obj.getId() > 0 ){
                filtros += "u.id = " + obj.getId();
            }
            if(obj.getNome() != null){
                if(filtros.length() > 0)
                    filtros += " and ";
                filtros += "u.nome like '%" + obj.getNome() + "%'"; 
            }
            //if(obj.getCpf() != null){
              //  if(filtros.length() > 0)
                //    filtros += " and ";
               // filtros += "a.cpf like '%" + obj.getCpf() + "%'"; 
           // }
        }
        
        if(filtros.length() > 0){
            sql += " where " + filtros;
        }
        
        Query consulta = manager.createQuery(sql);
        
        return consulta.getResultList();
    }
     
    
     
     
  public Usuario porLogin(String nome){
      
        String sql = "select u from Usuario u where u.nome=:s";
                // Cria a consulta no JPA
        Query consulta = manager.createQuery(sql);

        // Aplica os parâmetros da consulta
        consulta.setParameter("s", nome);

        // Executa a consulta
       try{
        return (Usuario)consulta.getSingleResult();
       }catch(Exception e){
           return null;
       }

    }
  
  public Usuario AbrirRG(String rg) {
        String sql = "select u from Usuario u where u.rg = :s";
        
        Query consulta = manager.createQuery(sql);
        
        consulta.setParameter("s", rg);
        try {
            return (Usuario)consulta.getSingleResult();
        } catch(Exception ex){
            return null;
        }
     }
    
     
      
      
      public Usuario AbrirPorCPF(String cpf) {
       
        String sql = "select u from Usuario u where u.cpf = :s";
        
        Query consulta = manager.createQuery(sql);
        
        consulta.setParameter("s", cpf);
        
        try {
            return (Usuario)consulta.getSingleResult();
        } catch(Exception ex){
            return null;
        }
    }
    
    
}

