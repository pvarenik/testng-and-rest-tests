package org.example;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import pages.GooglePage;

@Test
public class TestNGTest extends BaseTest {
    @Test
    public void testNGDemoTest() {
        GooglePage google = new GooglePage(driver);
        google.closeCookiesWindow();
        google.enterSearchString("Java");
        google.clickSearchButton();
        google.printResults();
        google.verifyResultContain(0, "Java");
        google.verifyLastResultNotContain("Interview");
    }
}
