package br.fatec.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.fatec.model.Professor;

public class ProfessorDAO {
	private EntityManagerFactory factory;
	private EntityManager manager;
	
	public ProfessorDAO()
	{
		open();
	}
	
	public boolean inserir(Professor professor)
	{	
		try
		{
			this.manager.getTransaction().begin();    
	 		this.manager.persist(professor);
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
	public List<Professor> listar()
	{		
		List<Professor> listProfessor = null;
		String query = "select * from tbl_professor";
		
		try
		{
			this.manager.getTransaction().begin();
			listProfessor = this.manager.createNativeQuery(query, new Professor().getClass()).getResultList();
			this.manager.getTransaction().commit();
		}
		catch(Exception ex)
		{
			listProfessor = null;
			this.manager.getTransaction().rollback();
		}
		
		return listProfessor;
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
