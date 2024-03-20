package Page_Objects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AdvancedConnectionPage {

    private final WebDriver driver;
    private WebDriverWait wait;

    public AdvancedConnectionPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    }

    public WebElement singSongButton() {
        By singButtonSelector = By.cssSelector("[data-test='save-button']");
        wait.until(ExpectedConditions.elementToBeClickable(singButtonSelector));

        return driver.findElement(singButtonSelector);
    }

    public void goToMainMenu() {
        singSongButton().click();
    }

}
