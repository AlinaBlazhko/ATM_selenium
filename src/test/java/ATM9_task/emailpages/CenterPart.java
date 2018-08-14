package ATM9_task.emailpages;

import org.openqa.selenium.By;

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
