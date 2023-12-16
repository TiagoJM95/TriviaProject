package Game.Dice;

import java.util.Random;

public class Dice {
    private static final String xxxxxxxxx = "+-------+";
    private static final String x_______x = "|       |";
    private static final String x_o_____x = "| o     |";
    private static final String x___o___x = "|   o   |";
    private static final String x_____o_x = "|     o |";
    private static final String x_o___o_x = "| o   o |";
    private int random;


    public void rollDice(){
        random = new Random().nextInt(1,7);
        printDice(random);
    }

    private String[] drawDice(int diceFace){
        return switch (diceFace) {
            case 1 -> new String[]{
                    xxxxxxxxx,
                    x_______x,
                    x___o___x,
                    x_______x,
                    xxxxxxxxx
            };
            case 2 -> new String[]{
                    xxxxxxxxx,
                    x_o_____x,
                    x_______x,
                    x_____o_x,
                    xxxxxxxxx
            };
            case 3 -> new String[]{
                    xxxxxxxxx,
                    x_o_____x,
                    x___o___x,
                    x_____o_x,
                    xxxxxxxxx
            };
            case 4 -> new String[]{
                    xxxxxxxxx,
                    x_o___o_x,
                    x_______x,
                    x_o___o_x,
                    xxxxxxxxx
            };
            case 5 -> new String[]{
                    xxxxxxxxx,
                    x_o___o_x,
                    x___o___x,
                    x_o___o_x,
                    xxxxxxxxx
            };
            case 6 -> new String[]{
                    xxxxxxxxx,
                    x_o___o_x,
                    x_o___o_x,
                    x_o___o_x,
                    xxxxxxxxx
            };
            default -> null;
        };
    }
    private void printDice(int number){
        for (String printEachLine : drawDice(number)) {
            System.out.println(printEachLine);
        };
    }
    public int getDiceNumber(){
        return random;
    }
    public static void main(String[] args) {
        Dice dice = new Dice();
        dice.rollDice();
    }

}
