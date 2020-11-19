package UseCase;

import Entity.UserAccount;

public interface AccountFactory {

    UserAccount createAccount(String userName, String password);

}
