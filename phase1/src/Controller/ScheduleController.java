package Controller;

import Entity.UserAccount;
import Entity.Event;
import Presenter.SchedulePresenter;
import UseCase.EventManager;
import UseCase.UserManager;
import javafx.util.Pair;

import java.util.ArrayList;
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
    private final SchedulePresenter schp = new SchedulePresenter();

    /**
     * Creates a instance of ScheduleController with a ManagerFacade instance.
     * @param em - an EventManager instance
     * @param um - an UserManager instance
     */
    public ScheduleController(EventManager em, UserManager um){
        this.eventManager = em;
        this.userManager = um;
    }

    /**
     * Calls SchedulePresenter to print out the Scheduling Menu.
     */
    public String getScheduleMenu(){
        Scanner scan = new Scanner(System.in);
        schp.printScheduleMenu();
        String option = scan.nextLine();
        // do we need a presenter for when the user doesnt input the correct number?
        return scan.nextLine();
    }

    /**
     * Calls SchedulePresenter to retrieve the name, time, room, and date and prints out the event details
     * if successful.
     */
    public void createEvent(){
        Scanner scan = new Scanner(System.in);
        schp.printName();
        String name = scan.nextLine();
        if (name.equals("0")) {
            return;
        }

        Scanner scan1 = new Scanner(System.in);
        schp.displayStartTimes();
        String time = scan1.nextLine();
        int intTime = Integer.parseInt(time);
        if (!(eventManager.getStartTime() <= intTime && intTime < eventManager.getEndTime())){
            schp.printFailStartTimes();
            return;
        }

        Scanner scan2 = new Scanner(System.in);
        schp.displayRoomList(time);
        String room = scan2.nextLine();
        Integer intRoom = Integer.parseInt(room);
        if (!schp.availableRooms(time).contains(intRoom)){
            schp.printFailRoom();
            return;
        }

        Scanner scan3 = new Scanner(System.in);
        schp.displaySpeakerList(userManager.getSpeakerList(), time);
        String speaker = scan3.nextLine();
        if (!schp.availableSpeakers(userManager.getSpeakerList(), time).contains(speaker)) {
            schp.printFailSpeaker();
            return;
        }
        this.callAddEvent(name, speaker, time, eventManager.getRoom(intRoom));
    }


    /**
     * Checks if this username already exists. Returns true if
     * this username is not an existed username of this conference.
     *
     * @param speakerName - name of the speaker
     * @return true if this speaker name is not an existed username of this conference
     */
    private boolean canCreateSpeaker(String speakerName){
        HashMap<String, UserAccount> userMap = userManager.getUserMap();

        return !userMap.containsKey(speakerName);
    }

    /**
     * Prints if the new speaker account is added to this conference.
     */
    public void addNewSpeaker() {
        Scanner scan = new Scanner(System.in);
        schp.addSpeaker();
        String speakerName = scan.nextLine();
        Scanner scan1 = new Scanner(System.in);
        schp.addSpeakerPassword();
        String password = scan1.nextLine();
        if(canCreateSpeaker(speakerName)) {
            userManager.createUser(speakerName, password, "Speaker");
            schp.successSpeaker();
        }
        schp.failedSpeaker();
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
                             Date time, Pair<Integer, Integer> room){
        // create a speaker account if this speaker haven't have an account yet.
        Event event = new Event (name, speaker, time, room);
        if(!userManager.isSpeaker(speaker)){
            schp.createEventResult(false, event);
        }
        eventManager.addEvent(name, speaker, time, room);
        schp.createEventResult(true, event);
    }

    /**
     * Prints that the scheduling system has been exited.
     */
    public void endScheduling(){
        schp.printEndScheduling();
    }
}

