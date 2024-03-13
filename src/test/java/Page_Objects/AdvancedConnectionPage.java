package Page_Objects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AdvancedConnectionPage {

    private final WebDriver driver;
    private WebDriverWait wait;

    @FindBy(css = "[data-test='save-button']")
    private WebElement singSongButton;

    public AdvancedConnectionPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    }

    public void saveAndGoToSing() {
        wait.until(ExpectedConditions.elementToBeClickable(singSongButton));
        singSongButton.click();
    }

}
