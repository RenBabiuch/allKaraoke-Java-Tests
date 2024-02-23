package Page_Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainMenuPage {

    private final WebDriver driver;
    private WebDriverWait wait;

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

    @FindBy(css = "[data-test='toggle-fullscreen']")
    private WebElement fullscreenElement;

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

    public void toggleFullscreen() {
        // StaleElementReferenceException - wait is using to give more time for finding that element, because sometimes
        // selenium can not locate it for click

        try {
            wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(fullscreenElement));
            fullscreenElement.click();

        } catch (StaleElementReferenceException e) {
            // The fullscreenElement became `too old`, so I need to localize it again
            WebElement refreshFullscreen = driver.findElement(By.cssSelector("[data-test='toggle-fullscreen']"));
            refreshFullscreen.click();
        }
    }

    public boolean isFullscreenOff() {
        return fullscreenElement.findElement(By.cssSelector("[data-testid='FullscreenIcon']")).isDisplayed();
    }

}
