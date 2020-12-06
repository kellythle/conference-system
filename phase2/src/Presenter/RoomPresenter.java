package Presenter;

import UseCase.RoomManager;

import java.util.ArrayList;

public class RoomPresenter {

    public void printRoomMenu() {
        System.out.print("Welcome to the room menu! Please select one of the following options:\n" +
                "1. Add a new room.\n" +
                "2. View list of rooms.\n" +
                "3. Delete a room.\n" +
                "4. Exit room menu.\n");
    }

    public void printEnterNumberEqualOrGreater(int number) {
        System.out.print("Please enter a number that is " + number + " or greater: ");
    }

    public void printEnterYesOrNo() {
        System.out.print("Please enter either Yes or No: ");
    }

    public void printEnterNonEmptyString() {
        System.out.println("Please do not leave this field blank: ");
    }

    public void printEnterRoomNumber() {
        System.out.print("Please enter the room number: ");
    }

    public void printRoomExists() {
        System.out.print("That room number already exists. Please enter another number: ");
    }

    public void printEnterCapacity() {
        System.out.print("Please enter the room's capacity (minimum capacity is 3): ");
    }

    public void printEnterSquareFeet() {
        System.out.print("Please enter the room's size in square feet: ");
    }

    public void printEnterScreens() {
        System.out.print("Please enter the number of screens in the room: ");
    }

    public void printEnterSoundSystem() {
        System.out.print("Does the room have a sound system? (Yes/No): ");
    }

    public void printEnterStage() {
        System.out.print("Does the room have a stage in it? (Yes/No): ");
    }

    public void printEnterAccessible() {
        System.out.print("Does this room provide accessibility features? (Yes/No): ");
    }

    public void printEnterWifi() {
        System.out.print("Does the room have WiFi? (Yes/No): ");
    }

    public void printEnterSpecialFeatures() {
        System.out.print("Please enter the room's special features.\n" +
                "If the room has no special features, please leave this blank: ");
    }

    public void printEnterDescription() {
        System.out.println("Please enter a description of the room: ");
    }

    public void printRoomAddSuccessful() {
        System.out.println("Room added successfully!");
    }

    private void printRoomNumberHeader(int roomNumber) {
        System.out.println("========== Room " + roomNumber + " ===========\n");
    }

    private void printRoomCapacity(int roomNumber, RoomManager roomManager) {
        System.out.println("Capacity: " + roomManager.getRoomCapacity(roomNumber) + "\n");
    }

    private void printSquareFeet(int roomNumber, RoomManager roomManager) {
        System.out.println("Size: " + roomManager.getScreens(roomNumber) + " sq ft\n");
    }

    private void printScreens(int roomNumber, RoomManager roomManager) {
        System.out.println("Screens: " + roomManager.getScreens(roomNumber) + "\n");
    }

    private String booleanToYesNo(boolean bool) {
        if (bool)
            return "Yes";
        else
            return "No";
    }

    private void printSoundSystem(int roomNumber, RoomManager roomManager) {
        boolean hasSoundSystem = roomManager.roomHasSoundSystem(roomNumber);
        String soundSystem = booleanToYesNo(hasSoundSystem);
        System.out.println("Sound System: " + soundSystem + "\n");
    }

    private void printStage(int roomNumber, RoomManager roomManager) {
        boolean hasStage = roomManager.roomHasStage(roomNumber);
        String stage = booleanToYesNo(hasStage);
        System.out.println("Stage: " + stage + "\n");
    }

    private void printAccessible(int roomNumber, RoomManager roomManager) {
        boolean isAccessible = roomManager.roomIsAccessible(roomNumber);
        String accessible = booleanToYesNo(isAccessible);
        System.out.println("Accessibility Features: " + accessible + "\n");
    }

    private void printWifi(int roomNumber, RoomManager roomManager) {
        boolean hasWifi = roomManager.roomHasWifi(roomNumber);
        String wifi = booleanToYesNo(hasWifi);
        System.out.println("Wifi: " + wifi + "\n");
    }

    private void printSpecialFeatures(int roomNumber, RoomManager roomManager) {
        String specialFeatures = roomManager.getSpecialFeatures(roomNumber);

        System.out.println("Special Features: " + specialFeatures + "\n");
    }

    private void printDescription(int roomNumber, RoomManager roomManager) {
        String description = roomManager.getDescription(roomNumber);
        System.out.println("Description: " + description + "\n");
    }

    public void printRoomShort(int roomNumber, RoomManager roomManager) {
        printRoomNumberHeader(roomNumber);
        printRoomCapacity(roomNumber, roomManager);
        printDescription(roomNumber, roomManager);
        System.out.println("==========");
    }

    public void printRoomFull(int roomNumber, RoomManager roomManager) {
        printRoomNumberHeader(roomNumber);
        printRoomCapacity(roomNumber, roomManager);
        printSquareFeet(roomNumber, roomManager);
        printScreens(roomNumber, roomManager);
        printSoundSystem(roomNumber, roomManager);
        printStage(roomNumber, roomManager);
        printAccessible(roomNumber, roomManager);
        printWifi(roomNumber, roomManager);
        printSpecialFeatures(roomNumber, roomManager);
        printDescription(roomNumber, roomManager);
    }

    public void printNoRooms() {
        System.out.println("No rooms have currently been registered.");
    }

    public void printSelectRoomForFullInfo() {
        System.out.println("Enter a room number to view all of the room's details: ");
    }

    public void printRoomNonExistent() {
        System.out.println("That room does not exist, please try again: ");
    }

    public void printSelectRoomForDeletion() {
        System.out.println("Enter the number of the room to delete: ");
    }

    public void printRoomDeletionSuccessful() {
        System.out.println("Room has been deleted successfully.");
    }

    public void printRoomDeletionFailure() {
        System.out.println("Room could not be deleted as it has events scheduled for future times.");
    }

    public void printAvailableRoomNumbers(ArrayList<Integer> availableRooms) {
        System.out.print("Available Rooms: ");
        if (availableRooms.isEmpty()) {
            System.out.println("None");
        } else {
            for (int roomNumber : availableRooms)
                System.out.print(roomNumber + " ");
            System.out.print("\n");
        }
    }

    public void printChooseAvailableRoom() {
        System.out.println("Select an available room number for the event, or enter 0 to cancel event creation: ");
    }

    public void printRoomNotAvailable() {
        System.out.println("That room is not an available room.");
    }

    public void printAboveRoomCapacity() {
        System.out.println("Capacity entered is greater than the room capacity.");
    }
}
