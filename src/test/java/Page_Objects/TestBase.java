package Page_Objects;

import org.openqa.selenium.WebDriver;

public class TestBase {

    private final WebDriver driver;
    private LandingPage landingPage;
    private InputSelectionPage inputSelectionPage;
    private MainMenuPage mainMenuPage;
    private ManageSongsPage manageSongsPage;
    private EditSongsPage editSongsPage;
    private SongListPage songListPage;
    private SmartphonesConnectionPage smartphonesConnectionPage;
    private SongLanguagesPage songLanguagesPage;

    public TestBase(WebDriver driver) {
        this.driver = driver;
        initializePageObjects();
    }

    private void initializePageObjects() {
        landingPage = new LandingPage(driver);
        inputSelectionPage = new InputSelectionPage(driver);
        mainMenuPage = new MainMenuPage(driver);
        manageSongsPage = new ManageSongsPage(driver);
        editSongsPage = new EditSongsPage(driver);
        songListPage = new SongListPage(driver);
        songLanguagesPage = new SongLanguagesPage(driver);
        smartphonesConnectionPage = new SmartphonesConnectionPage(driver);
    }

    public LandingPage getLandingPage() {
        return landingPage;
    }

    public InputSelectionPage getInputSelectionPage() {
        return inputSelectionPage;
    }

    public MainMenuPage getMainMenuPage() {
        return mainMenuPage;
    }

    public ManageSongsPage getManageSongsPage() {
        return manageSongsPage;
    }

    public EditSongsPage getEditSongsPage() {
        return editSongsPage;
    }

    public SongListPage getSongListPage() {
        return songListPage;
    }

    public SongLanguagesPage getSongLanguagesPage() {
        return songLanguagesPage;
    }

    public SmartphonesConnectionPage getSmartphonesConnectionPage() {
        return smartphonesConnectionPage;
    }

}
