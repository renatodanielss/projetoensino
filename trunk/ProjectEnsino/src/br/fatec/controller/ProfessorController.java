package br.fatec.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

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
}
