package Page_Objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LandingPage {

    private final WebDriver driver;
    private WebDriverWait wait;

    @FindBy(css = "[data-test='enter-the-game']")
    private WebElement enterTheGameButton;

    @FindBy(css = "[data-test='join-existing-game']")
    private WebElement joinExistGameButton;

    @FindBy(css = "a[href = '/allkaraoke']")
    private WebElement gitHubRepoLink;

    @FindBy(css = "[data-test='quick-connect-phone']")
    private WebElement quickConnectPhoneButton;

    public LandingPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterTheGame() {
        // StaleElementReferenceException - wait is using to give more time for finding that element, because sometimes
        // selenium can not locate it for click
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // The fullscreenElement became `too old`, so I need to localize it again
        WebElement enterClickable = wait.until(ExpectedConditions.elementToBeClickable(enterTheGameButton));
        enterClickable.click();

        //ElementClickInterceptedException - this is necessary, because the page doesn't have enough time
        //to make buttons on next page visible for clicking. Otherwise, another element would get the click
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    public void goToGitHubRepository() {
        gitHubRepoLink.click();
    }

    public void connectPhoneWithTheGame() {
        quickConnectPhoneButton.click();
    }

}
