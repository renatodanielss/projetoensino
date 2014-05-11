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
			if (this.manager.getTransaction().isActive())
				this.manager.getTransaction().rollback();
			return false;
		}
	}
	
	public boolean alterar(Autor autor)
	{
		try
		{
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
		}
	}
	
	public Autor buscar(Integer id)
	{
		Autor autor = null;
		
		try
		{
			this.manager.getTransaction().begin();    
			autor = this.manager.find(Autor.class, id);
	 		this.manager.getTransaction().commit();
		}
		catch(Exception ex){
			autor = null;
			if (this.manager.getTransaction().isActive())
				this.manager.getTransaction().rollback();
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
			this.manager.getTransaction().begin();
			listAutor = this.manager.createNativeQuery(query, new Autor().getClass()).getResultList();
			this.manager.getTransaction().commit();
		}
		catch(Exception ex)
		{
			listAutor = null;
			if (this.manager.getTransaction().isActive())
				this.manager.getTransaction().rollback();
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
