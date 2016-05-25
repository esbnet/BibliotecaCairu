/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cairu.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author edmilson
 */
@Entity
@Table(name = "editora")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Editora.findAll", query = "SELECT e FROM Editora e"),
    @NamedQuery(name = "Editora.findByIdEditora", query = "SELECT e FROM Editora e WHERE e.idEditora = :idEditora"),
    @NamedQuery(name = "Editora.findByNome", query = "SELECT e FROM Editora e WHERE e.nome = :nome")})
public class Editora implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idEditora")
    private Integer idEditora;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nome")
    private String nome;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "editoraId")
    private Collection<Livro> livroCollection;

    public Editora() {
    }

    public Editora(Integer idEditora) {
        this.idEditora = idEditora;
    }

    public Editora(Integer idEditora, String nome) {
        this.idEditora = idEditora;
        this.nome = nome;
    }

    public Integer getIdEditora() {
        return idEditora;
    }

    public void setIdEditora(Integer idEditora) {
        this.idEditora = idEditora;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @XmlTransient
    public Collection<Livro> getLivroCollection() {
        return livroCollection;
    }

    public void setLivroCollection(Collection<Livro> livroCollection) {
        this.livroCollection = livroCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEditora != null ? idEditora.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Editora)) {
            return false;
        }
        Editora other = (Editora) object;
        if ((this.idEditora == null && other.idEditora != null) || (this.idEditora != null && !this.idEditora.equals(other.idEditora))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cairu.model.Editora[ idEditora=" + idEditora + " ]";
    }
    
}
