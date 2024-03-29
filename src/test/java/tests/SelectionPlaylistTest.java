package tests;

import Page_Objects.TestBase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;

public class SelectionPlaylistTest {

    private ChromeDriver driver;
    private TestBase testBase;

    String engLanguage = "English";
    String songID = "xavier-rudd-stoney-creek";
    String selectionPlaylist = "Selection";

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        testBase = new TestBase(driver);
        testBase.setUp();
    }

    @Test
    public void addingCompletedSongToSelectionPlaylist() {

        // Step 1: Skip to the Song List
        testBase.getLandingPage().enterTheGame();
        testBase.getInputSelectionPage().skipToMainMenu();
        testBase.getMainMenuPage().goToSongList();

        // Step 2: Ensure song language is selected
        testBase.getSongLanguagesPage().ensureSongLanguageIsSelected(engLanguage);
        testBase.getSongLanguagesPage().continueAndGoToSongList();

        // Step 3: Playlist should contain the new and the most popular songs as well
        testBase.getSongListPage().expectPlaylistToBeSelected(selectionPlaylist);
        Assertions.assertTrue(testBase.getSongListPage().doesPlaylistContainSongsMarkedAsNew(), "Playlist should contain songs marked as new");
        Assertions.assertTrue(testBase.getSongListPage().doesPlaylistContainSongsMarkedAsPopular(), "Playlist should contain songs marked as popular");

        // Step 4: Choose and open the song in language playlist - ensure it`s not added to Selection playlist
        Assertions.assertFalse(testBase.getSongListPage().isSongOnTheList(songID), "Song should be not visible on the playlist yet");
        testBase.getSongListPage().goToPlaylist(engLanguage);
        testBase.getSongListPage().openPreviewForSong(songID);
        testBase.getSongPreviewPage().goNext();

        // Step 5: Select Advanced setup
        testBase.getSongPreviewPage().goToInputSelection();
        testBase.getInputSelectionPage().selectAdvancedSetup();
        testBase.getAdvancedConnectionPage().goBackToSongPreview();

        // Step 6: Play the song - skip to the Song List
        testBase.getSongPreviewPage().goToPlayTheSong();
        testBase.getGamePage().skipIntro();
        testBase.getGamePage().skipOutro();
        testBase.getPostGameResultsPage().skipScoresAnimation();
        testBase.getPostGameResultsPage().goToHighScoresStep();
        testBase.getPostGameHighScoresPage().goToSongListPage();

        // Step 7: After complete the song, it should be added to `Selection` playlist as one of favourite
        testBase.getSongListPage().goToPlaylist(selectionPlaylist);
        Assertions.assertTrue(testBase.getSongListPage().getSongElement(songID).isDisplayed(), "Song should be added to Selection playlist after completed");
        testBase.getSongListPage().expectSongToBeMarkedAsPlayedToday(songID);
    }
    @AfterEach
    public void tearDown() {
        driver.quit();
    }

}
