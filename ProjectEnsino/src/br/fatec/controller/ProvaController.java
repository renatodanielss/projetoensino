package br.fatec.controller;



import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import br.fatec.dao.ProvaDAO;
import br.fatec.dao.QuestaoDAO;
import br.fatec.model.Prova;
import br.fatec.model.Questao;


@ManagedBean(name="provaController")
@SessionScoped

public class ProvaController {

	
	private List<Prova> provas;
	private ProvaDAO provaDao;
	private Prova currentProva;
	private Prova newProva;
	private List<Questao> questoes;
	private QuestaoDAO questaoDao;
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

	public void cadastrar()
	{
		
		this.newProva.setQuestoes_prova(this.questoes);
		if (provaDao.inserir(this.newProva)){
			setProvas(null);
			System.out.println("Prova inserido com sucesso!");
			this.newProva = new Prova();
		}
		else
			System.out.println("Erro na inserção!");
	}
	
	public void iniciaAlterar() throws IOException
	{
		if (this.newProva != null)
		{
			try{
				this.newProva.setId_prova(this.currentProva.getId_prova());
				this.newProva.setTitulo_prova(this.currentProva.getTitulo_prova());
				this.newProva.setDisciplina_prova(this.currentProva.getDisciplina_prova());
				this.newProva.setNumeroQuestoes_prova(this.currentProva.getNumeroQuestoes_prova());
				this.newProva.setQuestoes_prova(this.currentProva.getQuestoes_prova());
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
		
		if (this.newProva.getDisciplina_prova() != null && this.newProva.getDisciplina_prova().getNome_disciplina().length() > 0)
			questaoAux = this.questaoDao.listar(this.newProva.getDisciplina_prova().getId_disciplina());
		else
			questaoAux = this.questaoDao.listar();
		
		if (this.newProva.getNumeroQuestoes_prova() > 0 && questaoAux.size() >= this.newProva.getNumeroQuestoes_prova()){
			for (int i=0; i < this.newProva.getNumeroQuestoes_prova(); i++){
				indice = random.nextInt(questaoAux.size());
				this.questoes.add(questaoAux.get(indice));
				questaoAux.remove(indice);
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
		this.newProva.setQuestoes_prova(null);
		this.questoes = null;
		this.mostrarSalvar();
	}
	
	public void goToProva() throws Exception{
		limparCampos();
		System.out.println("goToProva");
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		externalContext.redirect("Prova.xhtml");
	}
}