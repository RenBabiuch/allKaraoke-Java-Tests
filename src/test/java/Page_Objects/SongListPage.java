package Page_Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SongListPage {

    private final WebDriver driver;

    public SongListPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public List<WebElement> getSongElement(String songID) {
        return driver.findElements(By.cssSelector("div[data-test='song-" + songID + "']"));
    }

    public boolean isSongOnTheList(String songID) {
        return !getSongElement(songID).isEmpty();
    }

    public WebElement getFullscreenIcon() {
        return driver.findElement(By.cssSelector("svg[data-testid='FullscreenExitIcon']"));
    }

}
