package tests;

import Page_Objects.TestBase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class FullscreenTest {
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
    public void turningFullscreenOnAndOff() throws InterruptedException {
        testBase.getLandingPage().enterTheGame();
        Thread.sleep(500);

        // Step 1 - Fullscreen is disabled by default
        testBase.getInputSelectionPage().skipToMainMenu();
        Assertions.assertTrue(testBase.getMainMenuPage().isFullscreenOff());

        // Step 2 - Fullscreen is enabled by default on the song list page
        testBase.getMainMenuPage().goToSongList();
        testBase.getSongLanguagesPage().continueAndGoToSongList();
        Assertions.assertTrue(testBase.getSongListPage().isFullscreenOn());

        // Step 3 - Turning off fullscreen makes that mode is not getting on automatically
        actions.sendKeys("Backspace");
        testBase.getMainMenuPage().toggleFullscreen();
        Assertions.assertTrue(testBase.getMainMenuPage().isFullscreenOff());
        Assertions.assertTrue(testBase.getSongListPage().isFullscreenOff());
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

}
