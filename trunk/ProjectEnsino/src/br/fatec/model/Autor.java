package br.fatec.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tbl_autor")

public class Autor{
	
	@Id
	@Column(name="id_autor")
	private String id_autor;
		
	@Column(name="nome_autor")
	private String nome_autor;
	
	private int id;
	private String nome;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Autor(int id, String nome) {
		this.id = id;
		this.nome = nome;
	}
	
	public Autor () {}

}