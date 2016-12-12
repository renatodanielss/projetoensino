package br.fatec.controller;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

import br.fatec.dao.UsuarioProfessorDAO;
import br.fatec.model.UsuarioProfessor;
import br.fatec.util.FacesUtil;
import br.fatec.util.SessionUtil;

@SuppressWarnings("serial")
@ManagedBean(name="usuarioProfessorController")
@SessionScoped
//@WebServlet("/login")
public class UsuarioProfessorController extends HttpServlet{
	private String username;
	private String password;
	private Boolean isLogged;
	private UsuarioProfessor usuarioProfessor;
	private UsuarioProfessorDAO usuarioProfessorDAO;
	
	public UsuarioProfessorController(){
		
	}
	
	@PostConstruct
	public void preparaDados()
	{
		this.usuarioProfessor = new UsuarioProfessor();
		this.usuarioProfessorDAO = new UsuarioProfessorDAO();
		this.username = "";
		this.password = "";
	}

	public void login() throws ServletException, IOException {
		this.usuarioProfessor = this.usuarioProfessorDAO.buscar(this.username);
		//System.out.println("Usuário: " + this.usuarioProfessor.getUsuario());
		//System.out.println("Senha: " + this.usuarioProfessor.getSenha());
		
		if (this.usuarioProfessor != null){
			if (this.usuarioProfessor.getUsuario().equals(this.username) && this.usuarioProfessor.getSenha().equals(this.password)){
				SessionUtil.getSession();
				SessionUtil.setParam("user", this.usuarioProfessor);
				
				HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
				
				ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
				String from = (String)externalContext.getSessionMap().get("from");
				
				System.out.println("from: " + from);
				UsuarioProfessor usuarioProfessor = (UsuarioProfessor)SessionUtil.getParam("user");
				System.out.println("Usuário ID: " + usuarioProfessor.getId_user());
				System.out.println("Usuário Username: " + usuarioProfessor.getUsuario());
				
				if (from != null && !from.isEmpty()) {
				    response.sendRedirect(from);
				} else {
				    response.sendRedirect("/Pages/index.xhtml");
				}
			} else{
				FacesUtil.addErrorMessage("Usuário ou senha incorretos!");
			}
		}
		else{
			FacesUtil.addErrorMessage("Usuário ou senha incorretos!");
		}
	}
	
	public void logout() throws IOException{
		System.out.println("Logout");
		SessionUtil.getSession();
		SessionUtil.remove("user");
		//SessionUtil.invalidate();
		FacesContext.getCurrentInstance().getExternalContext().redirect("/Pages/LoginProfessor.xhtml");
	}
	
	public void setIsLogged(Boolean isLogged) {
		this.isLogged = isLogged;
	}

	public Boolean getIsLogged(){
		this.isLogged = SessionUtil.getParam("user") == null?false:true;
		return this.isLogged;
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

	public UsuarioProfessor getUsuarioProfessor() {
		return usuarioProfessor;
	}

	public void setUsuarioProfessor(UsuarioProfessor usuarioProfessor) {
		this.usuarioProfessor = usuarioProfessor;
	}
}