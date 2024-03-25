package Page_Objects;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SongPreviewPage {

    private WebDriver driver;

    @FindBy(css = "[data-test='next-step-button']")
    private WebElement nextButton;

    @FindBy(css = "[data-test='play-song-button']")
    private WebElement playButton;

    @FindBy(css = "[data-test='difficulty-setting']")
    private WebElement difficultyModeElement;

    @FindBy(css = "[data-test='game-mode-setting']")
    private WebElement gameModeElement;

    public SongPreviewPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void goNext() {
        nextButton.click();
    }

    public void goToPlayTheSong() {
        playButton.click();
    }

    public void toggleDifficultyLevel() {
        difficultyModeElement.click();
    }

    public void expectGameDifficultyLevelToBe(String level) {
        String difficultyLevelAttribute = difficultyModeElement.getAttribute("data-test-value");

        Assertions.assertEquals(level, difficultyLevelAttribute);
    }

    public void toggleGameMode() {
        gameModeElement.click();
    }

    public void expectGameModeToBe(String modeName) {
        String gameModeAttribute = gameModeElement.getAttribute("data-test-value");

        Assertions.assertEquals(modeName, gameModeAttribute);
    }

    public WebElement playerNameInput(int playerNumber) {
        return driver.findElement(By.cssSelector("[data-test='player-" + playerNumber + "-name']"));
    }

    public void enterPlayerName(int playerNumber, String playerName) {
        playerNameInput(playerNumber).sendKeys(playerName);
    }

    public void expectEnteredPlayerNameToBePrefilledWith(int playerNumber, String playerName) {
        String playerNameAttribute = playerNameInput(playerNumber).getAttribute("placeholder");

        Assertions.assertEquals(playerName, playerNameAttribute);
    }

    public boolean isTheNameOnTheRecentPlayerList(String expectedName) {
        playerNameInput(0).click();
        List<WebElement> allNamesOnTheList = driver.findElements(By.cssSelector("[role='listbox'] div"));

        for (WebElement name : allNamesOnTheList) {
            String nameText = name.getText();

            if(nameText.equals(expectedName)) {
                return true;
            }
        }
    return true;
    }
}
