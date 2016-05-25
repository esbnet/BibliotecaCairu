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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author edmilson
 */
@Entity
@Table(name = "livro")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Livro.findAll", query = "SELECT l FROM Livro l"),
    @NamedQuery(name = "Livro.findByIdLivro", query = "SELECT l FROM Livro l WHERE l.idLivro = :idLivro"),
    @NamedQuery(name = "Livro.findByIsbn", query = "SELECT l FROM Livro l WHERE l.isbn = :isbn"),
    @NamedQuery(name = "Livro.findByTitulo", query = "SELECT l FROM Livro l WHERE l.titulo = :titulo"),
    @NamedQuery(name = "Livro.findByEdicao", query = "SELECT l FROM Livro l WHERE l.edicao = :edicao"),
    @NamedQuery(name = "Livro.findByVolume", query = "SELECT l FROM Livro l WHERE l.volume = :volume"),
    @NamedQuery(name = "Livro.findByPaginas", query = "SELECT l FROM Livro l WHERE l.paginas = :paginas"),
    @NamedQuery(name = "Livro.findByLocalizacao", query = "SELECT l FROM Livro l WHERE l.localizacao = :localizacao"),
    @NamedQuery(name = "Livro.findByTipoAcervo", query = "SELECT l FROM Livro l WHERE l.tipoAcervo = :tipoAcervo"),
    @NamedQuery(name = "Livro.findByDataPublicacao", query = "SELECT l FROM Livro l WHERE l.dataPublicacao = :dataPublicacao")})
public class Livro implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idLivro")
    private Integer idLivro;
    @Size(max = 30)
    @Column(name = "isbn")
    private String isbn;
    @Size(max = 255)
    @Column(name = "titulo")
    private String titulo;
    @Size(max = 45)
    @Column(name = "edicao")
    private String edicao;
    @Size(max = 45)
    @Column(name = "volume")
    private String volume;
    @Column(name = "paginas")
    private Integer paginas;
    @Size(max = 45)
    @Column(name = "localizacao")
    private String localizacao;
    @Size(max = 45)
    @Column(name = "tipoAcervo")
    private String tipoAcervo;
    @Column(name = "dataPublicacao")
    @Temporal(TemporalType.DATE)
    private Date dataPublicacao;
    @ManyToMany(mappedBy = "livroCollection")
    private Collection<Emprestimo> emprestimoCollection;
    @ManyToMany(mappedBy = "livroCollection")
    private Collection<Reserva> reservaCollection;
    @JoinColumn(name = "autorId", referencedColumnName = "idAutor")
    @ManyToOne(optional = false)
    private Autor autorId;
    @JoinColumn(name = "editoraId", referencedColumnName = "idEditora")
    @ManyToOne(optional = false)
    private Editora editoraId;

    public Livro() {
    }

    public Livro(Integer idLivro) {
        this.idLivro = idLivro;
    }

    public Integer getIdLivro() {
        return idLivro;
    }

    public void setIdLivro(Integer idLivro) {
        this.idLivro = idLivro;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEdicao() {
        return edicao;
    }

    public void setEdicao(String edicao) {
        this.edicao = edicao;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public Integer getPaginas() {
        return paginas;
    }

    public void setPaginas(Integer paginas) {
        this.paginas = paginas;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public String getTipoAcervo() {
        return tipoAcervo;
    }

    public void setTipoAcervo(String tipoAcervo) {
        this.tipoAcervo = tipoAcervo;
    }

    public Date getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(Date dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    @XmlTransient
    public Collection<Emprestimo> getEmprestimoCollection() {
        return emprestimoCollection;
    }

    public void setEmprestimoCollection(Collection<Emprestimo> emprestimoCollection) {
        this.emprestimoCollection = emprestimoCollection;
    }

    @XmlTransient
    public Collection<Reserva> getReservaCollection() {
        return reservaCollection;
    }

    public void setReservaCollection(Collection<Reserva> reservaCollection) {
        this.reservaCollection = reservaCollection;
    }

    public Autor getAutorId() {
        return autorId;
    }

    public void setAutorId(Autor autorId) {
        this.autorId = autorId;
    }

    public Editora getEditoraId() {
        return editoraId;
    }

    public void setEditoraId(Editora editoraId) {
        this.editoraId = editoraId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLivro != null ? idLivro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Livro)) {
            return false;
        }
        Livro other = (Livro) object;
        if ((this.idLivro == null && other.idLivro != null) || (this.idLivro != null && !this.idLivro.equals(other.idLivro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cairu.model.Livro[ idLivro=" + idLivro + " ]";
    }
    
}
