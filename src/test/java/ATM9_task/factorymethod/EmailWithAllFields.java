package ATM9_task.factorymethod;

import ATM9_task.bo.Email;

import static com.codeborne.selenide.Selenide.$;

/**
 * Created by X240 on 8/14/2018.
 */
public class EmailWithAllFields implements AbstractEmail{
    @Override
    public void writeEmail(Email newEmail) {
        $(recipient).val(newEmail.getRecipient()).pressTab();
        $(subject).val(newEmail.getSubject()).pressTab();
        $(email).val(newEmail.getBody());
    }
}
