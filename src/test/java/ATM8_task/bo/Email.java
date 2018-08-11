package ATM8_task.bo;

import lombok.Getter;
import lombok.Setter;

//@Getter
//@Setter
public class Email {
    private String recipient;
    private String subject;
    private String body;

    public Email(String recipient, String subject, String body) {
        this.recipient = recipient;
        this.subject = subject;
        this.body = body;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
    //    private static final String RECIPIENT = "alinaBlazhko@yandex.ru";
//    private static final String SUBJECT = "Email for test";
//    private static final String BODY = "Hello Mr. Smith!";
//
//    public static String getRECIPIENT() {
//        return RECIPIENT;
//    }
//
//    public static String getSUBJECT() {
//        return SUBJECT;
//    }
//
//    public static String getBODY() {
//        return BODY;
//    }
}
