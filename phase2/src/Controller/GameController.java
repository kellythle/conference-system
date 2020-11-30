package Controller;

import Presenter.GamePresenter;

import java.util.Random;
import java.util.Scanner;

public class GameController {
    private final GamePresenter gp = new GamePresenter();

    /**
     * Calls GamePresenter to print the game mode menu.
     */
    public void getGameMenu(){
        gp.printModeMenu();
    }

    /**
     * Allows the user to select a game level and returns
     * 10, 50, or 100. (This number will be the upperbound
     * when generating random numbers)
     * @return 10, 50, or 100
     */
    public int setLevel(){
        int level = 0;
        boolean validInput;
        do {
            Scanner scan1 = new Scanner(System.in);
            gp.printLevelMenu();
            String l = scan1.nextLine();
            switch (l) {
                case "1":
                    level = 10;
                    validInput = true;
                    break;
                case "2":
                    level = 50;
                    validInput = true;
                    break;
                case "3":
                    level = 100;
                    validInput = true;
                    break;
                case "4":
                    level = 0;
                    validInput = true;
                    break;
                default:
                    gp.printInvalidInput();
                    validInput = false;
            }
        }while(!validInput);
        return level;
    }

    /**
     * Calls the corresponding game type and level. Type 1 for summation,
     * type 2 for subtraction, type 3 for multiplication, type 4 for
     * division.
     *
     * @param type - the type of the game.
     * @param level - the level of the game.
     */
    public void gameMode(int type, int level){
        switch(type){
            case 1:
                sum(level);
                break;
            case 2:
                subtract(level);
                break;
            case 3:
                multiply(level);
                break;
            case 4:
                divide(level);
                break;
        }
    }

    /**
     * Summation game. It generates 2 random numbers, num1 and num2, with upperbound "level"
     * and shows if the user answered correctly or not. (num1 + num2)
     *
     * @param level - the upperbound of the random numbers
     */
    public void sum(int level){
        int count = 0;
        int correct = 0;
        do {
            Random rand = new Random();
            int num1 = rand.nextInt(level);
            int num2 = rand.nextInt(level);

            Scanner scan = new Scanner(System.in);
            gp.printQuestion(count, num1, num2, "+");
            String answer = scan.nextLine();
            int ans = Integer.parseInt(answer);
            if (ans == num1 + num2) {
                gp.printCorrect();
                correct++;
            } else {
                gp.printWrong();
                gp.printAnswer(num1, num2, "+");
            }
            count++;
        }while(count != 5);
        gp.printResult(correct);
    }

    /**
     * Subtraction game. It generates 2 random numbers, num1 and num2, with upperbound "level"
     * and shows if the user answered correctly or not. (num1 - num2)
     *
     * @param level - the upperbound of the random numbers
     */
    public void subtract(int level){
        int count = 0;
        int correct = 0;
        do {
            Random rand = new Random();
            int num1 = rand.nextInt(level);
            int num2 = rand.nextInt(level);

            Scanner scan = new Scanner(System.in);
            gp.printQuestion(count, num1, num2, "-");
            String answer = scan.nextLine();
            int ans = Integer.parseInt(answer);
            if (ans == num1 - num2) {
                gp.printCorrect();
                correct++;
            } else {
                gp.printWrong();
                gp.printAnswer(num1, num2, "-");
            }
            count++;
        }while(count != 5);
        gp.printResult(correct);
    }

    /**
     * Multiplication game. It generates 2 random numbers, num1 and num2, with upperbound "level"
     * and shows if the user answered correctly or not. (num1 * num2)
     *
     * @param level - the upperbound of the random numbers
     */
    public void multiply(int level){
        int count = 0;
        int correct = 0;
        do {
            Random rand = new Random();
            int num1 = rand.nextInt(level);
            int num2 = rand.nextInt(level);

            Scanner scan = new Scanner(System.in);
            gp.printQuestion(count, num1, num2, "*");
            String answer = scan.nextLine();
            int ans = Integer.parseInt(answer);
            if (ans == num1 * num2) {
                gp.printCorrect();
                correct++;
            } else {
                gp.printWrong();
                gp.printAnswer(num1, num2, "*");
            }
            count++;
        }while(count != 5);
        gp.printResult(correct);
    }

    /**
     * Subtraction game. It generates 2 random numbers, num1 and num2,
     * with upperbound "level" and "10" respectively.
     * And shows if the user answered correctly or not. (num1 / num2)
     * Special restriction for num2: num2 cannot be 0 since we cannot divide
     * any number by 0.
     *
     * @param level - the upperbound of the random numbers
     */
    public void divide(int level){
        int count = 0;
        int correct = 0;
        do {
            int num1;
            int num2;
            do {
                Random rand = new Random();
                num1 = rand.nextInt(level);
                num2 = rand.nextInt(10);
            }while(num2 == 0);
            Scanner scan = new Scanner(System.in);
            gp.printQuestion(count, num1, num2, "/");
            String answer = scan.nextLine();
            int ans = Integer.parseInt(answer);
            if (ans == Math.floorDiv(num1, num2)) {
                gp.printCorrect();
                correct++;
            } else {
                gp.printWrong();
                gp.printAnswer(num1, num2, "/");
            }
            count++;
        }while(count != 5);
        gp.printResult(correct);
    }

    public void InvalidInput() {
        gp.printInvalidInput();
    }
}
