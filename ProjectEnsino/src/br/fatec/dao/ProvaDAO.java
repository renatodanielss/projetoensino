package br.fatec.dao;

import java.util.List;

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
			if (this.manager.getTransaction().isActive())
				this.manager.getTransaction().rollback();
			return false;
		}
	}
	
	public boolean alterar(Prova prova)
	{
		try
		{
			this.manager.getTransaction().begin();    
			this.manager.merge(prova);
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
	
	public boolean excluir(Prova prova)
	{
		try
		{
			this.manager.getTransaction().begin();
			this.manager.remove(manager.getReference(prova.getClass(), prova.getId_prova()));
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
	public List<Prova> listar()
	{		
		List<Prova> listProva = null;
		String query = "SELECT * FROM tbl_prova";
		
		try
		{
			this.manager.getTransaction().begin();
			listProva = this.manager.createNativeQuery(query, new Prova().getClass()).getResultList();
			this.manager.getTransaction().commit();
		}
		catch(Exception ex)
		{
			listProva = null;
			if (this.manager.getTransaction().isActive())
				this.manager.getTransaction().rollback();
		}
		
		return listProva;
	}
	
	@SuppressWarnings("unchecked")
	public List<Prova> listar(String tituloProva, Integer idDisciplina)
	{
		List<Prova> listProva = null;
		String query;
		
		if (idDisciplina == -1){
			query = "SELECT * FROM tbl_prova WHERE lower(titulo_prova) LIKE lower('%" + tituloProva.toLowerCase() + "%')";
		}
		else{
			query = "SELECT * FROM tbl_prova WHERE lower(titulo_prova) LIKE lower('%" + tituloProva.toLowerCase() + "%') AND disciplina_prova = " + idDisciplina;
		}
		
		try
		{
			this.manager.getTransaction().begin();
			listProva = this.manager.createNativeQuery(query, new Prova().getClass()).getResultList();
			this.manager.getTransaction().commit();
		}
		catch(Exception ex)
		{
			listProva = null;
			if (this.manager.getTransaction().isActive())
				this.manager.getTransaction().rollback();
		}
		
		return listProva;
	}
	
	public Prova buscar(Integer codigo)
	{
		Prova prova = null;
		
		try
		{
			this.manager.getTransaction().begin();
			prova = this.manager.find(Prova.class, codigo);
	 		this.manager.getTransaction().commit();
		}
		catch(Exception ex){
			prova = null;
			if (this.manager.getTransaction().isActive())
				this.manager.getTransaction().rollback();
		}

		return prova;
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