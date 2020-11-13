package Presenter;


public class LoginPresenter {

    public void printStartMenu(){
        System.out.println("Welcome to Start Menu! \n" +
                "Options:\n" +
                "1. Sign in \n" +
                "2. Sign up\n" +
                "(Enter 1, 2");
    }

    public void inputName(){
        System.out.println("Enter your name:\n");
    }

    public void inputPassword(){
        System.out.println("Enter your password:\n");
    }


    /**
     * display information when an account is created
     * @param value- boolean value got from login controller
     */
    public void displayAccountCreateInfo(boolean value){
        if (value){
            System.out.println("The user account is created successfully");
        }
        else
            System.out.println("The user account is existed");
    }

    /**
     * display information when a user is logging in
     * @param value- boolean value got from login controller
     */
    public void displayLoginInfo(boolean value){

        if (value){
            System.out.println("Correct Password");
        }
        else {
            System.out.println("Incorrect user name or password");
        }
    }

    /**
     * display information about current login user
     * @param info- user name got from login controller
     */
    public void displayLoginUser(String info){
        System.out.println("Current user is "+ info);
    }
}

