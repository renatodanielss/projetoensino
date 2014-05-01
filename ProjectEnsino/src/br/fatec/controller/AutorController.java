package br.fatec.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

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
		this.autores = autorDao.listar();
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
	}
	
	public void iniciaAlterar() throws IOException
	{
		if (newAutor != null)
		{
			try{
				this.getNewAutor().setId_autor(this.getCurrentAutor().getId_autor());
				this.getNewAutor().setNome_autor(this.getCurrentAutor().getNome_autor());
				
				ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
				externalContext.redirect("Autor.xhtml?faces-redirect=true&redirect=1");
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	public void alterar()
	{	
		if (autorDao.alterar(this.newAutor)){
			System.out.println("Autor alterado com sucesso!");
		}
		else
			System.out.println("Erro na alteração!");
	}
	
	public void cadastrarAlterar(){
		if (autorDao.existeAutor(newAutor.getId_autor()))
			alterar();
		else
			cadastrar();
	}
	
	public void excluir() throws IOException
	{
		if (autorDao.excluir(this.currentAutor)){
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			externalContext.redirect("Autorlist.xhtml");
			System.out.println("Autor excluido com sucesso!");
		}
		else
			System.out.println("Erro na exclusão!");
	}
	
	private void limparCampos(){
		this.newAutor.setId_autor(null);
		this.newAutor.setNome_autor(null);
	}
	
	public void goToAutor() throws Exception{
		limparCampos();
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		externalContext.redirect("Autor.xhtml");
	}
}