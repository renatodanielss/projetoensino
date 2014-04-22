package br.fatec.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.fatec.model.Autor;

public class AutorDAO {
	private EntityManagerFactory factory;
	private EntityManager manager;
	
	public AutorDAO()
	{
		open();
	}
	
	public boolean inserir(Autor autor)
	{	
		try
		{
			this.manager.getTransaction().begin();    
	 		this.manager.persist(autor);
			this.manager.getTransaction().commit();
			return true;
		}
		catch(Exception ex)
		{
			this.manager.getTransaction().rollback();
			return false;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Autor> listar()
	{		
		List<Autor> listAutor = null;
		String query = "select * from tbl_autor";
		
		try
		{
			this.manager.getTransaction().begin();
			listAutor = this.manager.createNativeQuery(query, new Autor().getClass()).getResultList();
			this.manager.getTransaction().commit();
		}
		catch(Exception ex)
		{
			listAutor = null;
			this.manager.getTransaction().rollback();
		}
		
		return listAutor;
	}
	
	public void open()
	{
		this.factory = Persistence.createEntityManagerFactory("projectensino");
		this.manager = factory.createEntityManager();
	}
	
	public void close()
	{
		this.manager.close();
		this.factory.close();
	}
}
