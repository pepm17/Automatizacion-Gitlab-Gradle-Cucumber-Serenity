package com.accenture.cucumbergradle.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;

@DefaultUrl("https://gitlab.com/users/sign_in")
public class GitLabLogInPage extends PageObject{
	@FindBy(xpath ="//input[@id = 'user_login']")
	private WebElementFacade username;
	
	@FindBy(xpath = "//input[@id = 'user_password']")
	private WebElementFacade password;
	
	public void logIn(String username, String password) {
		this.username.waitUntilClickable().type(username);
		this.password.waitUntilClickable().typeAndEnter(password);
	}
	
}
