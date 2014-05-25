package br.fatec.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import br.fatec.dao.AssuntoDAO;
import br.fatec.model.Assunto;

@ManagedBean(name="assuntoController")
@SessionScoped

public class AssuntoController {
	private Assunto newAssunto;
	private Assunto currentAssunto;
	private AssuntoDAO assuntoDao;
	private List<Assunto> assuntos;
	private List<Assunto> textobaseAssuntos;
	private TextobaseController textobaseController;
	private boolean showNewButton;
	
	public AssuntoController()
	{
		
	}

	@PostConstruct
	public void preparaDados()
	{
		this.assuntoDao = new AssuntoDAO();
		this.newAssunto = new Assunto();
		this.currentAssunto = new Assunto();
		this.textobaseController = this.getTextobaseController();
		this.mostrarSalvar();
	}

	public List<Assunto> getAssuntos() {
		if (this.assuntos == null){
			this.assuntos = assuntoDao.listar();
		}
		return assuntos;
	}

	public void setAssuntos(List<Assunto> assuntos) {
		this.assuntos = assuntos;
	}

	public List<Assunto> getTextobaseAssuntos() {
		textobaseController = this.getTextobaseController();
		if (textobaseController.getNewTextoBase().getDisciplina_textobase() < 1)
			this.textobaseAssuntos = null;
		else{
			FacesContext facesContext = FacesContext.getCurrentInstance();
			String redirectValue = (String) facesContext.getExternalContext().getRequestParameterMap().get("redirect");
			if (redirectValue != null && Integer.parseInt(redirectValue) == 1)
				mudarAssuntos();
		}
		return textobaseAssuntos;
	}

	public void setTextobaseAssuntos(List<Assunto> textobaseAssuntos) {
		this.textobaseAssuntos = textobaseAssuntos;
	}

	public AssuntoDAO getAssuntoDao() {
		return assuntoDao;
	}

	public void setAssuntoDao(AssuntoDAO assuntoDao) {
		this.assuntoDao = assuntoDao;
	}

	public Assunto getCurrentAssunto() {
		return currentAssunto;
	}

	public void setCurrentAssunto(Assunto currentAssunto) {
		this.currentAssunto = currentAssunto;
	}

	public Assunto getNewAssunto() {
		return newAssunto;
	}

	public void setNewAssunto(Assunto newAssunto) {
		this.newAssunto = newAssunto;
	}
	
	public boolean getShowNewButton(){
		return showNewButton;
	}
	
	public void setShowNewButton(boolean showNewButton){
		this.showNewButton = showNewButton;
	}
	
	public void mostrarAlterar(){
		this.showNewButton = false;
	}
		  
	public void mostrarSalvar(){
	     this.showNewButton = true;
	}
	
	public TextobaseController getTextobaseController() {
		try{
			FacesContext context = FacesContext.getCurrentInstance();
			@SuppressWarnings("rawtypes")
			Map sessionMap = context.getExternalContext().getSessionMap();
			textobaseController = (TextobaseController)sessionMap.get("textobaseController");
		}catch(Exception e){
			textobaseController = null;
			e.printStackTrace();
		}
		return textobaseController;
	}

	public void setTextobaseController(TextobaseController textobaseController) {
		this.textobaseController = textobaseController;
	}
	
	public void mudarAssuntos() {
		TextobaseController textobaseController = this.getTextobaseController();
		this.textobaseAssuntos = assuntoDao.listar(textobaseController.getNewTextoBase().getDisciplina_textobase());
	}
	
	public void cadastrar()
	{	
		if (assuntoDao.inserir(this.newAssunto)){
			setAssuntos(null);
			System.out.println("Assunto inserido com sucesso!");
			this.newAssunto = new Assunto();
		}
		else
			System.out.println("Erro na inserção!");
	}
	
	public void iniciaAlterar() throws IOException
	{
		if (newAssunto != null)
		{
			try{
				this.getNewAssunto().setId_assunto(this.getCurrentAssunto().getId_assunto());
				this.getNewAssunto().setNome_assunto(this.getCurrentAssunto().getNome_assunto());
				this.getNewAssunto().setIdDisciplina_assunto(this.getCurrentAssunto().getIdDisciplina_assunto());
				
				mostrarAlterar();
				
				ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
				externalContext.redirect("Assunto.xhtml?faces-redirect=true&redirect=1");
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	public void alterar()
	{	
		if (assuntoDao.alterar(this.newAssunto)){
			setAssuntos(null);
			System.out.println("Assunto alterado com sucesso!");
			this.newAssunto = new Assunto();
		}
		else
			System.out.println("Erro na alteração!");
	}
	
	public void excluir() throws IOException
	{
		if (assuntoDao.excluir(this.currentAssunto)){
			setAssuntos(null);
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			externalContext.redirect("Assuntolist.xhtml");
			System.out.println("Assunto excluido com sucesso!");
		}
		else
			System.out.println("Erro na exclusão!");
	}
	
	private void limparCampos(){
		this.newAssunto.setId_assunto(null);
		this.newAssunto.setIdDisciplina_assunto(null);
		this.newAssunto.setNome_assunto(null);
		this.mostrarSalvar();
	}
	
	public void goToAssunto() throws Exception{
		limparCampos();
		System.out.println("goToAssunto");
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		externalContext.redirect("Assunto.xhtml");
	}
}