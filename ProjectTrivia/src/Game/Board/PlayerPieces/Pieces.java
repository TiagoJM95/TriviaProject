package Game.Board.PlayerPieces;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Pieces {
    private static final List<String> emotesPieces = new ArrayList<>(List.of(new String[]{
            "🐫",
            "\uD83D\uDC08\u200D⬛",
            "\uD83D\uDC3B",
            "\uD83E\uDD16",
            "\uD83D\uDC2F",
            "\uD83D\uDC68\uD83C\uDFFE\u200D\uD83D\uDCBB",
            "\uD83D\uDC7B"}));

    public static String generatePiece(){
        int random = new Random().nextInt(emotesPieces.size()-1);
        String piece = emotesPieces.get(random);
        emotesPieces.remove(piece);
        return piece;
    }
}