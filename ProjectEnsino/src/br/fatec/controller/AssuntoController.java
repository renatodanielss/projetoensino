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

import br.fatec.dao.AssuntoDAO;
import br.fatec.model.Assunto;

@ManagedBean(name="assuntoController")
@SessionScoped

public class AssuntoController {
	private Assunto newAssunto;
	private Assunto currentAssunto;
	private AssuntoDAO assuntoDao;
	private List<Assunto> assuntos;
	private String pesquisa;
	private Integer disciplinaPesquisa;
	private boolean showNewButton;
	
	public AssuntoController()
	{
		
	}

	@PostConstruct
	public void preparaDados()
	{
		this.assuntoDao = new AssuntoDAO();
		this.newAssunto = new Assunto();
		this.currentAssunto = new Assunto();
		mostrarSalvar();
	}

	public List<Assunto> getAssuntos() {
		if (this.assuntos == null){
			this.assuntos = assuntoDao.listar();
		}
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
	
	public boolean getShowNewButton(){
		return showNewButton;
	}
	
	public void setShowNewButton(boolean showNewButton){
		this.showNewButton = showNewButton;
	}
	
	public void mostrarAlterar(){
		this.showNewButton = false;
	}
		  
	public void mostrarSalvar(){
	     this.showNewButton = true;
	}
	
	public String getPesquisa() {
		return pesquisa;
	}

	public void setPesquisa(String pesquisa) {
		this.pesquisa = pesquisa;
	}
	
	public Integer getDisciplinaPesquisa() {
		return disciplinaPesquisa;
	}

	public void setDisciplinaPesquisa(Integer disciplinaPesquisa) {
		this.disciplinaPesquisa = disciplinaPesquisa;
	}
	
	public void cadastrar() throws IOException, ParseException
	{	

		String mensagem = validarCampos(this.newAssunto);
	
		if (mensagem.length() == 0)
		{
			if (assuntoDao.inserir(this.newAssunto)){
				setAssuntos(null);
				System.out.println("Assunto inserido com sucesso!");
				this.newAssunto = new Assunto();
				
				ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
				externalContext.redirect("CadastroConcluido.xhtml?faces-redirect=true&origin=assunto");
			}
			else
				System.out.println("Erro na inserção!");
		}
		else
		{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erros!<br/>", mensagem));
		}
	}
	
	public void iniciaAlterar() throws IOException
	{
		if (newAssunto != null)
		{
			try{
				this.getNewAssunto().setId_assunto(this.getCurrentAssunto().getId_assunto());
				this.getNewAssunto().setNome_assunto(this.getCurrentAssunto().getNome_assunto());
				this.getNewAssunto().setDisciplina_assunto(this.getCurrentAssunto().getDisciplina_assunto());
				
				mostrarAlterar();
				
				ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
				externalContext.redirect("Assunto.xhtml?faces-redirect=true&redirect=1");
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	public void alterar() throws IOException, ParseException
	{	
		String mensagem = validarCampos(this.newAssunto);
		
		if (mensagem.length() == 0)
		{
			if (assuntoDao.alterar(this.newAssunto))
			{
				setAssuntos(null);
				System.out.println("Assunto alterado com sucesso!");
				this.newAssunto = new Assunto();
				
				//Atualiza referências
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("textobaseController");
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("questaoController");
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("provaController");
				
				ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();	
				externalContext.redirect("CadastroConcluido.xhtml?faces-redirect=true&origin=assunto");
				
			}
			else
				System.out.println("Erro na alteração!");
		}
		else
		{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erros!<br/>", mensagem));
		}
	}
	
	public void excluir() throws IOException
	{
		if (assuntoDao.excluir(this.currentAssunto)){
			setAssuntos(null);
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			externalContext.redirect("Assuntolist.xhtml");
			System.out.println("Assunto excluido com sucesso!");
		}
		else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!<br/>", "Não foi possível excluir!<br>&nbsp;Provavelmente há disciplinas, questões ou outros elementos associados a este assunto."));
			System.out.println("Erro na exclusão!");
		}
	}
	
	private void limparCampos(){
		this.newAssunto.setId_assunto(null);
		this.newAssunto.setDisciplina_assunto(null);
		this.newAssunto.setNome_assunto(null);
		this.mostrarSalvar();
	}
	
	public void goToAssunto() throws Exception{
		limparCampos();
		System.out.println("goToAssunto");
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		externalContext.redirect("Assunto.xhtml");
	}
	
	//validar - método para validação de atributos do objeto associado à view
	private String validarCampos(Assunto assunto){
		String mensagemErro = "";
		
		if (assunto.getNome_assunto().trim().length() == 0)
			mensagemErro += "<br/>-Preencher campo nome";
		if (assunto.getDisciplina_assunto() == null)
			mensagemErro += "<br/>-Preencher campo disciplina";
		
		return mensagemErro;
	}
	
	public void pesquisar(){
		this.assuntos = this.assuntoDao.listar(this.pesquisa, this.disciplinaPesquisa);
		if (this.assuntos == null){
			this.assuntos = this.getAssuntos();
		}
	}
}