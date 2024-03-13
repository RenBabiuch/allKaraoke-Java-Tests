package Page_Objects;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
        return driver.findElement(By.cssSelector("[data-test='player-" + playerNumber + "-score']"));
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

}
