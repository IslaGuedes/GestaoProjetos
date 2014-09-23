/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.gestaoprojetos.DomainModel;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Isla Guedes
 */
@Entity
public class Documento implements Serializable, Entidade {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @ManyToOne
    private Usuario funcionarioRecebedor;
    
    @ManyToOne
    private TipoDocumento tipoDocumento;
    
    @Temporal(javax.persistence.TemporalType.DATE) 
    private Date dataPrevista;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataEfetiva;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getFuncionarioRecebedor() {
        return funcionarioRecebedor;
    }

    public void setFuncionarioRecebedor(Usuario funcionarioRecebedor) {
        this.funcionarioRecebedor = funcionarioRecebedor;
    }

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public Date getDataPrevista() {
        return dataPrevista;
    }

    public void setDataPrevista(Date dataPrevista) {
        this.dataPrevista = dataPrevista;
    }

    public Date getDataEfetiva() {
        return dataEfetiva;
    }

    public void setDataEfetiva(Date dataEfetiva) {
        this.dataEfetiva = dataEfetiva;
    }
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Documento other = (Documento) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        if (this.funcionarioRecebedor != other.funcionarioRecebedor && (this.funcionarioRecebedor == null || !this.funcionarioRecebedor.equals(other.funcionarioRecebedor))) {
            return false;
        }
        if (this.tipoDocumento != other.tipoDocumento && (this.tipoDocumento == null || !this.tipoDocumento.equals(other.tipoDocumento))) {
            return false;
        }
        if (this.dataPrevista != other.dataPrevista && (this.dataPrevista == null || !this.dataPrevista.equals(other.dataPrevista))) {
            return false;
        }
        if (this.dataEfetiva != other.dataEfetiva && (this.dataEfetiva == null || !this.dataEfetiva.equals(other.dataEfetiva))) {
            return false;
        }
        return true;
    }

    

    @Override
    public String toString() {
        return "br.edu.ifnmg.gestaoprojetos.DomainModel.Documento[ id=" + id + " ]";
    }
    
}
