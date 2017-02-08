package br.fatec.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
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
	private boolean showNewButton;
	private String pesquisa;
	
	public AutorController()
	{
		
	}
	 
	@PostConstruct
	public void preparaDados()
	{
		this.autorDao = new AutorDAO();
		this.newAutor = new Autor();
		this.currentAutor = new Autor();
		mostrarSalvar();
	}
	
	public List<Autor> getAutores() {
		if (this.autores == null){
			this.autores = autorDao.listar();
		}
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
	
	//public void setShowNewButton(boolean showNewButton) {
		//this.showNewButton = showNewButton;
	//}
	
	public String getPesquisa() {
		return pesquisa;
	}

	public void setPesquisa(String pesquisa) {
		this.pesquisa = pesquisa;
	}
	
	public void cadastrar() throws IOException, ParseException
	{
		String mensagem = validarCampos(this.newAutor);
		if (mensagem.length() == 0){
			if (autorDao.inserir(this.newAutor)){
				setAutores(null);
				System.out.println("Autor inserido com sucesso!");
				this.newAutor = new Autor();
				
				ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
				externalContext.redirect("CadastroConcluido.xhtml?faces-redirect=true&origin=autor");
			}
			else
				System.out.println("Erro na inserção!");
		}
		else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erros!<br/>", mensagem));
		}
	}
	
	public void cadastrarProfessor() throws IOException
	{
		if (autorDao.inserir(this.newAutor)){
			setAutores(null);
			System.out.println("Autor inserido com sucesso!");
			this.newAutor = new Autor();
		}
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
				
				mostrarAlterar();
				ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
				externalContext.redirect("Autor.xhtml?faces-redirect=true&redirect=1");
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	public void alterar() throws IOException, ParseException
	{
		String mensagem = validarCampos(this.newAutor);
		if (mensagem.length() == 0){
			if (autorDao.alterar(this.newAutor)){
				setAutores(null);
				System.out.println("Autor alterado com sucesso!");
				this.newAutor = new Autor();
				
				//Atualiza referências
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("questaoController");
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("textobaseController");
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("provaController");
				
				ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
				externalContext.redirect("CadastroConcluido.xhtml?faces-redirect=true&origin=autor");
			}
			else
				System.out.println("Erro na alteração!");
		}
		else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erros!<br/>", mensagem));
		}
	}
	
	public void excluir() throws IOException
	{
		if (autorDao.excluir(this.currentAutor)){
			setAutores(null);
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			externalContext.redirect("Autorlist.xhtml");
			System.out.println("Autor excluido com sucesso!");
		}
		else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!<br/>", "Não foi possível excluir!<br>&nbsp;Provavelmente há questões ou outros elementos associados a este autor."));
			System.out.println("Erro na exclusão!");
		}
	}
	
	private void limparCampos(){
		this.newAutor.setId_autor(null);
		this.newAutor.setNome_autor(null);
		this.mostrarSalvar();
	}
	
	public void goToAutor() throws Exception{
		limparCampos();
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		externalContext.redirect("Autor.xhtml");
	}

	public String validarCampos(Autor autor) throws ParseException{
		String mensagemErro = "";
		if (autor.getNome_autor().trim().length() == 0)
			mensagemErro += "<br/>-Preencher nome do autor";
		return mensagemErro;
	}
	
	public void pesquisar(){
		this.autores = this.autorDao.listar(this.pesquisa);
		if (this.autores == null){
			this.autores = this.getAutores();
		}
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