package br.fatec.controller;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import br.fatec.dao.AlternativaDAO;
import br.fatec.dao.QuestaoDAO;
import br.fatec.model.Alternativa;
import br.fatec.model.Questao;

@ManagedBean(name="questaoController")
@ViewScoped
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
		this.alternativas.add(new Alternativa("", this.newQuestao));
		this.mostrarSalvar();
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

	public void cadastrar()
	{
		/*for (Alternativa alternativaAux : this.newQuestao.getAlternativas_questao()){
			alternativaDAO.inserir(alternativaAux);
		}*/
		/*Alternativa alternativa1 = new Alternativa("Franz Ferdinand", newQuestao);
		Alternativa alternativa2 = new Alternativa("Rio Ferdinand", newQuestao);
		
		alternativas = new ArrayList<Alternativa>();
		alternativas.add(alternativa1);
		alternativas.add(alternativa2);
		newQuestao.setAlternativas_questao(alternativas);*/
		
		if (questaoDao.inserir(this.newQuestao)){
			for (Alternativa altAux : this.alternativas)
				alternativaDAO.inserir(altAux);
			setQuestoes(null);
			System.out.println("Questão inserida com sucesso!");
			this.newQuestao = new Questao();
		}
		else{
			System.out.println("Erro na inserção!");
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
	
	public void adicionarAlternativa(){
		this.alternativas.add(new Alternativa(this.newQuestao));
	}
}