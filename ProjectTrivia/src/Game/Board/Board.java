package Game.Board;

import java.util.ArrayList;
import java.util.List;
import Game.Questions.QuestionType;
import Game.Game.Game;

public class Board {
    private String piece1 = "";
    private String piece2 = "";
    private String piece3 = "";
    private Game game;
    private final List<String> scoreMusic;
    private final List<String> scoreHistory;
    private final List<String> scoreGeography;
    private final List<String> scoreEntertainment;

    private final Position[][] boardTemplate;

    private final List<Integer> prizedPositions;

    public Board(Game game){
        boardTemplate = new Position[][]{
                new Position[]{new Position(1,QuestionType.HISTORY),new Position(2,QuestionType.ENTERTAINMENT),
                        new Position(3,QuestionType.GEOGRAPHY),new Position(4,QuestionType.HISTORY),
                        new Position(5, QuestionType.MUSIC)},
                new Position[]{new Position(16, QuestionType.GEOGRAPHY),new Position(6,QuestionType.ENTERTAINMENT)},
                new Position[]{new Position(15,QuestionType.MUSIC),new Position(7,QuestionType.HISTORY)},
                new Position[]{new Position(14,QuestionType.HISTORY),new Position(8,QuestionType.ENTERTAINMENT)},
                new Position[]{new Position(13,QuestionType.GEOGRAPHY),new Position(12,QuestionType.MUSIC),
                        new Position(11,QuestionType.ENTERTAINMENT),new Position(10,QuestionType.GEOGRAPHY),
                        new Position(9,QuestionType.MUSIC)},
        };
        prizedPositions = new ArrayList<>(List.of(new Integer[]{3, 7 , 11 ,15}));
        scoreMusic = new ArrayList<>(List.of(new String[]{"Music", "Music", "Music"}));
        scoreEntertainment = new ArrayList<>(List.of(new String[]{"Entertainment", "Entertainment", "Entertainment"}));
        scoreGeography = new ArrayList<>(List.of(new String[]{"Geography", "Geography", "Geography"}));
        scoreHistory = new ArrayList<>(List.of(new String[]{"History", "History", "History"}));
        this.game = game;
    }

    public String drawBoard(){
        return printFullLine(boardTemplate[0]) +
                printScoreLine(boardTemplate[1]) +
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

    public String printScoreLine(Position[] positions) {
        StringBuilder builder = new StringBuilder();

        String p1Name = game.getPLAYERS().get(0).getName();
        String p2Name = game.getPLAYERS().get(1).getName();
        String p3Name = game.getPLAYERS().get(2).getName();


        builder.append(positions[0].edge).append(" ".repeat(2))
                .append(p1Name).append(" ".repeat(19-p1Name.length()))
                .append(p2Name).append(" ".repeat(19-p2Name.length()))
                .append(p3Name).append(" ".repeat(19-p3Name.length()-2))
                .append(positions[1].edge).append("\n");

        builder.append(positions[0].themeLine).append(" ".repeat(57)).append(positions[1].themeLine);

        builder.append("\n").append(positions[0].pieceLine1).append(" ".repeat(2))
                .append(scoreMusic.get(0)).append(" ".repeat(19-scoreMusic.get(0).length()))
                .append(scoreMusic.get(1)).append(" ".repeat(19-scoreMusic.get(1).length()))
                .append(scoreMusic.get(2)).append(" ".repeat(19-scoreMusic.get(2).length()-2))
                .append(positions[1].pieceLine1).append("\n");
        builder.append(positions[0].pieceLine2).append(" ".repeat(2))
                .append(scoreEntertainment.get(0)).append(" ".repeat(19-scoreEntertainment.get(0).length()))
                .append(scoreEntertainment.get(1)).append(" ".repeat(19-scoreEntertainment.get(1).length()))
                .append(scoreEntertainment.get(2)).append(" ".repeat(19-scoreEntertainment.get(2).length()-2))
                .append(positions[1].pieceLine2).append("\n");
        builder.append(positions[0].pieceLine3).append(" ".repeat(2))
                .append(scoreGeography.get(0)).append(" ".repeat(19-scoreGeography.get(0).length()))
                .append(scoreGeography.get(1)).append(" ".repeat(19-scoreGeography.get(1).length()))
                .append(scoreGeography.get(2)).append(" ".repeat(19-scoreGeography.get(2).length()-2))
                .append(positions[1].pieceLine3).append("\n");
        builder.append(positions[0].numberLine).append(" ".repeat(2))
                .append(scoreHistory.get(0)).append(" ".repeat(19-scoreHistory.get(0).length()))
                .append(scoreHistory.get(1)).append(" ".repeat(19-scoreHistory.get(1).length()))
                .append(scoreHistory.get(2)).append(" ".repeat(19-scoreHistory.get(2).length()-2))
                .append(positions[1].numberLine).append("\n");
        builder.append(positions[0].edge).append(" ".repeat(57)).append(positions[1].edge);
        builder.append("\n");
        return builder.toString();
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

    public Position findPositionByPiece(String piece){
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

    public List<Integer> getPrizedPositions() {
        return prizedPositions;
    }

    public static class Position{
        private int positionId = 0;
        private final QuestionType questionType;


        String edge = "*----------------* ";
        String themeLine = "|                | ";
        String pieceLine1 = "|                | ";
        String pieceLine2 = "|                | ";
        String pieceLine3 = "|                | ";
        String numberLine;

        Position(int positionId, QuestionType questionType){
            this.positionId = positionId;
            numberLine = "|"+ positionId +"               | ";
            if(positionId>=10){
                numberLine = "|"+ positionId +"              | ";
            }
            this.questionType = questionType;
            formatString();
        }
        private void formatString(){
            String colorizeString = "| " + questionType.toString() + " ".repeat(15 - questionType.toString().length()) + "| ";
            String colorString = colorizeString.substring(0, colorizeString.length() - 1) ;
            String lastCharacterColorLess = colorizeString.substring(colorizeString.length() - 1);
            if (colorizeString.equals("| " + QuestionType.HISTORY + " ".repeat(15 - QuestionType.HISTORY.toString().length()) + "| ")) {
                themeLine = colorBoard.Red.colorize(colorizeString) ;
                if(positionId == 7){
                    themeLine = colorBoard.BgRed.colorize(colorString) + lastCharacterColorLess;
                }
            }
            if (colorizeString.equals("| " + QuestionType.GEOGRAPHY + " ".repeat(15 - QuestionType.GEOGRAPHY.toString().length()) + "| ")) {
                themeLine = colorBoard.Yellow.colorize(colorizeString) ;
                if(positionId == 3){
                    themeLine = colorBoard.BgYellow.colorize(colorString) + lastCharacterColorLess;
                }
            }
            if (colorizeString.equals("| " + QuestionType.ENTERTAINMENT + " ".repeat(15 - QuestionType.ENTERTAINMENT.toString().length()) + "| ")) {
                themeLine = colorBoard.Blue.colorize(colorizeString);
                if(positionId == 11){
                    themeLine = colorBoard.BgBlue.colorize(colorString) + lastCharacterColorLess;
                }
            }
            if (colorizeString.equals("| " + QuestionType.MUSIC + " ".repeat(15 - QuestionType.MUSIC.toString().length()) + "| ")) {
                themeLine = colorBoard.Magenta.colorize(colorizeString);
                if(positionId == 15) {
                    themeLine = colorBoard.BgMagenta.colorize(colorString) + lastCharacterColorLess;
                }
            }
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

        public QuestionType getQuestionType() {
            return questionType;
        }

        public int getPositionId() {
            return positionId;
        }
    }
}