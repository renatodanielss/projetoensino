package br.fatec.model;

/*import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;*/

//@Entity
//@Table(name="tbl_aluno")

public class Aluno {
	//@Id
	//@Column(name="ra_aluno")
	private String ra_aluno;
	
	//@Column(name="nome_aluno")
	private String nome_aluno;
	
	//@Column(name="cpf_aluno")
	private String cpf_aluno;
	
	//@Column(name="datanasc_aluno")
	private String datanasc_aluno;
		
	//@Column(name="telefone_aluno")
	private String telefone_aluno;
	
	//@Column(name="celular_aluno")
	private String celular_aluno;
	
	//@Column(name="sexo_aluno")
	private Boolean sexo_aluno;
	
	//@Column(name="logradouro_aluno")
	private String logradouro_aluno;
	
	//@Column(name="numero_aluno")
	private String numero_aluno;
	
	//@Column(name="complemento_aluno")
	private String complemento_aluno;
	
	//@Column(name="bairro_aluno")
	private String bairro_aluno;
	
	//@Column(name="cidade_aluno")
	private String cidade_aluno;
	
	//@Column(name="uf_aluno")
	private String uf_aluno;
	
	//@Column(name="cep_aluno")
	private String cep_aluno;
	
	//@Column(name="email_aluno")
	private String email_aluno;

	public String getRa_aluno() {
		return ra_aluno;
	}

	public void setRa_aluno(String ra_aluno) {
		this.ra_aluno = ra_aluno;
	}

	public String getNome_aluno() {
		return nome_aluno;
	}

	public void setNome_aluno(String nome_aluno) {
		this.nome_aluno = nome_aluno;
	}

	public String getCpf_aluno() {
		return cpf_aluno;
	}

	public void setCpf_aluno(String cpf_aluno) {
		this.cpf_aluno = cpf_aluno;
	}

	public String getDatanasc_aluno() {
		return datanasc_aluno;
	}

	public void setDatanasc_aluno(String datanasc_aluno) {
		this.datanasc_aluno = datanasc_aluno;
	}

	public String getTelefone_aluno() {
		return telefone_aluno;
	}

	public void setTelefone_aluno(String telefone_aluno) {
		this.telefone_aluno = telefone_aluno;
	}

	public String getCelular_aluno() {
		return celular_aluno;
	}
	
	public Boolean getSexo_aluno() {
		return sexo_aluno;
	}

	public void setSexo_aluno(Boolean sexo_aluno) {
		this.sexo_aluno = sexo_aluno;
	}

	public void setCelular_aluno(String celular_aluno) {
		this.celular_aluno = celular_aluno;
	}
	
	public String getLogradouro_aluno() {
		return logradouro_aluno;
	}

	public void setLogradouro_aluno(String logradouro_aluno) {
		this.logradouro_aluno = logradouro_aluno;
	}

	public String getNumero_aluno() {
		return numero_aluno;
	}

	public void setNumero_aluno(String numero_aluno) {
		this.numero_aluno = numero_aluno;
	}

	public String getComplemento_aluno() {
		return complemento_aluno;
	}

	public void setComplemento_aluno(String complemento_aluno) {
		this.complemento_aluno = complemento_aluno;
	}

	public String getBairro_aluno() {
		return bairro_aluno;
	}

	public void setBairro_aluno(String bairro_aluno) {
		this.bairro_aluno = bairro_aluno;
	}

	public String getCidade_aluno() {
		return cidade_aluno;
	}

	public void setCidade_aluno(String cidade_aluno) {
		this.cidade_aluno = cidade_aluno;
	}

	public String getUf_aluno() {
		return uf_aluno;
	}

	public void setUf_aluno(String uf_aluno) {
		this.uf_aluno = uf_aluno;
	}

	public String getCep_aluno() {
		return cep_aluno;
	}

	public void setCep_aluno(String cep_aluno) {
		this.cep_aluno = cep_aluno;
	}

	public String getEmail_aluno() {
		return email_aluno;
	}

	public void setEmail_aluno(String email_aluno) {
		this.email_aluno = email_aluno;
	}
	
	@Override
	public boolean equals(Object object) {
        if (object == this)
            return true;
        if ((object == null) || !(object instanceof Aluno))
            return false;
 
        final Aluno aluno = (Aluno)object;
 
        if (this.ra_aluno != null && aluno.getRa_aluno() != null) {
            return this.ra_aluno.equals(aluno.getRa_aluno());
        }
        return false;
    }
}
