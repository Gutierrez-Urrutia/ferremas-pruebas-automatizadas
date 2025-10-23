package cl.duoc.ferremas.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage {
    
    private WebDriver driver;
    private WebDriverWait wait;
    
    // Localizadores usando XPath
    private By userMenuButton = By.xpath("//*[@id='navbarColor01']/div/div[2]/button");
    private By iniciarSesionLink = By.xpath("//*[@id='navbarColor01']/div/div[2]/ul/li[1]/a");
    private By emailField = By.xpath("//*[@id='email']");
    private By passwordField = By.xpath("//*[@id='password']");
    private By ingresarButton = By.xpath("/html/body/app-root/div/main/app-login/div/form/button");
    private By userNameButton = By.xpath("//*[@id='navbarColor01']/div/div[2]/button");
    private By errorAlert = By.xpath("/html/body/app-root/div/main/app-login/div/div");
    private By errorMessage = By.xpath("/html/body/app-root/div/main/app-login/div/div/div");
    
    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    // Métodos
    public void navigateToLoginPage(String url) {
        driver.get(url);
    }
    
    public void clickUserMenuButton() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(userMenuButton));
        button.click();
    }
    
    public void clickIniciarSesionLink() {
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(iniciarSesionLink));
        link.click();
    }
    
    public void enterEmail(String email) {
        WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(emailField));
        emailInput.clear();
        emailInput.sendKeys(email);
    }
    
    public void enterPassword(String password) {
        WebElement passwordInput = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }
    
    public void clickIngresarButton() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(ingresarButton));
        button.click();
    }
    
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
    
    public String getUserNameText() {
        try {
            Thread.sleep(3000);
            WebElement userButton = wait.until(ExpectedConditions.visibilityOfElementLocated(userNameButton));
            return userButton.getText();
        } catch (Exception e) {
            return "Elemento no encontrado";
        }
    }
    
    public boolean isErrorAlertDisplayed() {
        try {
            WebElement alert = wait.until(ExpectedConditions.visibilityOfElementLocated(errorAlert));
            return alert.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public String getErrorMessageText() {
        try {
            WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
            return message.getText();
        } catch (Exception e) {
            return "Mensaje no encontrado";
        }
    }
}