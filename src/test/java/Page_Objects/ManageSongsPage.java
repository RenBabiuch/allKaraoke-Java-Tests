package Page_Objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ManageSongsPage {

    private final WebDriver driver;


    @FindBy(css = "button[data-test='exclude-languages']")
    private WebElement selectSongLanguagesButton;

    @FindBy(css = "button[data-test='edit-songs']")
    private WebElement editSongsButton;

    @FindBy(css = "button[data-test='convert-song']")
    private WebElement convertUltrastarButton;

    @FindBy(css = "button[data-test='back-button']")
    private WebElement returnToMainMenuButton;


    public ManageSongsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void goToSelectLanguages() {
        selectSongLanguagesButton.click();
    }

    public void goToEditSongs() {
        editSongsButton.click();
    }

    public void goToConvertUltrastarTxt() {
        convertUltrastarButton.click();
    }

    public void goBackToMainMenu() {
        returnToMainMenuButton.click();
    }

}
