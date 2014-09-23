/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.gestaoprojetos.DomainModel;

import javax.ejb.Local;

/**
 *
 * @author Isla Guedes
 */
@Local
public interface UsuarioRepositorio 
    
    extends Repositorio<Usuario>
    
    {
    public Usuario AbrirPorCPF(String cpf);
    public Usuario Abrir(String rg);
    public Usuario AbrirEmail(String email);
    public Usuario porLogin(String nome);
    
}

