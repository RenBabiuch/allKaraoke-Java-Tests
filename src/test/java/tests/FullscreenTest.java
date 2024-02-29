package tests;

import Page_Objects.TestBase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;

public class FullscreenTest {
    private ChromeDriver driver;
    private TestBase testBase;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        testBase = new TestBase(driver);
        testBase.setUp();
    }

    @Test
    public void turningFullscreenOnAndOff() {
        testBase.getLandingPage().enterTheGame();

        // Step 1: Fullscreen is disabled by default
        testBase.getInputSelectionPage().skipToMainMenu();
        Assertions.assertTrue(testBase.getFullscreenPage().isFullscreenOff(), "Fullscreen should be turned off");

        // Step 2: Fullscreen is enabled by default on the song list page
        testBase.getMainMenuPage().goToSongList();
        testBase.getSongLanguagesPage().continueAndGoToSongList();
        Assertions.assertTrue(testBase.getFullscreenPage().isFullscreenOn(), "Fullscreen should be turned on");

        // Step 3: Turning off fullscreen makes that mode is not getting on automatically
        testBase.getSongListPage().goBackToMainMenu();
        testBase.getFullscreenPage().toggleFullscreen();
        Assertions.assertTrue(testBase.getFullscreenPage().isFullscreenOff(), "Fullscreen should be turned off");

        testBase.getMainMenuPage().goToSongList();
        Assertions.assertTrue(testBase.getFullscreenPage().isFullscreenOff(), "Fullscreen should be turned off");
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

}
