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
		
	}
	
	public boolean inserir(Professor professor)
	{	
		try
		{
			open();
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
		}finally{
			close();
		}
	}
	
	public boolean alterar(Professor professor)
	{
		try
		{
			open();
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
		}finally{
			close();
		}
	}
	
	public Professor buscar(String matricula)
	{
		Professor professor = null;
		String query = "select * from tbl_professor where matricula_professor = '" + matricula + "'";
		
		try
		{
			open();
			this.manager.getTransaction().begin();
	 		professor = (Professor)this.manager.createNativeQuery(query, new Professor().getClass()).getSingleResult();
	 		this.manager.getTransaction().commit();
		}
		catch(Exception ex){
			professor = null;
			if (this.manager.getTransaction().isActive())
				this.manager.getTransaction().rollback();
		}finally{
			close();
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
			open();
			this.manager.getTransaction().begin();
			listProfessor = this.manager.createNativeQuery(query, new Professor().getClass()).getResultList();
			this.manager.getTransaction().commit();
		}
		catch(Exception ex)
		{
			listProfessor = null;
			if (this.manager.getTransaction().isActive())
				this.manager.getTransaction().rollback();
		}finally{
			close();
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
			open();
			this.manager.getTransaction().begin();
			listTextobase = this.manager.createNativeQuery(query, new Object().getClass()).getResultList();
			this.manager.getTransaction().commit();
		}
		catch(Exception ex)
		{
			listTextobase = null;
			if (this.manager.getTransaction().isActive())
				this.manager.getTransaction().rollback();
		}finally{
			close();
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
	
	@SuppressWarnings("unchecked")
	public List<Professor> listar(String nomeProfessor)
	{		
		List<Professor> listProfessor = null;
		String query = "SELECT * FROM tbl_professor WHERE lower(nome_professor) LIKE lower('%" + nomeProfessor.toLowerCase() + "%')";
		
		try
		{
			open();
			this.manager.getTransaction().begin();
			listProfessor = this.manager.createNativeQuery(query, new Professor().getClass()).getResultList();
			this.manager.getTransaction().commit();
		}
		catch(Exception ex)
		{
			listProfessor = null;
			if (this.manager.getTransaction().isActive())
				this.manager.getTransaction().rollback();
		}finally{
			close();
		}
		
		return listProfessor;
	}
	
	@SuppressWarnings("unchecked")
	public List<Professor> listarProfessorSemUsuario()
	{		
		List<Professor> listProfessor = null;
		String query = "SELECT * FROM tbl_professor LEFT JOIN tbl_user_professor ON tbl_user_professor.professor_user = tbl_professor.matricula_professor WHERE tbl_user_professor.username_user IS NULL";
		
		try
		{
			open();
			this.manager.getTransaction().begin();
			listProfessor = this.manager.createNativeQuery(query, new Professor().getClass()).getResultList();
			this.manager.getTransaction().commit();
		}
		catch(Exception ex)
		{
			listProfessor = null;
			if (this.manager.getTransaction().isActive())
				this.manager.getTransaction().rollback();
		}finally{
			close();
		}
		
		return listProfessor;
	}
		
	public boolean excluir(Professor professor)
	{
		try
		{
			open();
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
