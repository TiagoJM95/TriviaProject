package Game.Board;

import Game.Board.PlayerPieces.Pieces;

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

    public void movePiece(int id, String piece){
        if(findPositionById(id)==null){
            return;
        }

        checkForPieceAndRemove(piece);
        findPositionById(id).placePiece(piece);
    }

    private void checkForPieceAndRemove(String piece){
        for(Position[] line : boardTemplate){
            for(Position position : line){
                if(position.piece.equals(piece)){
                    position.removePiece();
                }
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
        private String piece = "";

        String edge = "*----------------* ";
        String themeLine = "|                | ";
        String pieceLine = "|                | ";
        String numberLine = "|"+ ++positionCounter +"               | ";

        Position(){
            if(positionCounter>=10){
                numberLine = "|"+ positionCounter +"              | ";
            }
            positionId = positionCounter;
        }

        public void placePiece(String piece){
            this.piece = piece;

            pieceLine = "|       "+ piece+"       | ";
        }

        public void removePiece(){
            pieceLine = "|                | ";
        }

    }
}