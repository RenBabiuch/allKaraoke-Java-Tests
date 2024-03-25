package Page_Objects;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PostGameResultsPage {

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(css = "[data-test='highscores-button']")
    private WebElement nextButton;

    public PostGameResultsPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);

    }

    public WebElement skipScoreElement() {
        By skipButtonSelector = By.cssSelector("[data-test='skip-animation-button']");

        wait.until(ExpectedConditions.elementToBeClickable(skipButtonSelector));
        return driver.findElement(skipButtonSelector);
    }

    public WebElement playerNameElement(int playerNumber) {
        return driver.findElement(By.cssSelector("[data-test='player-" + playerNumber + "-name']"));
    }

    public void expectPlayerNameToBeDisplayed(int playerNumber, String playerName) {
        String playerNameText = playerNameElement(playerNumber).getText();

        Assertions.assertEquals(playerName, playerNameText);
    }

    public WebElement playerScoreElement(int playerNumber) {
        return driver.findElement(By.cssSelector("[data-test='player-" + playerNumber + "-score']"));
    }

    public void skipScoresAnimation() {
        skipScoreElement().click();
    }

    public void goToHighScoresStep() {
        nextButton.click();
    }
}