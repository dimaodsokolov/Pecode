import home.test.Pages.AccountPage;
import home.test.Pages.MainPage;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;



@Listeners(TestListener.class)
public class LoginPageTest extends BaseTest {
    private MainPage mainPage;
    private AccountPage accountPage;


    @Step("Verify presents of elements")
    @Test(priority = 1,description = "Verify presents of UserName,Password fields and Login Button")
    public void verifyThatAllTheElementsArePresentOnThePage() {
        mainPage = new MainPage(chrome);
        soft.assertTrue(mainPage.userNameFieldIsDisplayed());
    soft.assertTrue(mainPage.PasswordFieldIsDisplayed());
    soft.assertTrue(mainPage.PasswordFieldIsDisplayed());
    soft.assertAll();
    }


    @Step("LoginTest with UserName and Password")
    @Test(priority = 2,description = "no such user with credentials")
    public void LoginTest() {
        mainPage = new MainPage(chrome);
        accountPage = new AccountPage();
        mainPage.fillUsername();
        mainPage.fillPassword();
        accountPage = mainPage.Login();
        String expectedResult = "No account found with that username.";
        String actualResult = mainPage.getUserNameErrorInfo();
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Step("LoginTest unsuccessful only UserName")
    @Test(priority = 3,description = "test unsuccessful login, no password")
    public void LoginTestUnsOnlyName() {
        mainPage = new MainPage(chrome);
        accountPage = new AccountPage();
        mainPage.fillUsername();
        accountPage = mainPage.Login();
        String expectedResult = "Please enter your password.";
        String actualResult = mainPage.getUserPasswordErrorInfo();
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Step("LoginTest unsuccessful only Password")
    @Test(priority = 4,description = "test unsuccessful login, no username")
    public void LoginTestUnsOnlyPass() {
        mainPage = new MainPage(chrome);
        accountPage = new AccountPage();
        mainPage.fillPassword();
        accountPage = mainPage.Login();
        String expectedResult = "Please enter username.";
        String actualResult = mainPage.getUserNameErrorInfo();
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Step("NoCredentials")
    @Description("Description of method - no name, no pass")
    @Test(priority = 5, description = "no name, no pass")

    public void NoCredentials() {
        mainPage = new MainPage(chrome);
        accountPage = new AccountPage();
        accountPage = mainPage.Login();
        String expNameErrorMessage = "Please enter username.";
        String actNameErrorMessage = mainPage.getUserNameErrorInfo();
        soft.assertEquals(actNameErrorMessage, expNameErrorMessage);
        String expPasswordErrorMessage = "Please enter your password.";
        String actPasswordErrorMessage = mainPage.getUserPasswordErrorInfo();
        soft.assertEquals(actPasswordErrorMessage, expPasswordErrorMessage);

    }

    @Step("Wrong expected result, test should fail")
    @Test(priority = 6,description = "test unsuccessful login, no username")
    public void LoginTestWithError() {
        mainPage = new MainPage(chrome);
        accountPage = new AccountPage();
        mainPage.fillPassword();
        accountPage = mainPage.Login();
        String expectedResult = "Please enter $$#@%^.";
        String actualResult = mainPage.getUserNameErrorInfo();
        Assert.assertEquals(actualResult, expectedResult);
    }
}
