package Page_Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SongListPage {

    private final WebDriver driver;
    private Actions actions;

    public SongListPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        actions = new Actions(driver);
    }

    public List<WebElement> getSongElement(String songID) {
        return driver.findElements(By.cssSelector("[data-test='song-" + songID + "']"));
    }

    public boolean isSongOnTheList(String songID) {
        return !getSongElement(songID).isEmpty();
    }

    public WebElement getSongPreviewElement() {
        return driver.findElement(By.cssSelector("[data-test='song-preview']"));
    }

    public String getSelectedSongID() {
        return getSongPreviewElement().getAttribute("data-song");
    }

    public WebElement getFullscreenElement() {
        return driver.findElement(By.cssSelector("[data-test='toggle-fullscreen']"));
    }

    public boolean isFullscreenOff() {
        WebElement fullscreen = getFullscreenElement().findElement(By.cssSelector("[data-testid='FullscreenIcon']"));
        return fullscreen.isDisplayed();
    }

    public boolean isFullscreenOn() {
        WebElement fullscreen = getFullscreenElement().findElement(By.cssSelector("[data-testid='FullscreenExitIcon']"));
        return fullscreen.isDisplayed();
    }

    public void goBackToMainMenu() {
        actions.sendKeys(Keys.BACK_SPACE).perform();
    }
}
