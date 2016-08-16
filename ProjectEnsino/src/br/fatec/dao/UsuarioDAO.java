package br.fatec.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.fatec.model.Usuario;

public class UsuarioDAO {
	private EntityManagerFactory factory;
	private EntityManager manager;
	
	public UsuarioDAO()
	{
		
	}
	
	public boolean inserir(Usuario usuario)
	{	
		try
		{
			open();
			this.manager.getTransaction().begin();    
	 		this.manager.persist(usuario);
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
	
	public boolean alterar(Usuario usuario)
	{
		try
		{
			open();
			this.manager.getTransaction().begin();    
			this.manager.merge(usuario);
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
	
	public Usuario buscar(String usuario)
	{
		Usuario Usuario = null;

		try
		{
			open();
			this.manager.getTransaction().begin();
			Usuario = this.manager.find(Usuario.class, usuario); 
	 		this.manager.getTransaction().commit();
		}
		catch(Exception ex){
			Usuario = null;
			if (this.manager.getTransaction().isActive())
				this.manager.getTransaction().rollback();
		}finally{
			close();
		}

		return Usuario;
	}
	
	public boolean excluir(Usuario usuario)
	{
		try
		{
			open();
			this.manager.getTransaction().begin();
			this.manager.remove(manager.getReference(usuario.getClass(), usuario.getUsuario()));
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
