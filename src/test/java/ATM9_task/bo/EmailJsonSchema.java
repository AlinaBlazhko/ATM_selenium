package ATM9_task.bo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by X240 on 8/19/2018.
 */
public class EmailJsonSchema {
    @SerializedName("recipient")
    @Expose
    private String recipient;
    @SerializedName("subject")
    @Expose
    private String subject;
    @SerializedName("body")
    @Expose
    private String body;

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

}
