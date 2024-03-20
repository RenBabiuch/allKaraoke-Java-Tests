package tests;

import Page_Objects.TestBase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;

public class RestartSongTest {

    private ChromeDriver driver;
    private TestBase testBase;

    String songName = "hakuna matata";
    String songID = "disneys-the-lion-king-hakuna-matata";
    String language = "English";


    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        testBase = new TestBase(driver);
        testBase.setUp();
    }

    @Test
    public void restartingSong() {
        testBase.getLandingPage().enterTheGame();

        // Step 1: Select Advanced setup
        testBase.getInputSelectionPage().selectAdvancedSetup();
        testBase.getAdvancedConnectionPage().goToMainMenu();

        // step 2: Ensure song language is selected
        testBase.getMainMenuPage().goToSongList();
        testBase.getSongLanguagesPage().ensureSongLanguageIsSelected(language);
        testBase.getSongLanguagesPage().continueAndGoToSongList();

        // step 3: Search the song
        testBase.getSongListPage().searchSong(songName);
        Assertions.assertEquals(songID, testBase.getSongListPage().getSelectedSongID());
        testBase.getSongListPage().focusSong(songID);

        // Step 4: Go to play the song
        testBase.getSongPreviewPage().goNext();
        testBase.getSongPreviewPage().goToPlayTheSong();
        testBase.getGamePage().waitForPlayersScoreToBeGreaterThan(100);

        // Step 5: After restarting, the song should be played from the beginning - with score '0'
        testBase.getGamePage().restartSong();
        testBase.getGamePage().expectPlayersCoopScoreToBeRestarted();
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
