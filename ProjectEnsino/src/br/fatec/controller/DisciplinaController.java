package br.fatec.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import br.fatec.dao.DisciplinaDAO;
import br.fatec.model.Disciplina;

@ManagedBean(name="disciplinaController")
@SessionScoped

public class DisciplinaController {
	private List<Disciplina> disciplinas;
	private DisciplinaDAO disciplinaDao;
	private Disciplina currentDisciplina;
	private Disciplina newDisciplina;
	private boolean showNewButton;
	
	public DisciplinaController()
	{
		
	}
	
	@PostConstruct
	public void preparaDados()
	{
		this.disciplinaDao = new DisciplinaDAO();
		this.newDisciplina = new Disciplina();
		this.currentDisciplina = new Disciplina();
		mostrarSalvar();
	}

	public List<Disciplina> getDisciplinas() {
		if (this.disciplinas == null){
			this.disciplinas = disciplinaDao.listar();
		}
		return disciplinas;
	}

	public void setDisciplinas(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}

	public DisciplinaDAO getDisciplinaDao() {
		return disciplinaDao;
	}

	public void setDisciplinaDao(DisciplinaDAO disciplinaDao) {
		this.disciplinaDao = disciplinaDao;
	}

	public Disciplina getCurrentDisciplina() {
		return currentDisciplina;
	}

	public void setCurrentDisciplina(Disciplina currentDisciplina) {
		this.currentDisciplina = currentDisciplina;
	}

	public Disciplina getNewDisciplina() {
		return newDisciplina;
	}

	public void setNewDisciplina(Disciplina newDisciplina) {
		this.newDisciplina = newDisciplina;
	}
	
	public void cadastrar()
	{	
		if (disciplinaDao.inserir(this.newDisciplina)){
			setDisciplinas(null);
			System.out.println("Disciplina inserida com sucesso!");
			this.newDisciplina = new Disciplina();
		}
		else
			System.out.println("Erro na inserção!");
	}
	
	public void iniciaAlterar() throws IOException
	{
		if (newDisciplina != null)
		{
			try{
				this.getNewDisciplina().setId_disciplina(this.getCurrentDisciplina().getId_disciplina());
				this.getNewDisciplina().setNome_disciplina(this.getCurrentDisciplina().getNome_disciplina());
				
				mostrarAlterar();
				ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
				externalContext.redirect("Disciplina.xhtml?faces-redirect=true&redirect=1");
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	public void alterar()
	{	
		if (disciplinaDao.alterar(this.newDisciplina)){
			setDisciplinas(null);
			System.out.println("Disciplina alterada com sucesso!");
			this.newDisciplina = new Disciplina();
		}
		else
			System.out.println("Erro na alteração!");
	}
	
	public void excluir() throws IOException
	{
		if (disciplinaDao.excluir(this.currentDisciplina)){
			setDisciplinas(null);
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			externalContext.redirect("Disciplinalist.xhtml");
			System.out.println("Disciplina excluida com sucesso!");
		}
		else
			System.out.println("Erro na exclusão!");
	}
	
	private void limparCampos(){
		this.newDisciplina.setId_disciplina(null);
		this.newDisciplina.setNome_disciplina(null);
		this.mostrarSalvar();
	}
	
	public void goToDisciplina() throws Exception{
		limparCampos();
		System.out.println("goToDisciplina");
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		externalContext.redirect("Disciplina.xhtml");
	}
	
	public boolean getShowNewButton(){
		return showNewButton;
	}
		  
	public void mostrarAlterar(){
		this.showNewButton = false;
	}
		  
	public void mostrarSalvar(){
	     this.showNewButton = true;
	}
}