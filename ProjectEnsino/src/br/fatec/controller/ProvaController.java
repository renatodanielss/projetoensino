package br.fatec.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.fatec.dao.ProfessorDAO;
import br.fatec.dao.ProvaDAO;
import br.fatec.model.Professor;
import br.fatec.model.Prova;


@ManagedBean(name="provaController")
@SessionScoped

public class ProvaController {

	
	private List<Prova> provas;
	private ProvaDAO provaDao;
	private Prova currentProva;
	private Prova newProva;
	
	public ProvaController()
	{
		
	}
	
	public List<Prova> getProvas() {
		return provas;
	}

	public void setProvas(List<Prova> provas) {
		this.provas = provas;
	}

	public ProvaDAO getProvaDao() {
		return provaDao;
	}

	public void setProvaDao(ProvaDAO provaDao) {
		this.provaDao = provaDao;
	}

	public Prova getCurrentProva() {
		return currentProva;
	}

	public void setCurrentProva(Prova currentProva) {
		this.currentProva = currentProva;
	}

	public Prova getNewProva() {
		return newProva;
	}

	public void setNewProva(Prova newProva) {
		this.newProva = newProva;
	}

	@PostConstruct
	public void preparaDados()
	{
		this.provaDao = new ProvaDAO();
		this.newProva = new Prova();
		this.currentProva = new Prova();
	}
	
	public void cadastrar()
	{	
		if (provaDao.inserir(this.newProva))
			System.out.println("Professor inserido com sucesso!");
		else
			System.out.println("Erro na inserção!");
		
		this.newProva = new Prova();
	}
	
}
