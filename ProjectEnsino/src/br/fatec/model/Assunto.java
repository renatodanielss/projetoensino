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
@Table(name="tbl_assunto")

public class Assunto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ASSUNTO_ID")
	@SequenceGenerator(name="ASSUNTO_ID", sequenceName="ASSUNTO_SEQ", allocationSize=1)
	@Column(name="id_assunto")
	private Integer id_assunto;
	
	@ManyToOne
    @JoinColumn(name="iddisciplina_assunto")
	private Disciplina disciplina_assunto;
	
	@Column(name="nome_assunto")
	private String nome_assunto;

	public Integer getId_assunto() {
		return id_assunto;
	}

	public void setId_assunto(Integer id_assunto) {
		this.id_assunto = id_assunto;
	}

	public Disciplina getDisciplina_assunto() {
		return disciplina_assunto;
	}

	public void setDisciplina_assunto(Disciplina disciplina_assunto) {
		this.disciplina_assunto = disciplina_assunto;
	}

	public String getNome_assunto() {
		return nome_assunto;
	}

	public void setNome_assunto(String nome_assunto) {
		this.nome_assunto = nome_assunto;
	}
	
	@Override
	public boolean equals(Object object) {
        if (object == this)
            return true;
        if ((object == null) || !(object instanceof Assunto))
            return false;
 
        final Assunto assunto = (Assunto)object;
 
        if (this.id_assunto != null && assunto.getId_assunto() != null) {
            return this.id_assunto.equals(assunto.getId_assunto());
        }
        return false;
    }
}
