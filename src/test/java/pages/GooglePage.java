package pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class GooglePage {
    static Logger logger = Logger.getLogger(GooglePage.class.getName());
    WebDriver driver;
    By searchField = By.name("q");
    By searchButton = By.name("btnK");
    By results = By.cssSelector("a > h3");

    public GooglePage(WebDriver driver) {
        this.driver = driver;
    }

    public void closeCookiesWindow() {
        List<WebElement> resultsElements = driver.findElements(By.cssSelector("div[role='none']"));
        resultsElements.get(resultsElements.size() - 1).click();
    }

    public List<WebElement> resultsElements() {
        return driver.findElements(results);
    }

    public void enterSearchString(String str) {
        driver.findElement(searchField).sendKeys(str);
    }

    public void clickSearchButton() {
        for (WebElement e : driver.findElements(searchButton)) {
            if (e.isDisplayed()) {
                e.click();
            }
        }
    }

    public void printResults() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(results));
        for (int i = 0; i < driver.findElements(results).size(); i++) {
            WebElement result = driver.findElements(results).get(i);
            if (!result.getText().isEmpty()) {
                System.out.println(result.getText());
//                logger.info(result.getText());
            }
        }
    }

    public void verifyResultContain(int num, String text) {
        Assert.assertTrue(driver.findElements(results).get(num).getText().contains(text));
    }

    public void verifyResultNotContain(int num, String text) {
        Assert.assertFalse(driver.findElements(results).get(num).getText().contains(text));
    }

    public void verifyLastResultNotContain(String text) {
        Assert.assertFalse(driver.findElements(results).get(resultsElements().size() - 1).getText().contains(text));
    }
}
