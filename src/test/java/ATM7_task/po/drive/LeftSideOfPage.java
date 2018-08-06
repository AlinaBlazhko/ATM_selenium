package ATM7_task.po.drive;

import ATM7_task.po.AbstractPage;
import org.openqa.selenium.By;

import java.io.File;

public class LeftSideOfPage extends AbstractPage{

    private By uploadFileButton = By.cssSelector("input.upload-button__attach");

    public UploadPopup uploadFile(){
        waitForElementVisibility(uploadFileButton);
        driver.findElements(By.cssSelector(".upload-button__attach-wrapper input[type='file']")).get(0).sendKeys(new File("src\\test\\resources\\test.json").getAbsolutePath());
        return new UploadPopup();
    }
}
