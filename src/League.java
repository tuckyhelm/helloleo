import java.util.Scanner;
import java.util.ArrayList;
public class League {
    Scanner myScanner = new Scanner(System.in);
    Team[] teams = new Team[0];
    int[] standings = new int[0];
    int[][] schedule;
    int week = 0;
    String name;
    public League(){

    }
    public void createSchedule(){
        schedule = new int[teams.length-1][teams.length];
        for (int i = 0; i < schedule.length; i++) {
            if(i == 0){
                for (int j = 0; j < schedule[i].length; j++) {
                    schedule[i][j] = j;
                }
            } else {
                for (int j = 0; j < schedule[i].length; j++) {
                    if(j == 0){
                        schedule[i][j] = schedule[i-1][j];
                    } else if(j == 1){
                        schedule[i][j] = schedule[i-1][j+1];
                    } else if(j == schedule[i].length-2){
                        schedule[i][j] = schedule[i-1][j+1];
                    } else if(j%2 == 0){
                        schedule[i][j] = schedule[i-1][j+2];
                    } else {
                        schedule[i][j] = schedule[i-1][j-2];
                    }
                }
            }
        }
    }
    public void create () {
        System.out.println("It's time to create your league! Please enter a name for the league (without spaces).");
        name = myScanner.next();
        System.out.println("Great! " + name + " it is! Now enter an even number of teams between four and thirty.");
        int teamsNum;
        teamsNum = myScanner.nextInt();
        while (teamsNum < 4 || teamsNum > 30 || teamsNum % 2 != 0) {
            System.out.println("Please enter an even number between four and thirty.");
            teamsNum = myScanner.nextInt();
        }
        System.out.println("Excellent! Now it's time to enter the team names. (Again, don't use spaces.)");
        teams = new Team[teamsNum];
        for (int i = 1; i <= teamsNum; i++) {
            System.out.println("Team " + i + ":");
            teams[i - 1] = new Team(myScanner.next());
        }
        System.out.println("The " + name + " season will take place over " + (teamsNum-1) + " weeks.");
    }
    public void simulateGame (Team teamA, Team teamB){
        int aGoals = 0, bGoals = 0;
        double j;
        double imbalance;
        for (int i = 0; i < teamA.players.length; i++) {
            j = Math.random();
            imbalance = 1+((teamA.players[i].skillLevel-(teamB.defense+teamB.midfield)/2)/20);
            switch (teamA.players[i].position){
                case ("FW"):
                    imbalance *=0.3;
                    while (j < imbalance) {
                        teamA.players[i].goals++;
                        aGoals++;
                        j = Math.random();
                        imbalance*=0.5;
                    }
                    break;
                case ("MF"):
                    imbalance *=0.15;
                    while (j < imbalance) {
                        teamA.players[i].goals++;
                        aGoals++;
                        j = Math.random();
                        imbalance*=0.5;
                    }
                    break;
                case ("DF"):
                    imbalance *=0.05;
                    while (j < imbalance) {
                        teamA.players[i].goals++;
                        aGoals++;
                        j = Math.random();
                        imbalance*=0.5;
                    }
                    break;
                case ("GK"):
                    imbalance *=0.01;
                    while (j < imbalance) {
                        teamA.players[i].goals++;
                        aGoals++;
                        j = Math.random();
                        imbalance*=0.5;
                    }
                    break;
                default:
                    System.out.println("Uh, we gotta problem");
                    break;
            }
            j = Math.random();
            imbalance = 1+((teamB.players[i].skillLevel-(teamA.defense+teamA.midfield)/2)/20);
            switch (teamB.players[i].position){
                case ("FW"):
                    imbalance *=0.3;
                    while (j < imbalance) {
                        teamB.players[i].goals++;
                        bGoals++;
                        j = Math.random();
                        imbalance*=0.5;
                    }
                    break;
                case ("MF"):
                    imbalance *=0.1;
                    while (j < imbalance) {
                        teamB.players[i].goals++;
                        bGoals++;
                        j = Math.random();
                        imbalance*=0.5;
                    }
                    break;
                case ("DF"):
                    imbalance *=0.03;
                    while (j < imbalance) {
                        teamB.players[i].goals++;
                        bGoals++;
                        j = Math.random();
                        imbalance*=0.5;
                    }
                    break;
                case ("GK"):
                    imbalance *=0.005;
                    while (j < imbalance) {
                        teamB.players[i].goals++;
                        bGoals++;
                        j = Math.random();
                        imbalance*=0.5;
                    }
                    break;
                default:
                    System.out.println("Uh, we gotta problem");
                    break;
            }
        }
        teamA.GF += aGoals;
        teamB.GF += bGoals;
        teamA.GA += bGoals;
        teamB.GA += aGoals;
        teamA.GD+=aGoals-bGoals;
        teamB.GD+=bGoals-aGoals;
        if(aGoals>bGoals){
            teamA.points+=3;
            teamA.W++;
            teamB.L++;
        } else if (bGoals>aGoals){
            teamB.points+=3;
            teamB.W++;
            teamA.L++;
        } else {
            teamA.points+=1;
            teamB.points+=1;
            teamA.D++;
            teamB.D++;
        }
        System.out.println(teamA.teamName + " " + aGoals + "-" + bGoals + " " + teamB.teamName);
    }
    public void simulateWeek (){
        System.out.println("Week "+(week+1)+" of "+schedule.length+":");
        for (int i = 0; i < schedule[week].length; i+=2) {
            simulateGame(teams[schedule[week][i]], teams[schedule[week][i+1]]);
        }
        week++;
    }
    public void createStandings (){
        standings = new int[teams.length];
        for (int i = 0; i < standings.length; i++) {
            standings[i] = i;
        }
        int temp = 0;
        for (int i = 0; i < standings.length-1; i++) {
            for (int j = 0; j < standings.length-i-1; j++) {
                if(teams[standings[j]].points < teams[standings[j+1]].points){
                    temp = standings[j];
                    standings[j] = standings[j+1];
                    standings[j+1] = temp;
                } else if(teams[standings[j]].points == teams[standings[j+1]].points &&
                        teams[standings[j]].GD < teams[standings[j+1]].GD){
                    temp = standings[j];
                    standings[j] = standings[j+1];
                    standings[j+1] = temp;
                } else if(teams[standings[j]].points == teams[standings[j+1]].points &&
                        teams[standings[j]].GD < teams[standings[j+1]].GD &&
                        teams[standings[j]].W < teams[standings[j+1]].W){
                    temp = standings[j];
                    standings[j] = standings[j+1];
                    standings[j+1] = temp;
                }
            }
        }
    }
    public void printInfo(){
        createStandings();
        System.out.println("|Team Name-----------PTS---W---D---L---GF---GA---GD---FW---MF---DF---OVR|");
        for (int i = 0; i < standings.length; i++) {
            System.out.print("|");
            System.out.print(teams[standings[i]].teamName);
            for (int j = 0; j < 20-teams[standings[i]].teamName.length(); j++) {
                System.out.print(" ");
            }
            System.out.print(teams[standings[i]].points);
            for (int j = 0; j < 6-Integer.toString(teams[standings[i]].points).length(); j++) {
                System.out.print(" ");
            }
            System.out.print(teams[standings[i]].W);
            for (int j = 0; j < 4-Integer.toString(teams[standings[i]].W).length(); j++) {
                System.out.print(" ");
            }
            System.out.print(teams[standings[i]].D);
            for (int j = 0; j < 4-Integer.toString(teams[standings[i]].D).length(); j++) {
                System.out.print(" ");
            }
            System.out.print(teams[standings[i]].L);
            for (int j = 0; j < 4-Integer.toString(teams[standings[i]].L).length(); j++) {
                System.out.print(" ");
            }
            System.out.print(teams[standings[i]].GF);
            for (int j = 0; j < 5-Integer.toString(teams[standings[i]].GF).length(); j++) {
                System.out.print(" ");
            }
            System.out.print(teams[standings[i]].GA);
            for (int j = 0; j < 5-Integer.toString(teams[standings[i]].GA).length(); j++) {
                System.out.print(" ");
            }
            System.out.print(teams[standings[i]].GD);
            for (int j = 0; j < 5-Integer.toString(teams[standings[i]].GD).length(); j++) {
                System.out.print(" ");
            }
            System.out.print((int)teams[standings[i]].attack);
            for (int j = 0; j < 5-Integer.toString((int) teams[standings[i]].attack).length(); j++) {
                System.out.print(" ");
            }
            System.out.print((int)teams[standings[i]].midfield);
            for (int j = 0; j < 5-Integer.toString((int) teams[standings[i]].midfield).length(); j++) {
                System.out.print(" ");
            }
            System.out.print((int)teams[standings[i]].defense);
            for (int j = 0; j < 5-Integer.toString((int) teams[standings[i]].defense).length(); j++) {
                System.out.print(" ");
            }
            System.out.print((int)teams[standings[i]].skillLevel);
            for (int j = 0; j < 3-Integer.toString((int) teams[standings[i]].skillLevel).length(); j++) {
                System.out.print(" ");
            }
            System.out.print("|");
            System.out.println("");
        }
        System.out.println("|-----------------------------------------------------------------------|");
    }
}
