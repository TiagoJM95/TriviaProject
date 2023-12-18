package Game.Board.PlayerPieces;

import Game.Board.Board;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Pieces {
    private static final List<String> emotesPieces  = new ArrayList<>();
    private Board board;

    public Pieces(){
        emotesPieces.add("🐫");
        emotesPieces.add("\uD83D\uDC08\u200D⬛");
        emotesPieces.add("\uD83D\uDC3B");
        emotesPieces.add("\uD83E\uDD16");
        emotesPieces.add("\uD83D\uDC2F");
        emotesPieces.add("\uD83D\uDC68\uD83C\uDFFE\u200D\uD83D\uDCBB");
        emotesPieces.add("\uD83D\uDC7B");
}

    public String chosePiece(int number){

        String piece = emotesPieces.get(number - 1);
        emotesPieces.remove(number - 1);
        return piece;
    }

    public static String printPieces(){

        List<String> string = new ArrayList<>();
        for (int i = 0; i < emotesPieces.size(); i++) {
            string.add((i + 1) + ": "+ emotesPieces.get(i));
        }
        return string.toString();
    }



    public static void main(String[] args) {
        Pieces pieces = new Pieces();
        Board board = new Board();
        //System.out.println(pieces.chosePiece(3));
        System.out.println(Pieces.printPieces());
        board.movePiece(1, pieces.chosePiece(1));
        System.out.println(emotesPieces);
        board.movePiece(6, pieces.chosePiece(1));
        System.out.println(emotesPieces);
        board.movePiece(9, pieces.chosePiece(1));
        System.out.println(emotesPieces);
        board.movePiece(14, pieces.chosePiece(1));
        System.out.println(emotesPieces);
        board.movePiece(17, pieces.chosePiece(1));
        System.out.println(emotesPieces);
        board.movePiece(6, pieces.chosePiece(1));
        System.out.println(emotesPieces);
        board.movePiece(7, pieces.chosePiece(1));
        System.out.println(board.drawBoard());


    }

}

