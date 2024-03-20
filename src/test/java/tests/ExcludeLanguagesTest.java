package tests;

import Page_Objects.TestBase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;

public class ExcludeLanguagesTest {

    private ChromeDriver driver;
    private TestBase testBase;

    String portugueseLang = "Portuguese";
    String englishLang = "English";
    String portSong = "os-quatro-e-meia-ola-solidao";
    String engSong = "bob-marley-the-wailers-three-little-birds";
    String portEngSong = "nelly-furtado-forca";

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        testBase = new TestBase(driver);
        testBase.setUp();
    }

    @Test
    public void excludingSongLanguages() {

        // Step 1: Select Advanced setup
        testBase.getLandingPage().enterTheGame();
        testBase.getInputSelectionPage().selectAdvancedSetup();
        testBase.getAdvancedConnectionPage().goToMainMenu();
        testBase.getMainMenuPage().goToSongList();

        // Step 2: Exclude Portuguese language
        testBase.getSongLanguagesPage().unselectLanguage(portugueseLang);
        testBase.getSongLanguagesPage().ensureSongLanguageIsSelected(englishLang);
        testBase.getSongLanguagesPage().continueAndGoToSongList();
        Assertions.assertFalse(testBase.getSongListPage().isSongOnTheList(portSong));
        Assertions.assertTrue(testBase.getSongListPage().isSongOnTheList(engSong));
        Assertions.assertTrue(testBase.getSongListPage().isSongOnTheList(portEngSong));

        // Step 3: Go back to Song Languages - exclude English language
        testBase.getSongListPage().goBackToMainMenu();
        testBase.getMainMenuPage().goToManageSongs();
        testBase.getManageSongsPage().goToSelectLanguages();

        testBase.getSongLanguagesPage().unselectLanguage(englishLang);
        testBase.getSongLanguagesPage().ensureSongLanguageIsSelected(portugueseLang);
        testBase.getSongLanguagesPage().continueAndGoToSongList();
        testBase.getMainMenuPage().goToSongList();
        Assertions.assertFalse(testBase.getSongListPage().isSongOnTheList(engSong));
        Assertions.assertTrue(testBase.getSongListPage().isSongOnTheList(portSong));
        Assertions.assertTrue(testBase.getSongListPage().isSongOnTheList(portEngSong));

        // Step 4: Go back to Song Languages - include both languages
        testBase.getSongListPage().goBackToMainMenu();
        testBase.getMainMenuPage().goToManageSongs();
        testBase.getManageSongsPage().goToSelectLanguages();

        testBase.getSongLanguagesPage().ensureSongLanguageIsSelected(englishLang);
        testBase.getSongLanguagesPage().ensureSongLanguageIsSelected(portugueseLang);
        testBase.getSongLanguagesPage().continueAndGoToSongList();
        testBase.getMainMenuPage().goToSongList();
        Assertions.assertTrue(testBase.getSongListPage().isSongOnTheList(engSong));
        Assertions.assertTrue(testBase.getSongListPage().isSongOnTheList(portSong));
        Assertions.assertTrue(testBase.getSongListPage().isSongOnTheList(portEngSong));

        // Step 5: When exclude all languages, alert should be visible
        testBase.getSongListPage().goBackToMainMenu();
        testBase.getMainMenuPage().goToManageSongs();
        testBase.getManageSongsPage().goToSelectLanguages();

        testBase.getSongLanguagesPage().unselectLanguage(englishLang);
        testBase.getSongLanguagesPage().unselectLanguage(portugueseLang);
        Assertions.assertTrue(testBase.getSongLanguagesPage().isAllLanguagesExcludedAlertVisible());
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
