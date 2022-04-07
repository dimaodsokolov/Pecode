package home.test.Pages;

import Utils.Config;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Properties;

public class MainPage {
    private static final Properties config = Config.loadProperties("test.properties");
    public static final String GLOBAL_USERNAME = "username";
    public static final String GLOBAL_PASSWORD = "password";
    public static final String GLOBAL_LOGINBUTTON = ".btn.btn-success";
    private WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    @FindBy(name = GLOBAL_USERNAME)
    private WebElement userName;

    @FindBy(name = GLOBAL_PASSWORD)
    private WebElement password;

    @FindBy(css = GLOBAL_LOGINBUTTON)
    private WebElement loginButton;

    @FindBy(xpath = "//input[@name='username']/following-sibling::span")
    private WebElement accountNameError;

    @FindBy(xpath = "//input[@name='password']/following-sibling::span")
    private WebElement accountPasswordError;


    public void fillUsername() {
        userName.clear();
        userName.sendKeys(config.getProperty("prop.username"));
    }

    public void fillPassword() {
        password.clear();
        password.sendKeys(config.getProperty("prop.password"));
    }

    public AccountPage Login() {
        loginButton.click();
        return new AccountPage(driver);
    }

    public String getUserNameErrorInfo() {
        return accountNameError.getText();

    }

    public String getUserPasswordErrorInfo() {
        return accountPasswordError.getText();

    }

    public boolean userNameFieldIsDisplayed(){
        WebElement el = driver.findElement(By.name(GLOBAL_USERNAME));
        if(el.isDisplayed())
            return true;
        else
            return false;
    }
    public boolean PasswordFieldIsDisplayed(){
        WebElement el = driver.findElement(By.name(GLOBAL_PASSWORD));
        if(el.isDisplayed())
            return true;
        else
            return false;
    }
    public boolean LoginButtonIsDisplayed(){
        WebElement el = driver.findElement(By.name(GLOBAL_LOGINBUTTON));
        if(el.isDisplayed())
            return true;
        else
            return false;
    }

}
