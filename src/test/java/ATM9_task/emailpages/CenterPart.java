package ATM9_task.emailpages;

import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/**
 * Created by X240 on 8/8/2018.
 */
public class CenterPart {

    private By checkboxes = By.cssSelector("label.nb-checkbox._nb-small-checkbox-checkbox._init");
    private By deleteButton = By.xpath("//span[text()='Удалить']");
    By email = By.cssSelector("span.js-message-snippet-body");


    private By listOfEmailsInFolder = By.cssSelector("div.mail-MessageSnippet-Content");

    public int countOfDrafts() {
        return $$(listOfEmailsInFolder).size();
    }

    public void openEmail(){
        $(email).click();
    }
    public void openDraftEmail(){
        $(listOfEmailsInFolder).click();
    }

    public void deleteAllDraftsFromFolder(){
        ElementsCollection checks = $$(checkboxes);
        if (!$$(checks).isEmpty()) {
            for (WebElement check : checks) {
                check.click();
            }
            $(deleteButton).click();
        }
    }

}
