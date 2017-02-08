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

import br.edu.fatec.calendar.Date;
import br.fatec.dao.ProfessorDAO;
import br.fatec.model.Autor;
import br.fatec.model.Professor;

@ManagedBean(name="professorController")
@SessionScoped
public class ProfessorController {
	private List<Professor> professores;
	private ProfessorDAO professorDao;
	private Professor currentProfessor;
	private Professor newProfessor;
	private AutorController autorController;
	private String pesquisa;
	private boolean showNewButton;
	private boolean bloquearMatricula;
	
	public ProfessorController()
	{
		
	}
	
	@PostConstruct
	public void preparaDados()
	{
		this.professorDao = new ProfessorDAO();
		this.newProfessor = new Professor();
		this.currentProfessor = new Professor();
		mostrarSalvar();
		
		liberarCampo();
	}

	public List<Professor> getProfessores() {
		if (this.professores == null){
			this.professores = this.professorDao.listar();
		}
		return this.professores;
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
	
	public String getPesquisa() {
		return pesquisa;
	}

	public void setPesquisa(String pesquisa) {
		this.pesquisa = pesquisa;
	}

	public boolean getShowNewButton() {
		return showNewButton;
	}
	
	public void setShowNewButton(boolean showNewButton) {
		this.showNewButton = showNewButton;
	}
	
	public boolean getBloquearMatricula() {
		return bloquearMatricula;
	}

	public void setBloquearMatricula(boolean bloquearMatricula) {
		this.bloquearMatricula = bloquearMatricula;
	}

	public void mostrarAlterar(){
		this.showNewButton = false;
	}
		  
	public void mostrarSalvar(){
	     this.showNewButton = true;
	}

	//validar - método cadastrar atualizado. Nesta versï¿½o ï¿½ verificado se o mï¿½todo cadastrarCampos verificou algum erro, caso sim, este mï¿½todo exibirï¿½ os erros na pï¿½gina.
	//As mesmas alteraï¿½ï¿½es deverï¿½o ser feitas no mï¿½todo alterar.
	public void cadastrar() throws IOException, ParseException
	{
		String mensagem = validarCampos(this.newProfessor);
		if (mensagem.length() == 0){
			if (professorDao.inserir(this.newProfessor)){
				autorController = new AutorController();
				autorController.preparaDados();
				Autor autor = new Autor();
				autor.setNome_autor(newProfessor.getNome_professor());
				autorController.setNewAutor(autor);
				autorController.cadastrarProfessor();
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("autorController");
				
				setProfessores(null);
				System.out.println("Professor inserido com sucesso!");
				this.newProfessor = new Professor();
				
				//validar - importante adicionar o redirect com o parï¿½metro origin=nome da entidade (letras minï¿½sculas, sem espaï¿½os oou caracteres especiais, por exemplo:
				//"CadastroConcluido.xhtml?faces-redirect=true&origin=textobase".
				ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
				externalContext.redirect("CadastroConcluido.xhtml?faces-redirect=true&origin=professor");
			}
			else
				System.out.println("Erro na inserÃ§Ã£o!");
			
			this.newProfessor = new Professor();
		}
		else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erros!<br/>", mensagem));
		}
	}
	
	public void iniciaAlterar() throws IOException
	{
		if (newProfessor != null)
		{
			try{
				this.getNewProfessor().setMatricula_professor(this.getCurrentProfessor().getMatricula_professor());
				this.getNewProfessor().setCpf_professor(this.getCurrentProfessor().getCpf_professor());
				this.getNewProfessor().setNome_professor(this.getCurrentProfessor().getNome_professor());
				this.getNewProfessor().setDatanasc_professor(this.getCurrentProfessor().getDatanasc_professor());
				this.getNewProfessor().setTelefone_professor(this.getCurrentProfessor().getTelefone_professor());
				this.getNewProfessor().setCelular_professor(this.getCurrentProfessor().getCelular_professor());
				this.getNewProfessor().setSexo_professor(this.getCurrentProfessor().getSexo_professor());
				this.getNewProfessor().setLogradouro_professor(this.getCurrentProfessor().getLogradouro_professor());
				this.getNewProfessor().setNumero_professor(this.getCurrentProfessor().getNumero_professor());
				this.getNewProfessor().setComplemento_professor(this.getCurrentProfessor().getComplemento_professor());
				this.getNewProfessor().setBairro_professor(this.getCurrentProfessor().getBairro_professor());
				this.getNewProfessor().setCidade_professor(this.getCurrentProfessor().getCidade_professor());
				this.getNewProfessor().setUf_professor(this.getCurrentProfessor().getUf_professor());
				this.getNewProfessor().setCep_professor(this.getCurrentProfessor().getCep_professor());
				this.getNewProfessor().setEmail_professor(this.getCurrentProfessor().getEmail_professor());
				
				mostrarAlterar();
				bloquearCampo();
				
				ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
				externalContext.redirect("Professor.xhtml?faces-redirect=true&redirect=1");
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	public void alterar() throws IOException, ParseException
	{
		String mensagem = validarCampos(this.newProfessor);
		if (mensagem.length() == 0){
			if (professorDao.alterar(this.newProfessor)){
				setProfessores(null);
				System.out.println("Professor alterado com sucesso!");
				this.newProfessor = new Professor();
				
				//Atualiza referências
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("usuarioProfessorController");
				
				ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
				externalContext.redirect("CadastroConcluido.xhtml?faces-redirect=true&origin=professor");
			}
			else
				System.out.println("Erro na alteraÃ§Ã£o!");
			}
		else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erros!<br/>", mensagem));
		}
	}
	
	public void excluir() throws IOException
	{
		if (professorDao.excluir(this.currentProfessor)){
			setProfessores(null);
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			externalContext.redirect("Professorlist.xhtml");
			System.out.println("Professor excluido com sucesso!");
		}
		else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!<br/>", "Não foi possível excluir!<br>&nbsp;Provavelmente há usuário associado a este professor."));
			System.out.println("Erro na exclusão!");
		}
	}
	
	private void limparCampos(){
			
		this.getNewProfessor().setMatricula_professor(null);
		this.getNewProfessor().setCpf_professor(null);
		this.getNewProfessor().setNome_professor(null);
		this.getNewProfessor().setDatanasc_professor(null);
		this.getNewProfessor().setTelefone_professor(null);
		this.getNewProfessor().setCelular_professor(null);
		this.getNewProfessor().setSexo_professor(null);
		this.getNewProfessor().setLogradouro_professor(null);
		this.getNewProfessor().setNumero_professor(null);
		this.getNewProfessor().setComplemento_professor(null);
		this.getNewProfessor().setBairro_professor(null);
		this.getNewProfessor().setCidade_professor(null);
		this.getNewProfessor().setUf_professor(null);
		this.getNewProfessor().setCep_professor(null);
		this.getNewProfessor().setEmail_professor(null);
		
		mostrarSalvar();
		liberarCampo();
	}
	
	public void goToProfessor() throws Exception{
		limparCampos();
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		externalContext.redirect("Professor.xhtml");
	}
	
	public void bloquearCampo()
	{
		this.bloquearMatricula = true;
	}
	
	public void liberarCampo()
	{
		this.bloquearMatricula = false;
	}
	
	//validar - mï¿½todo para validaï¿½ï¿½o de atributos do objeto associado ï¿½ view, este mï¿½todo serï¿½ chamado pelo mï¿½todo cadastrar e alterar
	public String validarCampos(Professor professor) throws ParseException{
		String mensagemErro = "";
		if (professor.getMatricula_professor().trim().length() == 0)
			mensagemErro += "<br/>-Preencher matrícula";
		if (professor.getCpf_professor().trim().length() == 0)
			mensagemErro += "<br/>-Preencher CPF";
		if (professor.getNome_professor().trim().length() == 0)
			mensagemErro += "<br/>-Preencher nome";
		if (!Date.isDate(professor.getDatanasc_professor()))
			mensagemErro += "<br/>-Data de nascimento inválida";
		if (!professor.getEmail_professor().trim().matches("[^@ ]*[@][a-zA-Z0-9]*[.][a-zA-Z.]*"))
			mensagemErro += "<br/>-Email inválido";
		return mensagemErro;
	}
	
	public void pesquisar(){
		this.professores = this.professorDao.listar(this.pesquisa);
		if (this.professores == null){
			this.professores = this.getProfessores();
		}
	}
}