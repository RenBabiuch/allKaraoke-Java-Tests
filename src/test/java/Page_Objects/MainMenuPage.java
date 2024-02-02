package Page_Objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainMenuPage {

    private final WebDriver driver;

    @FindBy(css = "button[data-test='sing-a-song']")
    private WebElement singSongButton;

    @FindBy(css = "button[data-test='select-input']")
    private WebElement setupMicrophonesButton;

    @FindBy(css = "button[data-test='settings']")
    private WebElement gameSettingsButton;

    @FindBy(css = "button[data-test='jukebox']")
    private WebElement jukeboxButton;

    @FindBy(css = "button[data-test='manage-songs']")
    private WebElement manageSongsButton;

    public MainMenuPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void goToSongList() {
        singSongButton.click();
    }

    public void goToManageSongs() {
        manageSongsButton.click();
    }

}
