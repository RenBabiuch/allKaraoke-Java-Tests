package Page_Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PostGameHighScoresPage {

    private WebDriver driver;

    @FindBy(css = "[data-test='play-next-song-button']")
    private WebElement selectSongButton;

    public PostGameHighScoresPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public WebElement playerNameInput() {
        return driver.findElement(By.cssSelector("[data-test='input-edit-highscore']"));
    }

    public void updateHighestScorePlayerName(String newName) {
        playerNameInput().sendKeys(newName);
    }

    public void goToSongListPage() {
        selectSongButton.click();
    }
}