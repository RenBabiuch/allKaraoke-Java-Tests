package tests;

import Page_Objects.TestBase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class HidingAndRestoringSongsTest {

    private WebDriver driver;
    private TestBase testBase;

    String songName = "Bohemian Rhapsody";
    String songID = "queen-bohemian-rhapsody";

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        testBase = new TestBase(driver);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://allkaraoke.party");
    }

    @Test
    public void hidingAndRestoringSongs() {
        testBase.getLandingPage().enterTheGame();
        testBase.getInputSelectionPage().selectSmartphones();
        testBase.getSmartphonesConnectionPage().saveAndGoToMainMenuPage();
        testBase.getMainMenuPage().goToManageSongs();

        // hiding song
        testBase.getManageSongsPage().goToEditSongs();
        testBase.getEditSongsPage().hideSong(songName, songID);
        testBase.getEditSongsPage().goToMainMenu();
        testBase.getMainMenuPage().goToSongList();
        testBase.getSongLanguagesPage().continueAndGoToSongList();
        Assertions.assertFalse(testBase.getSongListPage().isSongOnTheList(songID), "Ooops, this song is still enabled by the user's songs list :(");

        driver.navigate().back();
        driver.navigate().back();
        testBase.getMainMenuPage().goToManageSongs();

        // restoring song
        testBase.getManageSongsPage().goToEditSongs();
        testBase.getEditSongsPage().restoreSong(songName, songID);
        testBase.getEditSongsPage().goToMainMenu();
        testBase.getMainMenuPage().goToSongList();
        Assertions.assertTrue(testBase.getSongListPage().isSongOnTheList(songID), "Ooops, this song is still hidden by the user :(");
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
