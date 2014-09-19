package com.automatizacion.ipcam;
import junit.framework.TestCase;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.*;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public class LanzadorTestIpCam {
	@Rule public TestName name = new TestName(); // con esto le decimos que debe de ejecutarse segun el nombre del test
	
    private static WebDriver driver;
    private static String URL_PRUEBA = "https://pc-acciona/ipcam/Logout.action";

    /**
     * Solo iniciamos el driver
     */
    @BeforeClass
    public static void setUp() throws Exception {

    	//Establecemos el idioma ingles por defecto//
    	FirefoxProfile profile = new FirefoxProfile();
    	profile.setPreference("intl.accept_languages", "en");
    	driver = new FirefoxDriver(profile);   
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    }

    /**
     * Nos Logamos en la aplicación (Como Administrador)
     * @throws Exception
     */
    @Test
    public void LoginUsuario() throws Exception {
       
    	logicaLoginAplicacion manejadorLoginUsuario = new logicaLoginAplicacion(driver, URL_PRUEBA);
    	manejadorLoginUsuario.logicaLoginSteps();
    	
    	
    }
    
    /**
     * Crear nuevo usuario 
     * @throws InterruptedException 
     */
    @Test
    public void CrearUsuario() throws InterruptedException{
    
    	logicaCrearUsuario manejadorLogicaCrearUsuario = new logicaCrearUsuario(driver);
    	manejadorLogicaCrearUsuario.logicaCreacionUsuarioSteps();
    }
    
    @AfterClass
    public static void tearDown() throws Exception {
        driver.quit();
    }
}
		