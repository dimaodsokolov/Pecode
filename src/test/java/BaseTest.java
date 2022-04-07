import Utils.Config;
import Utils.TestHelper;

import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.io.IOException;
import java.util.Properties;


public abstract class BaseTest {
    protected static WebDriver chrome;
    protected SoftAssert soft = new SoftAssert();
    private static final Properties config = Config.loadProperties("test.properties");

    public WebDriver getDriver() {
        return chrome;
    }


    @BeforeMethod(description = "configure browser before test")
    public void setup() {
        System.setProperty("webdriver.chrome.driver", config.getProperty("chromedriver"));
        chrome = new ChromeDriver();

        chrome.manage().window().maximize();
        chrome.manage().deleteAllCookies();
        // chrome.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        chrome.get(config.getProperty("baseurl"));

    }


    @AfterTest(alwaysRun = true,description = "cleanup and close browser")
    public void cleanup() {
        if (chrome != null) {
            chrome.manage().deleteAllCookies();
            TestHelper.sleep5Seconds();
            chrome.close();
            chrome.quit();
        }

    }

}

