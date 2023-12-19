package Game.Board;

public class Board {
    private String piece1 = "";
    private String piece2 = "";
    private String piece3 = "";
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

    public void setPiece(String piece){
        if(piece1.isEmpty()){
            piece1 = piece;
            return;
        }
        if(piece2.isEmpty()){
            piece2 = piece;
            return;
        }
        piece3 = piece;
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
            buffer.append(position.pieceLine1).append(" ".repeat(19));
        }
        buffer.append("\n");
        for (Position position : positions) {
            buffer.append(position.pieceLine2).append(" ".repeat(19));
        }
        buffer.append("\n");
        for (Position position : positions) {
            buffer.append(position.pieceLine3).append(" ".repeat(19));
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
            buffer.append(position.pieceLine1);
        }
        buffer.append("\n");
        for (Position position : positions) {
            buffer.append(position.pieceLine2);
        }
        buffer.append("\n");
        for (Position position : positions) {
            buffer.append(position.pieceLine3);
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

    public void movePiece(int id, String piece){
        if(findPositionById(id)==null){
            return;
        }

        checkForPieceAndRemove(piece);
        if(piece.equals(piece1)){
            findPositionById(id).placePieceLine1(piece);
            return;
        }
        if(piece.equals(piece2)){
            findPositionById(id).placePieceLine2(piece);
            return;
        }
        findPositionById(id).placePieceLine3(piece);
    }

    private void checkForPieceAndRemove(String piece){
        if(piece.equals(piece1)){
            for(Position[] line : boardTemplate){
                for(Position position : line){
                    position.resetLine1();
                }
            }
            return;
        }
        if(piece.equals(piece3)){
            for(Position[] line : boardTemplate){
                for(Position position : line){
                    position.resetLine3();
                }
            }
            return;
        }
        for(Position[] line : boardTemplate){
            for(Position position : line){
                position.resetLine2();
            }
        }
    }

    private Position findPositionById(int id){
        for(Position[] line : boardTemplate){
            for(Position position : line){
                if(position.positionId == id){
                    return position;
                }
            }
        }
        return null;
    }

    public static class Position{
        private static int positionCounter = 0;
        private final int positionId;

        String edge = "*----------------* ";
        String themeLine = "|                | ";
        String pieceLine1 = "|                | ";
        String pieceLine2 = "|                | ";
        String pieceLine3 = "|                | ";
        String numberLine = "|"+ ++positionCounter +"               | ";

        Position(){
            if(positionCounter>=10){
                numberLine = "|"+ positionCounter +"              | ";
            }
            positionId = positionCounter;
        }

        public void placePieceLine1(String piece){
            pieceLine1  = "|       "+ piece+"       | ";
        }
        public void placePieceLine2(String piece){
            pieceLine2 = "|       "+ piece+"       | ";
        }
        public void placePieceLine3(String piece){
            pieceLine3 = "|       "+ piece+"       | ";
        }

        public void resetLine1(){
            pieceLine1 = "|                | ";
        }
        public void resetLine2(){
            pieceLine2 = "|                | ";
        }
        public void resetLine3(){
            pieceLine3 = "|                | ";
        }
    }
}