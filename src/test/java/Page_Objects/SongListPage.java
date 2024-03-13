package Page_Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SongListPage {

    private final WebDriver driver;
    private Actions actions;

    public SongListPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        actions = new Actions(driver);
    }

    public List<WebElement> getSongElement(String songID) {
        return driver.findElements(By.cssSelector("[data-test='song-" + songID + "']"));
    }

    public boolean isSongOnTheList(String songID) {
        return !getSongElement(songID).isEmpty();
    }

    public WebElement getSongPreviewElement() {
        return driver.findElement(By.cssSelector("[data-test='song-preview']"));
    }

    public String getSelectedSongID() {
        return getSongPreviewElement().getAttribute("data-song");
    }

    public void goBackToMainMenu() {
        actions.sendKeys(Keys.BACK_SPACE).perform();
    }

    public WebElement getGroupNameButton(String groupName) {
        return driver.findElement(By.cssSelector("[data-test='group-navigation-" + groupName + "']"));
    }

    public void goToGroupName(String groupName) {
        getGroupNameButton(groupName).click();

        // AssertionFailedError - Thread.sleep allows enough time to scroll to the expected group name
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean isGroupSelected(String groupName) {
        List<WebElement> activeGroups = driver.findElements(By.cssSelector("[data-active='true']"));

        for (WebElement activeGroup : activeGroups) {
            String activeGroupValue = activeGroup.getAttribute("data-test");

            if (activeGroupValue.equals("group-navigation-" + groupName)) {
                return true;
            }
            System.out.println(activeGroupValue);
        }
        return false;
    }

    public boolean isSongFromTheGroupSelected(String groupName) {
        return driver.findElement(By.cssSelector("[data-group-letter='" + groupName + "'] [data-focused='true']")).isDisplayed();
    }
}
