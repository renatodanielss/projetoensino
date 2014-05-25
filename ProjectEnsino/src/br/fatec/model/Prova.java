package br.fatec.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="tbl_prova")
public class Prova {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PROVA_ID")
	@SequenceGenerator(name="PROVA_ID", sequenceName="PROVA_SEQ", allocationSize=1)
	@Column(name="id_prova")
	private Integer id_prova;
	
	@Column(name="titulo_prova")
	private String titulo_prova;
	
	@ManyToOne
    @JoinColumn(name="disciplina_prova")
	private Disciplina disciplina_prova;
	
	@Column(name="numeroquestoes_prova")
	private int numeroQuestoes_prova;
	
	@ManyToMany
    @JoinTable(name="tbl_questaoprova", joinColumns={@JoinColumn(name="id_prova")}, inverseJoinColumns={@JoinColumn(name="id_questao")})
	private List<Questao> questoes_prova;
	
	public Integer getId_prova() {
		return id_prova;
	}

	public void setId_prova(Integer id_prova) {
		this.id_prova = id_prova;
	}
	
	public String getTitulo_prova(){
		return titulo_prova;
	}
	
	public void setTitulo_prova(String titulo_prova){
		this.titulo_prova = titulo_prova;
	}
	
	public Disciplina getDisciplina_prova() {
		return disciplina_prova;
	}

	public void setDisciplina_prova(Disciplina disciplina_prova) {
		this.disciplina_prova = disciplina_prova;
	}

	public int getNumeroQuestoes_prova() {
		return numeroQuestoes_prova;
	}

	public void setNumeroQuestoes_prova(int numeroQuestoes_prova) {
		this.numeroQuestoes_prova = numeroQuestoes_prova;
	}

	public List<Questao> getQuestoes_prova() {
		return questoes_prova;
	}

	public void setQuestoes_prova(List<Questao> questoes_prova) {
		this.questoes_prova = questoes_prova;
	}
}