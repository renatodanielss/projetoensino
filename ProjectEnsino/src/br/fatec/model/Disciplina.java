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
@Table(name="tbl_disciplina")

public class Disciplina implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DISCIPLINA_ID")
	@SequenceGenerator(name="DISCIPLINA_ID", sequenceName="DISCIPLINA_SEQ", allocationSize=1)
	@Column(name="id_disciplina")
	private Integer id_disciplina;
	
	@Column(name="nome_disciplina")
	private String nome_disciplina;

	public Integer getId_disciplina() {
		return id_disciplina;
	}

	public void setId_disciplina(Integer id_disciplina) {
		this.id_disciplina = id_disciplina;
	}

	public String getNome_disciplina() {
		return nome_disciplina;
	}

	public void setNome_disciplina(String nome_disciplina) {
		this.nome_disciplina = nome_disciplina;
	}
	
	@Override
	public boolean equals(Object object) {
        if (object == this)
            return true;
        if ((object == null) || !(object instanceof Disciplina))
            return false;
 
        final Disciplina disciplina = (Disciplina)object;
 
        if (this.id_disciplina != null && disciplina.getId_disciplina() != null) {
            return this.id_disciplina.equals(disciplina.getId_disciplina());
        }
        return false;
    }
}
