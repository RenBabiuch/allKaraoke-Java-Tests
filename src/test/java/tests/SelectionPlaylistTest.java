package tests;

import Page_Objects.TestBase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SelectionPlaylistTest {

    private ChromeDriver driver;
    private TestBase testBase;
    private WebDriverWait wait;

    String compSongID = "xavier-rudd-stoney-creek";
    String almCompSongID = "electric-light-orchestra-bluebird";
    String engLanguage = "English";
    String englishPlaylist = "English";
    String selectionPlaylist = "Selection";

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        testBase = new TestBase(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
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
        Assertions.assertFalse(testBase.getSongListPage().isSongOnTheList(compSongID), "Song should not be visible on the playlist yet");
        testBase.getSongListPage().goToPlaylist(englishPlaylist);
        testBase.getSongListPage().openPreviewForSong(compSongID);
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
        Assertions.assertTrue(testBase.getSongListPage().isSongOnTheList(compSongID), "Song should be added to Selection playlist after completed");
        testBase.getSongListPage().expectSongToBeMarkedAsPlayedToday(compSongID);
    }

    @Test
    public void addingAlmostCompletedSongToSelectionPlaylist() throws InterruptedException {

        // Step 1: Select Advanced setup
        testBase.getLandingPage().enterTheGame();
        testBase.getInputSelectionPage().selectAdvancedSetup();
        testBase.getAdvancedConnectionPage().goToMainMenu();

        // Step 2: Ensure song language is selected
        testBase.getMainMenuPage().goToSongList();
        testBase.getSongLanguagesPage().ensureSongLanguageIsSelected(engLanguage);
        testBase.getSongLanguagesPage().continueAndGoToSongList();

        // Step 3: Search and open the song - ensure it`s not added to Selection playlist
        testBase.getSongListPage().expectPlaylistToBeSelected(selectionPlaylist);
        Assertions.assertFalse(testBase.getSongListPage().isSongOnTheList(almCompSongID), "Song should not be visible on the playlist yet");
        testBase.getSongListPage().searchSong(almCompSongID);
        testBase.getSongListPage().openPreviewForSong(almCompSongID);
        testBase.getSongPreviewPage().goNext();

        // Step 4: Play the song and in above 80% of the song length - exit the game
        testBase.getSongPreviewPage().goToPlayTheSong();
        Thread.sleep(220 * 1000);
        testBase.getGamePage().exitSong();

        // Step 5: Skip to Song List
        Assertions.assertTrue(testBase.getPostGameResultsPage().skipScoreElement().isDisplayed(), "Element should be visible to skip scores");
        testBase.getPostGameResultsPage().skipScoresAnimation();
        testBase.getPostGameResultsPage().goToHighScoresStep();
        testBase.getPostGameHighScoresPage().goToSongListPage();

        // Step 6: A song that is more than 80% complete, should be added to the Selection playlist
        testBase.getSongListPage().goToPlaylist(selectionPlaylist);
        Assertions.assertTrue(testBase.getSongListPage().isSongOnTheList(almCompSongID), "Song should be added to Selection playlist after above 80% complete");
        testBase.getSongListPage().expectSongToBeMarkedAsPlayedToday(almCompSongID);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

}
