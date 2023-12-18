package Game.Dice;

import java.util.Random;

public class Dice {
    private static final String xxxxxxxxx = "+-------+";
    private static final String x_______x = "|       |";
    private static final String x_o_____x = "| o     |";
    private static final String x___o___x = "|   o   |";
    private static final String x_____o_x = "|     o |";
    private static final String x_o___o_x = "| o   o |";

    public int rollDice(){
        return new Random().nextInt(1,7);
    }

    public String drawDice(int diceFace){
        return switch (diceFace) {
            case 1 ->
                    xxxxxxxxx+"\n"+
                    x_______x+"\n"+
                    x___o___x+"\n"+
                    x_______x+"\n"+
                    xxxxxxxxx;
            case 2 ->
                    xxxxxxxxx+"\n"+
                    x_o_____x+"\n"+
                    x_______x+"\n"+
                    x_____o_x+"\n"+
                    xxxxxxxxx;
            case 3 ->
                    xxxxxxxxx+"\n"+
                    x_o_____x+"\n"+
                    x___o___x+"\n"+
                    x_____o_x+"\n"+
                    xxxxxxxxx;
            case 4 ->
                    xxxxxxxxx+"\n"+
                    x_o___o_x+"\n"+
                    x_______x+"\n"+
                    x_o___o_x+"\n"+
                    xxxxxxxxx;
            case 5 ->
                    xxxxxxxxx+"\n"+
                    x_o___o_x+"\n"+
                    x___o___x+"\n"+
                    x_o___o_x+"\n"+
                    xxxxxxxxx;
            case 6 ->
                    xxxxxxxxx+"\n"+
                    x_o___o_x+"\n"+
                    x_o___o_x+"\n"+
                    x_o___o_x+"\n"+
                    xxxxxxxxx;
            default -> null;
        };
    }
}
