package ATM9_task.factorymethod;

public class  EmailWithoutSubjectCreator implements ICreator {
    @Override
    public AbstractEmail getWriter() {
        return new EmailWithoutSubject();
    }
}
