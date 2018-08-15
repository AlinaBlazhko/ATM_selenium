package ATM9_task.factorymethod;

import ATM9_task.enums.TypeOfFillFields;

public class EmailFactory {
    public AbstractEmail getWriter(TypeOfFillFields type) {
        AbstractEmail email = null;
        switch (type){
            case  NO_SUBJECT: email = new EmailWithoutSubject();
                                break;
            case  ALL_FIELDS: email = new EmailWithAllFields();
                                break;
        }
        return email;
    }
}
