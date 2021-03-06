package ATM9_task.decorator;

import ATM9_task.bo.Email;
import ATM9_task.bo.EmailJsonSchema;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import static com.codeborne.selenide.Selenide.$;

public class JsonDataSource implements DataSource {


    @Override
    public Email readData() {
        Gson gson = new Gson();
        EmailJsonSchema result = null;
        BufferedReader br;
        try {
            br = new BufferedReader(
                    new FileReader("src\\test\\resources\\email.json"));
            result = gson.fromJson(br, EmailJsonSchema.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println(result.getBody());
        return new Email(result.getRecipient(), result.getSubject(), result.getBody());
    }
}
