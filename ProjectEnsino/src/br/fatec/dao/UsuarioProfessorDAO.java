package br.fatec.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.fatec.model.UsuarioProfessor;

public class UsuarioProfessorDAO {
	private EntityManagerFactory factory;
	private EntityManager manager;
	
	public UsuarioProfessorDAO()
	{
		
	}
	
	public boolean inserir(UsuarioProfessor usuarioProfessor)
	{	
		try
		{
			open();
			this.manager.getTransaction().begin();    
	 		this.manager.persist(usuarioProfessor);
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
	
	public boolean alterar(UsuarioProfessor usuarioProfessor)
	{
		try
		{
			open();
			this.manager.getTransaction().begin();    
			this.manager.merge(usuarioProfessor);
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
	
	public UsuarioProfessor buscar(String username)
	{
		UsuarioProfessor usuarioProfessor = null;
		String query = "select * from tbl_user_professor where username_user = '" + username + "'";
		
		try
		{
			open();
			this.manager.getTransaction().begin();
	 		usuarioProfessor = (UsuarioProfessor)this.manager.createNativeQuery(query, new UsuarioProfessor().getClass()).getSingleResult();
	 		this.manager.getTransaction().commit();
		}
		catch(Exception ex){
			usuarioProfessor = null;
			if (this.manager.getTransaction().isActive())
				this.manager.getTransaction().rollback();
		}finally{
			close();
		}

		return usuarioProfessor;
	}
	
	@SuppressWarnings("unchecked")
	public List<UsuarioProfessor> listar()
	{		
		List<UsuarioProfessor> listUsuarioProfessor = null;
		String query = "select * from tbl_user_professor";
		
		try
		{
			open();
			this.manager.getTransaction().begin();
			listUsuarioProfessor = this.manager.createNativeQuery(query, new UsuarioProfessor().getClass()).getResultList();
			this.manager.getTransaction().commit();
		}
		catch(Exception ex)
		{
			listUsuarioProfessor = null;
			if (this.manager.getTransaction().isActive())
				this.manager.getTransaction().rollback();
		}finally{
			close();
		}
		
		return listUsuarioProfessor;
	}
	
	@SuppressWarnings("unchecked")
	public List<UsuarioProfessor> listar(String nomeUsuarioProfessor)
	{		
		List<UsuarioProfessor> listUsuarioProfessor = null;
		String query = "SELECT * FROM tbl_user_professor WHERE lower(username_user) LIKE lower('%" + nomeUsuarioProfessor.toLowerCase() + "%')";
		
		try
		{
			open();
			this.manager.getTransaction().begin();
			listUsuarioProfessor = this.manager.createNativeQuery(query, new UsuarioProfessor().getClass()).getResultList();
			this.manager.getTransaction().commit();
		}
		catch(Exception ex)
		{
			listUsuarioProfessor = null;
			if (this.manager.getTransaction().isActive())
				this.manager.getTransaction().rollback();
		}finally{
			close();
		}
		
		return listUsuarioProfessor;
	}
	
	public boolean excluir(UsuarioProfessor usuarioProfessor)
	{
		try
		{
			open();
			this.manager.getTransaction().begin();
			this.manager.remove(manager.getReference(usuarioProfessor.getClass(), usuarioProfessor.getUsuario()));
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