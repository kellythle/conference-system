package Controller;

import Entity.UserAccount;
import Entity.Event;
import Presenter.SchedulePresenter;
import UseCase.EventManager;
import UseCase.UserManager;
import javafx.util.Pair;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

/**
 * A controller class that calls EventManager to manage any
 * command related to adding events.
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
     * Calls SchedulePresenter to retrieve the name, time, room, and date and prints out the event details
     * if successful.
     */
    public void createEvent(){
        Scanner scan = new Scanner(System.in);
        scheduleP.printName();
        String name = scan.nextLine();
        if (name.equals("0")) {
            return;
        }

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
        boolean ValidTime = false;
        do {
            Scanner scanT = new Scanner(System.in);
            scheduleP.displayStartTimes();
            scheduleP.printEnterTime();
            inputTime = scanT.nextLine();
            int intT = Integer.parseInt(inputTime);
            if (!(eventManager.getStartTime() <= intT && intT < eventManager.getEndTime())) {
                scheduleP.printFailStartTimes();
                ValidTime = false;
            } else {
                ValidTime = true;
            }
        } while (!ValidTime);

        //combine the date and time into one LocalDateTime instance
        LocalDateTime eventTime = LocalDateTime.parse(inputDate + "T" + inputTime + ":00:00");

        Scanner scan2 = new Scanner(System.in);
        scheduleP.displayRoomList(eventTime);
        String room = scan2.nextLine();
        Integer intRoom = Integer.parseInt(room);
        if (!scheduleP.availableRooms(eventTime).contains(intRoom)){
            scheduleP.printFailRoom();
            return;
        }

        Scanner scan3 = new Scanner(System.in);
        scheduleP.displaySpeakerList(userManager.getSpeakerList(), eventTime);
        String speaker = scan3.nextLine();
        if (!scheduleP.availableSpeakers(userManager.getSpeakerList(), eventTime).contains(speaker)) {
            scheduleP.printFailSpeaker();
            return;
        }
        this.callAddEvent(name, speaker, eventTime, eventManager.getRoom(intRoom));
    }


    /**
     * Checks if this username already exists. Returns true if
     * this username is not an existing username of this conference.
     *
     * @param speakerName - name of the speaker
     *
     * @return true if this speaker name is not an existed username of this conference
     */
    private boolean canCreateSpeaker(String speakerName){
        HashMap<String, UserAccount> userMap = userManager.getUserMap();

        return !userMap.containsKey(speakerName);
    }

    /**
     * Prints whether or not the new speaker account is added to this conference.
     */
    public void addNewSpeaker() {
        Scanner scan = new Scanner(System.in);
        scheduleP.addSpeaker();
        String speakerName = scan.nextLine();
        Scanner scan1 = new Scanner(System.in);
        scheduleP.addSpeakerPassword();
        String password = scan1.nextLine();
        if(canCreateSpeaker(speakerName)) {
            userManager.createUser(speakerName, password, "Speaker");
            scheduleP.successSpeaker();
        } else {
            scheduleP.failedSpeaker();
        }
    }

    /**
     * Calls the addEvent() method of EventManager and prints
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
        Event event = new Event (name, speaker, time, room);
        if(!userManager.isSpeaker(speaker)){
            scheduleP.createEventResult(false, event);
        }
        eventManager.addEvent(name, speaker, time, room);
        scheduleP.createEventResult(true, event);
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

