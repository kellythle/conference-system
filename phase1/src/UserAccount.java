import java.util.ArrayList;
abstract class UserAccount {
    private String userName;
    private String password;
    private ArrayList<String> friendList; // stores the userNames
    private ArrayList<Integer> messageSent;
    private ArrayList<Integer> messageRecieved;

    //**TODO getters setters constructors
    public abstract boolean isOrganizer() ;
    public abstract boolean isSpeaker() ;
}