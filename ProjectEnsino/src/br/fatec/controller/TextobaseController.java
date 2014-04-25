package br.fatec.controller;

//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.util.ArrayList;
import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
//import javax.faces.model.DataModel;
//import javax.faces.model.ListDataModel;


//import org.primefaces.model.LazyDataModel;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;






//import javax.faces.context.FacesContext;
//import javax.faces.model.DataModel;
//import javax.faces.model.ListDataModel;
//import org.primefaces.event.FileUploadEvent;
//import org.primefaces.model.UploadedFile;
import br.fatec.dao.TextobaseDAO;
import br.fatec.model.Textobase;

@ManagedBean(name="textobaseController")
@SessionScoped
public class TextobaseController {
	private List<Textobase> textosBases;
	private TextobaseDAO textoBaseDao;
	private Textobase currentTextoBase;
	private Textobase newTextoBase;
	
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
		this.textosBases = textoBaseDao.listar();
		return textosBases;
	}
	
	public List<Object> getTextosbasesObject() 
	{
		List<Object> textosBasesObject = textoBaseDao.listarInnerJoin();
		return textosBasesObject;
	}
    
	public void setTextosBases(List<Textobase> TextobaseList) 
	{
		this.textosBases = TextobaseList;
	}
	
	public void cadastrar()
	{	
		if (textoBaseDao.inserir(this.newTextoBase))
			System.out.println("Texto base inserido com sucesso!");
		else
			System.out.println("Erro na inserção!");
		
		this.newTextoBase = new Textobase();
	}
	
	public void iniciaAlterar() throws IOException
	{
		if (newTextoBase != null)
		{
			this.getNewTextoBase().setCodigo_textobase(this.getCurrentTextoBase().getCodigo_textobase());
			this.getNewTextoBase().setTitulo_textobase(this.getCurrentTextoBase().getTitulo_textobase());
			this.getNewTextoBase().setDisciplina_textobase(this.getCurrentTextoBase().getDisciplina_textobase());
			//AssuntoController assuntoController = new AssuntoController();
			//assuntoController.getAssuntoDao().listar(this.getCurrentTextoBase().getDisciplina_textobase());
			this.getNewTextoBase().setAssunto_textobase(this.getCurrentTextoBase().getAssunto_textobase());
			this.getNewTextoBase().setTexto_textobase(this.getCurrentTextoBase().getTexto_textobase());
			
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			externalContext.redirect("Textobase.xhtml?faces-redirect=true&redirect=1");
		}
	}
	
	public void alterar()
	{	
		if (textoBaseDao.alterar(this.newTextoBase)){
			System.out.println("Textobase alterado com sucesso!");
			this.newTextoBase.setCodigo_textobase(null);
			this.newTextoBase.setTitulo_textobase(null);
			this.newTextoBase.setDisciplina_textobase(0);
			this.newTextoBase.setAssunto_textobase(0);
			this.newTextoBase.setTexto_textobase(null);
		}
		else
			System.out.println("Erro na alteração!");
	}
	
	public void cadastrarAlterar(){
		if (textoBaseDao.existeTextobase(newTextoBase.getCodigo_textobase()))
			alterar();
		else
			cadastrar();
	}
}