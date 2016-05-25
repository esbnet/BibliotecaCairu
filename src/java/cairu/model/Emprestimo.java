/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cairu.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author edmilson
 */
@Entity
@Table(name = "emprestimo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Emprestimo.findAll", query = "SELECT e FROM Emprestimo e"),
    @NamedQuery(name = "Emprestimo.findByIdEmprestimo", query = "SELECT e FROM Emprestimo e WHERE e.idEmprestimo = :idEmprestimo"),
    @NamedQuery(name = "Emprestimo.findByDataEmprestimo", query = "SELECT e FROM Emprestimo e WHERE e.dataEmprestimo = :dataEmprestimo"),
    @NamedQuery(name = "Emprestimo.findByDataDevolucao", query = "SELECT e FROM Emprestimo e WHERE e.dataDevolucao = :dataDevolucao")})
public class Emprestimo implements Serializable {

    private static final long serialVersionUID = 1L;

    //Chave primária 
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idEmprestimo")
    private Integer idEmprestimo;

    //Data do empréstimo
    @Basic(optional = false)
    @NotNull
    @Column(name = "dataEmprestimo")
    @Temporal(TemporalType.DATE)
    private Date dataEmprestimo;
    
    //Data para devolução
    @Basic(optional = false)
    @NotNull
    @Column(name = "dataDevolucao")
    @Temporal(TemporalType.DATE)
    private Date dataDevolucao;
    
    /* Relação de um para muitos com dois campos Primary Key
    * Relação com a tabela 'livroemprestimo' que guarda todos os livros referentes a um empréstimo
    * Esta tabela tem dois campos na chave primária, livroEmprestimoId e livroEmprestadoId
    * que se relacionam respectivamente com as tabelas Emprestimo e Livro
    */
    @JoinTable(name = "livroemprestimo", joinColumns = {
        @JoinColumn(name = "livroEmprestimoId", referencedColumnName = "idEmprestimo")}, inverseJoinColumns = {
        @JoinColumn(name = "livroEmprestadoId", referencedColumnName = "idLivro")})
    @ManyToMany
    private Collection<Livro> livroCollection;
    
    /*
    *  Relação de um para muitos entre tabela de Empréstimo e Usuário do empréstimo
    */
    @JoinColumn(name = "usuarioId", referencedColumnName = "idMatricula")
    @ManyToOne(optional = false)
    private Usuario usuarioId;

    // Construtor da classe
    public Emprestimo() {
    }

    //Construtor da classe com o campo idEmprestimo
    public Emprestimo(Integer idEmprestimo) {
        this.idEmprestimo = idEmprestimo;
    }

    //Construtor da classe com idEmprestimo, dataEmprestimo e dataDevolucao
    public Emprestimo(Integer idEmprestimo, Date dataEmprestimo, Date dataDevolucao) {
        this.idEmprestimo = idEmprestimo;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
    }

    public Integer getIdEmprestimo() {
        return idEmprestimo;
    }

    public void setIdEmprestimo(Integer idEmprestimo) {
        this.idEmprestimo = idEmprestimo;
    }

    public Date getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(Date dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public Date getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    @XmlTransient
    public Collection<Livro> getLivroCollection() {
        return livroCollection;
    }

    public void setLivroCollection(Collection<Livro> livroCollection) {
        this.livroCollection = livroCollection;
    }

    public Usuario getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Usuario usuarioId) {
        this.usuarioId = usuarioId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEmprestimo != null ? idEmprestimo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Emprestimo)) {
            return false;
        }
        Emprestimo other = (Emprestimo) object;
        if ((this.idEmprestimo == null && other.idEmprestimo != null) || (this.idEmprestimo != null && !this.idEmprestimo.equals(other.idEmprestimo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cairu.model.Emprestimo[ idEmprestimo=" + idEmprestimo + " ]";
    }
    
}
