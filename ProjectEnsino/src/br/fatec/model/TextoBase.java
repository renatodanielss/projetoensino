package br.fatec.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tbl_textobase")
public class TextoBase {
	@Id
	@GeneratedValue
	@Column(name="codigo_textobase")
	private int codigo;
	@Column(name="texto_textobase")
	private String texto;
	
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public int getCodigo() {
		return codigo;
	}
}
