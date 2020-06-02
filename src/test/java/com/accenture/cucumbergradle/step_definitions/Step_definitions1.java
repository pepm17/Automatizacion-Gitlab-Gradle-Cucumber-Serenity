package com.accenture.cucumbergradle.step_definitions;

import com.accenture.cucumbergradle.steps.StepGitlab;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;

public class Step_definitions1 {
	
	@Steps
	private StepGitlab stepGitlab;
	
	@Given("^Que el usuario abre el navegador en la pagina de GitLab$")
	public void abrir() throws Throwable {
		this.stepGitlab.abrirNavegador();
	}
	
	@And("^User login with the following ([^\"]*) and ([^\"]*)$")
	public void logIn(String username, String password) throws Throwable {
		System.out.println("Holi6");
		this.stepGitlab.logIn(username, password);
	}
	
	@When("^User create project with ([^\"]*) and description ([^\"]*)$")
	public void crearProyecto(String nameProject, String descriptionProject) throws Throwable {
		this.stepGitlab.createProject(nameProject, descriptionProject);
	}
	
	@Then("^User created project ([^\"]*)$")
	public void verificarProyecto(String nameProject) throws Throwable {
		this.stepGitlab.verificarProyecto(nameProject);
	}

}
