package br.fatec.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import br.fatec.dao.ProfessorDAO;
import br.fatec.model.Professor;

@ManagedBean(name="professorController")
@SessionScoped

public class ProfessorController {
	private List<Professor> professores;
	private ProfessorDAO professorDao;
	private Professor currentProfessor;
	private Professor newProfessor;
	
	public ProfessorController()
	{
		
	}
	
	@PostConstruct
	public void preparaDados()
	{
		this.professorDao = new ProfessorDAO();
		this.newProfessor = new Professor();
		this.currentProfessor = new Professor();
	}

	
	
	public List<Professor> getProfessores() {
		professores = professorDao.listar();
		return professores;
	}

	public void setProfessores(List<Professor> professores) {
		this.professores = professores;
	}

	public ProfessorDAO getProfessorDao() {
		return professorDao;
	}

	public void setProfessorDao(ProfessorDAO professorDao) {
		this.professorDao = professorDao;
	}

	public Professor getCurrentProfessor() {
		return currentProfessor;
	}

	public void setCurrentProfessor(Professor currentProfessor) {
		this.currentProfessor = currentProfessor;
	}

	public Professor getNewProfessor() {
		return newProfessor;
	}

	public void setNewProfessor(Professor newProfessor) {
		this.newProfessor = newProfessor;
	}

	public void cadastrar()
	{	
		if (professorDao.inserir(this.newProfessor))
			System.out.println("Professor inserido com sucesso!");
		else
			System.out.println("Erro na inserção!");
		
		this.newProfessor = new Professor();
	}
	
	public void iniciaAlterar() throws IOException
	{
		if (newProfessor != null)
		{
			try{
				this.getNewProfessor().setMatricula_professor(this.getCurrentProfessor().getMatricula_professor());
				this.getNewProfessor().setCpf_professor(this.getCurrentProfessor().getCpf_professor());
				this.getNewProfessor().setDisciplina_professor(this.getCurrentProfessor().getDisciplina_professor());
				this.getNewProfessor().setNome_professor(this.getCurrentProfessor().getNome_professor());
				this.getNewProfessor().setDatanasc_professor(this.getCurrentProfessor().getDatanasc_professor());
				this.getNewProfessor().setTelefone_professor(this.getCurrentProfessor().getTelefone_professor());
				this.getNewProfessor().setCelular_professor(this.getCurrentProfessor().getCelular_professor());
				this.getNewProfessor().setSexo_professor(this.getCurrentProfessor().getSexo_professor());
				this.getNewProfessor().setLogradouro_professor(this.getCurrentProfessor().getLogradouro_professor());
				this.getNewProfessor().setNumero_professor(this.getCurrentProfessor().getNumero_professor());
				this.getNewProfessor().setComplemento_professor(this.getCurrentProfessor().getComplemento_professor());
				this.getNewProfessor().setBairro_professor(this.getCurrentProfessor().getBairro_professor());
				this.getNewProfessor().setCidade_professor(this.getCurrentProfessor().getCidade_professor());
				this.getNewProfessor().setUf_professor(this.getCurrentProfessor().getUf_professor());
				this.getNewProfessor().setCep_professor(this.getCurrentProfessor().getCep_professor());
				this.getNewProfessor().setEmail_professor(this.getCurrentProfessor().getEmail_professor());
				
				ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
				externalContext.redirect("Professor.xhtml?faces-redirect=true&redirect=1");
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	public void alterar()
	{	
		if (professorDao.alterar(this.newProfessor)){
			System.out.println("Professor alterado com sucesso!");
			limparCampos();
		}
		else
			System.out.println("Erro na alteração!");
	}
	
	public void cadastrarAlterar(){
		if (professorDao.existeProfessor(newProfessor.getMatricula_professor()))
			alterar();
		else
			cadastrar();
	}
	
	public void excluir() throws IOException
	{
		if (professorDao.excluir(this.currentProfessor)){
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			externalContext.redirect("Professorlist.xhtml");
			System.out.println("Professor excluido com sucesso!");
		}
		else
			System.out.println("Erro na exclusão!");
	}
	
	private void limparCampos(){
			
		this.getNewProfessor().setMatricula_professor(null);
		this.getNewProfessor().setCpf_professor(null);
		this.getNewProfessor().setDisciplina_professor(null);
		this.getNewProfessor().setNome_professor(null);
		this.getNewProfessor().setDatanasc_professor(null);
		this.getNewProfessor().setTelefone_professor(null);
		this.getNewProfessor().setCelular_professor(null);
		this.getNewProfessor().setSexo_professor(null);
		this.getNewProfessor().setLogradouro_professor(null);
		this.getNewProfessor().setNumero_professor(null);
		this.getNewProfessor().setComplemento_professor(null);
		this.getNewProfessor().setBairro_professor(null);
		this.getNewProfessor().setCidade_professor(null);
		this.getNewProfessor().setUf_professor(null);
		this.getNewProfessor().setCep_professor(null);
		this.getNewProfessor().setEmail_professor(null);
	}
	
	public void goToProfessor() throws Exception{
		limparCampos();
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		externalContext.redirect("Professor.xhtml");
	}
}