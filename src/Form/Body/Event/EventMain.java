package Form.Body.Event;

import Model.AccountModel;

public interface EventMain {

    public void showLoading(boolean show);
    public void initChat();
    public void SelectUser(AccountModel ac);
    public void SelectSystem();
    public void BlockUser();
    public void logout();
}
