package br.fatec.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import br.fatec.dao.ProvaDAO;
import br.fatec.dao.QuestaoDAO;
import br.fatec.model.Prova;
import br.fatec.model.Questao;
import br.fatec.model.UsuarioProfessor;
import br.fatec.util.SessionUtil;


@ManagedBean(name="provaController")
@SessionScoped
public class ProvaController {

	
	private List<Prova> provas;
	private ProvaDAO provaDao;
	private Prova currentProva;
	private Prova newProva;
	private List<Questao> questoes;
	private QuestaoDAO questaoDao;
	private String pesquisa;
	private Integer disciplinaPesquisa;
	private boolean showNewButton;
	
	public ProvaController()
	{
		
	}
	
	@PostConstruct
	public void preparaDados()
	{
		this.provaDao = new ProvaDAO();
		this.newProva = new Prova();
		this.currentProva = new Prova();
		this.questoes = null;
		this.questaoDao = new QuestaoDAO();
		mostrarSalvar();
	}
	
	public List<Prova> getProvas() {
		if (this.provas == null){
			this.provas = this.provaDao.listar();
		}
		return this.provas;
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
	
	public List<Questao> getQuestoes() {
		return questoes;
	}

	public void setQuestoes(List<Questao> questoes) {
		this.questoes = questoes;
	}
	
	public QuestaoDAO getQuestaoDao() {
		return questaoDao;
	}

	public void setQuestaoDao(QuestaoDAO questaoDao) {
		this.questaoDao = questaoDao;
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
		String mensagem = validarCampos(this.newProva);
		if (mensagem.length() == 0){
			Date dtDataCriacao = new Date();
			Calendar calDataCriacao = Calendar.getInstance();
			calDataCriacao.setTime(dtDataCriacao);
			this.newProva.setData_criacao_prova(new Timestamp(calDataCriacao.getTimeInMillis()));
			this.newProva.setData_ultima_alteracao_prova(new Timestamp(calDataCriacao.getTimeInMillis()));
			this.newProva.setUsuario_professor_prova((UsuarioProfessor)SessionUtil.getParam("user"));
			this.newProva.setQuestoes_prova(this.questoes);
			if (provaDao.inserir(this.newProva)){
				setProvas(null);
				System.out.println("Prova inserido com sucesso!");
				this.newProva = new Prova();
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("provaController");
				
				ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
				externalContext.redirect("CadastroConcluido.xhtml?faces-redirect=true&origin=prova");
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
		if (this.newProva != null)
		{
			try{
				this.newProva.setId_prova(this.currentProva.getId_prova());
				this.newProva.setTitulo_prova(this.currentProva.getTitulo_prova());
				this.newProva.setDisciplina_prova(this.currentProva.getDisciplina_prova());
				this.newProva.setAssunto_prova(this.currentProva.getAssunto_prova());
				this.newProva.setNumeroQuestoes_prova(this.currentProva.getNumeroQuestoes_prova());
				this.newProva.setData_criacao_prova(this.currentProva.getData_criacao_prova());
				this.newProva.setData_ultima_alteracao_prova(this.currentProva.getData_ultima_alteracao_prova());
				this.newProva.setVersao_prova(this.currentProva.getVersao_prova());
				this.newProva.setUsuario_professor_prova(this.currentProva.getUsuario_professor_prova());
				this.newProva.setQuestoes_prova(this.currentProva.getQuestoes_prova());
				this.newProva.setQuestoes_usadas_prova(this.currentProva.getQuestoes_usadas_prova());
				this.setQuestoes(this.newProva.getQuestoes_prova());
				
				mostrarAlterar();
				ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
				externalContext.redirect("Prova.xhtml?faces-redirect=true&redirect=1");
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	public void alterar() throws IOException, ParseException
	{
		String mensagem = validarCampos(this.newProva);
		if (mensagem.length() == 0){
			UsuarioProfessor usuarioSessao = (UsuarioProfessor)SessionUtil.getParam("user");
			if (usuarioSessao.equals(this.getNewProva().getUsuario_professor_prova())){
				Date dtDataUltimaAlteracao = new Date();
				Calendar calDataUltimaAlteracao = Calendar.getInstance();
				calDataUltimaAlteracao.setTime(dtDataUltimaAlteracao);
				this.newProva.setData_ultima_alteracao_prova(new Timestamp(calDataUltimaAlteracao.getTimeInMillis()));
				this.newProva.setUsuario_professor_prova((UsuarioProfessor)SessionUtil.getParam("user"));
				this.newProva.setQuestoes_prova(this.questoes);
				if (provaDao.alterar(this.newProva)){
					setProvas(null);
					System.out.println("Prova alterada com sucesso!");
					this.newProva = new Prova();
					
					ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
					externalContext.redirect("CadastroConcluido.xhtml?faces-redirect=true&origin=prova");
				}
				else
					System.out.println("Erro na alteração!");
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("provaController");
			}
			else{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!<br/>", "Só é permitido editar provas que você mesmo criou!"));
			}
		}
		else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erros!<br/>", mensagem));
		}
	}
	
	public void excluir() throws IOException
	{
		UsuarioProfessor usuarioSessao = (UsuarioProfessor)SessionUtil.getParam("user");
		if (usuarioSessao.equals(this.getCurrentProva().getUsuario_professor_prova())){
			if (provaDao.excluir(this.currentProva)){
				setProvas(null);
				ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
				externalContext.redirect("Provalist.xhtml");
				System.out.println("Prova excluida com sucesso!");
			}
			else
				System.out.println("Erro na exclusão!");
		}
		else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!<br/>", "Só é permitido excluir provas que você mesmo criou!"));
		}
	}
	
	public char obterLetra(int status){
		status += 96;
		return (char)status;
	}
	
	public void gerarProva(){
		Random random = new Random();
		int indice;
		this.questoes = null;
		this.questoes = new ArrayList<>();
		List<Questao> questaoAux = null;
		int id_disciplina;
		int id_assunto;
		
		if (this.newProva.getDisciplina_prova() != null && this.newProva.getDisciplina_prova().getNome_disciplina().length() > 0)
			id_disciplina = this.newProva.getDisciplina_prova().getId_disciplina();
		else
			id_disciplina = -1;
		
		if (this.newProva.getAssunto_prova() != null && this.newProva.getAssunto_prova().getNome_assunto().length() > 0)
			id_assunto = this.newProva.getAssunto_prova().getId_assunto();
		else
			id_assunto = -1;
		
		if (this.newProva.getQuestoes_usadas_prova().equals("Somente as minhas")){
			UsuarioProfessor usuarioProfessor = (UsuarioProfessor)SessionUtil.getParam("user");
			questaoAux = this.questaoDao.listar(id_disciplina, id_assunto, usuarioProfessor.getId_user());
		}
		else{
			questaoAux = this.questaoDao.listar(id_disciplina, id_assunto);
		}
		
		if (this.newProva.getNumeroQuestoes_prova() > 0 && questaoAux.size() >= this.newProva.getNumeroQuestoes_prova()){
			for (int i=0; i < this.newProva.getNumeroQuestoes_prova(); i++){
				indice = random.nextInt(questaoAux.size());
				this.questoes.add(questaoAux.get(indice));
				questaoAux.remove(indice);
			}
		}
	}
	
	public void embaralharAlternativas() {
		if (this.questoes.size() > 0){
			for (int i = 0; i < this.questoes.size(); i++){
				Collections.shuffle(this.questoes.get(i).getAlternativas_questao());
			}
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
	
	private void limparCampos(){
		this.newProva.setId_prova(null);
		this.newProva.setTitulo_prova(null);
		this.newProva.setDisciplina_prova(null);
		this.newProva.setNumeroQuestoes_prova(0);
		this.newProva.setData_criacao_prova(null);
		this.newProva.setData_ultima_alteracao_prova(null);
		this.newProva.setVersao_prova(null);
		this.newProva.setQuestoes_prova(null);
		this.newProva.setQuestoes_usadas_prova("Somente as minhas");
		this.questoes = null;
		this.mostrarSalvar();
	}
	
	public void goToProva() throws Exception{
		limparCampos();
		this.newProva.setVersao_prova("1");
		System.out.println("goToProva");
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		externalContext.redirect("Prova.xhtml");
	}
	
	public String validarCampos(Prova prova) throws ParseException{
		String mensagemErro = "";
		if (prova.getTitulo_prova().trim().length() == 0)
			mensagemErro += "<br/>-Preencher título";
		if (prova.getNumeroQuestoes_prova() < 1)
			mensagemErro += "<br/>-Preencher quantidade maior que 0";
		if (this.getQuestoes() == null || this.getQuestoes().size() < 1)
			mensagemErro += "<br/>-Selecionar questões";
		return mensagemErro;
	}
	
	public void pesquisar(){
		this.provas = this.provaDao.listar(this.pesquisa, this.disciplinaPesquisa);
		if (this.provas == null){
			this.provas = this.getProvas();
		}
	}
}