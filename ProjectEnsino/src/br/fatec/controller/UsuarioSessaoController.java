package br.fatec.controller;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="usuarioSessaoController")
@SessionScoped

public class UsuarioSessaoController {
	private String username;
	private String password;
	
	public UsuarioSessaoController(){
		
	}
	
	@PostConstruct
	public void preparaDados()
	{
		
		
	}
	
	public void login(){
		
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}