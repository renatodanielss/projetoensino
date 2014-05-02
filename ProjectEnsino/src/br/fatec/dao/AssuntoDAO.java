package br.fatec.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.fatec.model.Assunto;

public class AssuntoDAO {
	private EntityManagerFactory factory;
	private EntityManager manager;
	
	public AssuntoDAO()
	{
		open();
	}
	
	public boolean inserir(Assunto assunto)
	{	
		try
		{
			this.manager.getTransaction().begin();    
	 		this.manager.persist(assunto);
			this.manager.getTransaction().commit();
			return true;
		}
		catch(Exception ex)
		{
			if (this.manager.getTransaction().isActive())
				this.manager.getTransaction().rollback();
			return false;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Assunto> listar()
	{		
		List<Assunto> listAssunto = null;
		String query = "select * from tbl_assunto";
		
		try
		{
			this.manager.getTransaction().begin();
			listAssunto = this.manager.createNativeQuery(query, new Assunto().getClass()).getResultList();
			this.manager.getTransaction().commit();
		}
		catch(Exception ex)
		{
			listAssunto = null;
			this.manager.getTransaction().rollback();
		}
		
		return listAssunto;
	}
	
	@SuppressWarnings("unchecked")
	public List<Assunto> listar(int iddisciplina_assunto)
	{		
		List<Assunto> listAssunto = null;
		String query = "select * from tbl_assunto where iddisciplina_assunto = " + iddisciplina_assunto;
		
		try
		{
			this.manager.getTransaction().begin();
			listAssunto = this.manager.createNativeQuery(query, new Assunto().getClass()).getResultList();
			this.manager.getTransaction().commit();
		}
		catch(Exception ex)
		{
			listAssunto = null;
			this.manager.getTransaction().rollback();
		}
		
		return listAssunto;
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
