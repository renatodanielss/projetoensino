package br.fatec.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.fatec.model.Prova;

public class ProvaDAO {
	private EntityManagerFactory factory;
	private EntityManager manager;
	
	public ProvaDAO()
	{
		open();
	}
	
	public boolean inserir(Prova prova)
	{	
		try
		{
			this.manager.getTransaction().begin();    
	 		this.manager.persist(prova);
			this.manager.getTransaction().commit();
			return true;
		}
		catch(Exception ex)
		{
			this.manager.getTransaction().rollback();
			return false;
		}
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
