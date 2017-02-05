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

import br.fatec.dao.UsuarioAdminDAO;
import br.fatec.model.UsuarioAdmin;
import br.fatec.util.FacesUtil;
import br.fatec.util.SessionUtil;

@SuppressWarnings("serial")
@ManagedBean(name="usuarioAdminController")
@SessionScoped
//@WebServlet("/login")
public class UsuarioAdminController extends HttpServlet{
	private String username;
	private String password;
	private Boolean isLogged;
	private UsuarioAdmin usuarioAdmin;
	private UsuarioAdminDAO usuarioAdminDAO;
	
	public UsuarioAdminController(){
		
	}
	
	@PostConstruct
	public void preparaDados()
	{
		this.usuarioAdmin = new UsuarioAdmin();
		this.usuarioAdminDAO = new UsuarioAdminDAO();
		this.username = "";
		this.password = "";
	}

	public void login() throws ServletException, IOException {
		this.usuarioAdmin = this.usuarioAdminDAO.buscar(this.username);
		
		//System.out.println("Usuário: " + this.usuarioAdmin.getUsuario());
		//System.out.println("Senha: " + this.usuarioAdmin.getSenha());
		
		if (this.usuarioAdmin != null){
			if (this.usuarioAdmin.getUsuario().equals(this.username) && this.usuarioAdmin.getSenha().equals(this.password)){
				SessionUtil.getSession();
				SessionUtil.setParam("useradmin", this.usuarioAdmin);
				
				HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
				
				ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
				String from = (String)externalContext.getSessionMap().get("from");
				
				System.out.println("from: " + from);
				UsuarioAdmin usuarioAdmin = (UsuarioAdmin)SessionUtil.getParam("useradmin");
				System.out.println("Usuário ID: " + usuarioAdmin.getId_user());
				System.out.println("Usuário Username: " + usuarioAdmin.getUsuario());
				
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
		SessionUtil.remove("useradmin");
		//SessionUtil.invalidate();
		FacesContext.getCurrentInstance().getExternalContext().redirect("/Pages/LoginAdministracao.xhtml");
	}
	
	public void setIsLogged(Boolean isLogged) {
		this.isLogged = isLogged;
	}

	public Boolean getIsLogged(){
		this.isLogged = SessionUtil.getParam("useradmin") == null?false:true;
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

	public UsuarioAdmin getUsuarioAdmin() {
		return usuarioAdmin;
	}

	public void setUsuarioAdmin(UsuarioAdmin usuarioAdmin) {
		this.usuarioAdmin = usuarioAdmin;
	}
}