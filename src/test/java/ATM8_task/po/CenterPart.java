package ATM8_task.po;

import static com.codeborne.selenide.Selenide.$$;

/**
 * Created by X240 on 8/8/2018.
 */
public class CenterPart {
    public int countOfDrafts(){
        return $$("div.mail-MessageSnippet-Content").size();
    }
}
