package Page_Objects;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.awaitility.Awaitility.await;


import java.time.Duration;

public class GamePage {

    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;

    @FindBy(css = "[data-test='button-restart-song']")
    private WebElement restartButton;

    public GamePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        actions = new Actions(driver);
    }

    public WebElement playerScoreElement(int playerNumber) {
        By playerNumSelector = By.cssSelector("[data-test='player-" + playerNumber + "-score']");

        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(playerNumSelector));

        return driver.findElement(playerNumSelector);
    }

    public WebElement playersCoopScoreElement() {
        return driver.findElement(By.cssSelector("[data-test='players-score']"));
    }

    public String getCurrentPlayerScore(int playerNumber) {
        return playerScoreElement(playerNumber).getAttribute("data-score");
    }

    public String getCurrentPlayersCoopScore() {
        return playersCoopScoreElement().getAttribute("data-score");
    }

    public void waitForPlayersScoreToBeGreaterThan(double expectedNumber) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-test='players-score']")));

        await().atMost(Duration.ofSeconds(15)).until(() -> {
            String p1score = getCurrentPlayersCoopScore();
            double p1scoreNum = Double.parseDouble(p1score);

            if (p1scoreNum > expectedNumber) {
                return true;
            }
            return false;
        });
    }

    public void expectPlayersCoopScoreToBeRestarted() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-test='players-score']")));

        int expectedScoreValue = 0;

        String p1score = getCurrentPlayersCoopScore();
        int p1scoreNum = Integer.parseInt(p1score);

        Assertions.assertEquals(expectedScoreValue, p1scoreNum);
    }


    public void goToPauseMenu() {
        actions.sendKeys(Keys.BACK_SPACE).perform();
    }

    public void restartSong() {
        goToPauseMenu();
        restartButton.click();
    }

    public WebElement lyricsContainerElement(int playerNumber) {
        By lyricsContainerSelector = By.cssSelector("[data-test='lyrics-container-player-" + playerNumber + "']");

        wait.until(ExpectedConditions.visibilityOfElementLocated(lyricsContainerSelector));
        return driver.findElement(lyricsContainerSelector);
    }

    public boolean isPlayerLyricsVisible(int playerNumber) {
        return lyricsContainerElement(playerNumber).isDisplayed();
    }

    public void skipIntro() {
        By skipIntroSelector = By.cssSelector("[data-test='skip-intro-info']");

        wait.until(ExpectedConditions.visibilityOfElementLocated(skipIntroSelector));
        actions.sendKeys(Keys.ENTER).perform();
    }

    public void skipOutro() {
        By skipOutroSelector = By.cssSelector("[data-test='skip-outro-info']");

        wait = new WebDriverWait(driver, Duration.ofSeconds(300));
        wait.until(ExpectedConditions.visibilityOfElementLocated(skipOutroSelector));
        actions.sendKeys(Keys.ENTER).perform();
    }
}