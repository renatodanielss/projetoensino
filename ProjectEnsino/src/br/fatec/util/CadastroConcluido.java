package br.fatec.util;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

@ManagedBean(name="cadastroConcluidoController")
@RequestScoped
public class CadastroConcluido {
	private String pageMessage;
	private String template;
	
	public CadastroConcluido(){
		configurarPagina();
	}
	
	public String getPageMessage() {
		return pageMessage;
	}

	public void setPageMessage(String pageMessage) {
		this.pageMessage = pageMessage;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}
	
	private void configurarPagina(){
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpServletRequest req = (HttpServletRequest)facesContext.getExternalContext().getRequest();
		
		String requestUrl = "http://localhost:8084/ProjectEnsino/Pages/";
		String templateUrl = "/Template/";
		
		if (req.getRequestURL().toString().equals(requestUrl + "Aluno.xhtml")){
			setPageMessage("Aluno cadastrado com sucesso");
			setTemplate(templateUrl + "TemplateAdministracao.xhtml");
		}
		else if (req.getRequestURL().toString().equals(requestUrl + "Assunto.xhtml")){
			setPageMessage("Assunto cadastrado com sucesso");
			setTemplate(templateUrl + "TemplateProfessor.xhtml");
		}
		else if (req.getRequestURL().toString().equals(requestUrl + "Autor.xhtml")){
			setPageMessage("Autor cadastrado com sucesso");
			setTemplate(templateUrl + "TemplateProfessor.xhtml");
		}
		else if (req.getRequestURL().toString().equals(requestUrl + "Disciplina.xhtml")){
			setPageMessage("Disciplina cadastrada com sucesso");
			setTemplate(templateUrl + "TemplateProfessor.xhtml");
		}
		else if (req.getRequestURL().toString().equals(requestUrl + "Professor.xhtml")){
			setPageMessage("Professor cadastrado com sucesso");
			setTemplate(templateUrl + "TemplateAdministracao.xhtml");
		}
		else if (req.getRequestURL().toString().equals(requestUrl + "Prova.xhtml")){
			setPageMessage("Prova cadastrada com sucesso");
			setTemplate(templateUrl + "TemplateProfessor.xhtml");
		}
		else if (req.getRequestURL().toString().equals(requestUrl + "Questao.xhtml")){
			setPageMessage("Questão cadastrada com sucesso");
			setTemplate(templateUrl + "TemplateProfessor.xhtml");
		}
		else if (req.getRequestURL().toString().equals(requestUrl + "RealizarProva.xhtml")){
			setPageMessage("Prova realizada com sucesso");
			setTemplate(templateUrl + "TemplateAluno.xhtml");
		}
		else if (req.getRequestURL().toString().equals(requestUrl + "Textobase.xhtml")){
			setPageMessage("Texto base cadastrado com sucesso");
			setTemplate(templateUrl + "TemplateProfessor.xhtml");
		}
		else{
			setPageMessage("");
			setTemplate("");
		}
	}
}