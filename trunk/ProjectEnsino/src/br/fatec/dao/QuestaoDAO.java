package br.fatec.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import br.fatec.model.Questao;

public class QuestaoDAO {
	private EntityManagerFactory factory;
	private EntityManager manager;
	
	public QuestaoDAO() {
		open();
	}
	
	public boolean inserir(Questao questao)
	{
		try
		{
			this.manager.getTransaction().begin();
	 		this.manager.persist(questao);
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
	
	public boolean alterar(Questao questao)
	{
		try
		{
			this.manager.getTransaction().begin();    
			this.manager.merge(questao);
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
	
	public Questao buscar(Integer codigo)
	{
		Questao questao = null;
		
		try
		{
			this.manager.getTransaction().begin();
			questao = this.manager.find(Questao.class, codigo);
	 		this.manager.getTransaction().commit();
		}
		catch(Exception ex){
			questao = null;
			if (this.manager.getTransaction().isActive())
				this.manager.getTransaction().rollback();
		}

		return questao;
	}
	
	@SuppressWarnings("unchecked")
	public List<Questao> listar()
	{		
		List<Questao> listQuestao = null;
		String query = "SELECT * FROM tbl_questao";
		
		try
		{
			this.manager.getTransaction().begin();
			listQuestao = this.manager.createNativeQuery(query, new Questao().getClass()).getResultList();
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
	
	@SuppressWarnings("unchecked")
	public List<Questao> listar(int id_disciplina)
	{
		List<Questao> listQuestao = null;
		String query = "SELECT * FROM tbl_questao where  disciplina_questao = " + id_disciplina;
		
		try
		{
			this.manager.getTransaction().begin();
			listQuestao = this.manager.createNativeQuery(query, new Questao().getClass()).getResultList();
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
		Questao questao = buscar(codigo);
		
		if(questao == null)
			return false;
		return true;
	}
	
	public boolean excluir(Questao questao)
	{
		try
		{
			this.manager.getTransaction().begin();
			this.manager.remove(manager.getReference(questao.getClass(), questao.getId_questao()));
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