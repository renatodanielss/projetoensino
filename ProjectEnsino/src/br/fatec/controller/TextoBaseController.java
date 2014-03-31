package br.fatec.controller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import br.fatec.dao.TextoBaseDAO;
import br.fatec.model.TextoBase;
import br.fatec.model.TextoBase;

@ManagedBean(name="textoBaseController")
@SessionScoped
public class TextoBaseController {
	private List<TextoBase> TextosBases;
	private TextoBaseDAO textoBaseDao;
	private TextoBase currentTextoBase;
	private TextoBase newTextoBase;
	
	public TextoBaseController()
	{
	}
	
	@PostConstruct
	public void preparaDados()
	{
		textoBaseDao = new TextoBaseDAO();
		this.newTextoBase = new TextoBase();
		this.currentTextoBase = new TextoBase();
	}
	
	public TextoBaseDAO getTextoBaseDao() {
		return textoBaseDao;
	}

	public void setTextoBaseDao(TextoBaseDAO textoBaseDao) {
		this.textoBaseDao = textoBaseDao;
	}

	public TextoBase getCurrentTextoBase() {
		return currentTextoBase;
	}

	public void setCurrentTextoBase(TextoBase currentTextoBase) {
		this.currentTextoBase = currentTextoBase;
	}

	public TextoBase getNewTextoBase() {
		return newTextoBase;
	}

	public void setNewTextoBase(TextoBase newTextoBase) {
		this.newTextoBase = newTextoBase;
	}
	
	public void cadastrar()
	{	
		if (textoBaseDao.inserir(this.newTextoBase))
			System.out.println("Texto base inserido com sucesso!");
		else
			System.out.println("Erro na inserção!");
		
		this.newTextoBase = new TextoBase();
	}
}