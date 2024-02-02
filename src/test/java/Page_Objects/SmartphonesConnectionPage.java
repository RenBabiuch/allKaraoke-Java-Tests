package Page_Objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SmartphonesConnectionPage {

    private final WebDriver driver;

    @FindBy(css = "button[data-test='save-button']")
    private WebElement singSongButton;

    @FindBy(css = "button[data-test='back-button']")
    private WebElement changeInputTypeButton;

    @FindBy(css = "button[contains(text, 'Copy')]")
    private WebElement copyButton;


    public SmartphonesConnectionPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void saveAndGoToMainMenuPage() {
        singSongButton.click();
    }

    public void changeInputType() {
        changeInputTypeButton.click();
    }

    public void copyConnectionLink() {
        copyButton.click();
    }
}
