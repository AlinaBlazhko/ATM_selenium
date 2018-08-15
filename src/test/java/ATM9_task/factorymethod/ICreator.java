package ATM9_task.factorymethod;

import ATM9_task.bo.Email;

public interface ICreator {
    AbstractEmail getWriter();
}
