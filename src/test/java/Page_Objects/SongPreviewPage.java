package Page_Objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SongPreviewPage {

    private WebDriver driver;

    @FindBy(css = "[data-test='next-step-button']")
    private WebElement nextButton;

    @FindBy(css = "[data-test='play-song-button']")
    private WebElement playButton;

    public SongPreviewPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void goNext() {
        nextButton.click();
    }

    public void goToPlayTheSong() {
        playButton.click();
    }

}
