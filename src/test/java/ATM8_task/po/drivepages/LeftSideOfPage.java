package ATM8_task.po.drivepages;

import ATM7_task.po.AbstractPage;
import org.openqa.selenium.By;

import java.io.File;

import static ATM8_task.util.MethodsForTests.highlight;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class LeftSideOfPage{

    private By uploadFileButton = By.xpath("//span[@title='Загрузить файлы']");
//    private By uploadFileButton = By.cssSelector("input.upload-button__attach");

    public void uploadFile(){
        highlight($(uploadFileButton));
        $(uploadFileButton).sendKeys(new File("src\\test\\resources\\test.json").getAbsolutePath());
    }
}
