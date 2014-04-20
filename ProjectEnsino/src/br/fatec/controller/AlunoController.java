package br.fatec.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.fatec.dao.AlunoDAO;
import br.fatec.model.Aluno;

@ManagedBean(name="alunoController")
@SessionScoped

public class AlunoController {
	private List<Aluno> alunos;
	private AlunoDAO alunoDao;
	private Aluno currentAluno;
	private Aluno newAluno;
		
	public AlunoController()
	{
		
	}
	
	@PostConstruct
	public void preparaDados()
	{
		this.alunoDao = new AlunoDAO();
		this.newAluno = new Aluno();
		this.currentAluno = new Aluno();
	}
	
	public List<Aluno> getAlunos() {
		this.alunos = alunoDao.listar();
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}

	public AlunoDAO getAlunoDao() {
		return alunoDao;
	}

	public void setAlunoDao(AlunoDAO alunoDao) {
		this.alunoDao = alunoDao;
	}

	public Aluno getCurrentAluno() {
		return currentAluno;
	}

	public void setCurrentAluno(Aluno currentAluno) {
		this.currentAluno = currentAluno;
	}

	public Aluno getNewAluno() {
		return newAluno;
	}

	public void setNewAluno(Aluno newAluno) {
		this.newAluno = newAluno;
	}

	public void cadastrar()
	{	
		if (alunoDao.inserir(this.newAluno))
			System.out.println("Aluno inserido com sucesso!");
		else
			System.out.println("Erro na inserção!");
		
		this.newAluno= new Aluno();
	}
	
}
