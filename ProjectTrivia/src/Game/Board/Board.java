package Game.Board;

public class Board {
    private String piece1 = "";
    private String piece2 = "";
    private String piece3 = "";
    private final Position[][] boardTemplate;

    public Board(){
        boardTemplate = new Position[][]{
                new Position[]{new Position(1),new Position(2),new Position(3),new Position(4),new Position(5)},
                new Position[]{new Position(16),new Position(6)},
                new Position[]{new Position(15),new Position(7)},
                new Position[]{new Position(14),new Position(8)},
                new Position[]{new Position(13),new Position(12),new Position(11),new Position(10),new Position(9)},
        };
    }

    public String drawBoard(){
        return printFullLine(boardTemplate[0]) +
                printInnerLine(boardTemplate[1]) +
                printInnerLine(boardTemplate[2]) +
                printInnerLine(boardTemplate[3]) +
                printFullLine(boardTemplate[4]);
    }

    public void setPiece(String piece){
        if(piece1.isEmpty()){
            piece1 = piece;
            boardTemplate[0][0].placePieceLine1(piece);
            return;
        }
        if(piece2.isEmpty()){
            piece2 = piece;
            boardTemplate[0][0].placePieceLine2(piece);
            return;
        }
        piece3 = piece;
        boardTemplate[0][0].placePieceLine3(piece);
    }

    public String printInnerLine(Position[] positions) {
        StringBuilder buffer = new StringBuilder();
        for (Position position : positions) {
            buffer.append(position.edge).append(" ".repeat(57));
        }
        buffer.append("\n");
        for (Position position : positions) {
            buffer.append(position.themeLine).append(" ".repeat(57));
        }
        buffer.append("\n");
        for (Position position : positions) {
            buffer.append(position.pieceLine1).append(" ".repeat(57));
        }
        buffer.append("\n");
        for (Position position : positions) {
            buffer.append(position.pieceLine2).append(" ".repeat(57));
        }
        buffer.append("\n");
        for (Position position : positions) {
            buffer.append(position.pieceLine3).append(" ".repeat(57));
        }
        buffer.append("\n");
        for (Position position : positions) {
            buffer.append(position.numberLine).append(" ".repeat(57));
        }
        buffer.append("\n");
        for (Position position : positions) {
            buffer.append(position.edge).append(" ".repeat(57));
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

    public void movePiece(int diceRoll, String piece){
        int id = whereToMove(diceRoll, piece);

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

    private int whereToMove(int diceRoll, String piece) {
        if(findPositionByPiece(piece)==null){
            return -1;
        }
        int p = findPositionByPiece(piece).positionId;

        if(p+diceRoll<17){
            return p+diceRoll;
        }
        return (p+diceRoll)-16;
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
        if(piece.equals(piece2)){
            for(Position[] line : boardTemplate){
                for(Position position : line){
                    position.resetLine2();
                }
            }
            return;
        }
        for(Position[] line : boardTemplate){
            for(Position position : line){
                position.resetLine3();
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
    private Position findPositionByPiece(String piece){
        if(piece.equals(piece1)){
            for(Position[] line : boardTemplate){
                for(Position position : line){
                    if(position.pieceLine1.equals("|       "+ piece+"       | ")){
                        return position;
                    }
                }
            }

        }
        if(piece.equals(piece2)){
            for(Position[] line : boardTemplate){
                for(Position position : line){
                    if(position.pieceLine2.equals("|       "+ piece+"       | ")){
                        return position;
                    }
                }
            }

        }
        for(Position[] line : boardTemplate){
            for(Position position : line){
                if(position.pieceLine3.equals("|       "+ piece+"       | ")){
                    return position;
                }
            }
        }
        return null;
    }

    public static class Position{
        private int positionId = 0;

        String edge = "*----------------* ";
        String themeLine = "|                | ";
        String pieceLine1 = "|                | ";
        String pieceLine2 = "|                | ";
        String pieceLine3 = "|                | ";
        String numberLine;

        Position(int positionId){
            this.positionId = positionId;
            numberLine = "|"+ positionId +"               | ";
            if(positionId>=10){
                numberLine = "|"+ positionId +"              | ";
            };
        }

        public void placePieceLine1(String piece){
            pieceLine1 = "|       "+ piece+"       | ";
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