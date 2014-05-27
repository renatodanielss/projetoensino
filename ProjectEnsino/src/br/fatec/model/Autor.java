package br.fatec.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="tbl_autor")

public class Autor{
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
}
