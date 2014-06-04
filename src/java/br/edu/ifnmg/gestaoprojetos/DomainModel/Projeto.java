/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.gestaoprojetos.DomainModel;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Isla Guedes
 */
@Entity
public class Projeto implements Entidade, Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    //identificaÃ§Ã£o do projeto
    
    private String titulo; 
    
    @Column(unique=true) //vai ser unique??
    private int numeroCadastro; 
    
    //informaÃ§Ãµes adicionais
    @ManyToMany(cascade= CascadeType.MERGE)
    private List<AreaConhecimento> areaConhecimento;
    
    private boolean grupoPesquisa;
        
    private String nomegrupoPesquisa;
    
    @ManyToOne  //VERIFICAR
    private Campus campus;
    
    //Resumo do projeto
    @Lob
    private String resumo;
    
    @Column(length=500)
    private String palavrasChave;
    
    //DuraÃ§Ã£o do projeto
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataInicio;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataTermino;
    
    //CoordenaÃ§Ã£o do projeto
    @ManyToOne(optional=false)  
    private Orientador coordenador;
     
     private String setorCoordenador;
     
     //Financiamento/IniciaÃ§Ã£o cientÃ­fica
     private boolean projetoFinanciamento;
     
     private BigInteger valorFinanciamento;
     
     @Temporal(javax.persistence.TemporalType.DATE)
     private Date dataFinanciamento;
     
     private boolean bolsaIniciacaoCientifica;
     
     private int numeroBolsas;
     
     @ManyToOne     
     private AgenciaFinanciadora agenciaFinanciadora; 
     
     //ConvÃªnio/GestÃ£o
     private boolean projetoConvenio;
     
     private String nomeConvenio;
     
     private boolean projetoFundacao;
     
     private String nomeProjetoFundacao;
     
     private boolean projetoMulticampi;
     
     //IdentificaÃ§Ã£o dos Participantes dos Projetos
     @ManyToMany
     private List<Usuario> participantesProjeto;   
     
     @ManyToOne
     private Edital edital;

    public Projeto() {
        this.areaConhecimento = new ArrayList<AreaConhecimento>();
    }
     
     public void addAreaConhecimento(AreaConhecimento a){
        if(areaConhecimento == null) {
            areaConhecimento = new ArrayList<AreaConhecimento>();
        }
        if(!areaConhecimento.contains(a)){
            areaConhecimento.add(a);
        }
        
    }
    
    public void removeAreaConhecimento(AreaConhecimento a){
        if(areaConhecimento.contains(a)){
            areaConhecimento.remove(a);
        }
    }
   
     //GETTER E SETTER

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getNumeroCadastro() {
        return numeroCadastro;
    }

    public void setNumeroCadastro(int numeroCadastro) {
        this.numeroCadastro = numeroCadastro;
    }

    public List<AreaConhecimento> getAreaConhecimento() {
        return areaConhecimento;
    }

    public void setAreaConhecimento(List<AreaConhecimento> areaConhecimento) {
        this.areaConhecimento = areaConhecimento;
    }

    public boolean isGrupoPesquisa() {
        return grupoPesquisa;
    }

    public void setGrupoPesquisa(boolean grupoPesquisa) {
        this.grupoPesquisa = grupoPesquisa;
    }

    public String getNomegrupoPesquisa() {
        return nomegrupoPesquisa;
    }

    public void setNomegrupoPesquisa(String nomegrupoPesquisa) {
        this.nomegrupoPesquisa = nomegrupoPesquisa;
    }

    public Campus getCampus() {
        return campus;
    }

    public void setCampus(Campus campus) {
        this.campus = campus;
    }

    public String getResumo() {
        return resumo;
    }

    public void setResumo(String resumo) {
        this.resumo = resumo;
    }

    public String getPalavrasChave() {
        return palavrasChave;
    }

    public void setPalavrasChave(String palavrasChave) {
        this.palavrasChave = palavrasChave;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataTermino() {
        return dataTermino;
    }

    public void setDataTermino(Date dataTermino) {
        this.dataTermino = dataTermino;
    }

    public Orientador getCoordenador() {
        return coordenador;
    }

    public void setCoordenador(Orientador coordenador) {
        this.coordenador = coordenador;
    }

    public String getSetorCoordenador() {
        return setorCoordenador;
    }

    public void setSetorCoordenador(String setorCoordenador) {
        this.setorCoordenador = setorCoordenador;
    }

    public boolean isProjetoFinanciamento() {
        return projetoFinanciamento;
    }

    public void setProjetoFinanciamento(boolean projetoFinanciamento) {
        this.projetoFinanciamento = projetoFinanciamento;
    }

    public BigInteger getValorFinanciamento() {
        return valorFinanciamento;
    }

    public void setValorFinanciamento(BigInteger valorFinanciamento) {
        this.valorFinanciamento = valorFinanciamento;
    }

    public Date getDataFinanciamento() {
        return dataFinanciamento;
    }

    public void setDataFinanciamento(Date dataFinanciamento) {
        this.dataFinanciamento = dataFinanciamento;
    }

    public boolean isBolsaIniciacaoCientifica() {
        return bolsaIniciacaoCientifica;
    }

    public void setBolsaIniciacaoCientifica(boolean bolsaIniciacaoCientifica) {
        this.bolsaIniciacaoCientifica = bolsaIniciacaoCientifica;
    }

    public int getNumeroBolsas() {
        return numeroBolsas;
    }

    public void setNumeroBolsas(int numeroBolsas) {
        this.numeroBolsas = numeroBolsas;
    }

    public AgenciaFinanciadora getAgenciaFinanciadora() {
        return agenciaFinanciadora;
    }

    public void setAgenciaFinanciadora(AgenciaFinanciadora agenciaFinanciadora) {
        this.agenciaFinanciadora = agenciaFinanciadora;
    }

    public boolean isProjetoConvenio() {
        return projetoConvenio;
    }

    public void setProjetoConvenio(boolean projetoConvenio) {
        this.projetoConvenio = projetoConvenio;
    }

    public String getNomeConvenio() {
        return nomeConvenio;
    }

    public void setNomeConvenio(String nomeConvenio) {
        this.nomeConvenio = nomeConvenio;
    }

    public boolean isProjetoFundacao() {
        return projetoFundacao;
    }

    public void setProjetoFundacao(boolean projetoFundacao) {
        this.projetoFundacao = projetoFundacao;
    }

    public String getNomeProjetoFundacao() {
        return nomeProjetoFundacao;
    }

    public void setNomeProjetoFundacao(String nomeProjetoFundacao) {
        this.nomeProjetoFundacao = nomeProjetoFundacao;
    }

    public boolean isProjetoMulticampi() {
        return projetoMulticampi;
    }

    public void setProjetoMulticampi(boolean projetoMulticampi) {
        this.projetoMulticampi = projetoMulticampi;
    }

    public List<Usuario> getParticipantesProjeto() {
        return participantesProjeto;
    }

    public void setParticipantesProjeto(List<Usuario> participantesProjeto) {
        this.participantesProjeto = participantesProjeto;
    }

    public Edital getEdital() {
        return edital;
    }

    public void setEdital(Edital edital) {
        this.edital = edital;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 67 * hash + this.numeroCadastro;
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
        final Projeto other = (Projeto) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        if (this.numeroCadastro != other.numeroCadastro) {
            return false;
        }
        return true;
    }


    @Override
    public String toString() {
        return "Projeto{" + "id=" + id + '}';
    }

  
}
