package br.fatec.controller;

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
	private Alternativa alternativa;
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
		this.alternativa = new Alternativa();
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
	
	public Alternativa getAlternativa() {
		return alternativa;
	}

	public void setAlternativa(Alternativa alternativa) {
		this.alternativa = alternativa;
	}

	public AlternativaDAO getAlternativaDAO() {
		return alternativaDAO;
	}

	public void setAlternativaDAO(AlternativaDAO alternativaDAO) {
		this.alternativaDAO = alternativaDAO;
	}

	public void cadastrar()
	{
		try{
			questaoDao.inserir(this.newQuestao);
			for (Alternativa alternativaAux : this.newQuestao.getAlternativas_questao()){
				alternativaDAO.inserir(alternativaAux);
			}
			setQuestoes(null);
			System.out.println("Questão inserida com sucesso!");
			this.newQuestao = new Questao();
		}catch(Exception e){
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