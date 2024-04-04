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
    String compSongID = "xavier-rudd-stoney-creek";
    String almSongID = "electric-light-orchestra-bluebird";
    String uncompSongID = "don-mclean-american-pie";
    String engLanguage = "English";
    String englishPlaylist = "English";
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
        Assertions.assertFalse(testBase.getSongListPage().isSongOnTheList(almSongID), "Song should not be visible on the playlist yet");
        testBase.getSongListPage().searchSong(almSongID);
        testBase.getSongListPage().openPreviewForSong(almSongID);
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
        Assertions.assertTrue(testBase.getSongListPage().isSongOnTheList(almSongID), "Song should be added to Selection playlist after above 80% complete");
        testBase.getSongListPage().expectSongToBeMarkedAsPlayedToday(almSongID);
    }

    @Test
    public void uncompletedSongIsNotAddedToSelectionPlaylist() {

        // Step 1: Select Advanced setup
        testBase.getLandingPage().enterTheGame();
        testBase.getInputSelectionPage().selectAdvancedSetup();
        testBase.getAdvancedConnectionPage().goToMainMenu();

        // Step 2: Ensure song language is selected
        testBase.getMainMenuPage().goToManageSongs();
        testBase.getManageSongsPage().goToSelectLanguages();
        testBase.getSongLanguagesPage().ensureSongLanguageIsSelected(engLanguage);
        testBase.getSongLanguagesPage().goBackToMainMenu();

        // Step 3: Pick up the song in language playlist - ensure song is not visible in Selection playlist
        testBase.getMainMenuPage().goToSongList();
        testBase.getSongListPage().expectPlaylistToBeSelected(selectionPlaylist);
        Assertions.assertFalse(testBase.getSongListPage().isSongOnTheList(uncompSongID), "Song should not be visible on the playlist yet");
        testBase.getSongListPage().goToPlaylist(englishPlaylist);
        testBase.getSongListPage().openPreviewForSong(uncompSongID);

        // Step 4: Toggle game mode to `Cooperation` and play the song
        testBase.getSongPreviewPage().toggleGameMode();
        testBase.getSongPreviewPage().toggleGameMode();
        testBase.getSongPreviewPage().goNext();
        testBase.getSongPreviewPage().goToPlayTheSong();

        // Step 5: After reach the expected score - exit the game
        testBase.getGamePage().waitForPlayersScoreToBeGreaterThan(100);
        testBase.getGamePage().exitSong();

        // Step 6: Skip to Song List
        testBase.getPostGameResultsPage().skipScoresAnimation();
        testBase.getPostGameResultsPage().goToHighScoresStep();
        testBase.getPostGameHighScoresPage().goToSongListPage();

        // Step 7: The song that is less than 80% complete, should not be added to the Selection playlist
        testBase.getSongListPage().goToPlaylist(selectionPlaylist);
        Assertions.assertFalse(testBase.getSongListPage().isSongOnTheList(uncompSongID), "Song with less than 80% complete should not be added to Selection playlist");
        testBase.getSongListPage().goToPlaylist(englishPlaylist);
        testBase.getSongListPage().expectSongToBeMarkedAsPlayedToday(uncompSongID);
    }

    @Test
    public void changeSongPopularityIndicator() {

        // Step 1: Select Advanced setup
        testBase.getLandingPage().enterTheGame();
        testBase.getInputSelectionPage().selectAdvancedSetup();
        testBase.getAdvancedConnectionPage().goToMainMenu();

        // Step 2: Ensure song language is selected
        testBase.getMainMenuPage().goToSongList();
        testBase.getSongLanguagesPage().ensureSongLanguageIsSelected(engLanguage);
        testBase.getSongLanguagesPage().continueAndGoToSongList();

        // Step 3: Pick up and play random popular song from Selection playlist
        testBase.getSongListPage().goToPlaylist(selectionPlaylist);
        testBase.getSongListPage().doesPlaylistContainSongsMarkedAsPopular();

        String popSong = testBase.getSongListPage().getSelectedSongID();

        testBase.getSongListPage().approveSongByKeyboard();
        testBase.getSongPreviewPage().goNext();
        testBase.getSongPreviewPage().goToPlayTheSong();
        Assertions.assertTrue(testBase.getGamePage().lyricsContainerElement(0).isDisplayed(), "The song lyrics for player should be visible");
        testBase.getGamePage().skipOutro();

        // Step 4: Skip to the Song list
        testBase.getPostGameResultsPage().skipScoresAnimation();
        testBase.getPostGameResultsPage().goToHighScoresStep();
        testBase.getPostGameHighScoresPage().goToSongListPage();

        // Step 5: The song indicator should change from `popular` to `Played today`
        testBase.getSongListPage().expectPlaylistToBeSelected(selectionPlaylist);
        testBase.getSongListPage().expectSongToBeMarkedAsPlayedToday(popSong);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
