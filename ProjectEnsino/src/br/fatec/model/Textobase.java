package br.fatec.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="tbl_textobase")
public class Textobase{
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CLIENTE_ID")
	@SequenceGenerator(name="CLIENTE_ID", sequenceName="CLIENTE_SEQ", allocationSize=1)
	@Column(name="codigo_textobase", nullable=false)
	private Integer codigo_textobase;
	@Column(name="titulo_textobase", nullable=false)
	private String titulo_textobase;
	@Column(name="disciplina_textobase", nullable=false)
	private int disciplina_textobase;
	@Column(name="assunto_textobase", nullable=false)
	private int assunto_textobase;
	@Column(name="texto_textobase", nullable=false)
	private String texto_textobase;
	
	public Integer getCodigo_textobase() {
		return codigo_textobase;
	}

	public void setCodigo_textobase(Integer codigo_textobase) {
		this.codigo_textobase = codigo_textobase;
	}
	
	public String getTitulo_textobase() {
		return titulo_textobase;
	}

	public void setTitulo_textobase(String titulo_textobase) {
		this.titulo_textobase = titulo_textobase;
	}

	public int getDisciplina_textobase() {
		return disciplina_textobase;
	}

	public void setDisciplina_textobase(int disciplina_textobase) {
		this.disciplina_textobase = disciplina_textobase;
	}

	public int getAssunto_textobase() {
		return assunto_textobase;
	}

	public void setAssunto_textobase(int assunto_textobase) {
		this.assunto_textobase = assunto_textobase;
	}

	public String getTexto_textobase() {
		return texto_textobase;
	}

	public void setTexto_textobase(String texto_textobase) {
		this.texto_textobase = texto_textobase;
	}

	/*@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Textobase other = (Textobase) obj;
		if (texto_textobase == null) {
			if (other.texto_textobase != null)
				return false;
		} else if (!texto_textobase.equals(other.texto_textobase))
			return false;
		return true;
	}*/
	
	@Override
	public boolean equals(Object object) {
        if (object == this)
            return true;
        if ((object == null) || !(object instanceof Textobase))
            return false;
 
        final Textobase textobase = (Textobase)object;
 
        if (this.codigo_textobase != null && textobase.getCodigo_textobase() != null) {
            return this.codigo_textobase.equals(textobase.getCodigo_textobase());
        }
        return false;
    }
}