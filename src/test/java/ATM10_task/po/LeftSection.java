package ATM10_task.po;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

/**
 * Created by X240 on 8/8/2018.
 */
public class LeftSection {

    private By drafts = By.xpath("//span[text()='Черновики']");
    private By sents = By.xpath("//span[text()='Отправленные']");
    private By countOfEmails = By.cssSelector(".mail-NestedList-Item_current span.mail-NestedList-Item-Info-Extras");

    public void openDraftFolder(){
        $(drafts).click();
    }
    public void openSentFolder(){
        $(sents).click();
    }

    public boolean rightCountOfEmail(){
        return $(countOfEmails).getText().equals("1");
    }
}
