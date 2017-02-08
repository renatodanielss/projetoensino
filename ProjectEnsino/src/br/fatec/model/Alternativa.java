package br.fatec.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="tbl_alternativa")
public class Alternativa implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ALTERNATIVA_ID")
	@SequenceGenerator(name="ALTERNATIVA_ID", sequenceName="ALTERNATIVA_SEQ", allocationSize=1)
	@Column(name="id_alternativa")
	private Integer id_alternativa;
	
	@Column(name="texto_alternativa")
	private String texto_alternativa;

	@Column(name="correta_alternativa")
	private Boolean correta_alternativa;
	
	@ManyToOne
	@JoinColumn(name="questao_alternativa")
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
	
	public Integer getId_alternativa() {
		return id_alternativa;
	}

	public void setId_alternativa(Integer id_alternativa) {
		this.id_alternativa = id_alternativa;
	}

	public String getTexto_alternativa() {
		return texto_alternativa;
	}

	public void setTexto_alternativa(String texto_alternativa) {
		this.texto_alternativa = texto_alternativa;
	}

	public Boolean getCorreta_alternativa() {
		return correta_alternativa;
	}

	public void setCorreta_alternativa(Boolean correta_alternativa) {
		this.correta_alternativa = correta_alternativa;
	}

	public Questao getQuestao_alternativa() {
		return questao_alternativa;
	}

	public void setQuestao_alternativa(Questao questao_alternativa) {
		this.questao_alternativa = questao_alternativa;
	}
	
	@Override
	public boolean equals(Object object) {
        if (object == this)
            return true;
        if ((object == null) || !(object instanceof Alternativa))
            return false;
 
        final Alternativa alternativa = (Alternativa)object;
 
        if (this.id_alternativa != null && alternativa.getId_alternativa() != null) {
            return this.id_alternativa.equals(alternativa.getId_alternativa());
        }
        return false;
    }
}