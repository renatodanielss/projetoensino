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
	private TextobaseController textobaseController;
	
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
	}

	public List<Assunto> getAssuntos() {
		if (textobaseController.getNewTextoBase() == null)
			this.assuntos = assuntoDao.listar();
		else{
			if (textobaseController.getNewTextoBase().getDisciplina_textobase() < 1)
				this.assuntos = null;
			else{
				FacesContext facesContext = FacesContext.getCurrentInstance();
				String redirectValue = (String) facesContext.getExternalContext().getRequestParameterMap().get("redirect");
				if (redirectValue != null && Integer.parseInt(redirectValue) == 1)
					mudarAssuntos();
			}
		}
		return assuntos;
	}
	
	public void mudarAssuntos() {
		TextobaseController textobaseController = this.getTextobaseController();
		this.assuntos = assuntoDao.listar(textobaseController.getNewTextoBase().getDisciplina_textobase());
	}

	public void setAssuntos(List<Assunto> assuntos) {
		this.assuntos = assuntos;
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
	
	public TextobaseController getTextobaseController() {
		FacesContext context = FacesContext.getCurrentInstance();
		@SuppressWarnings("rawtypes")
		Map sessionMap = context.getExternalContext().getSessionMap();
		textobaseController = (TextobaseController)sessionMap.get("textobaseController");
		return textobaseController;
	}

	public void setTextobaseController(TextobaseController textobaseController) {
		this.textobaseController = textobaseController;
	}
	
	public void cadastrar()
	{	
		if (assuntoDao.inserir(this.newAssunto))
			System.out.println("Assunto inserido com sucesso!");
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
				
				
				ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
				externalContext.redirect("Assunto.xhtml?faces-redirect=true&redirect=1");
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	public void excluir() throws IOException
	{
		if (assuntoDao.excluir(this.currentAssunto)){
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			externalContext.redirect("Alunolist.xhtml");
			System.out.println("Aluno excluido com sucesso!");
		}
		else
			System.out.println("Erro na exclusão!");
	}
	
}