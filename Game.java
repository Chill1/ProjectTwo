import java.util.Scanner;

public class Game {

    // Globals
    public static final boolean DEBUGGING = false;   // Debugging flag.

    public static int currentLocale = 0;            // Player starts in locale 0.
    public static String command;                   // What the player types as he or she plays the game.
    public static boolean stillPlaying = true;      // Controls the game loop.
    public static Items[] interaction;
    public static Items[] taken;
    public static Locale[] locations;               // An uninitialized array of type Locale. See init() for initialization.
    public static int[][]  nav;                     // An uninitialized array of type int int.
    public static int moves = 0;                    // Counter of the player's moves.
    public static int score = 0;                    // Tracker of the player's score.





    public static void main(String[] args) {
        if (DEBUGGING) {
           // Display the command line args.
           System.out.println("Starting with args:");
           for (int i = 0; i < args.length; i++) {
               System.out.println(i + ":" + args[i]);
           }
        }



        // Get the game started.
        init();
        updateDisplay();

        // Game Loop
        while (stillPlaying) {
            getCommand();
            navigate();
            updateDisplay();
        }

        // We're done. Thank the player and exit.
        System.out.println("Thank you for playing.");
    }


    private static void init() {
        // Initialize any uninitialized globals.
        command = new String();
        stillPlaying = true;

        // Set up the location instances of the Locale class.
        FirstFloor loc0 = new FirstFloor(0);
        loc0.setName("Living Room (Shop)");
        loc0.setDesc("You are greeted by Colonel Mustard. He tells you there has been a murder in this house. He hands you a map and a camera. He shows more items you may consider taking. \n" +
                     "[Item: DNA Scanner. This device scans substances for DNA.] \n" +
                     "[Item: Deerstalker hat. This hat improves the detective stat by 10.] \n" +
                     "[Item: B.F. Sword. This sword grants +45 Attack Damage.] \n" +
                     "Just tell Colonel Mustard which item you want to take. \n" +
                     "You can go north, east or west from here");


        FirstFloor loc1 = new FirstFloor(1);
        loc1.setName("Kitchen");
        loc1.setDesc("The kitchen seems unscathed until the maid, Mrs. White, tells you to check the sink. In the sink you notice a knife covered in blood. \n" +
                    "You can go east from here");


        FirstFloor loc2 = new FirstFloor(2); // Locale(2);
        loc2.setName("Library");
        loc2.setDesc("A man named Professor Plum shows you an opened book. The book is titled 'How to Hide a Body for Dummies'. Prof. Plum tells you someone was reading it before the murder. \n" +
                    "You can go north or west from here");


        FirstFloor loc3 = new FirstFloor(3);
        loc3.setName("Lounge");
        loc3.setDesc("Miss Scarlet and Mrs. Peacock are smoking in the lounge. They offer to tell you what they saw. \n" +
                    "You can go north or south from here");


        SecondFloor loc4 = new SecondFloor(4);
        loc4.setName("Hall");
        loc4.setDesc("There is a knocked over table and a picture on the wall of the home owner. \n" +
                    "You can go north, south, east or west from here");



        SecondFloor loc5 = new SecondFloor(5);
        loc5.setName("Bathroom");
        loc5.setDesc("There is a dead body in bathtub. The body belongs to the home owner. \n" +
                    "You can go east from here");


        SecondFloor loc6 = new SecondFloor(6);
        loc6.setName("Master Bed");
        loc6.setDesc("It looks as if a tornado has gone through the room. You notice the safe is open and empty. \n" +
                    "You can go south from here");


        SecondFloor loc7 = new SecondFloor(7);
        loc7.setName("Guest Bed");
        loc7.setDesc("There is a briefcase full of money and you also notice a secret pathway leading south. \n" +
                    "You can go south or west from here.");


        // Set up the location array.
        locations = new Locale[8];
        locations[0] = loc0; // "Living Room";
        locations[1] = loc1; // "Kitchen";
        locations[2] = loc2; // "Library";
        locations[3] = loc3; // "Lounge";
        locations[4] = loc4; // "Hall";
        locations[5] = loc5; // "Bathroom";
        locations[6] = loc6; // "Master Bed";
        locations[7] = loc7; // "Guest Bed";

        //Instances for items
        Items item0 = new Items(0);
        item0.setName("A photo of the bloody knife");
        item0.setDesc("You took a photo of the bloody knife in the sink.");

        Items item1 = new Items(1);
        item1.setName("A photo of 'How to Hide a Body for Dummies'");
        item1.setDesc("You took a photo of the opened book.");

        Items item2 = new Items(2);
        item2.setName("Audio recording of the Witness' Story");
        item2.setDesc("You record Mrs. Peacock and Miss Scarlet claim that they saw a green man frantically leave the house.");

        Items item3 = new Items(3);
        item3.setName("Picture of house owner.");
        item3.setDesc("You take the picture that is a portrait of a middle aged man, the house owner.");

        Items item4 = new Items(4);
        item4.setName("Photo of the victim's body");
        item4.setDesc("You take a photo of the victim. It's the house owner who is dead in the bathtub.");

        Items item5 = new Items(5);
        item5.setName("A photo of the opened safe");
        item5.setDesc("The safe in the owner's room was broken into and is now empty. You took a photo of it.");

        Items item6 = new Items(6);
        item6.setName("A photo of the briefcase full of money");
        item6.setDesc("There was a briefcase full of money on the guest bed. You took a photo of it.");

        Items item7 = new Items(7);
        item7.setName("DNA Scanner");
        item7.setDesc("This device scans substances for DNA.");

        Items item8 = new Items(8);
        item8.setName("Deerstalker hat");
        item8.setDesc("This hat improves the criminal justice stat by 10");

        Items item9 = new Items(9);
        item9.setName("B.F. Sword");
        item9.setDesc("This sword grants +45 Attack Damage.");


        //Item array
        interaction = new Items[10];
        interaction[0] = item0;
        interaction[1] = item1;
        interaction[2] = item2;
        interaction[3] = item3;
        interaction[4] = item4;
        interaction[5] = item5;
        interaction[6] = item6;
        interaction[7] = item7;
        interaction[8] = item8;
        interaction[9] = item9;

        Items nottaken0 = new Items(0);
        nottaken0.setName("empty slot");
        nottaken0.setDesc("");

        Items nottaken1 = new Items(1);
        nottaken1.setName("empty slot");
        nottaken1.setDesc("");

        Items nottaken2 = new Items(2);
        nottaken2.setName("empty slot");
        nottaken2.setDesc("");

        Items nottaken3 = new Items(3);
        nottaken3.setName("empty slot");
        nottaken3.setDesc("");

        Items nottaken4 = new Items(4);
        nottaken4.setName("empty slot");
        nottaken4.setDesc("");

        Items nottaken5 = new Items(5);
        nottaken5.setName("empty slot");
        nottaken5.setDesc("");

        Items nottaken6 = new Items(6);
        nottaken6.setName("empty slot");
        nottaken6.setDesc("");

        Items nottaken7 = new Items(7);
        nottaken7.setName("empty slot");
        nottaken7.setDesc("");

        Items nottaken8 = new Items(8);
        nottaken8.setName("empty slot");
        nottaken8.setDesc("");

        Items nottaken9 = new Items(9);
        nottaken9.setName("empty slot");
        nottaken9.setDesc("empty slot");


        taken = new Items[10];
        taken[0] = nottaken0;
        taken[1] = nottaken1;
        taken[2] = nottaken2;
        taken[3] = nottaken3;
        taken[4] = nottaken4;
        taken[5] = nottaken5;
        taken[6] = nottaken6;
        taken[7] = nottaken7;
        taken[8] = nottaken8;
        taken[9] = nottaken9;


        if (DEBUGGING) {
           System.out.println("All game locations:");
           for (int i = 0; i < locations.length; ++i) {
              System.out.println(i + ":" + locations[i].toString());
           }
        }
        // Set up the navigation matrix.
        nav = new int[][] {
                                 /* N   S   E   W */
                                 /* 0   1   2   3 */
         /* nav[0] for loc 0 */  {  4, -1,  2,  1 },
         /* nav[1] for loc 1 */  { -1, -1,  0, -1 },
         /* nav[2] for loc 2 */  {  3, -1, -1,  0 },
         /* nav[3] for loc 3 */  {  7,  2, -1, -1 },
         /* nav[4] for loc 4 */  {  6,  0,  7,  5 },
         /* nav[5] for loc 5 */  { -1, -1,  4, -1 },
         /* nav[6] for loc 6 */  { -1,  4, -1, -1 },
         /* nav[7] for loc 7 */  { -1,  3, -1,  4 }

        };


    }


    private static void updateDisplay() {
        System.out.println("\nLocation: " + locations[currentLocale].getName());
        System.out.println(locations[currentLocale].getDesc());
    }

    private static void getCommand() {
        System.out.print("[" + moves + " moves, score " + score + "] \n");
        Scanner inputReader = new Scanner(System.in);
        command = inputReader.nextLine();  // command is global.
    }

    private static void navigate() {
        final int INVALID = -1;
        int dir = INVALID;  // This will get set to a value > 0 if a direction command was entered.

        if (        command.equalsIgnoreCase("north") || command.equalsIgnoreCase("n") ) {
            dir = 0;
        } else if ( command.equalsIgnoreCase("south") || command.equalsIgnoreCase("s") ) {
            dir = 1;
        } else if ( command.equalsIgnoreCase("east")  || command.equalsIgnoreCase("e") ) {
            dir = 2;
        } else if ( command.equalsIgnoreCase("west")  || command.equalsIgnoreCase("w") ) {
            dir = 3;
        } else if ( command.equalsIgnoreCase("quit")  || command.equalsIgnoreCase("q")) {
            quit();
        } else if ( command.equalsIgnoreCase("help")  || command.equalsIgnoreCase("h")) {
            help();
        }  else if ( command.equalsIgnoreCase("take")  || command.equalsIgnoreCase("t")) {
            take();
        }  else if ( command.equalsIgnoreCase("inventory")  || command.equalsIgnoreCase("i")) {
            inv();
        }  else if ( command.equalsIgnoreCase("map")  || command.equalsIgnoreCase("m")) {
            map();
        } else if ( command.equalsIgnoreCase("DNA Scanner")  && currentLocale == 0) {
            takedna();
        } else if ( command.equalsIgnoreCase("Deerstalker hat")  && currentLocale == 0) {
            takehat();
        } else if ( command.equalsIgnoreCase("B.F. Sword")  && currentLocale == 0) {
            takesword();
        }

        ;


        if (dir > -1) {   // This means a dir was set.
            int newLocation = nav[currentLocale][dir];
            if (newLocation == INVALID) {
                System.out.println("You cannot go that way.");
            } else {
                currentLocale = newLocation;
                moves = moves + 1;
                if (locations[newLocation].getHasVisited() == false && newLocation != 0){
                    score = score + 5;
                    locations[newLocation].setHasVisited(true);
                 }

                }

            }
        }


    private static void help() {
        System.out.println("The commands are as follows:");
        System.out.println("   n/north");
        System.out.println("   s/south");
        System.out.println("   e/east");
        System.out.println("   w/west");
        System.out.println("   t/take");
        System.out.println("   i/inventory");
        System.out.println("   m/map");
        System.out.println("   q/quit");
        System.out.println("If you are in the living room, these commands work: ");
        System.out.println("   DNA Scanner");
        System.out.println("   Deerstalker Hat");
        System.out.println("   B.F. Sword");
    }

    private static void quit() {
        stillPlaying = false;
    }




    private static void take() {


        if(currentLocale == 1){

            taken[0] = interaction[0];
            System.out.println(interaction[0].getDesc());
        }
        else if (currentLocale == 2) {

            taken[1] = interaction[1];
            System.out.println(interaction[1].getDesc());
        }
        else if (currentLocale == 3) {

            taken[2] = interaction[2];
            System.out.println(interaction[2].getDesc());
        }
        else if (currentLocale == 4) {

            taken[3] = interaction[3];
            System.out.println(interaction[3].getDesc());
        }
        else if (currentLocale == 5) {

            taken[4] = interaction[4];
            System.out.println(interaction[4].getDesc());
        }
        else if (currentLocale == 6) {

            taken[5] = interaction[5];
            System.out.println(interaction[5].getDesc());
        }
        else if (currentLocale == 7) {

            taken[6] = interaction[6];
            System.out.println(interaction[6].getDesc());
        }

    }

    private static void inv() {
        System.out.println("Items: Map of House, Camera, " + taken[7] + ", " + taken[8] + ", " + taken[9]+ ", " + taken[0] + ", " + taken[1] + ", " + taken[2] + ", " + taken[3] + ", " + taken[4] + ", " + taken[5] + ", " + taken[6] );
    }
    private static void map() {
        System.out.println("\t\t\t\t   Master Bed\n" +
                "\t\t\t\t\t   |\n" +
                "\tBathroom    -    Hallway   -   Guest Bed\n" +
                "\t\t\t\t\t    |\t\t\t   |\n" +
                "\t \t\t\t   \t    |\t         Lounge\n" +
                "\t\t\t\t\t    |\t\t\t   |\n" +
                "\tKitchen\t   -   Living Room  -   Library");
    }
    private static void takedna(){
        taken[7] = interaction[7];
        System.out.println("You took: " + interaction[7].getName());
    }
    private static void takehat(){
        taken[8] = interaction[8];
        System.out.println("You took: " + interaction[8].getName());
    }
    private static void takesword(){
        taken[9] = interaction[9];
        System.out.println("You took: " + interaction[9].getName());
    }

}