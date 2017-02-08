package br.fatec.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tbl_professor")

public class Professor implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="matricula_professor")
	private String matricula_professor;
	
	@Column(name="nome_professor")
	private String nome_professor;
	
	@Column(name="cpf_professor")
	private String cpf_professor;
	
	@Column(name="datanasc_professor")
	private String datanasc_professor;
	
	@Column(name="telefone_professor")
	private String telefone_professor;
	
	@Column(name="celular_professor")
	private String celular_professor;
	
	@Column(name="sexo_professor")
	private Boolean sexo_professor;
	
	@Column(name="logradouro_professor")
	private String logradouro_professor;
	
	@Column(name="numero_professor")
	private String numero_professor;
	
	@Column(name="complemento_professor")
	private String complemento_professor;
	
	@Column(name="bairro_professor")
	private String bairro_professor;
	
	@Column(name="cidade_professor")
	private String cidade_professor;
	
	@Column(name="uf_professor")
	private String uf_professor;
	
	@Column(name="cep_professor")
	private String cep_professor;
	
	@Column(name="email_professor")
	private String email_professor;

	public String getMatricula_professor() {
		return matricula_professor;
	}

	public void setMatricula_professor(String matricula_professor) {
		this.matricula_professor = matricula_professor;
	}

	public String getNome_professor() {
		return nome_professor;
	}

	public void setNome_professor(String nome_professor) {
		this.nome_professor = nome_professor;
	}

	public String getCpf_professor() {
		return cpf_professor;
	}

	public void setCpf_professor(String cpf_professor) {
		this.cpf_professor = cpf_professor;
	}

	public String getDatanasc_professor() {
		return datanasc_professor;
	}

	public void setDatanasc_professor(String datanasc_professor) {
		this.datanasc_professor = datanasc_professor;
	}

	public String getTelefone_professor() {
		return telefone_professor;
	}

	public void setTelefone_professor(String telefone_professor) {
		this.telefone_professor = telefone_professor;
	}

	public String getCelular_professor() {
		return celular_professor;
	}

	public void setCelular_professor(String celular_professor) {
		this.celular_professor = celular_professor;
	}

	public Boolean getSexo_professor() {
		return sexo_professor;
	}

	public void setSexo_professor(Boolean sexo_professor) {
		this.sexo_professor = sexo_professor;
	}

	public String getLogradouro_professor() {
		return logradouro_professor;
	}

	public void setLogradouro_professor(String logradouro_professor) {
		this.logradouro_professor = logradouro_professor;
	}

	public String getNumero_professor() {
		return numero_professor;
	}

	public void setNumero_professor(String numero_professor) {
		this.numero_professor = numero_professor;
	}

	public String getComplemento_professor() {
		return complemento_professor;
	}

	public void setComplemento_professor(String complemento_professor) {
		this.complemento_professor = complemento_professor;
	}

	public String getBairro_professor() {
		return bairro_professor;
	}

	public void setBairro_professor(String bairro_professor) {
		this.bairro_professor = bairro_professor;
	}

	public String getCidade_professor() {
		return cidade_professor;
	}

	public void setCidade_professor(String cidade_professor) {
		this.cidade_professor = cidade_professor;
	}

	public String getUf_professor() {
		return uf_professor;
	}

	public void setUf_professor(String uf_professor) {
		this.uf_professor = uf_professor;
	}

	public String getCep_professor() {
		return cep_professor;
	}

	public void setCep_professor(String cep_professor) {
		this.cep_professor = cep_professor;
	}

	public String getEmail_professor() {
		return email_professor;
	}

	public void setEmail_professor(String email_professor) {
		this.email_professor = email_professor;
	}
	
	@Override
	public boolean equals(Object object) {
        if (object == this)
            return true;
        if ((object == null) || !(object instanceof Professor))
            return false;
 
        final Professor professor = (Professor)object;
 
        if (this.matricula_professor != null && professor.getMatricula_professor() != null) {
            return this.matricula_professor.equals(professor.getMatricula_professor());
        }
        return false;
    }
}
