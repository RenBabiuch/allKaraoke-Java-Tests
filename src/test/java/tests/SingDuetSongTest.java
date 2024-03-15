package tests;

import Page_Objects.TestBase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class SingDuetSongTest {

    private ChromeDriver driver;
    private TestBase testBase;
    private Actions actions;

    String songID = "mana-y-prince-royce-el-verdadero-amor-perdona";
    String songLanguage = "Spanish";
    String playlistName = "Duets";
    String groupName = "M";
    String difficultyLevel = "Hard";
    String gameMode = "Duel";
    String player1Name = "all";
    String player2Name = "Karaoke";
    String updatedName = "I won - heheheh";
    int player1 = 0;
    int player2 = 1;


    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        testBase = new TestBase(driver);
        actions = new Actions(driver);
        testBase.setUp();
    }

    @Test
    public void singingDuetSong() {
        testBase.getLandingPage().enterTheGame();

        // Step 1: Select Advanced setup
        testBase.getInputSelectionPage().selectAdvancedSetup();
        testBase.getAdvancedConnectionPage().saveAndGoToSing();

        // Step 2: Ensure song language is selected
        testBase.getMainMenuPage().goToSongList();
        testBase.getSongLanguagesPage().ensureSongLanguageIsSelected(songLanguage);
        testBase.getSongLanguagesPage().continueAndGoToSongList();

        // Step 3: Go to playlist and open preview the song
        testBase.getFullscreenPage().isFullscreenOn();
        testBase.getSongListPage().goToPlaylist(playlistName);
        testBase.getSongListPage().expectPlaylistToBeSelected(playlistName);
        testBase.getSongListPage().goToGroupName(groupName);
        Assertions.assertTrue(testBase.getSongListPage().isSongMarkedWithDuetIcon(songID));
        testBase.getSongListPage().openPreviewForSong(songID);

        // Step 4: Set difficulty level and game mode
        testBase.getSongPreviewPage().toggleDifficultyLevel();
        testBase.getSongPreviewPage().expectGameDifficultyLevelToBe(difficultyLevel);

        testBase.getSongPreviewPage().toggleGameMode();
        testBase.getSongPreviewPage().expectGameModeToBe(gameMode);

        // Step 5: Enter players names
        testBase.getSongPreviewPage().goNext();
        Assertions.assertTrue(testBase.getSongPreviewPage().playerNameInput(player1).isDisplayed());
        testBase.getSongPreviewPage().enterPlayerName(player1, player1Name);
        Assertions.assertTrue(testBase.getSongPreviewPage().playerNameInput(player2).isDisplayed());
        testBase.getSongPreviewPage().enterPlayerName(player2, player2Name);

        // Step 6: Play the song - scores and lyrics should be visible for players
        testBase.getSongPreviewPage().goToPlayTheSong();
        Assertions.assertTrue(testBase.getGamePage().playerScoreElement(player1).isDisplayed());
        Assertions.assertTrue(testBase.getGamePage().playerScoreElement(player2).isDisplayed());

        Assertions.assertTrue(testBase.getGamePage().isPlayerLyricsVisible(player1));
        Assertions.assertTrue(testBase.getGamePage().isPlayerLyricsVisible(player2));
        testBase.getGamePage().skipOutro();

        // Step 7: After game, players names and scores should be displayed in Results
        Assertions.assertTrue(testBase.getPostGameResultsPage().skipScoreElement().isDisplayed());
        testBase.getPostGameResultsPage().expectPlayerNameToBeDisplayed(player1, player1Name);
        testBase.getPostGameResultsPage().expectPlayerNameToBeDisplayed(player2, player2Name);
        Assertions.assertTrue(testBase.getPostGameResultsPage().playerScoreElement(player1).isDisplayed());
        Assertions.assertTrue(testBase.getPostGameResultsPage().playerScoreElement(player2).isDisplayed());

        // Step 8: Skip to High Scores and update one player's name
        testBase.getPostGameResultsPage().skipScoresAnimation();
        testBase.getPostGameResultsPage().goToHighScoresStep();
        testBase.getPostGameHighScoresPage().updateHighestScorePlayerName(updatedName);

        // Step 9: Choose another song from Song List
        testBase.getPostGameHighScoresPage().goToSongListPage();
        actions.sendKeys(Keys.ARROW_UP).perform();
        testBase.getSongListPage().approveSongByKeyboard();
        testBase.getSongPreviewPage().goNext();

        // Step 10: Players names should be already prefilled and updated name visible on recent player list
        testBase.getSongPreviewPage().expectEnteredPlayerNameToBePrefilledWith(player1, player1Name);
        testBase.getSongPreviewPage().expectEnteredPlayerNameToBePrefilledWith(player2, player2Name);
        Assertions.assertTrue(testBase.getSongPreviewPage().isTheNameOnTheRecentPlayerList(updatedName));
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
