package UseCase;

import Entity.UserAccount;

public interface AccountFactory {

    public UserAccount createAccount(String userName, String password);

}
