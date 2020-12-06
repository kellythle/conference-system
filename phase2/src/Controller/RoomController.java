package Controller;

import Presenter.RoomPresenter;
import UseCase.RoomBuilder;
import UseCase.RoomManager;

import java.util.ArrayList;
import java.util.Scanner;

public class RoomController {
    private final RoomManager roomManager;
    private final RoomPresenter roomPresenter = new RoomPresenter();
    private RoomBuilder roomBuilder = new RoomBuilder();
    private Scanner scanner = new Scanner(System.in);

    public RoomController(RoomManager roomManager)
    {
        this.roomManager = roomManager;
    }

    public String getRoomMenu() {
        roomPresenter.printRoomMenu();
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    /**
     * Gets an integer greater or equal to a certain integer from the user.
     * If the user enters a number less than the specified integer,
     * they will be prompted to try again.
     *
     * @param num - the integer that the input should be greater to or equal to.
     * @return number received as input from user.
     */
    private int getIntGreaterOrEqual(int num) {
        int input;
        boolean validInput = false;

        do {
            while (!scanner.hasNextInt()) {
                scanner.next();
                roomPresenter.printEnterNumberEqualOrGreater(num);
            }

            input = scanner.nextInt();

            if (input < num)
                roomPresenter.printEnterNumberEqualOrGreater(num);
            else
                validInput = true;

        } while(!validInput);

        scanner.nextLine(); // Gets rid of the \n after getting the int.
        return input;
    }

    /**
     * Gets a string input from the user and returns a boolean if input is "yes" or "no".
     * If user enters a value that is not "yes" or "no", the user is prompted for an input again.
     *
     * @return true if input was "yes", false if input is "no".
     */
    private boolean getStringForBoolean() {
        String input;
        boolean output = false;
        boolean validInput = false;

        while (!validInput) {
            input = scanner.nextLine().trim().toLowerCase();

            if (input.equals("yes")) {
                output = true;
                validInput = true;
            } else if (input.equals("no")) {
                validInput = true;
            } else {
                roomPresenter.printEnterYesOrNo();
            }
        }

        return output;
    }

    /**
     * Gets a string input from the user as long as it is not empty. If it is empty,
     * the user is prompted for an input again.
     *
     * @return the nonempty string input.
     */
    private String getNonEmptyString() {
        String input = "";
        boolean validInput = false;

        while (!validInput) {
            input = scanner.nextLine();

            if (input == null || input.isEmpty())
                roomPresenter.printEnterNonEmptyString();
            else
                validInput = true;
        }

        return input;
    }

    /**
     * Gets an int input from the user for a new room's number.
     * If the user enters a number of a room that already exists,
     * will prompt the user to enter another number.
     *
     * @return an integer greater than or equal to 0
     */
    private int getNewRoomNumber() {
        int roomNumber = 0;
        boolean validNumber = false;

        roomPresenter.printEnterRoomNumber();

        while (!validNumber) {
            roomNumber = getIntGreaterOrEqual(0);

            if (roomManager.doesRoomExist(roomNumber))
                roomPresenter.printRoomExists();
            else
                validNumber = true;
        }

        return roomNumber;
    }

    /**
     * Gets an int input from the user for a new room's capacity.
     *
     * @return an integer greater than or equal to 2.
     */
    private int getNewRoomCapacity() {
        roomPresenter.printEnterCapacity();

        return getIntGreaterOrEqual(3);
    }

    /**
     * Gets an int input from the user for a new room's square footage.
     *
     * @return an integer greater than or equal to 1.
     */
    private int getNewRoomSquareFeet() {
        roomPresenter.printEnterSquareFeet();

        return getIntGreaterOrEqual(1);
    }

    /**
     * Gets an int input from the user for a new room's number of screens.
     *
     * @return an integer greater than or equal to 2.
     */
    private int getNewRoomScreens() {
        roomPresenter.printEnterScreens();

        return getIntGreaterOrEqual(0);
    }

    /**
     * Gets a string input from the user indicating if the new room has a sound system.
     *
     * @return true if user entered "yes", false if user entered "no".
     */
    private boolean getNewRoomSoundSystem() {
        roomPresenter.printEnterSoundSystem();

        return getStringForBoolean();
    }

    /**
     * Gets a string input from the user indicating if the new room has a stage.
     *
     * @return true if user entered "yes", false if user entered "no".
     */
    private boolean getNewRoomStage() {
        roomPresenter.printEnterStage();

        return getStringForBoolean();
    }

    /**
     * Gets a string input from the user indicating if the new room has accessibility features.
     *
     * @return true if user entered "yes", false if user entered "no".
     */
    private boolean getNewRoomAccessible() {
        roomPresenter.printEnterAccessible();

        return getStringForBoolean();
    }

    /**
     * Gets a string input from the user indicating if the new room has WiFi.
     *
     * @return true if user entered "yes", false if user entered "no".
     */
    private boolean getNewRoomWifi() {
        roomPresenter.printEnterWifi();

        return getStringForBoolean();
    }

    /**
     * Gets a string input from the user for the new room's special features.
     *
     * @return String containing the special features.
     */
    private String getNewRoomSpecialFeatures() {
        roomPresenter.printEnterSpecialFeatures();

        return scanner.nextLine();
    }

    /**
     * Gets a string input from the user for the new room's description.
     *
     * @return String containing the description.
     */
    private String getNewRoomDescription() {
        roomPresenter.printEnterDescription();

        return getNonEmptyString();
    }

    /**
     * Prompts the user to input details about the new room, creates the room,
     * and adds it to the list of rooms.
     */
    public void createNewRoom() {
        int roomNumber = getNewRoomNumber();
        int capacity = getNewRoomCapacity();
        int squareFeet = getNewRoomSquareFeet();
        int screens = getNewRoomScreens();
        boolean soundSystem = getNewRoomSoundSystem();
        boolean stage = getNewRoomStage();
        boolean accessible = getNewRoomAccessible();
        boolean wifi = getNewRoomWifi();
        String specialFeatures = getNewRoomSpecialFeatures();
        String description = getNewRoomDescription();

        roomBuilder.roomNumber(roomNumber)
                .roomNumber(roomNumber)
                .capacity(capacity)
                .squareFootage(squareFeet)
                .screens(screens)
                .soundSystem(soundSystem)
                .stage(stage)
                .accessible(accessible)
                .wifi(wifi)
                .specialFeatures(specialFeatures)
                .description(description);

        roomManager.addRoom(roomBuilder);
        roomPresenter.printRoomAddSuccessful();
    }

    /**
     * Gets an int input from the user for a room that already exists.
     * If the user enters a number of a room that does not exist,
     * will prompt the user to enter another number.
     *
     * @return the int that the user entered.
     */
    private int getRoomNumber() {
        int roomNumber = 0;
        boolean validNumber = false;

        while (!validNumber) {
            roomNumber = getIntGreaterOrEqual(0);

            if (!roomManager.doesRoomExist(roomNumber))
                roomPresenter.printRoomNonExistent();
            else
                validNumber = true;
        }

        return roomNumber;
    }

    /**
     * Displays a list of all rooms, showing basic information about each room,
     * and then prompts the user to enter an existing room number for full information.
     * If the room list is empty, will return to the menu of room options.
     */
    public void displayRooms() {
        if (roomManager.isRoomMapEmpty()) {
            roomPresenter.printNoRooms();
            return;
        }

        ArrayList<Integer> rooms = roomManager.getRoomNumbers();

        for (int room : rooms) {
            roomPresenter.printRoomShort(room, roomManager);
        }

        roomPresenter.printSelectRoomForFullInfo();

        int displayRoomNumber = getRoomNumber();

        roomPresenter.printRoomFull(displayRoomNumber, roomManager);
    }

    /**
     * Deletes an existing room if there are no scheduled times in the room after the current date and time.
     */
    public void deleteRoom() {
        if (roomManager.isRoomMapEmpty()) {
            roomPresenter.printNoRooms();
            return;
        }

        roomPresenter.printSelectRoomForDeletion();

        int roomNumber = getRoomNumber();

        roomManager.removePastTimes(roomNumber);

        if (roomManager.canDeleteRoom(roomNumber)) {
            roomManager.deleteRoom(roomNumber);
            roomPresenter.printRoomDeletionSuccessful();
        } else {
            roomPresenter.printRoomDeletionFailure();
        }

    }
}
