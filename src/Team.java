import java.util.ArrayList;

public class Team {
    int year = (int)(Math.random()*120+1880);
    double skillLevel = (int)(Math.random()*30+60);
    int points = 0;
    int W = 0, L = 0, D = 0;
    int GD = 0;
    int GF = 0;
    int GA = 0;
    double attack = 0, defense = 0, midfield = 0;
    String teamName;
    int[] numbers = new int[101];
    Player[] players = new Player[11];

    public Team(String name){
        teamName = name;
        for (int i = 0; i < 11; i++) {
            players[i] = new Player();
            players[i].skillLevel += skillLevel;
            if(i < 3){
                players[i].position = "FW";
                attack+=players[i].skillLevel;
            } else if (i < 6){
                players[i].position = "MF";
                midfield+=players[i].skillLevel;
            } else if (i < 10){
                players[i].position = "DF";
                defense+=players[i].skillLevel;
            } else {
                players[i].position = "GK";
                defense+=players[i].skillLevel;
            }
        }
        defense = (int)(defense/5);
        midfield = (int)(midfield/3);
        attack = (int)(attack/3);
        for (int i = 0; i < 101; i++) {
            numbers[i] = i-1;
        }
        for (int i = 0; i < players.length; i++) {
            int ii = 0;
            while (numbers[ii] == -1) {
                ii = (int)(Math.random()*101);
            }
            players[i].number = numbers[ii];
        }
    }
    public void updateSkillLevels (){
        attack = 0;
        midfield = 0;
        defense = 0;
        for (int i = 0; i < 11; i++) {
            players[i] = new Player();
            players[i].skillLevel += skillLevel;
            if(i < 3){
                players[i].position = "FW";
                attack+=players[i].skillLevel;
            } else if (i < 6){
                players[i].position = "MF";
                midfield+=players[i].skillLevel;
            } else if (i < 10){
                players[i].position = "DF";
                defense+=players[i].skillLevel;
            } else {
                players[i].position = "GK";
                defense+=players[i].skillLevel;
            }
        }
        defense = (int)(defense/5);
        midfield = (int)(midfield/3);
        attack = (int)(attack/3);
    }
    public void printInfo (){
        System.out.println("---Team Name: " + teamName + "---");
        System.out.println("Established: " + year);
        System.out.println("Roster: ");
        System.out.println("|Name----------Num---Ovr---Pos---Gls|");
        for (int ii = 0; ii < players.length; ii++) {
            //System.out.println(players[ii].name + " - " + players[ii].number + ", rated " + players[ii].skillLevel
            //+ " overall.");
            System.out.print("|"+players[ii].name);
            for (int i = 0; i < 14-players[ii].name.length(); i++) {
                System.out.print(" ");
            }
            System.out.print(players[ii].number);
            for (int i = 0; i < 6-Integer.toString(players[ii].number).length(); i++) {
                System.out.print(" ");
            }
            System.out.print((int) players[ii].skillLevel+"    "+players[ii].position);
            for (int i = 0; i < 6-players[ii].position.length(); i++) {
                System.out.print(" ");
            }
            System.out.print(players[ii].goals);
            for (int i = 0; i < 4-players[ii].position.length(); i++) {
                System.out.print(" ");
            }
            System.out.print("|");
            System.out.println("");
        }
        System.out.println("|-----------------------------------|");
    }

}
