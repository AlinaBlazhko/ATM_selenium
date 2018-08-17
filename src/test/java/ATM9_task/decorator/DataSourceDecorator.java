package ATM9_task.decorator;

import ATM9_task.bo.Email;

public class DataSourceDecorator implements DataSource{

    protected DataSource wrapper;

    public DataSourceDecorator(DataSource wrapper) {
        this.wrapper = wrapper;
    }

    @Override
    public Email readData(String file) {
        return null;
    }

    @Override
    public void writeEmail(Email content) {

    }
}
