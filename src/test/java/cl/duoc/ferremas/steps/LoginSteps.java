package cl.duoc.ferremas.steps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import cl.duoc.ferremas.pages.LoginPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

import static org.junit.Assert.*;

public class LoginSteps {
    
    private WebDriver driver;
    private LoginPage loginPage;
    private String baseUrl = "http://98.92.159.73:8080";
    
    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
    }
    
    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
    
    @Given("que estoy en la página de login de Ferremas")
    public void queEstoyEnLaPaginaDeLoginDeFerremas() {
        loginPage.navigateToLoginPage(baseUrl);
        loginPage.clickUserMenuButton();
        loginPage.clickIniciarSesionLink();
    }
    
    @When("ingreso el usuario {string}")
    public void ingresoElUsuario(String email) {
        loginPage.enterEmail(email);
    }
    
    @When("ingreso la contraseña {string}")
    public void ingresoLaContrasena(String password) {
        loginPage.enterPassword(password);
    }
    
    @When("hago click en el botón de login")
    public void hagoClickEnElBotonDeLogin() {
        loginPage.clickIngresarButton();
    }
    
    @Then("debería ver mi nombre de usuario {string} en el navbar")
    public void deberiaVerMiNombreDeUsuarioEnElNavbar(String expectedName) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        String userName = loginPage.getUserNameText();
        
        assertTrue("No se muestra el nombre esperado '" + expectedName + "' en el navbar. Texto encontrado: '" + userName + "'", 
                   userName.toUpperCase().contains(expectedName.toUpperCase()));
    }
    
    @Then("debería estar en la página principal")
    public void deberiaEstarEnLaPaginaPrincipal() {
        String currentUrl = loginPage.getCurrentUrl();
        assertEquals("No está en la página principal", baseUrl + "/", currentUrl);
    }
    
    @Then("debería estar en la página de administración")
    public void deberiaEstarEnLaPaginaDeAdministracion() {
        String currentUrl = loginPage.getCurrentUrl();
        assertEquals("No está en la página de administración", baseUrl + "/admin", currentUrl);
    }
    
    @Then("la URL debería contener {string}")
    public void laUrlDeberiaContener(String urlPart) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        String currentUrl = loginPage.getCurrentUrl();
        assertTrue("La URL no contiene '" + urlPart + "'. URL actual: " + currentUrl, 
                   currentUrl.endsWith(urlPart));
    }
    
    @Then("debería ver un mensaje de error de credenciales incorrectas")
    public void deberiaVerUnMensajeDeErrorDeCredencialesIncorrectas() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        assertTrue("No se muestra la alerta de error", loginPage.isErrorAlertDisplayed());
        
        String errorMessage = loginPage.getErrorMessageText();
        assertTrue("El mensaje de error no es el esperado. Mensaje encontrado: '" + errorMessage + "'",
                   errorMessage.contains("Credenciales incorrectas"));
    }
    
    @Then("debería permanecer en la página de login")
    public void deberiaPermancerEnLaPaginaDeLogin() {
        String currentUrl = loginPage.getCurrentUrl();
        assertTrue("No permanece en la página de login", currentUrl.contains("/login"));
    }
}