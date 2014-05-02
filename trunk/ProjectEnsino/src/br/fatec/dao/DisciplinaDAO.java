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
			if (this.manager.getTransaction().isActive())
				this.manager.getTransaction().rollback();
			return false;
		}
	}
	
	public boolean alterar(Disciplina disciplina)
	{
		try
		{
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
		}
	}
	
	public Disciplina buscar(Integer id)
	{
		Disciplina disciplina = null;
		
		try
		{
			this.manager.getTransaction().begin();    
	 		disciplina = this.manager.find(Disciplina.class, id);
	 		this.manager.getTransaction().commit();
		}
		catch(Exception ex){
			disciplina = null;
			if (this.manager.getTransaction().isActive())
				this.manager.getTransaction().rollback();
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
			this.manager.getTransaction().begin();
			listDisciplina = this.manager.createNativeQuery(query, new Disciplina().getClass()).getResultList();
			this.manager.getTransaction().commit();
		}
		catch(Exception ex)
		{
			listDisciplina = null;
			if (this.manager.getTransaction().isActive())
				this.manager.getTransaction().rollback();
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