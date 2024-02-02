package Page_Objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SongLanguagesPage {

    private final WebDriver driver;

    @FindBy(css = "button[data-test='close-exclude-languages']")
    private WebElement continueButton;

    public SongLanguagesPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void continueAndGoToSongList() {
        continueButton.click();
    }
}
