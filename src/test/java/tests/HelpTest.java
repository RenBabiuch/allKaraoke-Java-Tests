package tests;

import Page_Objects.TestBase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;


public class HelpTest {

    private WebDriver driver;
    private TestBase testBase;
    private Actions actions;


    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        testBase = new TestBase(driver);
        actions = new Actions(driver);
        testBase.setUp();
    }

    @Test
    public void hidingAndOpeningHelpContainer() throws InterruptedException {
        testBase.getLandingPage().enterTheGame();
        // ElementClickInterceptedException - this is necessary, because the page doesn't have enough time
        // to make the 'skip' button visible for clicking. Otherwise, another element would get the click
        Thread.sleep(500);

        // Step 1 - Help container is visible by default on the page if it's not turned off
        testBase.getInputSelectionPage().skipToMainMenu();
        Assertions.assertTrue(testBase.getMainMenuPage().getHelpContainer().isDisplayed());

        // Step 2 - After clicking on the container, it's hidden
        testBase.getMainMenuPage().getHelpContainer().click();
        Assertions.assertFalse(testBase.getMainMenuPage().getHelpContainer().isDisplayed());

        // Step 3 - The setting is remembered after refresh
        driver.navigate().refresh();
        Assertions.assertFalse(testBase.getMainMenuPage().getHelpContainer().isDisplayed());

        // Step 4 - The container is visible again after clicking the help-icon
        testBase.getMainMenuPage().getToggleHelp().click();
        Assertions.assertTrue(testBase.getMainMenuPage().getHelpContainer().isDisplayed());

        // Step 5 - The container is hidden, when disabled with a shortcut
        actions.sendKeys("Shift+H").perform();
        Assertions.assertFalse(testBase.getMainMenuPage().getHelpContainer().isDisplayed());
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
