package br.fatec.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import br.fatec.dao.AlunoDAO;
import br.fatec.model.Aluno;

@ManagedBean(name="alunoController")
@SessionScoped

public class AlunoController {
	private List<Aluno> alunos;
	private AlunoDAO alunoDao;
	private Aluno currentAluno;
	private Aluno newAluno;
	private boolean showNewButton;
	private String pesquisa;
	private String pesquisarPor;
		
	public AlunoController()
	{
		
	}
	
	@PostConstruct
	public void preparaDados()
	{
		this.alunoDao = new AlunoDAO();
		this.newAluno = new Aluno();
		this.currentAluno = new Aluno();
	}
	
	public List<Aluno> getAlunos() {
		if (this.alunos == null){
			this.alunos = alunoDao.listar();
		}
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}

	public AlunoDAO getAlunoDao() {
		return alunoDao;
	}

	public void setAlunoDao(AlunoDAO alunoDao) {
		this.alunoDao = alunoDao;
	}

	public Aluno getCurrentAluno() {
		return currentAluno;
	}

	public void setCurrentAluno(Aluno currentAluno) {
		this.currentAluno = currentAluno;
	}

	public Aluno getNewAluno() {
		return newAluno;
	}

	public void setNewAluno(Aluno newAluno) {
		this.newAluno = newAluno;
	}
	
	public String getPesquisa() {
		return pesquisa;
	}

	public void setPesquisa(String pesquisa) {
		this.pesquisa = pesquisa;
	}

	public String getPesquisarPor() {
		return pesquisarPor;
	}

	public void setPesquisarPor(String pesquisarPor) {
		this.pesquisarPor = pesquisarPor;
	}

	public void cadastrar()
	{	
		if (alunoDao.inserir(this.newAluno)){
			setAlunos(null);
			System.out.println("Aluno inserido com sucesso!");
		}
		else
			System.out.println("Erro na inserção!");
	}
	
	public void iniciaAlterar() throws IOException
	{
		if (newAluno != null)
		{
			try{
				this.getNewAluno().setRa_aluno(this.getCurrentAluno().getRa_aluno());
				this.getNewAluno().setCpf_aluno(this.getCurrentAluno().getCpf_aluno());
				this.getNewAluno().setNome_aluno(this.getCurrentAluno().getNome_aluno());
				this.getNewAluno().setDatanasc_aluno(this.getCurrentAluno().getDatanasc_aluno());
				this.getNewAluno().setTelefone_aluno(this.getCurrentAluno().getTelefone_aluno());
				this.getNewAluno().setCelular_aluno(this.getCurrentAluno().getCelular_aluno());
				this.getNewAluno().setSexo_aluno(this.getCurrentAluno().getSexo_aluno());
				this.getNewAluno().setLogradouro_aluno(this.getCurrentAluno().getLogradouro_aluno());
				this.getNewAluno().setNumero_aluno(this.getCurrentAluno().getNumero_aluno());
				this.getNewAluno().setComplemento_aluno(this.getCurrentAluno().getComplemento_aluno());
				this.getNewAluno().setBairro_aluno(this.getCurrentAluno().getBairro_aluno());
				this.getNewAluno().setCidade_aluno(this.getCurrentAluno().getCidade_aluno());
				this.getNewAluno().setUf_aluno(this.getCurrentAluno().getUf_aluno());
				this.getNewAluno().setCep_aluno(this.getCurrentAluno().getCep_aluno());
				this.getNewAluno().setEmail_aluno(this.getCurrentAluno().getEmail_aluno());
				
				ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
				externalContext.redirect("Aluno.xhtml?faces-redirect=true&redirect=1");
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	public void alterar()
	{	
		if (alunoDao.alterar(this.newAluno)){
			setAlunos(null);
			System.out.println("Aluno alterado com sucesso!");
		}
		else
			System.out.println("Erro na alteração!");
	}
	
	public void excluir() throws IOException
	{
		if (alunoDao.excluir(this.currentAluno)){
			setAlunos(null);
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			externalContext.redirect("Alunolist.xhtml");
			System.out.println("Aluno excluido com sucesso!");
		}
		else
			System.out.println("Erro na exclusão!");
	}
	
	private void limparCampos(){
			
		this.getNewAluno().setRa_aluno(null);
		this.getNewAluno().setCpf_aluno(null);
		this.getNewAluno().setNome_aluno(null);
		this.getNewAluno().setDatanasc_aluno(null);
		this.getNewAluno().setTelefone_aluno(null);
		this.getNewAluno().setCelular_aluno(null);
		this.getNewAluno().setSexo_aluno(null);
		this.getNewAluno().setLogradouro_aluno(null);
		this.getNewAluno().setNumero_aluno(null);
		this.getNewAluno().setComplemento_aluno(null);
		this.getNewAluno().setBairro_aluno(null);
		this.getNewAluno().setCidade_aluno(null);
		this.getNewAluno().setUf_aluno(null);
		this.getNewAluno().setCep_aluno(null);
		this.getNewAluno().setEmail_aluno(null);
		this.mostrarSalvar();
	}
	
	public void goToAluno() throws Exception{
		limparCampos();
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		externalContext.redirect("Aluno.xhtml");
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
