package br.fatec.dao;

//import java.util.List;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
//import javax.persistence.Query;

import br.fatec.model.Textobase;

public class TextobaseDAO {
	private EntityManagerFactory factory;
	private EntityManager manager;
	
	public TextobaseDAO()
	{
		open();
	}
	
	public boolean inserir(Textobase textobase)
	{	
		try
		{
			this.manager.getTransaction().begin();    
	 		this.manager.persist(textobase);
			this.manager.getTransaction().commit();
			return true;
		}
		catch(Exception ex)
		{
			this.manager.getTransaction().rollback();
			return false;
		}
	}
	
	/*public TextoBase buscar(Integer numero)
	{
		TextoBase textobase = null;
		
		try
		{
			this.manager.getTransaction().begin();    
			textobase = this.manager.find(TextoBase.class, numero);
			this.manager.getTransaction().commit();
		}
		catch(Exception ex)
		{
			textobase = null;
		}

		return textobase;
	}
	
	public TextoBase buscar(String texto)
	{
		TextoBase textobase = null;
		String query = "select * from tbl_textobase where tbl_textobase.texto_textobase = '" + texto + "'";
		
		try
		{
			this.manager.getTransaction().begin();    
			textobase = (TextoBase)this.manager.createNativeQuery(query, new TextoBase().getClass()).getSingleResult();
			this.manager.getTransaction().commit();
		}
		catch(Exception ex)
		{
			textobase = null;
		}

		return textobase;
	}
	
	public List<TextoBase> buscarTestoBase(String texto)
	{
		List<TextoBase> listTextoBase = null;
		
		texto = texto.replace("*", "%");
		
		String query = "select * from tbl_textobase where tbl_textobase.texto_textobase like '" + texto + "'";
		
		try
		{
			this.manager.getTransaction().begin();    
			listTextoBase = this.manager.createNativeQuery(query, new TextoBase().getClass()).getResultList();
			this.manager.getTransaction().commit();	
		}
		catch(Exception ex)
		{
			listTextoBase = null;
		}

		return listTextoBase;
	}
	
	public boolean alterar(Textobase textobase)
	{
		if(!existeTextobase(textobase.getNumero()))
			return false;
		
		boolean retorno = false;
		Textobase textobaseAlterado = null;
		
		try
		{
			this.manager.getTransaction().begin();    
			textobaseAlterado = this.manager.merge(textobase);
			this.manager.getTransaction().commit();
		}
		catch(Exception ex)
		{
			retorno = false;
		}
		
		if(textobaseAlterado.equals(textobase))
			retorno = true;
		
		return retorno;
	}
	
	public boolean excluir(Textobase textobase)
	{
		if(!existeTextobase(textobase.getNumero()))
			return false;
		
		boolean retorno = false;
		
		try
		{
			this.manager.getTransaction().begin();    
			this.manager.remove(manager.getReference(textobase.getClass(), textobase.getNumero()));
			this.manager.getTransaction().commit();
		}
		catch(Exception ex)
		{
			retorno = false;
		}
		
		if(!existeTextobase(textobase.getNumero()))
		{
			textobase = new Textobase();
			retorno = true;
		}
		
		return retorno;
	}
	
	public List<Textobase> listar()
	{		
		List<Textobase> listTextobase = null;
		String query = "select * from tbl_textobase";
		
		try
		{
			this.manager.getTransaction().begin();    
			listTextobase = this.manager.createNativeQuery(query, new Textobase().getClass()).getResultList();
			this.manager.getTransaction().commit();
		}
		catch(Exception ex)
		{
			listTextobase = null;
		}
		
		return listTextobase;
	}*/
	
	/*private boolean contemNumero(Textobase textobase)
	{
		Integer numero = textobase.getCodigo_textobase();
		
		if(numero != null)
			return true; 
		return false;
	}
	
	public List<Textobase> exibir(){
        try{
            Query q = this.manager.createQuery("select object(c) from tbl_textobase as c");
 
        return q.getResultList();}
        finally{
        	this.manager.close();
        }
    }*/
	
	//@SuppressWarnings("unchecked")
	public List<Textobase> listar()
	{		
		List<Textobase> listTextobase = null;
		String query = "select * from tbl_textobase";
		
		try
		{
			//close();
			//open();
			this.manager.getTransaction().begin();
			listTextobase = this.manager.createNativeQuery(query, new Textobase().getClass()).getResultList();
			//listTextobase = this.manager.createQuery("select c * Textobase").getResultList();
			this.manager.getTransaction().commit();
		}
		catch(Exception ex)
		{
			listTextobase = null;
			this.manager.getTransaction().rollback();
		}
		
		return listTextobase;
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
	
	/*private boolean existeTextobase(Integer numero)
	{
		Textobase textobase = buscar(numero);
		
		if(textobase == null)
			return false;
		return true;
	}*/
}