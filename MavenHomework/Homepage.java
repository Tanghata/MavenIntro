import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Homepage {

    public static String baseUrl = "https://placelab.com";
    public WebDriver driver;
    private String browser;

    public Homepage() { }

    @BeforeTest
    public void openBrowser() {
        this.browser = "Chrome";

        if (this.browser.contains("Chrome")) {
            WebDriverManager.chromedriver().setup();
            this.driver = new ChromeDriver();
        } else if (this.browser.contains("Firefox")) {
            WebDriverManager.firefoxdriver().setup();
            this.driver = new FirefoxDriver();
        } else if (this.browser.contains("Opera")) {
            WebDriverManager.operadriver().setup();
            this.driver = new OperaDriver();
        }
        this.driver.navigate().to(baseUrl);
    }

    @Test
    public void verifyHomepageSubtitle() {
        String expectedText = "CASE STUDIES";
        String actualText = "";
        By by = new By.ByXPath("//div [@ class='case-studies-container']/h3");
        actualText = this.driver.findElement(by).getText();
        Assert.assertEquals(actualText, expectedText);
    }

    @AfterTest
    public void closeBrowser() {
        this.driver.quit();
    }
}
