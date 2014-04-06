package br.fatec.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.fatec.dao.DisciplinaDAO;
import br.fatec.model.Disciplina;

@ManagedBean(name="disciplinaController")
@SessionScoped

public class DisciplinaController {
	private List<Disciplina> disciplinas;
	private DisciplinaDAO disciplinaDao;
	private Disciplina currentDisciplina;
	private Disciplina newDisciplina;
	
	public DisciplinaController()
	{
		
	}
	
	@PostConstruct
	public void preparaDados()
	{
		this.disciplinaDao = new DisciplinaDAO();
		this.newDisciplina = new Disciplina();
		this.currentDisciplina = new Disciplina();
	}

	public List<Disciplina> getDisciplinas() {
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
		if (disciplinaDao.inserir(this.newDisciplina))
			System.out.println("Disciplina inserida com sucesso!");
		else
			System.out.println("Erro na inserção!");
		
		this.newDisciplina = new Disciplina();
	}
}
