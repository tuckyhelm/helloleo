import javax.swing.*;
import java.util.ArrayList;

public class Player {
    String[] nameParts = {"dan", "per", "sam", "cos", "sin", "je", "scot", "sher", "bert", "ken", "mac", "big",
    "bla", "eig", "flo", "die", "gal", "get", "gir", "lag", "lev", "mer", "new", "pen", "o'", "old", "pal", "lo",
    "a", "e", "i", "o", "u", "por", "min", "lan", "sid", "val", "sho", "cho", "lim"};
    String[] nameEnds = {"son", "iel", "ing", "lar", "len", "otte", "sin", "ack", "ich", "ant", "is", "ell",
    "sie", "sca", "orf"};
    double skillLevel = (int)(Math.random()*20-10);
    String name = "";
    String position = "cm";
    int goals = 0;
    boolean redCard = false;
    int number;
    public Player(){
        for(int i = 1; i <= Math.ceil(Math.random()*2); i++){
            name = name+(nameParts[(int)(Math.random()*nameParts.length)]);
        }
        name = name +(nameEnds[(int)(Math.random()*nameEnds.length)]);
    }


}
