package br.fatec.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="tbl_autor")

public class Autor implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AUTOR_ID")
	@SequenceGenerator(name="AUTOR_ID", sequenceName="AUTOR_SEQ", allocationSize=1)
	@Column(name="id_autor")
	private Integer id_autor;	
	@Column(name="nome_autor")
	private String nome_autor;
	
	public Integer getId_autor() {
		return id_autor;
	}
	public void setId_autor(Integer id_autor) {
		this.id_autor = id_autor;
	}
	public String getNome_autor() {
		return nome_autor;
	}
	public void setNome_autor(String nome_autor) {
		this.nome_autor = nome_autor;
	}
	
	@Override
	public boolean equals(Object object) {
        if (object == this)
            return true;
        if ((object == null) || !(object instanceof Autor))
            return false;
 
        final Autor autor = (Autor)object;
 
        if (this.id_autor != null && autor.getId_autor() != null) {
            return this.id_autor.equals(autor.getId_autor());
        }
        return false;
    }
}
