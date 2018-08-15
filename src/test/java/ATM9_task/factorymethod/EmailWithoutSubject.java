package ATM9_task.factorymethod;

import ATM9_task.bo.Email;

import static com.codeborne.selenide.Selenide.$;

public class EmailWithoutSubject implements AbstractEmail {

    @Override
    public void writeEmail(Email newEmail) {
        $(recipient).val(newEmail.getRecipient()).pressEnter();
        $(email).val(newEmail.getBody());
    }
}
