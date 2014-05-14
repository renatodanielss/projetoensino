package br.fatec.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="tbl_alternativa")
public class Alternativa{
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ALTERNATIVA_ID")
	@SequenceGenerator(name="ALTERNATIVA_ID", sequenceName="ALTERNATIVA_SEQ", allocationSize=1)
	@Column(name="id_alternativa")
	private int id_alternativa;
	
	@Column(name="texto_alternativa")
	private String texto_alternativa;
	
	@ManyToOne
	private Questao questao_alternativa;

	public Alternativa(){
	}
	
	public Alternativa(Questao questao_alternativa){
		this.questao_alternativa = questao_alternativa;
	}
	
	public Alternativa(String texto_alternativa, Questao questao_alternativa){
		this.texto_alternativa = texto_alternativa;
		this.questao_alternativa = questao_alternativa;
	}
	
	public int getId_alternativa() {
		return id_alternativa;
	}

	public void setId_alternativa(int id_alternativa) {
		this.id_alternativa = id_alternativa;
	}

	public String getTexto_alternativa() {
		return texto_alternativa;
	}

	public void setTexto_alternativa(String texto_alternativa) {
		this.texto_alternativa = texto_alternativa;
	}

	public Questao getQuestao_alternativa() {
		return questao_alternativa;
	}

	public void setQuestao_alternativa(Questao questao_alternativa) {
		this.questao_alternativa = questao_alternativa;
	}
}