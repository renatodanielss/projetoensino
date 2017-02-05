package br.fatec.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import br.fatec.dao.AlternativaDAO;
import br.fatec.dao.QuestaoDAO;
import br.fatec.model.Alternativa;
import br.fatec.model.Questao;

@ManagedBean(name="questaoController")
@SessionScoped
public class QuestaoController {
	private Questao newQuestao;
	private Questao currentQuestao;
	private List<Questao> questoes;
	private QuestaoDAO questaoDao;
	private List<Alternativa> alternativas;
	private AlternativaDAO alternativaDAO;
	private boolean showNewButton;
	
	public QuestaoController()
	{
		
	}
	
	@PostConstruct
	public void preparaDados()
	{
		this.questaoDao = new QuestaoDAO();
		this.newQuestao = new Questao();
		this.currentQuestao = new Questao();
		this.alternativaDAO = new AlternativaDAO();
		this.alternativas = new ArrayList<Alternativa>();
		//this.alternativas.add(new Alternativa("", this.newQuestao));
		mostrarSalvar();
	}
	
	public Questao getNewQuestao() {
		return newQuestao;
	}

	public void setNewQuestao(Questao newQuestao) {
		this.newQuestao = newQuestao;
	}

	public Questao getCurrentQuestao() {
		return currentQuestao;
	}

	public void setCurrentQuestao(Questao currentQuestao) {
		this.currentQuestao = currentQuestao;
	}

	public QuestaoDAO getQuestaoDao() {
		return questaoDao;
	}

	public void setQuestaoDao(QuestaoDAO questaoDao) {
		this.questaoDao = questaoDao;
	}

	public List<Alternativa> getAlternativas() {
		return alternativas;
	}

	public void setAlternativas(List<Alternativa> alternativas) {
		this.alternativas = alternativas;
	}

	public List<Questao> getQuestoes() 
	{
		if (this.questoes == null){
			this.questoes = questaoDao.listar();
		}
		return questoes;
	}
    
	public void setQuestoes(List<Questao> QuestaoList) 
	{
		this.questoes = QuestaoList;
	}

	public AlternativaDAO getAlternativaDAO() {
		return alternativaDAO;
	}

	public void setAlternativaDAO(AlternativaDAO alternativaDAO) {
		this.alternativaDAO = alternativaDAO;
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
	
	public void adicionarAlternativa(){
		this.alternativas.add(new Alternativa(this.newQuestao));
		System.out.println("Adicionou alternativa");
	}
	
	public void removerAlternativas(){
		for (int i = 0; i < this.alternativas.size(); i++){
			//System.out.println("Alternativa Mantida " + i+1 + ": " + this.alternativas.get(i).getTexto_alternativa());
			if (this.alternativas.get(i).getTexto_alternativa().equals("")){
				//System.out.println("Tamanho antes: " + Integer.toString(this.alternativas.size()));
				//alternativaDAO.excluir(this.alternativas.get(i));
				System.out.println("ID Alternativa: " + this.alternativas.get(i).getId_alternativa());
				System.out.println("Texto Alternativa: " + this.alternativas.get(i).getTexto_alternativa());
				this.alternativas.remove(i);
				//System.out.println("Tamanho depois: " + Integer.toString(this.alternativas.size()));
			}
		}
		//this.newQuestao.setAlternativas_questao(this.alternativas);
	}
	
	public void cadastrar() throws IOException
	{
		this.newQuestao.setAlternativas_questao(this.alternativas);
		if (questaoDao.inserir(this.newQuestao)){
			setQuestoes(null);
			setAlternativas(null);
			System.out.println("Questão inserida com sucesso!");
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("questaoController");
			//FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("realizarProvaController");
			
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			externalContext.redirect("CadastroConcluido.xhtml?faces-redirect=true&origin=questao");
		}
		else{
			System.out.println("Erro na inserção!");
		}
	}
	
	public void iniciaAlterar() throws IOException
	{
		if (this.newQuestao != null)
		{
			try{
				this.newQuestao.setId_questao(this.getCurrentQuestao().getId_questao());
				this.newQuestao.setAutor_questao(this.getCurrentQuestao().getAutor_questao());
				this.newQuestao.setDisciplina_questao(this.getCurrentQuestao().getDisciplina_questao());
				this.newQuestao.setAssunto_questao(this.getCurrentQuestao().getAssunto_questao());
				this.newQuestao.setTextobase_questao(this.getCurrentQuestao().getTextobase_questao());
				this.newQuestao.setEnunciado_questao(this.getCurrentQuestao().getEnunciado_questao());
				this.newQuestao.setAlternativas_questao(this.getCurrentQuestao().getAlternativas_questao());
				this.setAlternativas(this.newQuestao.getAlternativas_questao());
				
				mostrarAlterar();
				ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
				externalContext.redirect("Questao.xhtml?faces-redirect=true&redirect=1");
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	public void alterar() throws IOException
	{
		System.out.println("Tamanho alternação: " + Integer.toString(this.alternativas.size()));
		this.newQuestao.setAlternativas_questao(this.alternativas);
		if (questaoDao.alterar(this.newQuestao)){
			setQuestoes(null);
			setAlternativas(null);
			System.out.println("Questão alterada com sucesso!");
			this.newQuestao = new Questao();
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("questaoController");
			//FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("realizarProvaController");
			
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			externalContext.redirect("CadastroConcluido.xhtml?faces-redirect=true&origin=questao");
		}
		else
			System.out.println("Erro na alteração!");
	}
	
	private void limparCampos(){
		this.newQuestao.setId_questao(null);
		this.newQuestao.setAutor_questao(null);
		this.newQuestao.setDisciplina_questao(null);
		this.newQuestao.setAssunto_questao(null);
		this.newQuestao.setTextobase_questao(null);
		this.newQuestao.setEnunciado_questao(null);
		this.newQuestao.setAlternativas_questao(null);
		this.alternativas = new ArrayList<Alternativa>();
		//this.alternativas.add(new Alternativa("", this.newQuestao));
		this.mostrarSalvar();
	}
	
	public void excluir() throws IOException
	{
		if (questaoDao.excluir(this.currentQuestao)){
			setQuestoes(null);
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			externalContext.redirect("Questaolist.xhtml");
			System.out.println("Questão excluida com sucesso!");
		}
		else
			System.out.println("Erro na exclusão!");
	}
	
	public void goToQuestao() throws Exception{
		limparCampos();
		System.out.println("goToQuestao");
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		externalContext.redirect("Questao.xhtml");
	}
}