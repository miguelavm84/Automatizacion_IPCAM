package com.automatizacion.ipcam;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import junit.framework.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.SendKeysAction;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class logicaCrearUsuario {
	
	private WebDriver driverUtilizar = null;
	
   
    @FindBy (linkText = "Users Management")
	private WebElement btnUsersManagement;
    @FindBy (name="newUser")
    private WebElement btnNewUser;
    
    @FindBy(id="calendarioIni")
    private WebElement inputSubscriptionDate;
    @FindBy (id = "name")
	private WebElement inputName;
    @FindBy (id = "apellido1")
	private WebElement inputApellido1;
    @FindBy (id = "apellido2")
	private WebElement inputApellido2;
    @FindBy (id="perfil")
    private WebElement selectProfile;
    @FindBy (name="newUser.estado")
    private WebElement selectState;
    @FindBy (id="login")
    private WebElement inputLogin;
    @FindBy (id="password")
    private WebElement inputPassword;
    @FindBy (id="confirmpassword")
    private WebElement inputConfirmPassword;
    @FindBy (id="asignacionZonas")
    private WebElement lstZones;
    @FindBy (name="saveUser")
    private WebElement btnAcceptUser;
    
    
	
	/**
	 * Constructor de la clase
	 * @param driver
	 * @param urlEnviada
	 */
	public logicaCrearUsuario(WebDriver driver){
		PageFactory.initElements(driver, this);
		this.driverUtilizar = driver;
	}

	/**
	 * Donde vamos a hacer toda la logica de la clase
	 * @throws InterruptedException 
	 */
	public void logicaCreacionUsuarioSteps() throws InterruptedException{
		
		btnUsersManagement.click(); //hacemos click sobre users management
		
		new WebDriverWait(driverUtilizar,10).
        until(ExpectedConditions.visibilityOf(btnNewUser));
		
		btnNewUser.click(); // hacemos click para dar de alta un nuevo usuario
		
		inputSubscriptionDate.click(); // clickamos sobre el objeto calendar para subscription date
		
		
		rellenoCalendar("calendar"); //Ahora llamamos a la logica para que rellene automaticamente la fecha
		
		inputName.sendKeys("QA AUTOMATIC USER"); //se rellena el nombre
		inputApellido1.sendKeys("QA SURNAME1"); // se rellena el primer apellido
		inputApellido2.sendKeys("QA SURNAME2"); // se rellena el segundo apellido
		
		rellenoOptionSelect("Administrator", selectProfile);//ahora vamos a seleccionar profile
		rellenoOptionSelect("Enabled", selectState); //ahora vamos a seleccionar en state
		
		
		inputLogin.sendKeys("qa_automatic"); // rellenamos el dato de login
		inputPassword.sendKeys("A12345678"); //rellenamos el dato de password
		inputConfirmPassword.sendKeys("A12345678"); //rellenamos el dato de confirmacion de password
		
		
		
		
		rellenoLista(lstZones);
		
		//btnAcceptUser.click(); //pulsamos el boton para guardar el usuario
		
		Thread.sleep(3000);
		

	}
	
	
	/**
	 * Rellenamos la fecha en objeto tipo calendario
	 */
	
	public void rellenoCalendar(String idObjetoCalendar){
		/**
		 * Proceso de automatización para poder rellenar la fecha de Subscription date	
		 */
		
		  WebElement dateWidget = driverUtilizar.findElement(By.className(idObjetoCalendar));  
		  List<WebElement> columns=dateWidget.findElements(By.tagName("td"));  
		    
		  for (WebElement cell: columns){ 
		   if (cell.getText().equals(getDiaActual())){
			   cell.click();  
		   break;  
		   }
		  }
		  /**
			 * FIN Proceso de automatización para poder rellenar la fecha de Subscription date	
			 */
	}
	
	/**
	 * Con esta funcion podemos rellenar la opción que queramos en un select
	 * @param valorARellenar
	 * @param elementoSelect
	 */
	
	public void rellenoOptionSelect(String valorARellenar,WebElement elementoSelect)
	{
		Select select = new Select(elementoSelect);
		//select.deselectAll();
		select.selectByVisibleText(valorARellenar);
	}
	
	/**
	 * Con esta funcion vamos a rellenar la zona (access zone) con el primer valor que veamos
	 */
	
	public void rellenoLista(WebElement elementoLista){
		elementoLista.findElement(By.cssSelector("ul.nivel0 > li > input.isParentChkNewZona")).click();
	}
	
	
	
	/**
	 * Con esto recogemos el dia actual del mes
	 * @return
	 */
	
	public String getDiaActual(){
	
		Calendar calendar = new GregorianCalendar();
		
		String diaActual = Integer.toString(calendar.get(Calendar.DAY_OF_MONTH));
		return diaActual;
	}
	
}
