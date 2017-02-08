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
import br.fatec.dao.TextobaseDAO;
import br.fatec.model.Assunto;
import br.fatec.model.Textobase;

@ManagedBean(name="textobaseController")
@SessionScoped
public class TextobaseController{
	private Textobase newTextoBase;
	private Textobase currentTextoBase;
	private List<Textobase> textosBases;
	private TextobaseDAO textoBaseDao;
	private AssuntoDAO assuntoDao;
	private List<Assunto> textobaseAssuntos;
	private String pesquisa;
	private Integer disciplinaPesquisa;
	private boolean showNewButton;
	
	public TextobaseController()
	{
		
	}
	
	@PostConstruct
	public void preparaDados()
	{
		this.textoBaseDao = new TextobaseDAO();
		this.newTextoBase = new Textobase();
		this.currentTextoBase = new Textobase();
		this.assuntoDao = new AssuntoDAO();
		mostrarSalvar();
	}
	
	public TextobaseDAO getTextoBaseDao() {
		return textoBaseDao;
	}

	public void setTextoBaseDao(TextobaseDAO textoBaseDao) {
		this.textoBaseDao = textoBaseDao;
	}

	public Textobase getCurrentTextoBase() {
		return currentTextoBase;
	}

	public void setCurrentTextoBase(Textobase currentTextoBase) {
		this.currentTextoBase = currentTextoBase;
	}

	public Textobase getNewTextoBase() {
		return newTextoBase;
	}

	public void setNewTextoBase(Textobase newTextoBase) {
		this.newTextoBase = newTextoBase;
	}
	
	public List<Textobase> getTextosBases() 
	{
		if (this.textosBases == null){
			this.textosBases = textoBaseDao.listar();
		}
		return textosBases;
	}
    
	public void setTextosBases(List<Textobase> TextobaseList) 
	{
		this.textosBases = TextobaseList;
	}
	
	public AssuntoDAO getAssuntoDao() {
		return assuntoDao;
	}

	public void setAssuntoDao(AssuntoDAO assuntoDao) {
		this.assuntoDao = assuntoDao;
	}

	public List<Assunto> getTextobaseAssuntos() {
		return this.textobaseAssuntos;
	}

	public void setTextobaseAssuntos(List<Assunto> textobaseAssuntos) {
		textobaseAssuntos = this.textobaseAssuntos;
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
		String mensagem = validarCampos(this.newTextoBase);
		if (mensagem.length() == 0){
			if (textoBaseDao.inserir(this.newTextoBase)){
				setTextosBases(null);
				System.out.println("Texto base inserido com sucesso!");
				this.newTextoBase = new Textobase();
				
				ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
				externalContext.redirect("CadastroConcluido.xhtml?faces-redirect=true&origin=textobase");
			}
			else
				System.out.println("Erro na inserção!");
		}
		else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erros!<br/>", mensagem));
		}
	}
	
	public void iniciaAlterar() throws IOException
	{
		if (newTextoBase != null)
		{
			try{
				this.getNewTextoBase().setCodigo_textobase(this.getCurrentTextoBase().getCodigo_textobase());
				this.getNewTextoBase().setTitulo_textobase(this.getCurrentTextoBase().getTitulo_textobase());
				this.getNewTextoBase().setAutor_textobase(this.getCurrentTextoBase().getAutor_textobase());
				this.getNewTextoBase().setDisciplina_textobase(this.getCurrentTextoBase().getDisciplina_textobase());
				this.getNewTextoBase().setAssunto_textobase(this.getCurrentTextoBase().getAssunto_textobase());
				this.getNewTextoBase().setTexto_textobase(this.getCurrentTextoBase().getTexto_textobase());
				//Popular combo de assuntos com os assuntos relacionados à disciplina recorrente no registro do banco de dados
				this.textobaseAssuntos = this.assuntoDao.listar(this.getNewTextoBase().getDisciplina_textobase().getId_disciplina());
				
				mostrarAlterar();
				ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
				externalContext.redirect("Textobase.xhtml?faces-redirect=true&redirect=1");
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	public void alterar() throws IOException, ParseException
	{
		String mensagem = validarCampos(this.newTextoBase);
		if (mensagem.length() == 0){
			if (textoBaseDao.alterar(this.newTextoBase)){
				setTextosBases(null);
				System.out.println("Textobase alterado com sucesso!");
				this.newTextoBase = new Textobase();
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("questaoController");
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("provaController");
				
				ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
				externalContext.redirect("CadastroConcluido.xhtml?faces-redirect=true&origin=textobase");
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
		if (textoBaseDao.excluir(this.currentTextoBase)){
			setTextosBases(null);
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			externalContext.redirect("Textobaselist.xhtml");
			System.out.println("Textobase excluido com sucesso!");
		}
		else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!<br/>", "Não foi possível excluir!<br>&nbsp;Provavelmente há questões ou outros elementos associados a este texto base."));
			System.out.println("Erro na exclusão!");
		}
	}
	
	private void limparCampos(){
		this.newTextoBase.setCodigo_textobase(null);
		this.newTextoBase.setTitulo_textobase(null);
		this.newTextoBase.setAutor_textobase(null);
		this.newTextoBase.setDisciplina_textobase(null);
		this.newTextoBase.setAssunto_textobase(null);
		this.newTextoBase.setTexto_textobase(null);
		this.mostrarSalvar();
	}
	
	public void goToTextobase() throws Exception{
		limparCampos();
		System.out.println("goToTextobase");
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		externalContext.redirect("Textobase.xhtml");
	}
	
	public void mudarAssuntos() {
		if (this.newTextoBase.getDisciplina_textobase() != null){
			this.textobaseAssuntos = this.assuntoDao.listar(this.newTextoBase.getDisciplina_textobase().getId_disciplina());
		}
		else{
			this.textobaseAssuntos = null;
		}
	}
	
	public String validarCampos(Textobase textobase) throws ParseException{
		String mensagemErro = "";
		if (textobase.getTitulo_textobase().trim().length() == 0)
			mensagemErro += "<br/>-Preencher título";
		if (textobase.getAutor_textobase() == null)
			mensagemErro += "<br/>-Selecionar autor";
		if (textobase.getDisciplina_textobase() == null)
			mensagemErro += "<br/>-Selecionar disciplina";
		if (textobase.getAssunto_textobase() == null)
			mensagemErro += "<br/>-Selecionar assunto";
		if (textobase.getTexto_textobase().trim().length() == 0)
			mensagemErro += "<br/>-Preencher texto";
		return mensagemErro;
	}
	
	public void pesquisar(){
		this.textosBases = this.textoBaseDao.listar(this.pesquisa, this.disciplinaPesquisa);
		if (this.textosBases == null){
			this.textosBases = this.getTextosBases();
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