/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.gestaoprojetos.DataAcess;

import br.edu.ifnmg.gestaoprojetos.DomainModel.Projeto;
import br.edu.ifnmg.gestaoprojetos.DomainModel.ProjetoRepositorio;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author Isla Guedes
 */
@Stateless(name="ProjetoRepositorio")
public class ProjetoDAO
    
    extends DAOGenerico<Projeto>
    implements ProjetoRepositorio{
    
    public ProjetoDAO() {
        super(Projeto.class);
    }
    
    @Override
    public List<Projeto> Buscar(Projeto obj) {
        // Corpo da consulta
        String consulta = "select p from Projeto p";

        // A parte where da consulta
        String filtro = "";

        // Guarda a lista de parâmetros da query
        HashMap<String, Object> parametros = new HashMap<String, Object>();

        // Verifica campo por campo os valores que serão filtrados
        if(obj!=null)
            
        if (obj.getNumeroCadastro() > 0) {
           
            filtro += " p.numeroCadastro=:numeroCadastro";
            parametros.put("numeroCadastro", obj.getNumeroCadastro());
        }

        if (obj.getTitulo() != null && obj.getTitulo().length() > 0) {
            if (filtro.length() > 0) {
                    filtro = filtro + " and ";
                }
             filtro += " lower(p.titulo) like lower('%" + obj.getTitulo() + "%')";
        }


        if (obj.getAgenciaFinanciadora() != null && obj.getAgenciaFinanciadora().toString().length() > 0) {
            if (filtro.length() > 0) {
                filtro = filtro + " and ";
            }
            filtro += " p.agenciaFinanciadora=:agenciaFinanciadora";
            parametros.put("agenciaFinanciadora", obj.getAgenciaFinanciadora());
        }

        if (obj.getModalidade() != null && obj.getModalidade().toString().length() > 0) {
            if (filtro.length() > 0) {
                filtro = filtro + " and ";
            }
            filtro += " p.modalidade=:modalidade";
            parametros.put("modalidade", obj.getModalidade());
        }

        if (obj.getId() != null && obj.getId() > 0) {
            if (filtro.length() > 0) {
                filtro = filtro + " and ";
            }
            filtro += " p.id =:id ";
            parametros.put("id", obj.getId());
        }

       

        if (obj.getDataInicio() != null && obj.getDataInicio().toString().length() > 0) {
            if (filtro.length() > 0) {
                filtro = filtro + " and ";
            }
            filtro += " p.dataInicio=:dataInicio";
            parametros.put("dataInicio", obj.getDataInicio());
        }

        if (obj.getDataTermino() != null && obj.getDataTermino().toString().length() > 0) {
            if (filtro.length() > 0) {
                filtro = filtro + " and ";
            }
            filtro += " p.dataTermino=:dataTermino ";
            parametros.put("dataTermino", obj.getDataTermino());
        }

        // Se houver filtros, coloca o "where" na consulta
        if (filtro.length() > 0) {
            consulta = consulta + " where " + filtro;
        }

        // Cria a consulta no JPA
        Query query = manager.createQuery(consulta);

        // Aplica os parâmetros da consulta
        for (String par : parametros.keySet()) {
            query.setParameter(par, parametros.get(par));
        }

        // Executa a consulta
        return query.getResultList();
    }
        
    
}

