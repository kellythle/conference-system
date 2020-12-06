Instructions:

Table of Contents:
    - [Important Note for First Time Running the Program]
    - [Reading and Writing to Files]
    - [Login System]
    - [Sign Up System]
    - [Schedule System]
    - [Message System]
    - [Questions for TA]



======== [Important Note for First Time Running the Program] ========
If this is the first time the program has been run, the message "Unable to read in data from the files." and a list
of options will be displayed, since the users.ser, events.ser, and messages.ser files do not exist.

Select option 2 in order to start the program with no user, event, and message data being read in.
After logging out of the program, if the data is written to the files successfully, then this message will not
appear when running the program again (provided there are no other issues while reading from the files).

For more information, please refer to the [Reading and Writing to Files] section of this readme.

The file paths of the files is with the assumption that the group_0210 directory is the working directory.



======== [Reading and Writing to Files] ========
Every time the program starts running, it will attempt to read the files users.ser, events.ser, and messages.ser.
If these files are successfully read, a success message will be displayed in the console.
If any of these files do not exist, could not be opened, contain invalid data or data created by an earlier version of
the program, a message indicating that files could not be read and an option menu will be displayed.

This option menu includes the following:
    1. Retry reading from the files.
    2. Continue using the program without reading in any data from the files. The program will start off containing
       no user, event, or message data.
    3. Exit the program.

Warning: If option 2 is selected and the files exist, they will be overwritten once the user logs out (provided that
writing to the files was successful).

Every time the user logs out, the program will attempt to write to the files users.ser, events.ser, and messages.ser.
If these files are successfully written to, a success message will be displayed in the console.
If any of these files could not be opened, a message indicating the files could not be written to and an option menu
will be displayed.

This option menu includes the following:
    1. Retry writing to the files.
    2. Exit the program.



======== [Login System] ========
The login system give user access to sign in an existed account or register a new account.
1. Once you use the application, there is a start menu coming with 2 options: Sign in and register. You can choose either
of them. If the user input unrelated strings, the system will notify the user to follow the instruction.
2. For new user, choosing 'register' and you will enter register menu. You can choose the user type. For p1 we don't set
any restrictions on username and password so you input anything as well as it is not empty, on which case the system will
warn you not leaving it empty.
3. For existed user, choosing 'sign in ', logging in and you will see the main menu if it is a successful login.Try again
 if you input anything wrong. For different type of user you can different stuff.
(a). For organizer you can create other users, access to sign up system, schedule system, messaging system, play math games
and logout option.
(b). For speaker you can only access to sign up system, messaging system, play math games and logout.
(c). For attendee you can access to sign up system and message system, play math games as well as an option to logout.
(d). If you choose logout on any menu there is a logout menu to assure whether you want logout or still keep login. If
you choose to logout you will logout successfully if the system tell you that.
(e). In any menu if you are too naughty to input something outside the options, our system will inform you to follow our
instructions politely.



======== [Sign Up System] ========
This system helps you sign up for events or delete registered events.
1. Sign Up System is accessible for Attendees and Organizers, but not accessible for Speaker.
2. On the Sign Up System menu, there are 5 options,
    1. Sign up an event
    2. Delete an registered event
    3. See events
    4. Exit Sign Up System
Enter the number that you want to process. For example, enter 1 to sign up.
3. If you entered 1, you will first see all existing events. You will be required to enter an
event's name. After entering the name, the system will tell you the sign up result, then
automatically return to Sign Up System menu.
4. If you entered 2, you will first see all your registered events. You will be required to
enter an event's name. After entering the name, the system will tell you the deletion result,
then automatically return to Sign Up System menu.
5. If you entered 3, you will see options of how you can see the events.
(All events, Registered events, Events of a Day, Events of a Time,
Events of a Speaker, and My events[only for Speakers])
6. If you entered 4, you will jump back to Main menu.



======== [Schedule System] ========
This system helps create events or speaker accounts.
1. Schedule System is accessible for only Organizers.
2. On the Schedule System menu, there are 3 options:
    1. Create an event
    2. Create a Speaker
    3. Exit Schedule System
Enter the number you want to process. For example, enter 1 to create an event.
3. If you entered 1, you will need to enter various information about the event you are trying
to create when prompted. First, you need to enter an event name. Second, you must enter a valid
date. Third you must enter a valid time. Fourth, you must enter a valid room. Fifth, you must
assign a valid speaker for your event.
4. If you entered 2, you will create a speaker by entering their username and then password when prompted.
5. If you entered 3, you will jump back to the Main Menu.



======== [Message System] ========
This system helps view or send messages.
1. Message System is accessible for all Users, but menu options will be generated for different types
of users.
2. On the Message System menu, there are these options:
    For Attendees   0. Exit Message Menu.
           & VIPS:  1. View conversations and reply to messages.
                    2. Send a message to a single user.
                    3. View archived messages.
                    4. Manage friend list.
    For Speakers:   0. Exit Message Menu.
                    1. View conversations and reply to messages.
                    2. Send a message to Attendees of your Talks.
                    3. View archived conversations.
                    4. Manage friend list.
    For Organizers: 0. Exit Message Menu.
                    1. View conversations.
                    2. Send a message to a single user.
                    3. Send a message to all Speakers.
                    4. Send a message to all Attendees.
                    5. View archived messages.
                    6. View trash bin (system messages deleted by both its sender and receiver).
    To access each of these options, enter the number corresponding to the option.
3. For all users, if you entered 1, you will first view a list of all users with whom you have conversation
histories. Enter the username of the user whose conversation you wish to view. Speakers will be able to respond
to a conversations with Attendees, and Attendees will be able to respond to conversations with Attendees and
Speakers. Organizers do not need the option to respond to conversations, because other Users cannot message
Organizers. All users will be able to archive or delete conversations or individual messages. For Speakers and
Attendees, the deletion will only occur in their own inbox. For Organizers and VIPs, the message will also be
deleted from their conversation partner's inbox.
4. For messaging options, the user will be prompted for which individual username or subset of users they wish to message.
For example, if a Speaker enters 2, they will be prompted to choose between messaging Attendees of all their talks, or
Attendees of a specific talk. If they choose a specific talk, then they will be prompted to enter the ID of the event
as well as the content of the message.
5. All users can view their archived messages, and delete archived conversations or unarchive conversations. Organizers
can also view the system's message trash bin, view messages that have been fully deleted by both its sender and receiver
(or by an Organizer or VIP), and clear the system's storage of fully deleted messages.
6. Speakers, Attendees, and VIPs can manage their friend requests. Organizers can message anyone, so they don't have
friend lists.
7. Entering 0 for any type of User will return you to the main menu.



======== [Math Game System] ========
This system allows you to play some math games. Including summation, subtraction, multiplication, and division.
When you first enter the Math Game System, you will have to select a level.
    1. Easy
    2. Normal
    3. Hard
    4. Exit Game
If you entered 4, you will exist the Game System. Otherwise, you will see the game mode menu.
    1. Summation
    2. Subtraction
    3. Multiplication
    4. Division
    5. Exit Game
If you entered 5, you will exist the Game System. Otherwise your game will start!
Each game contains 5 questions. After each question, the system will tell you
whether you get the correct answer or not. When the game ends, you are also able
to see your final result.