package br.fatec.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tbl_textobase")
public class Textobase {
	@Id
	@Column(name="codigo_textobase")
	private Integer codigo_textobase;
	
	@Column(name="texto_textobase")
	private String texto_textobase;
	
	public Integer getCodigo_textobase() {
		return codigo_textobase;
	}

	public void setCodigo_textobase(Integer codigo_textobase) {
		this.codigo_textobase = codigo_textobase;
	}

	public String getTexto_textobase() {
		return texto_textobase;
	}

	public void setTexto_textobase(String texto_textobase) {
		this.texto_textobase = texto_textobase;
	}

	@Override
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
	}
}