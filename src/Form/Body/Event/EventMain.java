package Form.Body.Event;

import Model.AccountModel;
import Model.GroupModel;

public interface EventMain {

    public void showLoading(boolean show);
    public void initChat();
    public void SelectUser(AccountModel ac);
    public void SelectSystem();
    public void SelectGroup(GroupModel gr);
    public void BlockUser(String text);
    public void logout();
}
