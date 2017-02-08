package br.fatec.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="tbl_user_professor")
public class UsuarioProfessor implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_PROFESSOR_ID")
	@SequenceGenerator(name="USER_PROFESSOR_ID", sequenceName="USER_PROFESSOR_SEQ", allocationSize=1)
	@Column(name="id_user")
	private  Integer id_user;
	
	@Column(name="username_user")
	private String usuario;
	
	@Column(name="password_user")
	private String senha;
	
	@OneToOne
	@JoinColumn(name="professor_user")
	private Professor professor;

	public Integer getId_user() {
		return id_user;
	}

	public void setId_user(Integer id_user) {
		this.id_user = id_user;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	@Override
	public boolean equals(Object object) {
        if (object == this)
            return true;
        if ((object == null) || !(object instanceof UsuarioProfessor))
            return false;
 
        final UsuarioProfessor usuario = (UsuarioProfessor)object;
 
        if (this.id_user != null && usuario.getId_user() != null) {
            return this.id_user.equals(usuario.getId_user());
        }
        return false;
    }
}