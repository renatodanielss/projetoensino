package br.fatec.controller;

//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
//import javax.faces.model.DataModel;
//import javax.faces.model.ListDataModel;


//import org.primefaces.model.LazyDataModel;

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
}