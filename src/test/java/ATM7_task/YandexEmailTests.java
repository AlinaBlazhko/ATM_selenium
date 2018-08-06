package ATM7_task;

import ATM7_task.po.mail.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import static ATM7_task.WebDriverSingleton.kill;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Created by X240 on 7/22/2018.
 */
public class YandexEmailTests extends BaseTest{

    @Test(description = "write new email and save as draft")
    public void writeNewEmailTest() {
        header = new Header();
        newEmailPage = header.openNewEmail();
        newEmailPage.writeEmail();
        newEmailPage.closeEmail();
        popupPage = new PopupPage();
        popupPage.closeAndSaveEmail();

        // open draft folder
        foldersPage = new FoldersPage();
        foldersPage.openDrafts();
        header.refreshPage();
        assertTrue(foldersPage.getCountOfEmailsInDraftFolder());
    }

    @Test(description = "verify email's content", dependsOnMethods = "writeNewEmailTest")
    public void sentEmailAndVerifyThatEmailIsSent() {
        centerPart = new CenterPart();
        newEmailPage = centerPart.openEmail();
        assertTrue(newEmailPage.getTo().equals("alinaBlazhko") || newEmailPage.getTo().equals("alinaBlazhko@yandex.ru"));
        assertEquals(newEmailPage.getSubject(), "Email for test");
        assertEquals(newEmailPage.getLetter(), "Hello Mr. Smith!\n");
    }


    @Test(description = "send email and verify that email appear in Sent Folder", dependsOnMethods = "sentEmailAndVerifyThatEmailIsSent")
    public void sendingEmailFromDraft(){
        newEmailPage.sentEmail();
        foldersPage.openSents();
        header.refreshPage();
        assertTrue(foldersPage.getCountOfEmailsInSentFolder());
    }

    @AfterClass(description = "clear Sent folder for next test", alwaysRun = true)
    public void clearSentFolder(){
        header.refreshPage();
        centerPart.deleteEmail();
//        header.openProfile();
//        kill();
    }

}
