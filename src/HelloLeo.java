import java.util.Scanner;
public class HelloLeo {
    public static void main (String[] args){
        new HelloLeo();
    }
    League myLeague = new League();
    Team team1 = new Team("Team 1");
    Team team2 = new Team("Team 1");
    public HelloLeo() {
        //circle();
        myLeague.create();
        myLeague.createSchedule();
        Scanner myScanner = new Scanner(System.in);
        String input;
        while (myLeague.week < myLeague.schedule.length) {
            System.out.println("Input S to simulate 1 week of gameplay.\n" +
                    "Input L to view league standings.\n" +
                    "Input T to view team information.\n" +
                    "Input E to edit teams.\n" +
                    "Input Q to end league.");
            input = myScanner.next();
            if(input.equals("S")){
                myLeague.simulateWeek();
            } else if (input.equals("L")){
                myLeague.printInfo();
            } else if (input.equals("T")){
                for (int i = 0; i < myLeague.teams.length; i++) {
                    System.out.print("(" + i + ") - " + myLeague.teams[i].teamName);
                    if((i+1)%3 == 0){
                        System.out.println("");
                    } else{
                        for (int j = 0; j < 25-myLeague.teams[i].teamName.length(); j++) {
                            System.out.print(" ");
                        }
                    }
                }
                System.out.println("");
                System.out.println("Enter the number next to the team you wish to view the information of.");
                int teamInput = -1;
                while (teamInput < 0 || teamInput >=myLeague.teams.length){
                    teamInput = myScanner.nextInt();
                }
                myLeague.teams[teamInput].printInfo();
            } else if (input.equals("Q")){
                myLeague.week = myLeague.schedule.length;
                System.out.println("The league ended after " + myLeague.week + " weeks.");
            } else if(input.equals("E")){
                for (int i = 0; i < myLeague.teams.length; i++) {
                    System.out.print("(" + i + ") - " + myLeague.teams[i].teamName);
                    if((i+1)%3 == 0){
                        System.out.println("");
                    } else{
                        for (int j = 0; j < 25-myLeague.teams[i].teamName.length(); j++) {
                            System.out.print(" ");
                        }
                    }
                }
                System.out.println("");
                System.out.println("Enter the number next to the team you wish to edit");
                int teamInput = -1;
                while (teamInput < 0 || teamInput >=myLeague.teams.length){
                    teamInput = myScanner.nextInt();
                }
                myLeague.teams[teamInput].printInfo();
                boolean editing = true;
                while (editing) {
                    System.out.println("Enter N to edit team name.\n" +
                            "Enter Y to edit year established.\n" +
                            "Enter P to edit player information.\n" +
                            "Enter C to return to the main menu.");
                    String editInput = myScanner.next();
                    if (editInput.equals("N")) {
                        System.out.println("Enter new team name:");
                        myLeague.teams[teamInput].teamName = myScanner.next();
                        System.out.println("Great! " + myLeague.teams[teamInput].teamName + " it is!");
                    } else if (editInput.equals("Y")) {
                        System.out.println("Enter year established:");
                        myLeague.teams[teamInput].year = myScanner.nextInt();
                    } else if (editInput.equals("P")) {
                        for (int i = 0; i < myLeague.teams[teamInput].players.length; i++) {
                            System.out.print("(" + i + ") - " + myLeague.teams[teamInput].players[i].name);
                            if((i+1)%3 == 0){
                                System.out.println("");
                            } else{
                                for (int j = 0; j < 25-myLeague.teams[teamInput].players[i].name.length(); j++) {
                                    System.out.print(" ");
                                }
                            }
                        }
                        System.out.println("");
                        System.out.println("Enter the number next to the player you wish to edit");
                        int playerInput = -1;
                        while (playerInput < 0 || playerInput >=myLeague.teams[teamInput].players.length){
                            playerInput = myScanner.nextInt();
                        }
                        boolean editingPlayer = true;
                        while (editingPlayer) {
                            System.out.println("Enter P to edit player name.\n" +
                                    "Enter N to edit player number.\n" +
                                    "Enter R to edit player rating.\n" +
                                    "Enter C to return to the editing menu.");
                            String playerEditInput = myScanner.next();
                            if(playerEditInput.equals("P")){
                                System.out.println("Enter player name:");
                                myLeague.teams[teamInput].players[playerInput].name = myScanner.next();
                                System.out.println("Great! " + myLeague.teams[teamInput].players[playerInput].name + " it is!");
                            } else if(playerEditInput.equals("N")){
                                System.out.println("Enter player number:");
                                myLeague.teams[teamInput].players[playerInput].number = myScanner.nextInt();
                            } else if(playerEditInput.equals("R")){
                                int r = -1;
                                System.out.println("Enter player rating (0-99):");
                                r = myScanner.nextInt();
                                while (r < 0 || r > 99){
                                    System.out.println("Please enter a number between 0 and 99:");
                                    r = myScanner.nextInt();
                                }
                                myLeague.teams[teamInput].players[playerInput].skillLevel = r;
                                myLeague.teams[teamInput].updateSkillLevels();
                            } else if(playerEditInput.equals("C")){
                                editingPlayer = false;
                            } else {
                                System.out.println("Sorry, I didn't catch that.");
                            }
                        }
                    } else if (editInput.equals("C")) {
                        editing = false;
                    } else {
                        System.out.println("Sorry, I didn't catch that.");
                    }
                }
            } else {
                System.out.println("Sorry, I didn't catch that.");
            }
        }
        myLeague.printInfo();

    }
    public void circle(){
        Scanner myScanner = new Scanner(System.in);
        System.out.println("Enter diameter:");
        int diameter = myScanner.nextInt();
        for(double i = 0.5-(diameter/2); i <= diameter/2-0.5; i++){
            for(double j = 0.5-(diameter/2); j <= diameter/2-0.5; j++){
                if(i*i+j*j<=(diameter/2)*(diameter/2)) {
                    System.out.print("##");
                } else {
                    System.out.print("  ");
                }
            }
            System.out.println("");
        }
    }

}
