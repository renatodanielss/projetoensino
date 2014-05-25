package br.fatec.controller;

import java.io.IOException;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import br.fatec.dao.TextobaseDAO;
import br.fatec.model.Textobase;

@ManagedBean(name="textobaseController")
@SessionScoped
public class TextobaseController{
	private Textobase newTextoBase;
	private Textobase currentTextoBase;
	private List<Textobase> textosBases;
	private TextobaseDAO textoBaseDao;
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
	
	public void cadastrar()
	{
		if (textoBaseDao.inserir(this.newTextoBase)){
			setTextosBases(null);
			System.out.println("Texto base inserido com sucesso!");
			this.newTextoBase = new Textobase();
		}
		else
			System.out.println("Erro na inserção!");
	}
	
	public void iniciaAlterar() throws IOException
	{
		if (newTextoBase != null)
		{
			try{
				this.getNewTextoBase().setCodigo_textobase(this.getCurrentTextoBase().getCodigo_textobase());
				this.getNewTextoBase().setTitulo_textobase(this.getCurrentTextoBase().getTitulo_textobase());
				this.getNewTextoBase().setDisciplina_textobase(this.getCurrentTextoBase().getDisciplina_textobase());
				this.getNewTextoBase().setAssunto_textobase(this.getCurrentTextoBase().getAssunto_textobase());
				this.getNewTextoBase().setTexto_textobase(this.getCurrentTextoBase().getTexto_textobase());
				
				mostrarAlterar();
				ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
				externalContext.redirect("Textobase.xhtml?faces-redirect=true&redirect=1");
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	public void alterar()
	{	
		if (textoBaseDao.alterar(this.newTextoBase)){
			setTextosBases(null);
			System.out.println("Textobase alterado com sucesso!");
			this.newTextoBase = new Textobase();
		}
		else
			System.out.println("Erro na alteração!");
	}
	
	public void excluir() throws IOException
	{
		if (textoBaseDao.excluir(this.currentTextoBase)){
			setTextosBases(null);
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			externalContext.redirect("Textobaselist.xhtml");
			System.out.println("Textobase excluido com sucesso!");
		}
		else
			System.out.println("Erro na exclusão!");
	}
	
	private void limparCampos(){
		this.newTextoBase.setCodigo_textobase(null);
		this.newTextoBase.setTitulo_textobase(null);
		this.newTextoBase.setDisciplina_textobase(0);
		this.newTextoBase.setAssunto_textobase(0);
		this.newTextoBase.setTexto_textobase(null);
		this.mostrarSalvar();
	}
	
	public void goToTextobase() throws Exception{
		limparCampos();
		System.out.println("goToTextobase");
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		externalContext.redirect("Textobase.xhtml");
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