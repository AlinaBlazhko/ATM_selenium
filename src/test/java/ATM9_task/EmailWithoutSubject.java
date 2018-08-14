package ATM9_task;

import ATM9_task.bo.Email;

import static com.codeborne.selenide.Selenide.$;

public class EmailWithoutSubject implements AbstractEmail {

    @Override
    public void writeEmail(Email newEmail) {
        $(recipient).val(newEmail.getRecipient()).pressTab();
        $(subject).val(newEmail.getSubject()).pressTab();
        $(email).val(newEmail.getBody());
    }
}
