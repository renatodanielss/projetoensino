package br.fatec.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import br.fatec.model.RealizarProva;

public class RealizarProvaDAO {
	private EntityManagerFactory factory;
	private EntityManager manager;
	
	public RealizarProvaDAO()
	{
		open();
	}
	
	public boolean inserir(RealizarProva realizarprova)
	{	
		try
		{
			this.manager.getTransaction().begin();    
	 		this.manager.persist(realizarprova);
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
	
	public boolean alterar(RealizarProva realizarprova)
	{
		try
		{
			this.manager.getTransaction().begin();    
			this.manager.merge(realizarprova);
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
	
	public RealizarProva buscar(Integer codigo)
	{
		RealizarProva realizarprova = null;
		
		try
		{
			this.manager.getTransaction().begin();    
	 		realizarprova = this.manager.find(RealizarProva.class, codigo);
	 		this.manager.getTransaction().commit();
		}
		catch(Exception ex){
			realizarprova = null;
			if (this.manager.getTransaction().isActive())
				this.manager.getTransaction().rollback();
		}

		return realizarprova;
	}
	
	@SuppressWarnings("unchecked")
	public List<RealizarProva> listar()
	{		
		List<RealizarProva> listRealizarProva = null;
		String query = "SELECT * FROM tbl_realizarprova";
		
		try
		{
			this.manager.getTransaction().begin();
			listRealizarProva = this.manager.createNativeQuery(query, new RealizarProva().getClass()).getResultList();
			this.manager.getTransaction().commit();
		}
		catch(Exception ex)
		{
			listRealizarProva = null;
			if (this.manager.getTransaction().isActive())
				this.manager.getTransaction().rollback();
		}
		
		return listRealizarProva;
	}
	
	public boolean existeRealizarProva(Integer codigo)
	{
		RealizarProva realizarprova = buscar(codigo);
		
		if(realizarprova == null)
			return false;
		return true;
	}
	
	public boolean excluir(RealizarProva realizarprova)
	{
		try
		{
			this.manager.getTransaction().begin();
			this.manager.remove(manager.getReference(realizarprova.getClass(), realizarprova.getId_realizarprova()));
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