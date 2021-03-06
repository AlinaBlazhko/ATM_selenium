package ATM8_task.po.emailpages;

import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/**
 * Created by X240 on 8/8/2018.
 */
public class CenterPart {


    private By listOfEmailsInFolder = By.cssSelector("div.mail-MessageSnippet-Content");

    public int countOfDrafts() {
        return $$(listOfEmailsInFolder).size();
    }

    public void openDraftEmail(){
        $(listOfEmailsInFolder).click();
    }


}
