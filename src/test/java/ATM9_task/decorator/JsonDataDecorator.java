package ATM9_task.decorator;

import ATM9_task.bo.Email;

import static com.codeborne.selenide.Selenide.$;

/**
 * Created by X240 on 8/19/2018.
 */
public class JsonDataDecorator extends DataSourceDecorator {
    public JsonDataDecorator(DataSource wrapper) {
        super(wrapper);
    }

    @Override
    public Email readData(){
        return super.readData();
    }

    @Override
    public void writeEmail() {
        Email content = readData();
        System.out.println(content.getRecipient() + " " + content.getSubject() + " " + content.getBody());
        $(recipient).val(content.getRecipient()).pressTab();
        $(subject).val(content.getSubject()).pressTab();
        $(email).val(content.getBody());
    }
}
