package Page_Objects;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.v121.network.Network;


import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class TestBase {

    private ChromeDriver driver;
    private LandingPage landingPage;
    private InputSelectionPage inputSelectionPage;
    private MainMenuPage mainMenuPage;
    private ManageSongsPage manageSongsPage;
    private EditSongsPage editSongsPage;
    private SongListPage songListPage;
    private SmartphonesConnectionPage smartphonesConnectionPage;
    private SongLanguagesPage songLanguagesPage;

    private JukeboxPage jukeboxPage;

    public TestBase(ChromeDriver driver) {
        this.driver = driver;
        initializePageObjects();
    }

    public void setUp() {
        driver.getDevTools().createSession();
        driver.getDevTools().send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
        driver.getDevTools().send(Network.setBlockedURLs(List.of(
                "https://backend.allkaraoke.party/posthog/*")));

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        driver.get("https://allkaraoke.party");
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
        jukeboxPage = new JukeboxPage(driver);
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

    public JukeboxPage getJukeboxPage() {
        return jukeboxPage;
    }

}
