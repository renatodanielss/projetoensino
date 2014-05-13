package br.fatec.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

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
		Alternativa alternativa1 = new Alternativa();
		alternativa1.setTexto_alternativa("Franz Ferdinand");
		alternativa1.setQuestao_alternativa(newQuestao);
		
		Alternativa alternativa2 = new Alternativa();
		alternativa2.setTexto_alternativa("Rio Ferdinand");
		alternativa2.setQuestao_alternativa(newQuestao);
		
		alternativas = new ArrayList<Alternativa>();
		alternativas.add(alternativa1);
		alternativas.add(alternativa2);
		newQuestao.setAlternativas_questao(alternativas);
		
		if (questaoDao.inserir(this.newQuestao)){
			for (Alternativa altAux : newQuestao.getAlternativas_questao())
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
}