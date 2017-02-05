package br.fatec.util;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
//import javax.servlet.http.HttpServletRequest;

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
		/*FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpServletRequest req = (HttpServletRequest)facesContext.getExternalContext().getRequest();*/
		
		String templateUrl = "/Template/";
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		String redirectValue = (String) facesContext.getExternalContext().getRequestParameterMap().get("origin");
		switch (redirectValue){
			
		case "aluno":
			setPageMessage("Aluno cadastrado com sucesso");
			setTemplate(templateUrl + "TemplateAdministracao.xhtml");
			break;
		case "assunto":
			setPageMessage("Assunto cadastrado com sucesso");
			setTemplate(templateUrl + "TemplateProfessor.xhtml");
			break;
		case "autor":
			setPageMessage("Autor cadastrado com sucesso");
			setTemplate(templateUrl + "TemplateProfessor.xhtml");
			break;
		case "disciplina":
			setPageMessage("Disciplina cadastrada com sucesso");
			setTemplate(templateUrl + "TemplateProfessor.xhtml");
			break;
		case "professor":
			setPageMessage("Professor cadastrado com sucesso");
			setTemplate(templateUrl + "TemplateAdministracao.xhtml");
			break;
		case "usuarioprofessor":
			setPageMessage("Usuário cadastrado com sucesso");
			setTemplate(templateUrl + "TemplateAdministracao.xhtml");
			break;
		case "prova":
			setPageMessage("Prova cadastrada com sucesso");
			setTemplate(templateUrl + "TemplateProfessor.xhtml");
			break;
		case "questao":
			setPageMessage("Questão cadastrada com sucesso");
			setTemplate(templateUrl + "TemplateProfessor.xhtml");
			break;
		case "realizarprova":
			setPageMessage("Prova realizada com sucesso");
			setTemplate(templateUrl + "TemplateAluno.xhtml");
			break;
		case "textobase":
			setPageMessage("Texto base cadastrado com sucesso");
			setTemplate(templateUrl + "TemplateProfessor.xhtml");
			break;
		default:
			setPageMessage("");
			setTemplate("");
		}
	}
}