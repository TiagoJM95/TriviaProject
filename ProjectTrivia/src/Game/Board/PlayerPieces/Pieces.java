package Game.Board.PlayerPieces;

import java.util.Arrays;

public class Pieces {
    private  final String[] emotesPieces  = new String[]{"🐫","\uD83D\uDC08\u200D⬛","\uD83D\uDC3B" ,"\uD83D\uDC2F","\uD83E\uDD16","\uD83D\uDC68\uD83C\uDFFE\u200D\uD83D\uDCBB","\uD83D\uDC7B"};
    private String piece;

    public Pieces(){

    }
    public String chosePiece(int number){
        piece = emotesPieces[number];
        return piece;
    }
    public String printPieces(){
        return Arrays.toString(emotesPieces);
    }



    public static void main(String[] args) {
        Pieces pieces = new Pieces();
        System.out.println(pieces.printPieces());
        System.out.println(pieces.chosePiece(4));

    }

}

