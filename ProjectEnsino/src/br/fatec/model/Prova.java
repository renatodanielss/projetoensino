package br.fatec.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tbl_prova")

public class Prova {
	@Id
	@Column(name="id_prova")
	private String id_prova;
	
	@Column(name="disciplina_prova")
	private String disciplina_prova;
	
	public String getId_prova() {
		return id_prova;
	}

	public void setId_prova(String id_prova) {
		this.id_prova = id_prova;
	}

	public String getDisciplina_prova() {
		return disciplina_prova;
	}

	public void setDisciplina_prova(String disciplina_prova) {
		this.disciplina_prova = disciplina_prova;
	}


	
	
	
}
