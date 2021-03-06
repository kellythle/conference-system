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
(a). For organizer you can access to sign up system, schedule system where you can either create a new talk or assign
the a speaker to a talk, and logout option.
(b). For speaker you can only enter messaging system and logout.
(c). For attendee you can access to sign up system and message system as well as an option to logout.
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
    3. See all events
    4. See registered events
    5. Exit Sign Up System
Enter the number that you want to process. For example, enter 1 to sign up.
3. If you entered 1, you will first see all existing events. You will be required to enter an
event's name. After entering the name, the system will tell you the sign up result, then
automatically return to Sign Up System menu.
4. If you entered 2, you will first see all your registered events. You will be required to
enter an event's name. After entering the name, the system will tell you the deletion result,
then automatically return to Sign Up System menu.
5. If you entered 3, you will see all existing events.
6. If you entered 4, you will see all your registered events.
7. If you entered 5, you will jump back to Main menu.



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
    For Attendees:  0. Exit Message Menu.
                    1. View conversations and reply to messages.
                    2. Send a message to a single user.
    For Speakers:   0. Exit Message Menu.
                    1. View conversations and reply to messages.
                    2. Send a message to Attendees of your Talks.
    For Organizers: 0. Exit Message Menu.
                    1. View conversations.
                    2. Send a message to a single user.
                    3. Send a message to all Speakers.
                    4. Send a message to all Attendees.
    To access each of these options, enter the number corresponding to the option.
3. For all users, if you entered 1, you will first view a list of all users with whom you have conversation
histories. Enter the username of the user whose conversation you wish to view. Speakers will be able to respond
to a conversations with Attendees, and Attendees will be able to respond to conversations with Attendees and
Speakers. Organizers do not need the option to respond to conversations, because other Users cannot message
Organizers.
4. For other options, the user will be prompted for which individual username or subset of users they wish to message.
For example, if a Speaker enters 2, they will be prompted to choose between messaging Attendees of all their talks, or
Attendees of a specific talk. If they choose a specific talk, then they will be prompted to enter the ID of the event
as well as the content of the message.
5. Entering 0 for any type of User will return you to the main menu.



======== [Questions for TA] ========
- Please provide feedback on the Gateway design and design of option menus for handling errors with file reading and
writing (ex: should there be more options for the user to handle these errors), if there is time. Thank you!