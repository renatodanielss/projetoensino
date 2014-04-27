package br.fatec.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.fatec.dao.AutorDAO;
import br.fatec.model.Autor;

@ManagedBean(name="autorController")
@SessionScoped

public class AutorController {
	private List<Autor> autores;
	private AutorDAO autorDao;
	private Autor currentAutor;
	private Autor newAutor;
	
	public AutorController()
	{
		
	}
	
	@PostConstruct
	public void preparaDados()
	{
		this.autorDao = new AutorDAO();
		this.newAutor = new Autor();
		this.currentAutor = new Autor();
	}
	
	public List<Autor> getAutores() {
		return autores;
	}

	public void setAutores(List<Autor> autores) {
		this.autores = autores;
	}

	public AutorDAO getAutorDao() {
		return autorDao;
	}

	public void setAutorDao(AutorDAO autorDao) {
		this.autorDao = autorDao;
	}

	public Autor getCurrentAutor() {
		return currentAutor;
	}

	public void setCurrentAutor(Autor currentAutor) {
		this.currentAutor = currentAutor;
	}

	public Autor getNewAutor() {
		return newAutor;
	}

	public void setNewAutor(Autor newAutor) {
		this.newAutor = newAutor;
	}

	public void cadastrar()
	{	
		if (autorDao.inserir(this.newAutor))
			System.out.println("Autor inserido com sucesso!");
		else
			System.out.println("Erro na inserção!");
		
		this.newAutor = new Autor();
	}
}