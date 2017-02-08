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
		
	}
	
	public boolean inserir(Disciplina disciplina)
	{	
		try
		{
			open();
			this.manager.getTransaction().begin();    
	 		this.manager.persist(disciplina);
			this.manager.getTransaction().commit();
			return true;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			if (this.manager.getTransaction().isActive())
				this.manager.getTransaction().rollback();
			return false;
		}finally{
			close();
		}
	}
	
	public boolean alterar(Disciplina disciplina)
	{
		try
		{
			open();
			this.manager.getTransaction().begin();    
			this.manager.merge(disciplina);
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
	
	public Disciplina buscar(Integer id)
	{
		Disciplina disciplina = null;
		
		try
		{
			open();
			this.manager.getTransaction().begin();    
	 		disciplina = this.manager.find(Disciplina.class, id);
	 		this.manager.getTransaction().commit();
		}
		catch(Exception ex){
			disciplina = null;
			if (this.manager.getTransaction().isActive())
				this.manager.getTransaction().rollback();
		}finally{
			close();
		}

		return disciplina;
	}
	
	@SuppressWarnings("unchecked")
	public List<Disciplina> listar()
	{		
		List<Disciplina> listDisciplina = null;
		String query = "SELECT * FROM tbl_disciplina";
		
		try
		{
			open();
			this.manager.getTransaction().begin();
			listDisciplina = this.manager.createNativeQuery(query, new Disciplina().getClass()).getResultList();
			this.manager.getTransaction().commit();
		}
		catch(Exception ex)
		{
			listDisciplina = null;
			if (this.manager.getTransaction().isActive())
				this.manager.getTransaction().rollback();
		}finally{
			close();
		}
		
		return listDisciplina;
	}
	
	@SuppressWarnings("unchecked")
	public List<Disciplina> listar(String disciplina)
	{		
		List<Disciplina> listDisciplina = null;
		String query = "SELECT * FROM tbl_disciplina WHERE lower(nome_disciplina) LIKE lower('%" + disciplina.toLowerCase() + "%')";
		
		try
		{
			open();
			this.manager.getTransaction().begin();
			listDisciplina = this.manager.createNativeQuery(query, new Disciplina().getClass()).getResultList();
			this.manager.getTransaction().commit();
		}
		catch(Exception ex)
		{
			listDisciplina = null;
			if (this.manager.getTransaction().isActive())
				this.manager.getTransaction().rollback();
		}finally{
			close();
		}
		
		return listDisciplina;
	}
	
	public boolean existeDisciplina(Integer id)
	{
		Disciplina disciplina = buscar(id);
		
		if(disciplina == null)
			return false;
		return true;
	}
	
	public boolean excluir(Disciplina disciplina)
	{
		try
		{
			open();
			this.manager.getTransaction().begin();
			this.manager.remove(manager.getReference(disciplina.getClass(), disciplina.getId_disciplina()));
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