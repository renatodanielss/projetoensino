package br.fatec.dao;

import java.util.List;

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
	
	public boolean alterar(Aluno aluno)
	{
		try
		{
			this.manager.getTransaction().begin();    
			this.manager.merge(aluno);
			this.manager.getTransaction().commit();
			return true;
		}
		catch(Exception ex)
		{
			this.manager.getTransaction().rollback();
			return false;
		}
	}
	
	public Aluno buscar(String ra)
	{
		Aluno aluno = null;
		String query = "select * from tbl_aluno where ra_aluno = '" + ra + "'";
		
		try
		{
			this.manager.getTransaction().begin();
	 		aluno = (Aluno)this.manager.createNativeQuery(query, new Aluno().getClass()).getSingleResult();
	 		this.manager.getTransaction().commit();
		}
		catch(Exception ex){
			aluno = null;
			this.manager.getTransaction().rollback();
		}

		return aluno;
	}
	
	@SuppressWarnings("unchecked")
	public List<Aluno> listar()
	{	
		List<Aluno> listAluno = null;
		String query = "select * from tbl_aluno";
		
		try
		{
			this.manager.getTransaction().begin();
			listAluno = this.manager.createNativeQuery(query, new Aluno().getClass()).getResultList();
			this.manager.getTransaction().commit();
		}
		catch(Exception ex)
		{
			listAluno = null;
			this.manager.getTransaction().rollback();
		}
		
		return listAluno;
	}
	
	public boolean existeAluno(String ra)
	{
		Aluno aluno = buscar(ra);
		
		if(aluno == null)
			return false;
		return true;
	}
	
	public boolean excluir(Aluno aluno)
	{
		try
		{
			this.manager.getTransaction().begin();
			this.manager.remove(manager.getReference(aluno.getClass(), aluno.getRa_aluno()));
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
