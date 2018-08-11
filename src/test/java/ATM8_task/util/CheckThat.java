package ATM8_task.util;

import ATM8_task.po.emailpages.CenterPart;
import ATM8_task.po.emailpages.LeftSection;
import org.testng.Assert;

import static com.codeborne.selenide.Selenide.title;
import static org.testng.Assert.assertTrue;

/**
 * Created by X240 on 8/11/2018.
 */
public class CheckThat {
    public static void emailIsDisplayedInDraft(){
        CenterPart centerPart = new CenterPart();
        assertTrue(centerPart.countOfDrafts() == 1);
    }

    public static void justOneEmailInFolder(){
        LeftSection leftSection = new LeftSection();
        assertTrue(leftSection.rightCountOfEmail());
    }

    public static void userIsSignInDriver(){
        Assert.assertTrue(title().contains("Яндекс.Диск"));
    }
    public static void userIsSignInEmail(){
        assertTrue(title().contains("Яндекс.Почта"));
    }
}
