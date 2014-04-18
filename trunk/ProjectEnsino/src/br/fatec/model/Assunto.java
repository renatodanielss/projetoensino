package br.fatec.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tbl_assunto")

public class Assunto {
	@Id
	@Column(name="iddisciplina_assunto")
	private Integer idDisciplina_assunto;
	
	@Column(name="nome_assunto")
	private String nome_assunto;

	public Integer getIdDisciplina_assunto() {
		return idDisciplina_assunto;
	}

	public void setIdDisciplina_assunto(Integer idDisciplina_assunto) {
		this.idDisciplina_assunto = idDisciplina_assunto;
	}

	public String getNome_assunto() {
		return nome_assunto;
	}

	public void setNome_assunto(String nome_assunto) {
		this.nome_assunto = nome_assunto;
	}
}
