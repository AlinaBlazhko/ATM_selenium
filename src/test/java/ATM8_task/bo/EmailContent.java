package ATM8_task.bo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailContent {
    private static final String RECIPIENT = "alinaBlazhko@yandex.ru";
    private static final String SUBJECT = "Email for test";
    private static final String BODY = "Hello Mr. Smith!";

    public static String getRECIPIENT() {
        return RECIPIENT;
    }

    public static String getSUBJECT() {
        return SUBJECT;
    }

    public static String getBODY() {
        return BODY;
    }
}
