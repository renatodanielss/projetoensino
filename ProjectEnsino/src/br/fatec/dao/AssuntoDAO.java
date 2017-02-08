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
		
	}
	
	public boolean inserir(Assunto assunto)
	{	
		try
		{
			open();
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
		}finally{
			close();
		}
	}
	
	public boolean alterar(Assunto assunto)
	{
		try
		{
			open();
			this.manager.getTransaction().begin();    
			this.manager.merge(assunto);
			this.manager.getTransaction().commit();
			return true;
		}
		catch(Exception ex)
		{
			if (this.manager.getTransaction().isActive())
				this.manager.getTransaction().rollback();
			return false;
		}finally{
			close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Assunto> listar()
	{		
		List<Assunto> listAssunto = null;
		String query = "select * from tbl_assunto";
		
		try
		{
			open();
			this.manager.getTransaction().begin();
			listAssunto = this.manager.createNativeQuery(query, new Assunto().getClass()).getResultList();
			this.manager.getTransaction().commit();
		}
		catch(Exception ex)
		{
			listAssunto = null;
			if (this.manager.getTransaction().isActive())
				this.manager.getTransaction().rollback();
		}finally{
			close();
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
			open();
			this.manager.getTransaction().begin();
			listAssunto = this.manager.createNativeQuery(query, new Assunto().getClass()).getResultList();
			this.manager.getTransaction().commit();
		}
		catch(Exception ex)
		{
			listAssunto = null;
			if (this.manager.getTransaction().isActive())
				this.manager.getTransaction().rollback();
		}finally{
			close();
		}
		
		return listAssunto;
	}
	
	@SuppressWarnings("unchecked")
	public List<Assunto> listar(String assunto, Integer idDisciplina)
	{		
		List<Assunto> listAssunto = null;
		String query;
		
		if (idDisciplina == -1)
			query = "SELECT * FROM tbl_assunto WHERE lower(nome_assunto) LIKE lower('%" + assunto.toLowerCase() + "%')";
		else
			query = "SELECT * FROM tbl_assunto WHERE lower(nome_assunto) LIKE lower('%" + assunto.toLowerCase() + "%') AND iddisciplina_assunto = " + idDisciplina;
			
		try
		{
			open();
			this.manager.getTransaction().begin();
			listAssunto = this.manager.createNativeQuery(query, new Assunto().getClass()).getResultList();
			this.manager.getTransaction().commit();
		}
		catch(Exception ex)
		{
			listAssunto = null;
			if (this.manager.getTransaction().isActive())
				this.manager.getTransaction().rollback();
		}finally{
			close();
		}
		
		return listAssunto;
	}
	
	public boolean excluir(Assunto assunto)
	{
		try
		{
			open();
			this.manager.getTransaction().begin();
			this.manager.remove(manager.getReference(assunto.getClass(), assunto.getId_assunto()));
			this.manager.getTransaction().commit();
			return true;
		}
		catch(Exception ex)
		{
			if (this.manager.getTransaction().isActive())
				this.manager.getTransaction().rollback();
			
			return false;
		}finally{
			close();
		}
	}
	
	private void open()
	{
		this.factory = Persistence.createEntityManagerFactory("projectensino");
		this.manager = factory.createEntityManager();
	}
	
	private void close()
	{
		this.manager.close();
		this.factory.close();
	}
}