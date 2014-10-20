/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.gestaoprojetos.Presentation.Reports;

import br.edu.ifnmg.gestaoprojetos.DomainModel.Projeto;
import br.edu.ifnmg.gestaoprojetos.DomainModel.ProjetoRepositorio;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author HOME
 */
@Named(value = "listagemprojetosController")
@SessionScoped
public class ListagemProjetosController 
    extends RelatorioGenericoController<Projeto> 
    implements Serializable {
    
    Projeto filtro;
    
    @EJB
    ProjetoRepositorio dao; 
    
    
   
    public ListagemProjetosController() {
     setCaminhoRelatorio("ListagemDeProjetos.jasper");
     setNomeRelatorio("ListagemDeProjetos");
     filtro = new Projeto();
    }
    
   public List<Projeto> getDados(){
       return dao.Buscar(filtro);   
   }

    public Projeto getFiltro() {
        return filtro;
    }

    public void setFiltro(Projeto filtro) {
        this.filtro = filtro;
    }

    
}
