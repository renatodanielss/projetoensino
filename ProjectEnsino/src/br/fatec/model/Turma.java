package br.fatec.model;

import java.util.List;

//@Entity
//@Table(name="tbl_turma")
public class Turma {
	//@Id
	//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TURMA_ID")
	//@SequenceGenerator(name="TURMA_ID", sequenceName="TURMA_SEQ", allocationSize=1)
	//@Column(name="id_turma")
	private Integer id_turma;
	
	//@Column(name="nome_turma")
	private String nome_turma;
	
	//@Column(name="dataInicio_turma")
	private String dataInicio_turma;
	
	//@Column(name="dataFim_turma")
	private String dataFim_turma;
	
	//@Column(name="ativa_turma")
	private Boolean ativa_turma;
	
	//@Column(name="qtdAlunos_turma")
	private Integer qtdAlunos_turma;
	
	//@ManyToMany
	//@JoinTable(name="tbl_alunoturma", joinColumns={@JoinColumn(name="id_turma")}, inverseJoinColumns={@JoinColumn(name="id_aluno")})
	private List<Aluno> alunos;
	
	public Integer getId_turma() {
		return id_turma;
	}

	public void setId_turma(Integer id_turma) {
		this.id_turma = id_turma;
	}

	public String getNome_turma() {
		return nome_turma;
	}

	public void setNome_turma(String nome_turma) {
		this.nome_turma = nome_turma;
	}

	public String getDataInicio_turma() {
		return dataInicio_turma;
	}

	public void setDataInicio_turma(String dataInicio_turma) {
		this.dataInicio_turma = dataInicio_turma;
	}

	public String getDataFim_turma() {
		return dataFim_turma;
	}

	public void setDataFim_turma(String dataFim_turma) {
		this.dataFim_turma = dataFim_turma;
	}

	public Boolean getAtiva_turma() {
		return ativa_turma;
	}

	public void setAtiva_turma(Boolean ativa_turma) {
		this.ativa_turma = ativa_turma;
	}

	public Integer getQtdAlunos_turma() {
		return qtdAlunos_turma;
	}

	public void setQtdAlunos_turma(Integer qtdAlunos_turma) {
		this.qtdAlunos_turma = qtdAlunos_turma;
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}

	@Override
	public boolean equals(Object object) {
        if (object == this)
            return true;
        if ((object == null) || !(object instanceof Turma))
            return false;
 
        final Turma turma = (Turma)object;
 
        if (this.id_turma != null && turma.getId_turma() != null) {
            return this.id_turma.equals(turma.getId_turma());
        }
        return false;
    }
}