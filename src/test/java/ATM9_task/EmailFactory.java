package ATM9_task;

import javax.swing.text.AbstractWriter;

public class EmailFactory {
    public AbstractEmail getWriter(String type) {
        AbstractEmail email = null;
        switch (type){
            case "no" : email = new EmailWithoutSubject();
        }
        return email;
    }
}
