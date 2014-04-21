package br.fatec.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.fatec.dao.AssuntoDAO;
import br.fatec.model.Assunto;

@ManagedBean(name="assuntoController")
@SessionScoped

public class AssuntoController {
	private List<Assunto> assuntos;
	private AssuntoDAO assuntoDao;
	private Assunto currentAssunto;
	private Assunto newAssunto;
	
	public AssuntoController()
	{
		
	}
	
	@PostConstruct
	public void preparaDados()
	{
		this.assuntoDao = new AssuntoDAO();
		this.newAssunto = new Assunto();
		this.currentAssunto = new Assunto();
	}

	public List<Assunto> getAssuntos() {
		this.assuntos = assuntoDao.listar();
		return assuntos;
	}
	
	public List<Assunto> getAssuntos(int iddisciplina_assunto) {
		this.assuntos = assuntoDao.listar(iddisciplina_assunto);
		return assuntos;
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
	
	public void cadastrar()
	{	
		if (assuntoDao.inserir(this.newAssunto))
			System.out.println("Assunto inserido com sucesso!");
		else
			System.out.println("Erro na inserção!");
		
		this.newAssunto = new Assunto();
	}
}