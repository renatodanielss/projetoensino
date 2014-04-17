package br.fatec.dao;

//import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.fatec.model.Aluno;

public class AlunoDAO {
	private EntityManagerFactory factory;
	private EntityManager manager;
	
	public AlunoDAO()
	{
		open();
	}
	
	public boolean inserir(Aluno aluno)
	{	
		try
		{
			this.manager.getTransaction().begin();    
	 		this.manager.persist(aluno);
			this.manager.getTransaction().commit();
			return true;
		}
		catch(Exception ex)
		{
			this.manager.getTransaction().rollback();
			return false;
		}
	}
	
	/*@SuppressWarnings("unchecked")
	public List<Aluno> listar()
	{	
	
	}*/
	
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
