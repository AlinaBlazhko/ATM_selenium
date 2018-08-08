package ATM8_task.po;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

/**
 * Created by X240 on 8/8/2018.
 */
public class LeftSection {
    public void openDraftFolder(){
        $(By.xpath("//span[text()='Черновики']")).click();
    }
}
