package br.fatec.dao;

import java.sql.ResultSet;
//import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

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
	
	public boolean alterar(Pokemon pokemon)
	{
		if(!existePokemon(pokemon.getNumero()))
			return false;
		
		boolean retorno = false;
		Pokemon pokemonAlterado = null;
		
		try
		{
			this.manager.getTransaction().begin();    
			pokemonAlterado = this.manager.merge(pokemon);
			this.manager.getTransaction().commit();
		}
		catch(Exception ex)
		{
			retorno = false;
		}
		
		if(pokemonAlterado.equals(pokemon))
			retorno = true;
		
		return retorno;
	}
	
	public boolean excluir(Pokemon pokemon)
	{
		if(!existePokemon(pokemon.getNumero()))
			return false;
		
		boolean retorno = false;
		
		try
		{
			this.manager.getTransaction().begin();    
			this.manager.remove(manager.getReference(pokemon.getClass(), pokemon.getNumero()));
			this.manager.getTransaction().commit();
		}
		catch(Exception ex)
		{
			retorno = false;
		}
		
		if(!existePokemon(pokemon.getNumero()))
		{
			pokemon = new Pokemon();
			retorno = true;
		}
		
		return retorno;
	}
	
	public List<Pokemon> listar()
	{		
		List<Pokemon> listPokemons = null;
		String query = "select * from pokemon";
		
		try
		{
			this.manager.getTransaction().begin();    
			listPokemons = this.manager.createNativeQuery(query, new Pokemon().getClass()).getResultList();
			this.manager.getTransaction().commit();
		}
		catch(Exception ex)
		{
			listPokemons = null;
		}
		
		return listPokemons;
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
	
	public ResultSet exibir(){
        try{
            Query q = this.manager.createQuery("select object(c) from tbl_textobase as c");
 
        return (ResultSet)q.getResultList();}
        finally{
        	this.manager.close();
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
	
	/*private boolean existePokemon(Integer numero)
	{
		Pokemon pokemon = buscar(numero);
		
		if(pokemon == null)
			return false;
		return true;
	}*/
}
