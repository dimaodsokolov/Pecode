import io.qameta.allure.Allure;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;



public class TestListener  implements TestWatcher {
    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        Allure.getLifecycle().addAttachment(

                "screenshot", "image/png", "png"
                , ((TakesScreenshot) BaseTest.chrome).getScreenshotAs(OutputType.BYTES));
        System.out.println("=======\nscreen\n=======");
        BaseTest.chrome.close();
        BaseTest.chrome.quit();


    }
    @Override
    public void testSuccessful(ExtensionContext context) {
        BaseTest.chrome.close();
        BaseTest.chrome.quit();
    }
}