package br.fatec.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

import br.fatec.dao.ProfessorDAO;
import br.fatec.dao.UsuarioProfessorDAO;
import br.fatec.model.Professor;
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
	private UsuarioProfessor currentUsuarioProfessor;
	private UsuarioProfessor newUsuarioProfessor;
	private List<UsuarioProfessor> usuarioProfessores;
	private UsuarioProfessor usuarioProfessor;
	private UsuarioProfessorDAO usuarioProfessorDAO;
	private List<Professor> professoresSemUsuario; 
	private String pesquisa;
	private boolean showNewButton;
	
	public UsuarioProfessorController(){
		
	}
	
	@PostConstruct
	public void preparaDados()
	{
		this.usuarioProfessor = new UsuarioProfessor();
		this.usuarioProfessorDAO = new UsuarioProfessorDAO();
		this.currentUsuarioProfessor = new UsuarioProfessor();
		this.newUsuarioProfessor = new UsuarioProfessor();
		this.username = "";
		this.password = "";
	}

	public void login() throws ServletException, IOException {
		this.usuarioProfessor = this.usuarioProfessorDAO.buscar(this.username);
		
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

	public UsuarioProfessor getCurrentUsuarioProfessor() {
		return currentUsuarioProfessor;
	}

	public void setCurrentUsuarioProfessor(UsuarioProfessor currentUsuarioProfessor) {
		this.currentUsuarioProfessor = currentUsuarioProfessor;
	}

	public UsuarioProfessor getNewUsuarioProfessor() {
		return newUsuarioProfessor;
	}

	public void setNewUsuarioProfessor(UsuarioProfessor newUsuarioProfessor) {
		this.newUsuarioProfessor = newUsuarioProfessor;
	}

	public List<UsuarioProfessor> getUsuarioProfessores() {
		if (this.usuarioProfessores == null){
			this.usuarioProfessores = this.usuarioProfessorDAO.listar();
		}
		return usuarioProfessores;
	}

	public void setUsuarioProfessores(List<UsuarioProfessor> usuarioProfessores) {
		this.usuarioProfessores = usuarioProfessores;
	}

	public UsuarioProfessorDAO getUsuarioProfessorDAO() {
		return usuarioProfessorDAO;
	}

	public void setUsuarioProfessorDAO(UsuarioProfessorDAO usuarioProfessorDAO) {
		this.usuarioProfessorDAO = usuarioProfessorDAO;
	}
	
	public List<Professor> getProfessoresSemUsuario() {
		return professoresSemUsuario;
	}

	public void setProfessoresSemUsuario(List<Professor> professoresSemUsuario) {
		this.professoresSemUsuario = professoresSemUsuario;
	}

	public String getPesquisa() {
		return pesquisa;
	}

	public void setPesquisa(String pesquisa) {
		this.pesquisa = pesquisa;
	}

	public boolean isShowNewButton() {
		return showNewButton;
	}

	public void setShowNewButton(boolean showNewButton) {
		this.showNewButton = showNewButton;
	}
	
	public void mostrarAlterar(){
		this.showNewButton = false;
	}
		  
	public void mostrarSalvar(){
	     this.showNewButton = true;
	}
	
	public void goToUsuarioProfessor() throws Exception{
		ProfessorDAO professorDAO = new ProfessorDAO();
		this.professoresSemUsuario = professorDAO.listarProfessorSemUsuario();
		if (this.professoresSemUsuario.size() > 0){
			limparCampos();
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			externalContext.redirect("Usuarioprofessor.xhtml");
		}
		else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Alerta!<br/>", "Não há professores a serem cadastrados!<br>&nbsp;Todos os professores possuem usuário."));
		}
	}
	
	//validar - método cadastrar atualizado. Nesta versï¿½o ï¿½ verificado se o mï¿½todo cadastrarCampos verificou algum erro, caso sim, este mï¿½todo exibirï¿½ os erros na pï¿½gina.
	//As mesmas alteraï¿½ï¿½es deverï¿½o ser feitas no mï¿½todo alterar.
	public void cadastrar() throws IOException, ParseException
	{
		String mensagem = validarCampos(this.newUsuarioProfessor);
		if (mensagem.length() == 0){
			if (usuarioProfessorDAO.inserir(this.newUsuarioProfessor)){
				setUsuarioProfessores(null);
				System.out.println("Professor inserido com sucesso!");
				this.newUsuarioProfessor = new UsuarioProfessor();
				
				//validar - importante adicionar o redirect com o parï¿½metro origin=nome da entidade (letras minï¿½sculas, sem espaï¿½os oou caracteres especiais, por exemplo:
				//"CadastroConcluido.xhtml?faces-redirect=true&origin=textobase".
				ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
				externalContext.redirect("CadastroConcluido.xhtml?faces-redirect=true&origin=usuarioprofessor");
			}
			else
				System.out.println("Erro na inserção!");
			
			this.newUsuarioProfessor = new UsuarioProfessor();
		}
		else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erros!<br/>", mensagem));
		}
	}
	
	private void limparCampos(){
		
		this.getNewUsuarioProfessor().setId_user(null);
		this.getNewUsuarioProfessor().setProfessor(null);
		this.getNewUsuarioProfessor().setUsuario(null);
		this.getNewUsuarioProfessor().setSenha(null);
		
		mostrarSalvar();
	}
	
	public void iniciaAlterar() throws IOException
	{
		if (this.newUsuarioProfessor != null)
		{
			try{
				this.getNewUsuarioProfessor().setId_user(this.getCurrentUsuarioProfessor().getId_user());
				this.getNewUsuarioProfessor().setProfessor(this.getCurrentUsuarioProfessor().getProfessor());
				this.getNewUsuarioProfessor().setUsuario(this.getCurrentUsuarioProfessor().getUsuario());
				this.getNewUsuarioProfessor().setSenha(this.getCurrentUsuarioProfessor().getSenha());
				ProfessorDAO professorDAO = new ProfessorDAO();
				this.setProfessoresSemUsuario(professorDAO.listarProfessorSemUsuario());
				this.professoresSemUsuario.add(professorDAO.buscar(this.getNewUsuarioProfessor().getProfessor().getMatricula_professor()));
				
				mostrarAlterar();
				
				ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
				externalContext.redirect("Usuarioprofessor.xhtml?faces-redirect=true&redirect=1");
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	public void alterar() throws IOException, ParseException
	{
		String mensagem = validarCampos(this.newUsuarioProfessor);
		if (mensagem.length() == 0){
			if (usuarioProfessorDAO.alterar(this.newUsuarioProfessor)){
				setUsuarioProfessores(null);
				System.out.println("Professor alterado com sucesso!");
				this.newUsuarioProfessor = new UsuarioProfessor();
				
				ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
				externalContext.redirect("CadastroConcluido.xhtml?faces-redirect=true&origin=usuarioprofessor");
			}
			else
				System.out.println("Erro na alteração!");
			}
		else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erros!<br/>", mensagem));
		}
	}
	
	public void excluir() throws IOException
	{
		if (usuarioProfessorDAO.excluir(this.currentUsuarioProfessor)){
			setUsuarioProfessores(null);
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			externalContext.redirect("Usuarioprofessorlist.xhtml");
			System.out.println("Professor excluido com sucesso!");
		}
		else
			System.out.println("Erro na exclusão!");
	}
	
	//validar - método para validação de atributos do objeto associado à view, este método será chamado pelo método cadastrar e alterar
		public String validarCampos(UsuarioProfessor usuarioProfessor) throws ParseException{
			String mensagemErro = "";
			if (usuarioProfessor.getUsuario().trim().length() == 0)
				mensagemErro += "<br/>-Preencher usuário";
			if (usuarioProfessor.getSenha().trim().length() == 0)
				mensagemErro += "<br/>-Preencher senha";
			if (usuarioProfessor.getProfessor() == null)
				mensagemErro += "<br/>-Selecione um professor";
			return mensagemErro;
		}
		
		public void pesquisar(){
			this.usuarioProfessores = this.usuarioProfessorDAO.listar(this.pesquisa);
			if (this.usuarioProfessores == null){
				this.usuarioProfessores = this.getUsuarioProfessores();
			}
		}
}