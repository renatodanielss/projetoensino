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
			if (this.manager.getTransaction().isActive())
				this.manager.getTransaction().rollback();
			return false;
		}
	}
	
	public boolean alterar(Professor professor)
	{
		try
		{
			this.manager.getTransaction().begin();    
			this.manager.merge(professor);
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
	
	public Professor buscar(String matricula)
	{
		Professor professor = null;
		String query = "select * from tbl_professor where matricula_professor = '" + matricula + "'";
		
		try
		{
			this.manager.getTransaction().begin();
	 		professor = (Professor)this.manager.createNativeQuery(query, new Professor().getClass()).getSingleResult();
	 		this.manager.getTransaction().commit();
		}
		catch(Exception ex){
			professor = null;
			if (this.manager.getTransaction().isActive())
				this.manager.getTransaction().rollback();
		}

		return professor;
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
			if (this.manager.getTransaction().isActive())
				this.manager.getTransaction().rollback();
		}
		
		return listProfessor;
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Object> listarInnerJoin()
	{		
		List<Object> listTextobase = null;
		String query = "SELECT tbl_professor.matricula_professor, tbl_disciplina.nome_disciplina FROM tbl_professor inner join tbl_disciplina on tbl_professor.disciplina_professor = tbl_disciplina.id_disciplina";
		
		try
		{
			this.manager.getTransaction().begin();
			listTextobase = this.manager.createNativeQuery(query, new Object().getClass()).getResultList();
			this.manager.getTransaction().commit();
		}
		catch(Exception ex)
		{
			listTextobase = null;
			if (this.manager.getTransaction().isActive())
				this.manager.getTransaction().rollback();
		}
		
		return listTextobase;
	}
		
	public boolean existeProfessor(String matricula)
	{
		Professor professor = buscar(matricula);
			
		if(professor == null)
			return false;
		return true;
	}
		
	public boolean excluir(Professor professor)
	{
		try
		{
			this.manager.getTransaction().begin();
			this.manager.remove(manager.getReference(professor.getClass(), professor.getMatricula_professor()));
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
