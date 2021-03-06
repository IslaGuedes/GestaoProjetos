/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.gestaoprojetos.DataAcess;

import br.edu.ifnmg.gestaoprojetos.DomainModel.AreaConhecimento;
import br.edu.ifnmg.gestaoprojetos.DomainModel.TipoDocumento;
import br.edu.ifnmg.gestaoprojetos.DomainModel.TipoDocumentoRepositorio;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author HOME
 */
@Stateless(name= "TipoDocumentoRepositorio")
public class TipoDocumentoDAO 

    extends DAOGenerico<TipoDocumento>
    implements TipoDocumentoRepositorio{
    
    public TipoDocumentoDAO() {
        super(TipoDocumento.class);
    }
    
    @Override
    public List<TipoDocumento> Buscar(TipoDocumento obj) {
        String sql = "select t from TipoDocumento t";
        
        String filtros = "";
        
        if(obj != null){
            if(obj.getId() != null){
                filtros += "t.id = " + obj.getId();
            }
            if(obj.getNome() != null){
                if(filtros.length() > 0)
                    filtros += " and ";
                filtros += "t.nome like '%" + obj.getNome() + "%'"; 
            }
           
        }
        
        if(filtros.length() > 0){
            sql += " where " + filtros;
        }
        
        Query consulta = manager.createQuery(sql);
        
        return consulta.getResultList();
    }
    
     public TipoDocumento Abrir(String nome) {
        String sql = "select t from TipoDocumento t where t.nome = :s";
        
        Query consulta = manager.createQuery(sql);
        
        consulta.setParameter("s", nome);
        try {
            return (TipoDocumento)consulta.getSingleResult();
        } catch(Exception ex){
            return null;
        }
    }  
}
