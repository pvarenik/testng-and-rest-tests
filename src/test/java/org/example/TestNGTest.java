package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import pages.GooglePage;

import java.net.SocketException;

public class TestNGTest {
    private WebDriver driver;

    @Test
    public void testNGDemoTest() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.google.co.il/");
        GooglePage google = new GooglePage(driver);
        google.closeCookiesWindow();
        google.enterSearchString("Java");
        google.clickSearchButton();
        google.printResults();
        google.verifyResultContain(0, "Java");
        google.verifyLastResultNotContain("Interview");
    }

    @AfterTest
    public void teardown() {
        driver.quit();
    }
}
