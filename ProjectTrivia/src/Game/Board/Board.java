package Game.Board;

public class Board {
    private static final String xxxxxxxxx = "+-----------+";
    private static final String x_______x = "|           |";
    private static final String history = "| HISTORY   |";
    private static final String geography = "| GEOGRAPHY |";
    private static final String blank = "             ";




    private String[] drawBoard(){
        return new String[]{
                xxxxxxxxx + xxxxxxxxx + xxxxxxxxx + xxxxxxxxx + xxxxxxxxx,
                history + geography + history + geography + history,
                x_______x + x_______x + x_______x + x_______x + x_______x,
                xxxxxxxxx + xxxxxxxxx + xxxxxxxxx + xxxxxxxxx + xxxxxxxxx,
                xxxxxxxxx + blank + xxxxxxxxx + blank + xxxxxxxxx,
                history + blank +history + blank + history,
                x_______x + blank + x_______x + blank + x_______x,
                xxxxxxxxx + blank + xxxxxxxxx + blank + xxxxxxxxx,
                xxxxxxxxx + xxxxxxxxx + xxxxxxxxx + xxxxxxxxx + xxxxxxxxx,
                history + geography + history + geography + history,
                x_______x + x_______x + x_______x + x_______x + x_______x,
                xxxxxxxxx + xxxxxxxxx + xxxxxxxxx + xxxxxxxxx + xxxxxxxxx,
                xxxxxxxxx + blank + xxxxxxxxx + blank + xxxxxxxxx,
                history + blank +history + blank + history,
                x_______x + blank + x_______x + blank + x_______x,
                xxxxxxxxx + blank + xxxxxxxxx + blank + xxxxxxxxx,
                xxxxxxxxx + xxxxxxxxx + xxxxxxxxx + xxxxxxxxx + xxxxxxxxx,
                history + geography + history + geography + history,
                x_______x + x_______x + x_______x + x_______x + x_______x,
                xxxxxxxxx + xxxxxxxxx + xxxxxxxxx + xxxxxxxxx + xxxxxxxxx};
    }
    public void printBoard(){
        for (String printEachLine : drawBoard()) {
            System.out.println(printEachLine);
        }
    }


    public static void main(String[] args) {
        Board board = new Board();
        board.printBoard();
    }



}
