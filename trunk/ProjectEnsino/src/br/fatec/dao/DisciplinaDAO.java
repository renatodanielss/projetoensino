package br.fatec.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.fatec.model.Disciplina;

public class DisciplinaDAO {
	private EntityManagerFactory factory;
	private EntityManager manager;
	
	public DisciplinaDAO()
	{
		open();
	}
	
	public boolean inserir(Disciplina disciplina)
	{	
		try
		{
			this.manager.getTransaction().begin();    
	 		this.manager.persist(disciplina);
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
	public List<Disciplina> listar()
	{		
		List<Disciplina> listDisciplina = null;
		String query = "select * from tbl_disciplina";
		
		try
		{
			this.manager.getTransaction().begin();
			listDisciplina = this.manager.createNativeQuery(query, new Disciplina().getClass()).getResultList();
			this.manager.getTransaction().commit();
		}
		catch(Exception ex)
		{
			listDisciplina = null;
			this.manager.getTransaction().rollback();
		}
		
		return listDisciplina;
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
