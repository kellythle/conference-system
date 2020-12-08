package Presenter;

public class InputPresenter {


    public void printEnterNumberGreaterOrEqual(int number) {
        System.out.print("Please enter a number that is " + number + " or greater: ");
    }

    public void printEnterYesOrNo() {
        System.out.print("Please enter either Yes or No: ");
    }

    public void printEnterNonEmptyString() {
        System.out.println("Please do not leave this field blank: ");
    }

    public void printInvalidInput() {
        System.out.println("Invalid input, please try again.");
    }
}
