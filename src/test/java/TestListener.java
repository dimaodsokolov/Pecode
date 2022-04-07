import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;


public class TestListener  implements ITestListener {
    @Override
    public void onTestFailure(ITestResult result) {
        Allure.getLifecycle().addAttachment(
                "screenshot", "image/png", "png"
                , ((TakesScreenshot) BaseTest.chrome).getScreenshotAs(OutputType.BYTES));
        System.out.println("screen\n=======\nscreen");
        BaseTest.chrome.close();
        BaseTest.chrome.quit();


    }
    @Override
    public void onTestSuccess(ITestResult result) {
        BaseTest.chrome.close();
        BaseTest.chrome.quit();
    }
}