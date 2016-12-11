package br.fatec.model;

/*import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;*/

//@Entity
//@Table(name="tbl_realizarprova")
public class RealizarProva {
	//@Id
	//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REALIZARPROVA_ID")
	//@SequenceGenerator(name="REALIZARPROVA_ID", sequenceName="REALIZARPROVA_SEQ", allocationSize=1)
	//@Column(name="id_realizarprova")
	private Integer id_realizarprova;
	
	//@ManyToOne
	//@JoinColumn(name="aluno_realizarprova")
    private Aluno aluno_realizarprova;
	
    //@ManyToOne
	//@JoinColumn(name="prova_realizarprova")
	private Prova prova_realizarprova;
	
	//@Column(name="nota_realizarprova")
	private Integer nota_realizarprova;

	public Integer getId_realizarprova() {
		return id_realizarprova;
	}

	public void setId_realizarprova(Integer id_realizarprova) {
		this.id_realizarprova = id_realizarprova;
	}

	public Aluno getAluno_realizarprova() {
		return aluno_realizarprova;
	}

	public void setAluno_realizarprova(Aluno aluno_realizarprova) {
		this.aluno_realizarprova = aluno_realizarprova;
	}

	public Prova getProva_realizarprova() {
		return prova_realizarprova;
	}

	public void setProva_realizarprova(Prova prova_realizarprova) {
		this.prova_realizarprova = prova_realizarprova;
	}

	public Integer getNota_realizarprova() {
		return nota_realizarprova;
	}

	public void setNota_realizarprova(Integer nota_realizarprova) {
		this.nota_realizarprova = nota_realizarprova;
	}
	
	@Override
	public boolean equals(Object object) {
        if (object == this)
            return true;
        if ((object == null) || !(object instanceof RealizarProva))
            return false;
 
        final RealizarProva realizarprova = (RealizarProva)object;
 
        if (this.id_realizarprova != null && realizarprova.getId_realizarprova() != null) {
            return this.id_realizarprova.equals(realizarprova.getId_realizarprova());
        }
        return false;
    }
}