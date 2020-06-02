package com.accenture.cucumbergradle.pages;

import java.util.List;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

public class GitlabHomePage extends PageObject {
	
	@FindBy(xpath = "//a[@class = 'btn btn-success']")
	private WebElementFacade newProject;
	
	@FindBy(xpath = "//input[@name = 'project[name]']")
	private WebElementFacade nameProject;
	
	@FindBy(xpath = "//textarea[@name = 'project[description]']")
	private WebElementFacade descriptionProject;
	
	@FindBy(xpath = "//input[@id = 'project_visibility_level_20']")
	private WebElementFacade typePublic;
	
	@FindBy(xpath = "//input[@name = 'commit']")
	private WebElementFacade createProject;
	
	@FindBy(xpath = "//span[@class = 'project-name']")
	private List<WebElementFacade> listProject;
	
	@FindBy(xpath = "//a[@id = 'logo']")
	private WebElementFacade inicio;
	
	public void crearProyecto(String nameProject, String descriptionProject) {
		this.newProject.waitUntilClickable().click();
		this.nameProject.waitUntilClickable().type(nameProject);
		this.descriptionProject.waitUntilClickable().type(descriptionProject);
		this.typePublic.waitUntilClickable().click();
		this.createProject.waitUntilClickable().click();
		this.inicio.waitUntilClickable().click();
	}
	
	public void buscarProyecto(String nameProject) {
		for(WebElementFacade project: this.listProject) {
			if(project.getText().equals(nameProject)) project.waitUntilClickable().click();
		}
	}

}
