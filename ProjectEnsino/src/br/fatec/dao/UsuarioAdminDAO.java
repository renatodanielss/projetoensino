package br.fatec.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.fatec.model.UsuarioAdmin;

public class UsuarioAdminDAO {
	private EntityManagerFactory factory;
	private EntityManager manager;
	
	public UsuarioAdminDAO()
	{
		
	}
	
	public boolean inserir(UsuarioAdmin usuarioAdmin)
	{	
		try
		{
			open();
			this.manager.getTransaction().begin();    
	 		this.manager.persist(usuarioAdmin);
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
	
	public boolean alterar(UsuarioAdmin usuarioAdmin)
	{
		try
		{
			open();
			this.manager.getTransaction().begin();    
			this.manager.merge(usuarioAdmin);
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
	
	public UsuarioAdmin buscar(String username)
	{
		UsuarioAdmin usuarioAdmin = null;
		String query = "select * from tbl_user_admin where username_user = '" + username + "'";
		
		try
		{
			open();
			this.manager.getTransaction().begin();
	 		usuarioAdmin = (UsuarioAdmin)this.manager.createNativeQuery(query, new UsuarioAdmin().getClass()).getSingleResult();
	 		this.manager.getTransaction().commit();
		}
		catch(Exception ex){
			usuarioAdmin = null;
			if (this.manager.getTransaction().isActive())
				this.manager.getTransaction().rollback();
		}finally{
			close();
		}

		return usuarioAdmin;
	}
	
	@SuppressWarnings("unchecked")
	public List<UsuarioAdmin> listar()
	{		
		List<UsuarioAdmin> listUsuarioAdmin = null;
		String query = "select * from tbl_user_admin";
		
		try
		{
			open();
			this.manager.getTransaction().begin();
			listUsuarioAdmin = this.manager.createNativeQuery(query, new UsuarioAdmin().getClass()).getResultList();
			this.manager.getTransaction().commit();
		}
		catch(Exception ex)
		{
			listUsuarioAdmin = null;
			if (this.manager.getTransaction().isActive())
				this.manager.getTransaction().rollback();
		}finally{
			close();
		}
		
		return listUsuarioAdmin;
	}
	
	public boolean excluir(UsuarioAdmin usuarioAdmin)
	{
		try
		{
			open();
			this.manager.getTransaction().begin();
			this.manager.remove(manager.getReference(usuarioAdmin.getClass(), usuarioAdmin.getUsuario()));
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