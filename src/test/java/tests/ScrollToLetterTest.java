package tests;

import Page_Objects.TestBase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;


public class ScrollToLetterTest {

    private ChromeDriver driver;
    private TestBase testBase;

    String language = "English";
    String groupName = "X";

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        testBase = new TestBase(driver);
        testBase.setUp();
    }

    @Test
    public void scrollingToGroupLetter() {
        testBase.getLandingPage().enterTheGame();

        // Step 1: Go to song list page
        testBase.getInputSelectionPage().skipToMainMenu();
        testBase.getMainMenuPage().goToSongList();

        // Step 2: Pick up at least 1 song language
        testBase.getSongLanguagesPage().ensureSongLanguageIsSelected(language);
        testBase.getSongLanguagesPage().continueAndGoToSongList();

        // Step 3: Preview of random song should be visible
        Assertions.assertTrue(testBase.getSongListPage().getSongPreviewElement().isEnabled());

        // Step 4: Select a group name - the app should scroll to the letter, showing proper results in the viewport
        testBase.getSongListPage().goToGroupName(groupName);
        Assertions.assertTrue(testBase.getSongListPage().isGroupSelected(groupName));
        Assertions.assertTrue(testBase.getSongListPage().isSongFromTheGroupSelected(groupName));
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
