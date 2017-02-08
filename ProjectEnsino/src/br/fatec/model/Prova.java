package br.fatec.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OrderColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="tbl_prova")
public class Prova implements Serializable{
	
	private static final long serialVersionUID = 1L;

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
	
	@ManyToOne
    @JoinColumn(name="assunto_prova")
	private Assunto assunto_prova;
	
	@Column(name="numeroquestoes_prova")
	private int numeroQuestoes_prova;
	
	@ManyToOne
	@JoinColumn(name="usuario_professor_prova")
	private UsuarioProfessor usuario_professor_prova;
	
	@Column(name="data_criacao_prova")
	private java.sql.Timestamp data_criacao_prova;
	
	@Column(name="data_ultima_alteracao_prova")
	private java.sql.Timestamp data_ultima_alteracao_prova;
	
	@Column(name="versao_prova")
	private String versao_prova;
	
	@Column(name="questoes_usadas_prova")
	private String questoes_usadas_prova;
	
	@ManyToMany(cascade=CascadeType.MERGE)
    @JoinTable(name="tbl_questaoprova", joinColumns={@JoinColumn(name="id_prova")}, inverseJoinColumns={@JoinColumn(name="id_questao")})
	@OrderColumn(name="ordem_questaoprova")
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

	public Assunto getAssunto_prova() {
		return assunto_prova;
	}

	public void setAssunto_prova(Assunto assunto_prova) {
		this.assunto_prova = assunto_prova;
	}

	public int getNumeroQuestoes_prova() {
		return numeroQuestoes_prova;
	}

	public void setNumeroQuestoes_prova(int numeroQuestoes_prova) {
		this.numeroQuestoes_prova = numeroQuestoes_prova;
	}

	public UsuarioProfessor getUsuario_professor_prova() {
		return usuario_professor_prova;
	}

	public void setUsuario_professor_prova(UsuarioProfessor usuario_professor_prova) {
		this.usuario_professor_prova = usuario_professor_prova;
	}
	
	public java.sql.Timestamp getData_criacao_prova() {
		return data_criacao_prova;
	}

	public void setData_criacao_prova(java.sql.Timestamp data_criacao_prova) {
		this.data_criacao_prova = data_criacao_prova;
	}

	public java.sql.Timestamp getData_ultima_alteracao_prova() {
		return data_ultima_alteracao_prova;
	}

	public void setData_ultima_alteracao_prova(
			java.sql.Timestamp data_ultima_alteracao_prova) {
		this.data_ultima_alteracao_prova = data_ultima_alteracao_prova;
	}

	public String getVersao_prova() {
		return versao_prova;
	}

	public void setVersao_prova(String versao_prova) {
		this.versao_prova = versao_prova;
	}

	public List<Questao> getQuestoes_prova() {
		return questoes_prova;
	}

	public void setQuestoes_prova(List<Questao> questoes_prova) {
		this.questoes_prova = questoes_prova;
	}
	
	public String getQuestoes_usadas_prova() {
		return questoes_usadas_prova;
	}

	public void setQuestoes_usadas_prova(String questoes_usadas_prova) {
		this.questoes_usadas_prova = questoes_usadas_prova;
	}

	@Override
	public boolean equals(Object object) {
        if (object == this)
            return true;
        if ((object == null) || !(object instanceof Prova))
            return false;
 
        final Prova prova = (Prova)object;
 
        if (this.id_prova != null && prova.getId_prova() != null) {
            return this.id_prova.equals(prova.getId_prova());
        }
        return false;
    }
}