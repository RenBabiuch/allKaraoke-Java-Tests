package Page_Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SongLanguagesPage {

    private final WebDriver driver;

    @FindBy(css = "[data-test='close-exclude-languages']")
    private WebElement continueButton;

    public SongLanguagesPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void continueAndGoToSongList() {
        continueButton.click();
    }

    public WebElement getLanguageElement(String language) {
        return driver.findElement(By.cssSelector("[data-test=lang-" + language + "]"));
    }

    public WebElement getSongLanguageCheckbox(String language) {
        return getLanguageElement(language).findElement(By.cssSelector("svg"));
    }

    public void ensureSongLanguageIsSelected(String language) {
        if(getSongLanguageCheckbox(language).getAttribute("data-testid").equals("CheckBoxOutlineBlankIcon")) {
            getSongLanguageCheckbox(language).click();
        }
    }

    public void ensureAllSongLanguagesAreSelected() {

        List<WebElement> languages = driver.findElements(By.cssSelector("[data-test^='lang-'] svg"));

        for (WebElement language : languages) {
            if (language.getAttribute("data-testid").equals("CheckBoxOutlineBlankIcon")) {
                language.click();
            }
        }
    }
}
