package Page_Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainMenuPage {

    private final WebDriver driver;

    @FindBy(css = "[data-test='sing-a-song']")
    private WebElement singSongButton;

    @FindBy(css = "[data-test='select-input']")
    private WebElement setupMicrophonesButton;

    @FindBy(css = "[data-test='settings']")
    private WebElement gameSettingsButton;

    @FindBy(css = "[data-test='jukebox']")
    private WebElement jukeboxButton;

    @FindBy(css = "[data-test='manage-songs']")
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

    public void goToJukebox() {
        jukeboxButton.click();
    }

    public WebElement getHelpContainer() {
        return driver.findElement(By.cssSelector("[data-test='help-container']"));
    }

    public WebElement getToggleHelp() {
        return driver.findElement(By.xpath("//*[@data-test='toggle-help']"));
    }
}
