package ATM9_task.factorymethod;

public class EmailWithAllFieldsCreator implements ICreator {
    @Override
    public AbstractEmail getWriter() {
        return new EmailWithAllFields();
    }
}
