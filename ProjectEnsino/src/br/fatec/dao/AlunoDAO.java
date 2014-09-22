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
		
	}
	
	public boolean inserir(Aluno aluno)
	{	
		try
		{
			open();
			this.manager.getTransaction().begin();    
	 		this.manager.persist(aluno);
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
	
	public boolean alterar(Aluno aluno)
	{
		try
		{
			open();
			this.manager.getTransaction().begin();    
			this.manager.merge(aluno);
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
	
	public Aluno buscar(String ra)
	{
		Aluno aluno = null;

		try
		{
			open();
			this.manager.getTransaction().begin();
			aluno = this.manager.find(Aluno.class, ra); 
	 		this.manager.getTransaction().commit();
		}
		catch(Exception ex){
			aluno = null;
			if (this.manager.getTransaction().isActive())
				this.manager.getTransaction().rollback();
		}finally{
			close();
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
			open();
			this.manager.getTransaction().begin();
			listAluno = this.manager.createNativeQuery(query, new Aluno().getClass()).getResultList();
			this.manager.getTransaction().commit();
		}
		catch(Exception ex)
		{
			listAluno = null;
			if (this.manager.getTransaction().isActive())
				this.manager.getTransaction().rollback();
		}finally{
			close();
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
			open();
			this.manager.getTransaction().begin();
			this.manager.remove(manager.getReference(aluno.getClass(), aluno.getRa_aluno()));
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