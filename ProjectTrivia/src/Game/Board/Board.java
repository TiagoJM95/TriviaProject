package Game.Board;

public class Board {

    private final Position[][] boardTemplate;

    public Board(){
        boardTemplate = new Position[][]{
                new Position[]{new Position(),new Position(),new Position(),new Position(),new Position()},
                new Position[]{new Position(),new Position(),new Position()},
                new Position[]{new Position(),new Position(),new Position(),new Position(),new Position()},
                new Position[]{new Position(),new Position(),new Position()},
                new Position[]{new Position(),new Position(),new Position(),new Position(),new Position()},
        };
    }

    public String drawBoard(){
        return printFullLine(boardTemplate[0]) +
                printInnerLine(boardTemplate[1]) +
                printFullLine(boardTemplate[2]) +
                printInnerLine(boardTemplate[3]) +
                printFullLine(boardTemplate[4]);
    }

    public String printInnerLine(Position[] positions) {
        StringBuilder buffer = new StringBuilder();
        for (Position position : positions) {
            buffer.append(position.edge).append(" ".repeat(19));
        }
        buffer.append("\n");
        for (Position position : positions) {
            buffer.append(position.themeLine).append(" ".repeat(19));
        }
        buffer.append("\n");
        for (Position position : positions) {
            buffer.append(position.pieceLine).append(" ".repeat(19));
        }
        buffer.append("\n");
        for (Position position : positions) {
            buffer.append(position.numberLine).append(" ".repeat(19));
        }
        buffer.append("\n");
        for (Position position : positions) {
            buffer.append(position.edge).append(" ".repeat(19));
        }
        buffer.append("\n");
        return buffer.toString();
    }

    public String printFullLine(Position[] positions) {
        StringBuilder buffer = new StringBuilder();
        for (Position position : positions) {
            buffer.append(position.edge);
        }
        buffer.append("\n");
        for (Position position : positions) {
            buffer.append(position.themeLine);
        }
        buffer.append("\n");
        for (Position position : positions) {
            buffer.append(position.pieceLine);
        }
        buffer.append("\n");
        for (Position position : positions) {
            buffer.append(position.numberLine);
        }
        buffer.append("\n");
        for (Position position : positions) {
            buffer.append(position.edge);
        }
        buffer.append("\n");
        return buffer.toString();
    }

    public static class Position{
        private static int positionCounter = 0;

        String edge = "*----------------* ";
        String themeLine = "|                | ";
        String pieceLine = "|                | ";
        String numberLine = "|"+ ++positionCounter +"               | ";

        Position(){
            if(positionCounter>=10){
                numberLine = "|"+ positionCounter +"              | ";
            }
        }

        public void movePieceToPosition(String piece){
            pieceLine = "|       "+ piece+"        | ";
        }
    }
}