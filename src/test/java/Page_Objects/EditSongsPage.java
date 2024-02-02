package Page_Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EditSongsPage {

    private final WebDriver driver;

    @FindBy(css = "input[placeholder='Search']")
    private WebElement searchInput;

    @FindBy(css = "a[data-test='main-menu-link']")
    private WebElement mainMenuLink;


    public EditSongsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void searchSong(String songName) {
        searchInput.sendKeys(songName);
    }

    public WebElement getHideSongButton(String songID) {
        return driver.findElement(By.cssSelector("[data-test='hide-song'][data-song='" + songID + "']"));
    }

    public void hideSong(String songName, String songID) {
        searchSong(songName);
        getHideSongButton(songID).click();
    }

    public WebElement getRestoreSongButton(String songID) {
        return driver.findElement(By.cssSelector("[data-test='restore-song'][data-song='" + songID + "']"));
    }

    public void restoreSong(String songName, String songID) {
        searchSong(songName);
        getRestoreSongButton(songID).click();
    }

    public void goToMainMenu() {
        mainMenuLink.click();
    }

}
