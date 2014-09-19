package com.automatizacion.ipcam;

import junit.framework.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class logicaLoginAplicacion {
	
	private String URL_TEST = "";
	private WebDriver driverUtilizar = null;
	
   
    @FindBy (name = "user.login")
	private WebElement inputCampoLogin;
    @FindBy (name = "user.password")
	private WebElement inputCampoPassword;
    @FindBy (name = "loginAccess")
	private WebElement btnLogin;
	@FindBy (className = "nombreAcceso")
	private WebElement cabeceraAdministrador;
	
	/**
	 * Constructor de la clase
	 * @param driver
	 * @param urlEnviada
	 */
	public logicaLoginAplicacion(WebDriver driver,String urlEnviada){
		PageFactory.initElements(driver, this);
		this.URL_TEST = urlEnviada;
		this.driverUtilizar = driver;
		driverUtilizar.get(URL_TEST);
	}

	/**
	 * Donde vamos a hacer toda la logica de la clase
	 */
	public void logicaLoginSteps(){
		// Primero limpiamos los datos de la pantalla
		inputCampoLogin.clear();
		inputCampoPassword.clear();
		
		// Ahora rellenamos usuario y clave
		inputCampoLogin.sendKeys("Administrador");
		inputCampoPassword.sendKeys("A12345678");
		
		// ahora pulsamos el boton de login
		btnLogin.click();
		
		
		new WebDriverWait(driverUtilizar,10).
        until(ExpectedConditions.visibilityOf(cabeceraAdministrador));
		

	}
	
}
