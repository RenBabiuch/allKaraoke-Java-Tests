package Page_Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class JukeboxPage {

    private final WebDriver driver;


    public JukeboxPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public WebElement getJukeboxElement() {
        return driver.findElement(By.cssSelector("[data-test='jukebox-container']"));
    }

    public String getCurrentSong() {
        return getJukeboxElement().getAttribute("data-song");
    }

    public WebElement getSkipButton() {
        return driver.findElement(By.cssSelector("[data-test='skip-button']"));
    }

    public void goToSkipSong() {
        getSkipButton().click();
    }

    public WebElement getSingButton() {
        return driver.findElement(By.cssSelector("[data-test='sing-button']"));
    }

    public void goToSingSong() {
        getSingButton().click();
    }

}
