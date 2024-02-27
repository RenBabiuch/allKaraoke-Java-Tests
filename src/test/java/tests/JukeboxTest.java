package tests;

import Page_Objects.TestBase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;

public class JukeboxTest {

    private ChromeDriver driver;
    private TestBase testBase;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        testBase = new TestBase(driver);
        testBase.setUp();
    }

    @Test
    public void jukeboxModeTest() {
        testBase.getLandingPage().enterTheGame();

        // Step 1: Go to Jukebox
        testBase.getInputSelectionPage().skipToMainMenu();
        testBase.getMainMenuPage().goToJukebox();
        testBase.getJukeboxPage().getJukeboxElement().isDisplayed();

        // Step 2: Skip the song - the jukebox should display next song
        String previousSong = testBase.getJukeboxPage().getCurrentSong();
        testBase.getJukeboxPage().goToSkipSong();
        testBase.getJukeboxPage().getJukeboxElement().isDisplayed();
        Assertions.assertNotEquals(previousSong, testBase.getJukeboxPage().getCurrentSong());

        // Step 3: After click to sing a song, that song should appear as a preview in the song list
        String expectedSong = testBase.getJukeboxPage().getCurrentSong();
        testBase.getJukeboxPage().goToSingSong();
        testBase.getSongLanguagesPage().ensureAllSongLanguagesAreSelected();
        testBase.getSongLanguagesPage().continueAndGoToSongList();
        Assertions.assertEquals(expectedSong, testBase.getSongListPage().getSelectedSongID());
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
