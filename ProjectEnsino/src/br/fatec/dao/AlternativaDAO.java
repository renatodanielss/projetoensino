package br.fatec.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import br.fatec.model.Alternativa;

public class AlternativaDAO {
	private EntityManagerFactory factory;
	private EntityManager manager;
	
	public AlternativaDAO() {
		open();
	}
	
	public boolean inserir(Alternativa alternativa)
	{
		try
		{
			this.manager.getTransaction().begin();
	 		this.manager.persist(alternativa);
			this.manager.getTransaction().commit();
			return true;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			if (this.manager.getTransaction().isActive())
				this.manager.getTransaction().rollback();
			return false;
		}
	}
	
	public boolean alterar(Alternativa alternativa)
	{
		try
		{
			this.manager.getTransaction().begin();    
			this.manager.merge(alternativa);
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
	
	public Alternativa buscar(Integer codigo)
	{
		Alternativa alternativa = null;
		
		try
		{
			this.manager.getTransaction().begin();
			alternativa = this.manager.find(Alternativa.class, codigo);
	 		this.manager.getTransaction().commit();
		}
		catch(Exception ex){
			alternativa = null;
			if (this.manager.getTransaction().isActive())
				this.manager.getTransaction().rollback();
		}

		return alternativa;
	}
	
	@SuppressWarnings("unchecked")
	public List<Alternativa> listar()
	{		
		List<Alternativa> listAlternativa = null;
		String query = "SELECT * FROM tbl_alternativa";
		
		try
		{
			this.manager.getTransaction().begin();
			listAlternativa = this.manager.createNativeQuery(query, new Alternativa().getClass()).getResultList();
			this.manager.getTransaction().commit();
		}
		catch(Exception ex)
		{
			listAlternativa = null;
			if (this.manager.getTransaction().isActive())
				this.manager.getTransaction().rollback();
		}
		
		return listAlternativa;
	}
	
	@SuppressWarnings("unchecked")
	public List<Object> listarInnerJoin()
	{		
		List<Object> listQuestao = null;
		String query = "SELECT tbl_textobase.codigo_textobase, tbl_textobase.titulo_textobase, tbl_disciplina.nome_disciplina FROM tbl_textobase inner join tbl_disciplina on tbl_textobase.disciplina_textobase = tbl_disciplina.id_disciplina";
		
		try
		{
			this.manager.getTransaction().begin();
			listQuestao = this.manager.createNativeQuery(query, new Object().getClass()).getResultList();
			this.manager.getTransaction().commit();
		}
		catch(Exception ex)
		{
			listQuestao = null;
			if (this.manager.getTransaction().isActive())
				this.manager.getTransaction().rollback();
		}
		
		return listQuestao;
	}
	
	public boolean existeTextobase(Integer codigo)
	{
		Alternativa alternativa = buscar(codigo);
		
		if(alternativa == null)
			return false;
		return true;
	}
	
	public boolean excluir(Alternativa alternativa)
	{
		try
		{
			this.manager.getTransaction().begin();
			this.manager.remove(manager.getReference(alternativa.getClass(), alternativa.getId_alternativa()));
			this.manager.getTransaction().commit();
			System.out.println("Alternativa Commit");
			return true;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			System.out.println("Alternativa Rollback");
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