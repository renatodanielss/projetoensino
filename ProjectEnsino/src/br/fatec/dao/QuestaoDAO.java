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
			ex.printStackTrace();
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
		String query = "SELECT * FROM tbl_questao WHERE disciplina_questao = " + id_disciplina;
		
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
	public List<Questao> listar(int id_disciplina, int id_assunto)
	{
		List<Questao> listQuestao = null;
		String query;
		
		if (id_disciplina != -1 && id_assunto != -1)
			query = "SELECT * FROM tbl_questao WHERE disciplina_questao = " + id_disciplina + " AND assunto_questao = " + id_assunto;
		else if (id_disciplina != -1)
			query = "SELECT * FROM tbl_questao WHERE disciplina_questao = " + id_disciplina;
		else if (id_assunto != -1)
			query = "SELECT * FROM tbl_questao WHERE assunto_questao = " + id_assunto;
		else
			query = "SELECT * FROM tbl_questao";
			
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
	public List<Questao> listar(int id_disciplina, int id_assunto, int id_usuario_professor)
	{
		List<Questao> listQuestao = null;
		String query;
		
		if (id_disciplina != -1 && id_assunto != -1)
			query = "SELECT * FROM tbl_questao WHERE disciplina_questao = " + id_disciplina + " AND assunto_questao = " + id_assunto + " AND usuario_professor_questao = " + id_usuario_professor;
		else if (id_disciplina != -1)
			query = "SELECT * FROM tbl_questao WHERE disciplina_questao = " + id_disciplina + " AND usuario_professor_questao = " + id_usuario_professor;
		else if (id_assunto != -1)
			query = "SELECT * FROM tbl_questao WHERE assunto_questao = " + id_assunto + " AND usuario_professor_questao = " + id_usuario_professor;
		else
			query = "SELECT * FROM tbl_questao WHERE usuario_professor_questao = " + id_usuario_professor;
			
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
	public List<Questao> listar(String enunciadoQuestao, Integer idDisciplina)
	{
		List<Questao> listQuestao = null;
		String query;
		
		if (idDisciplina == -1){
			query = "SELECT * FROM tbl_questao WHERE lower(enunciado_questao) LIKE lower('%" + enunciadoQuestao.toLowerCase() + "%')";
		}
		else{
			query = "SELECT * FROM tbl_questao WHERE lower(enunciado_questao) LIKE lower('%" + enunciadoQuestao.toLowerCase() + "%') AND disciplina_questao = " + idDisciplina;
		}
		
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