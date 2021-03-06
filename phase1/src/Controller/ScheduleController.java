package Controller;

import Presenter.SchedulePresenter;
import UseCase.EventManager;
import UseCase.UserManager;
import javafx.util.Pair;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

/**
 * A controller class that reads in user input related to adding events by
 * calling EventManager. User input is prompted by calling SchedulePresenter
 * which has printing methods that show and ask for information.
 *
 * @author An Yen, Kelly Le
 */
public class ScheduleController {
    private final EventManager eventManager;
    private final UserManager userManager;
    private final SchedulePresenter scheduleP;

    /**
     * Creates a instance of ScheduleController with a ManagerFacade instance.
     * @param em - an EventManager instance
     * @param um - an UserManager instance
     */
    public ScheduleController(EventManager em, UserManager um){
        this.eventManager = em;
        this.userManager = um;
        this.scheduleP  = new SchedulePresenter(eventManager);
    }

    /**
     * Calls SchedulePresenter to print out the Scheduling Menu.
     */
    public String getScheduleMenu(){
        Scanner scan = new Scanner(System.in);
        scheduleP.printScheduleMenu();
        return scan.nextLine();
    }

    /**
     * Calls SchedulePresenter to prompt the user to input
     * the name, time, room, and date and prints out the
     * event details if successful.
     */
    public void createEvent(){
        String name;
        boolean ValidName;
        do {
            Scanner scan = new Scanner(System.in);
            scheduleP.printName();
            name = scan.nextLine();
            if (name.equals("0")) {
                return;
            }
            else if (name.trim().isEmpty() || eventManager.getEvent(name)){
                scheduleP.printFailedName();
                ValidName = false;
            } else {
                ValidName = true;
            }
        } while (!ValidName);

        //enter date, check date
        String inputDate;
        boolean ValidDate = false;
        do {
            Scanner scanD = new Scanner(System.in);
            scheduleP.printEnterDate();
            inputDate = scanD.nextLine();
            LocalDate localDate;
            try {
                localDate = LocalDate.parse(inputDate);
                if (localDate.compareTo(LocalDate.now()) < 0) {
                    scheduleP.printInvalidDate();
                    ValidDate = false;
                } else{
                    ValidDate = true;
                }
            }catch (Exception e){
                scheduleP.printInvalidDateFormat();
            }

        } while (!ValidDate);

        //enter time, check time
        String inputTime;
        boolean ValidTime;
        do {
            Scanner scanT = new Scanner(System.in);
            scheduleP.displayStartTimes();
            scheduleP.printEnterTime();
            inputTime = scanT.nextLine();
            if (!eventManager.getStartTimes().contains(inputTime)) {
                scheduleP.printFailStartTimes();
                ValidTime = false;
            } else {
                ValidTime = true;
            }
        } while (!ValidTime);

        //combine the date and time into one LocalDateTime instance
        LocalDateTime eventTime = LocalDateTime.parse(inputDate + "T" + inputTime + ":00:00");

        String room;
        boolean ValidRoom = false;
        do {
            Scanner scan2 = new Scanner(System.in);
            scheduleP.displayRoomList(eventTime);
            room = scan2.nextLine();
            try {
                Integer intRoom = Integer.parseInt(room);
                if (room.equals("0")) {
                    return;
                } else if (!eventManager.getAvailableRooms(eventTime).contains(intRoom)){
                    scheduleP.printFailRoom();
                    ValidRoom = false;
                } else {
                    ValidRoom = true;
                }
            }catch (Exception e){
                scheduleP.printFailRoom();
            }

        } while (!ValidRoom);



        String speaker;
        boolean ValidSpeaker;
        do {
            Scanner scanS = new Scanner(System.in);
            scheduleP.displaySpeakerList(userManager.getSpeakerList(), eventTime);
            speaker = scanS.nextLine();
            if (speaker.equals("0")){
                return;
            } else if (!eventManager.getAvailableSpeakers(userManager.getSpeakerList(), eventTime).contains(speaker)) {
                scheduleP.printFailSpeaker();
                ValidSpeaker = false;
            } else {
                ValidSpeaker = true;
            }
        } while (!ValidSpeaker);

        Integer intRoom = Integer.parseInt(room);
        this.callAddEvent(name.trim(), speaker, eventTime, eventManager.getRoom(intRoom));
    }


    /**
     * Checks if this username already exists. Returns true if
     * this username is not an existing username of this conference.
     *
     * @param speakerName - name of the speaker
     *
     * @return true if this speaker name is not an existing username of this conference
     */
    private boolean canCreateSpeaker(String speakerName){
        return !userManager.containsUser(speakerName);
    }

    /**
     * Calls SchedulePresenter to prompt the User for the Speaker username and password.
     * Prints if the new speaker account is added to this conference.
     */
    public void addNewSpeaker() {
        String speakerName;
        String password;
        boolean ValidName;
        boolean ValidPassword;
        do {
            Scanner scan = new Scanner(System.in);
            scheduleP.addSpeaker();
            speakerName = scan.nextLine();
            if (speakerName.equals("0")) {
                return;
            } else if (speakerName.trim().isEmpty() || userManager.isDuplicate(speakerName)
                    || speakerName.contains(" ")) {
                scheduleP.failedUsername();
                ValidName = false;
            } else {
                ValidName = true;
            }
        } while (!ValidName);

        do {
            Scanner scan1 = new Scanner(System.in);
            scheduleP.addSpeakerPassword();
            password = scan1.nextLine();
            if (password.trim().isEmpty() || password.contains(" ")) {
                scheduleP.failedPassword();
                ValidPassword = false;
            } else {
                ValidPassword = true;
            }
        } while (!ValidPassword);


        if(canCreateSpeaker(speakerName)) {
            userManager.createUser(speakerName, password, "Speaker");
            scheduleP.successSpeaker();
        } else {
            scheduleP.failedSpeaker();
        }
    }

    /**
     * Calls the addEvent() method of EventManager and calls SchedulePresenter to print
     * if a event is successfully created.
     *
     * @param name - name of the event wanted to be created (receive from UI)
     * @param speaker - name of the speaker of the event wanted to be created (receive from UI)
     * @param time - the occurring time of the event wanted to be created (receive from UI)
     * @param room - the occurring room of the event wanted to be created (receive from UI)
     */
    public void callAddEvent(String name, String speaker,
                             LocalDateTime time, Pair<Integer, Integer> room){
        // create a speaker account if this speaker haven't have an account yet.
        if(!userManager.isSpeaker(speaker)){
            scheduleP.createEventResult(false, name, speaker, time, room);
        }
        eventManager.addEvent(name, speaker, time, room);
        scheduleP.createEventResult(true, name, speaker, time, room);
    }

    /**
     * Prints that the scheduling system has been exited.
     */
    public void endScheduling(){
        scheduleP.printEndScheduling();
    }

    /**
     * Prints that the user has put an invalid option in for the Menu.
     */
    public void failScheduleMenu(){
        scheduleP.printFailScheduleMenu();
    }
}

