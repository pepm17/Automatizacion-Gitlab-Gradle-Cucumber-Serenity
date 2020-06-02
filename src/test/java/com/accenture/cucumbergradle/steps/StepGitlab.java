package com.accenture.cucumbergradle.steps;

import com.accenture.cucumbergradle.pages.GitLabLogInPage;
import com.accenture.cucumbergradle.pages.GitlabHomePage;

import net.thucydides.core.annotations.Step;

public class StepGitlab {
	private GitLabLogInPage gitlabPage;
	private GitlabHomePage gitlabHomePage;
	
	@Step 
	public void abrirNavegador() {
		this.gitlabPage.open();
	}
	
	@Step
	public void logIn(String username, String password) {
		this.gitlabPage.logIn(username, password);
	}
	
	@Step
	public void createProject(String nameProject, String descriptionProject) {
		this.gitlabHomePage.crearProyecto(nameProject, descriptionProject);
	}
	
	@Step
	public void verificarProyecto(String nameProject) {
		this.gitlabHomePage.buscarProyecto(nameProject);
	}
}
