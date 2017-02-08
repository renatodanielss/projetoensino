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
		
	}
	
	public boolean inserir(Autor autor)
	{	
		try
		{
			open();
			this.manager.getTransaction().begin();    
	 		this.manager.persist(autor);
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
	
	public boolean alterar(Autor autor)
	{
		try
		{
			open();
			this.manager.getTransaction().begin();    
			this.manager.merge(autor);
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
	
	public Autor buscar(Integer id)
	{
		Autor autor = null;
		
		try
		{
			open();
			this.manager.getTransaction().begin();    
			autor = this.manager.find(Autor.class, id);
	 		this.manager.getTransaction().commit();
		}
		catch(Exception ex){
			autor = null;
			if (this.manager.getTransaction().isActive())
				this.manager.getTransaction().rollback();
		}finally{
			close();
		}

		return autor;
	}
	
	@SuppressWarnings("unchecked")
	public List<Autor> listar()
	{		
		List<Autor> listAutor = null;
		String query = "select * from tbl_autor";
		
		try
		{
			open();
			this.manager.getTransaction().begin();
			listAutor = this.manager.createNativeQuery(query, new Autor().getClass()).getResultList();
			this.manager.getTransaction().commit();
		}
		catch(Exception ex)
		{
			listAutor = null;
			if (this.manager.getTransaction().isActive())
				this.manager.getTransaction().rollback();
		}finally{
			close();
		}
		
		return listAutor;
	}
	
	@SuppressWarnings("unchecked")
	public List<Autor> listar(String autor)
	{		
		List<Autor> listAutor = null;
		String query = "SELECT * FROM tbl_autor WHERE lower(nome_autor) LIKE lower('%" + autor.toLowerCase() + "%')";
		
		try
		{
			open();
			this.manager.getTransaction().begin();
			listAutor = this.manager.createNativeQuery(query, new Autor().getClass()).getResultList();
			this.manager.getTransaction().commit();
		}
		catch(Exception ex)
		{
			listAutor = null;
			if (this.manager.getTransaction().isActive())
				this.manager.getTransaction().rollback();
		}finally{
			close();
		}
		
		return listAutor;
	}
	
	public boolean existeAutor(Integer id)
	{
		Autor autor = buscar(id);
		
		if(autor == null)
			return false;
		return true;
	}
	
	public boolean excluir(Autor autor)
	{
		try
		{
			open();
			this.manager.getTransaction().begin();
			this.manager.remove(manager.getReference(autor.getClass(), autor.getId_autor()));
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
