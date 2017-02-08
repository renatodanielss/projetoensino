package br.fatec.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.fatec.model.Textobase;

public class TextobaseDAO {
	private EntityManagerFactory factory;
	private EntityManager manager;
	
	public TextobaseDAO()
	{
		
	}
	
	public boolean inserir(Textobase textobase)
	{	
		try
		{
			open();
			this.manager.getTransaction().begin();    
	 		this.manager.persist(textobase);
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
	
	/*	
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
	
	public List<TextoBase> buscarTextoBase(String texto)
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
        
        	this.manager.
        }
    }*/
	
	public boolean alterar(Textobase textobase)
	{
		try
		{
			open();
			this.manager.getTransaction().begin();    
			this.manager.merge(textobase);
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
	
	public Textobase buscar(Integer codigo)
	{
		Textobase textobase = null;
		
		try
		{
			open();
			this.manager.getTransaction().begin();    
	 		textobase = this.manager.find(Textobase.class, codigo);
	 		this.manager.getTransaction().commit();
		}
		catch(Exception ex){
			textobase = null;
			if (this.manager.getTransaction().isActive())
				this.manager.getTransaction().rollback();
		}finally{
			close();
		}

		return textobase;
	}
	
	@SuppressWarnings("unchecked")
	public List<Textobase> listar()
	{		
		List<Textobase> listTextobase = null;
		String query = "SELECT * FROM tbl_textobase";
		
		try
		{
			open();
			this.manager.getTransaction().begin();
			listTextobase = this.manager.createNativeQuery(query, new Textobase().getClass()).getResultList();
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
	
	@SuppressWarnings("unchecked")
	public List<Object> listarInnerJoin()
	{		
		List<Object> listTextobase = null;
		String query = "SELECT tbl_textobase.codigo_textobase, tbl_textobase.titulo_textobase, tbl_disciplina.nome_disciplina FROM tbl_textobase inner join tbl_disciplina on tbl_textobase.disciplina_textobase = tbl_disciplina.id_disciplina";
		
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
	
	@SuppressWarnings("unchecked")
	public List<Textobase> listar(String textobase, Integer idDisciplina)
	{		
		List<Textobase> listTextobase = null;
		String query;
		
		if (idDisciplina == -1)
			query = "SELECT * FROM tbl_textobase WHERE lower(titulo_textobase) LIKE lower('%" + textobase.toLowerCase() + "%')";
		else
			query = "SELECT * FROM tbl_textobase WHERE lower(titulo_textobase) LIKE lower('%" + textobase.toLowerCase() + "%') AND disciplina_textobase = " + idDisciplina;
			
		try
		{
			open();
			this.manager.getTransaction().begin();
			listTextobase = this.manager.createNativeQuery(query, new Textobase().getClass()).getResultList();
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
	
	public boolean existeTextobase(Integer codigo)
	{
		Textobase textobase = buscar(codigo);
		
		if(textobase == null)
			return false;
		return true;
	}
	
	public boolean excluir(Textobase textobase)
	{
		try
		{
			open();
			this.manager.getTransaction().begin();
			this.manager.remove(manager.getReference(textobase.getClass(), textobase.getCodigo_textobase()));
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