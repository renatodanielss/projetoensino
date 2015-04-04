package br.fatec.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import br.fatec.dao.AlunoDAO;
import br.fatec.dao.ProvaDAO;
import br.fatec.dao.RealizarProvaDAO;
import br.fatec.model.Alternativa;
import br.fatec.model.Prova;
import br.fatec.model.Questao;
import br.fatec.model.RealizarProva;

@ManagedBean(name="realizarProvaController")
@SessionScoped
public class RealizarProvaController{
	private RealizarProva newRealizarProva;
	private RealizarProva currentRealizarProva;
	private List<RealizarProva> realizarProvas;
	private RealizarProvaDAO realizarProvaDao;
	private String raAluno;
	private Prova currentProva;
	private AlunoDAO alunoDao;
	private ProvaDAO provaDao;
	private List<String> alternativasSelecionadas;
	private boolean showNewButton;
	
	public RealizarProvaController()
	{
		
	}
	
	@PostConstruct
	public void preparaDados()
	{
		this.realizarProvaDao = new RealizarProvaDAO();
		this.newRealizarProva = new RealizarProva();
		this.currentRealizarProva = new RealizarProva();
		this.provaDao = new ProvaDAO();
		this.alunoDao = new AlunoDAO();
		this.currentProva = new Prova();
		this.alternativasSelecionadas = new ArrayList<String>();
		mostrarSalvar();
	}
	
	public RealizarProvaDAO getRealizarProvaDao() {
		return realizarProvaDao;
	}

	public void setRealizarProvaDao(RealizarProvaDAO realizarProvaDao) {
		this.realizarProvaDao = realizarProvaDao;
	}

	public String getRaAluno() {
		return raAluno;
	}

	public void setRaAluno(String raAluno) {
		this.raAluno = raAluno;
	}

	public Prova getCurrentProva() {
		return currentProva;
	}

	public void setCurrentProva(Prova currentProva) {
		this.currentProva = currentProva;
	}

	public RealizarProva getCurrentRealizarProva() {
		return currentRealizarProva;
	}

	public void setCurrentRealizarProva(RealizarProva currentRealizarProva) {
		this.currentRealizarProva = currentRealizarProva;
	}

	public RealizarProva getNewRealizarProva() {
		return newRealizarProva;
	}

	public void setNewRealizarProva(RealizarProva newRealizarProva) {
		this.newRealizarProva = newRealizarProva;
	}
	
	public List<RealizarProva> getRealizarProvas() 
	{
		if (this.realizarProvas == null){
			this.realizarProvas = realizarProvaDao.listar();
		}
		return realizarProvas;
	}
    
	public void setRealizarProvas(List<RealizarProva> RealizarProvaList) 
	{
		this.realizarProvas = RealizarProvaList;
	}
	
	public AlunoDAO getAlunoDao() {
		return alunoDao;
	}

	public void setAlunoDao(AlunoDAO alunoDao) {
		this.alunoDao = alunoDao;
	}

	public ProvaDAO getProvaDao() {
		return provaDao;
	}

	public void setProvaDao(ProvaDAO provaDao) {
		this.provaDao = provaDao;
	}

	public List<String> getAlternativasSelecionadas() {
		if (alternativasSelecionadas.size() < this.currentProva.getNumeroQuestoes_prova())
			this.alternativasSelecionadas.add(new String());
		return alternativasSelecionadas;
	}

	public void setAlternativasSelecionadas(List<String> alternativasSelecionadas) {
		this.alternativasSelecionadas = alternativasSelecionadas;
	}

	public void cadastrar()
	{
		if (realizarProvaDao.inserir(this.newRealizarProva)){
			setRealizarProvas(null);
			System.out.println("Prova realizada com sucesso!");
			this.newRealizarProva = new RealizarProva();
		}
		else
			System.out.println("Erro na inserção!");
	}
	
	public void iniciaAlterar() throws IOException
	{
		if (newRealizarProva != null)
		{
			try{
				this.getNewRealizarProva().setId_realizarprova(this.getCurrentRealizarProva().getId_realizarprova());
				this.getNewRealizarProva().setAluno_realizarprova(this.getCurrentRealizarProva().getAluno_realizarprova());
				this.getNewRealizarProva().setProva_realizarprova(this.getCurrentRealizarProva().getProva_realizarprova());
				this.getNewRealizarProva().setNota_realizarprova(this.getCurrentRealizarProva().getNota_realizarprova());
								
				mostrarAlterar();
				ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
				externalContext.redirect("RealizarProva.xhtml?faces-redirect=true&redirect=1");
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	public void alterar()
	{	
		if (realizarProvaDao.alterar(this.newRealizarProva)){
			setRealizarProvas(null);
			System.out.println("RealizarProva alterado com sucesso!");
			this.newRealizarProva = new RealizarProva();
		}
		else
			System.out.println("Erro na alteração!");
	}
	
	public void excluir() throws IOException
	{
		if (realizarProvaDao.excluir(this.currentRealizarProva)){
			setRealizarProvas(null);
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			externalContext.redirect("RealizarProvalist.xhtml");
			System.out.println("RealizarProva excluido com sucesso!");
		}
		else
			System.out.println("Erro na exclusão!");
	}
	
	/*private void limparCampos(){
		this.getNewRealizarProva().setId_realizarprova(null);
		this.getNewRealizarProva().setAluno_realizarprova(null);
		this.getNewRealizarProva().setProva_realizarprova(null);
		this.getNewRealizarProva().setNota_realizarprova(null);
		this.mostrarSalvar();
	}*/
	
	public void goToRealizarProva() throws Exception{
		if (this.newRealizarProva != null){
			this.alternativasSelecionadas = new ArrayList<String>();
			this.getNewRealizarProva().setAluno_realizarprova(this.alunoDao.buscar(this.raAluno));
			this.getNewRealizarProva().setProva_realizarprova(this.currentProva);
			
			System.out.println("goToRealizarProva");
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			externalContext.redirect("RealizarProva.xhtml");
		}
	}
	
	public void encerrarProva() throws IOException{
		ArrayList<String> altsQuests = new ArrayList<String>();
		int i = 1;
		for (Questao quest : this.currentProva.getQuestoes_prova()){
			for(Alternativa alt : quest.getAlternativas_questao()){
				if (alt.getCorreta_alternativa() == true){
					altsQuests.add(String.valueOf((char)(i+96)));
					i=1;
					break;
				}
				else
					i++;
			}
		}
		
		int nota = 0;
		for (int index=0; index<this.currentProva.getQuestoes_prova().size(); index++){
			if (altsQuests.get(index).toString().toLowerCase().equals(this.alternativasSelecionadas.get(index).toString().toLowerCase()))
				nota++;
		}
		this.getNewRealizarProva().setNota_realizarprova(nota);
		this.cadastrar();
		
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		externalContext.redirect("CadastroConcluido.xhtml?faces-redirect=true&origin=realizarprova");
	}
	
	public void apertarBotao(){
		@SuppressWarnings("unused")
		int i = 0;
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
	
	public char obterLetra(int status){
		status += 96;
		return (char)status;
	}
}