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

public class FullscreenPage {

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(css = "[data-test='toggle-fullscreen']")
    private WebElement fullscreenElement;

    public FullscreenPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void toggleFullscreen() {
        // StaleElementReferenceException - wait is using to give more time for finding that element, because sometimes
        // selenium can not locate it for click

        try {
            wait.until(ExpectedConditions.visibilityOf(fullscreenElement));
            fullscreenElement.click();

        } catch (StaleElementReferenceException e) {
            // The fullscreenElement became `too old`, so I need to localize it again
            WebElement refreshFullscreen = driver.findElement(By.cssSelector("[data-test='toggle-fullscreen']"));
            refreshFullscreen.click();
        }
    }

    public boolean isFullscreenOff() {
        // StaleElementReferenceException - wait is using here to give more time for finding element, to be able to
        // do assertion on it

        By fullscreenOffIcon = By.cssSelector("[data-testid='FullscreenIcon']");

        try{
            return fullscreenElement.findElement(fullscreenOffIcon).isDisplayed();

        } catch (StaleElementReferenceException e) {
            // The fullscreenElement became `too old`, so I need to localize it again
            WebElement refreshFullscreenElem = driver.findElement(By.cssSelector("[data-test='toggle-fullscreen']"));
            wait.until(ExpectedConditions.elementToBeClickable(refreshFullscreenElem));
            return refreshFullscreenElem.findElement(fullscreenOffIcon).isDisplayed();
        }
    }

    public boolean isFullscreenOn() {
        WebElement fullscreenOn = fullscreenElement.findElement(By.cssSelector("[data-testid='FullscreenExitIcon']"));
        return fullscreenOn.isDisplayed();
    }

}
