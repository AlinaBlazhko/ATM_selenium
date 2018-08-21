package ATM10_task;

import ATM10_task.divermanager.WebDriverSingleton;
import ATM10_task.entity.Email;
import ATM10_task.po.*;
import ATM6_task.po.HomePage;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import com.codeborne.selenide.*;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

/**
 * Created by X240 on 8/19/2018.
 */
public class Stepdefs {

    private HomePage homePage = new HomePage();
    private AuthorizationPage loginPage = new AuthorizationPage();
    private Header header = new Header();
    private EmailPage emailPage = new EmailPage();
    private EmailPopup popup = new EmailPopup();
    private LeftSection leftSection = new LeftSection();
    private Email content = new Email("alinaBlazhko@yandex.ru", "Email for test", "Hello Mr. Smith!");

    @Given("^I sign in in email with username and password$")
    public void iSignInInEmailWithUsernameAndPassword() throws Throwable {
        homePage.open();
        homePage.clickOnButtonAuthorization();
        loginPage.login();
    }

    @When("^I write new letter$")
    public void iWriteNewLetter() throws Throwable {
        header.openNewEmail();
        emailPage.writeEmail(content);
        throw new PendingException();
    }

    @And("^I close it and save as draft$")
    public void iCloseItAndSaveAsDraft() throws Throwable {
        emailPage.closeEmail();
        popup.closeEmailAndSaveAsDraft();
        throw new PendingException();
    }

    @Then("^Email is saved as draft$")
    public void emailIsSavedAsDraft() throws Throwable {
        leftSection.openDraftFolder();
        Assert.assertTrue(leftSection.rightCountOfEmail());
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }
}
