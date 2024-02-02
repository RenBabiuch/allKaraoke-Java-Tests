package Page_Objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage {

    private final WebDriver driver;

    @FindBy(css = "button[data-test='enter-the-game']")
    private WebElement enterTheGameButton;

    @FindBy(css = "button[data-test='join-existing-game']")
    private WebElement joinExistGameButton;

    @FindBy(css = "a[href = '/allkaraoke']")
    private WebElement gitHubRepoLink;

    @FindBy(css = "button[data-test='quick-connect-phone']")
    private WebElement quickConnectPhoneButton;

    public LandingPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterTheGame() {
        enterTheGameButton.click();
    }

    public void goToGitHubRepository() {
        gitHubRepoLink.click();
    }

    public void connectPhoneWithTheGame() {
        quickConnectPhoneButton.click();
    }

}
