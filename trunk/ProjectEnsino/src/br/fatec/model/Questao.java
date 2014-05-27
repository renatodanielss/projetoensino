package br.fatec.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="tbl_questao")
public class Questao {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "QUESTAO_ID")
	@SequenceGenerator(name="QUESTAO_ID", sequenceName="QUESTAO_SEQ", allocationSize=1)
	@Column(name="id_questao")
	private Integer id_questao;
	
	@ManyToOne
    @JoinColumn(name="textobase_questao")
	private Textobase textobase_questao;
	
	@ManyToOne
	@JoinColumn(name="autor_questao")
	private Autor autor_questao;
	
	@Column(name="enunciado_questao")
	private String enunciado_questao;
	
	@ManyToOne
	@JoinColumn(name="disciplina_questao")
	private Disciplina disciplina_questao;
	
	@ManyToOne
	@JoinColumn(name="assunto_questao")
	private Assunto assunto_questao;
	
	@OneToMany(mappedBy="questao_alternativa", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private List<Alternativa> alternativas_questao;

	public Integer getId_questao() {
		return id_questao;
	}

	public void setId_questao(Integer id_questao) {
		this.id_questao = id_questao;
	}

	public Textobase getTextobase_questao() {
		return textobase_questao;
	}

	public void setTextobase_questao(Textobase textobase_questao) {
		this.textobase_questao = textobase_questao;
	}

	public Autor getAutor_questao() {
		return autor_questao;
	}

	public void setAutor_questao(Autor autor_questao) {
		this.autor_questao = autor_questao;
	}

	public String getEnunciado_questao() {
		return enunciado_questao;
	}

	public void setEnunciado_questao(String enunciado_questao) {
		this.enunciado_questao = enunciado_questao;
	}

	public Disciplina getDisciplina_questao() {
		return disciplina_questao;
	}

	public void setDisciplina_questao(Disciplina disciplina_questao) {
		this.disciplina_questao = disciplina_questao;
	}

	public Assunto getAssunto_questao() {
		return assunto_questao;
	}

	public void setAssunto_questao(Assunto assunto_questao) {
		this.assunto_questao = assunto_questao;
	}

	public List<Alternativa> getAlternativas_questao() {
		return alternativas_questao;
	}

	public void setAlternativas_questao(List<Alternativa> alternativas_questao) {
		this.alternativas_questao = alternativas_questao;
	}
}