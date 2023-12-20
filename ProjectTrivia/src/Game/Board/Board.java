package Game.Board;

import java.util.ArrayList;
import java.util.List;
import Game.Questions.QuestionType;
import Game.Game.Game;
import Game.Util.AnsiColors;
import static Game.Questions.QuestionType.*;

public class Board {
    private String piece1 = "";
    private String piece2 = "";
    private String piece3 = "";
    private final Game game;
    private final Position[][] boardTemplate;
    private final List<Integer> prizedPositions;
    private final List<String> scoreMusic;
    private final List<String> scoreHistory;
    private final List<String> scoreGeography;
    private final List<String> scoreEntertainment;

    public Board(Game game){
        this.game = game;
        boardTemplate = new Position[][]{
                new Position[]{
                        new Position(1,HISTORY),
                        new Position(2,ENTERTAINMENT),
                        new Position(3,GEOGRAPHY),
                        new Position(4,HISTORY),
                        new Position(5,MUSIC)},
                new Position[]{
                        new Position(16,GEOGRAPHY),
                        new Position(6, ENTERTAINMENT)},
                new Position[]{
                        new Position(15,MUSIC),
                        new Position(7, HISTORY)},
                new Position[]{
                        new Position(14,HISTORY),
                        new Position(8, ENTERTAINMENT)},
                new Position[]{
                        new Position(13,GEOGRAPHY),
                        new Position(12,MUSIC),
                        new Position(11,ENTERTAINMENT),
                        new Position(10,GEOGRAPHY),
                        new Position(9, MUSIC)},
        };
        prizedPositions = new ArrayList<>(List.of(new Integer[]{3, 7 , 11 ,15}));
        scoreMusic = new ArrayList<>(List.of(new String[]{"Music", "Music", "Music"}));
        scoreHistory = new ArrayList<>(List.of(new String[]{"History", "History", "History"}));
        scoreGeography = new ArrayList<>(List.of(new String[]{"Geography", "Geography", "Geography"}));
        scoreEntertainment = new ArrayList<>(List.of(new String[]{"Entertainment", "Entertainment", "Entertainment"}));
    }

    public void markScoreCategory(QuestionType questionType, int playerIndex){
        switch (questionType){
            case MUSIC:
                scoreMusic.remove(playerIndex);
                scoreMusic.add(playerIndex, "Music *");
                break;
            case HISTORY:
                scoreHistory.remove(playerIndex);
                scoreHistory.add(playerIndex, "History *");
                break;
            case GEOGRAPHY:
                scoreGeography.remove(playerIndex);
                scoreGeography.add(playerIndex, "Geography *");
                break;
            case ENTERTAINMENT:
                scoreEntertainment.remove(playerIndex);
                scoreEntertainment.add(playerIndex, "Entertainment *");
                break;
        }
    }

    public String drawBoard(){
        return  printFullRow(boardTemplate[0]) +
                printScoreRow(boardTemplate[1]) +
                printInnerRow(boardTemplate[2]) +
                printInnerRow(boardTemplate[3]) +
                printFullRow(boardTemplate[4]);
    }

    public void setPiece(String piece){
        if(piece3.isEmpty()){
            piece3 = piece;
            boardTemplate[0][0].placePieceLine3(piece);
            return;
        }
        if(piece2.isEmpty()){
            piece2 = piece;
            boardTemplate[0][0].placePieceLine2(piece);
            return;
        }
        piece1 = piece;
        boardTemplate[0][0].placePieceLine1(piece);
    }

    private String printFullRow(Position[] positions) {
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

    private String printInnerRow(Position[] positions) {
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

    private String printScoreRow(Position[] positions) {
        StringBuilder builder = new StringBuilder();

        String p1Name = game.getPlayers().get(0).getName();
        String p2Name = game.getPlayers().get(1).getName();
        String p3Name = game.getPlayers().get(2).getName();

        printFirstLine(positions, builder, p1Name, p2Name, p3Name);
        printSecondLine(positions, builder);
        printThirdLine(positions, builder);
        printFourthLine(positions, builder);
        printFifthLine(positions, builder);
        printSixthLine(positions, builder);
        printSeventhLine(positions, builder);

        return builder.toString();
    }

    private void printFirstLine(Position[] positions, StringBuilder builder, String p1, String p2, String p3) {
        builder
                .append(positions[0].edge).append(" ".repeat(2))
                .append(p1).append(" ".repeat(19- p1.length()))
                .append(p2).append(" ".repeat(19- p2.length()))
                .append(p3).append(" ".repeat(19- p3.length()-2))
                .append(positions[1].edge).append("\n");
    }

    private  void printSecondLine(Position[] positions, StringBuilder builder) {
        builder
                .append(positions[0].themeLine).append(" ".repeat(57)).append(positions[1].themeLine);
    }

    private void printThirdLine(Position[] positions, StringBuilder builder) {
        builder.append("\n")
                .append(positions[0].pieceLine1).append(" ".repeat(2))
                .append(scoreMusic.get(0)).append(" ".repeat(19-scoreMusic.get(0).length()))
                .append(scoreMusic.get(1)).append(" ".repeat(19-scoreMusic.get(1).length()))
                .append(scoreMusic.get(2)).append(" ".repeat(19-scoreMusic.get(2).length()-2))
                .append(positions[1].pieceLine1);
    }

    private void printFourthLine(Position[] positions, StringBuilder builder) {
        builder.append("\n")
                .append(positions[0].pieceLine2).append(" ".repeat(2))
                .append(scoreEntertainment.get(0)).append(" ".repeat(19-scoreEntertainment.get(0).length()))
                .append(scoreEntertainment.get(1)).append(" ".repeat(19-scoreEntertainment.get(1).length()))
                .append(scoreEntertainment.get(2)).append(" ".repeat(19-scoreEntertainment.get(2).length()-2))
                .append(positions[1].pieceLine2);
    }

    private void printFifthLine(Position[] positions, StringBuilder builder) {
        builder.append("\n")
                .append(positions[0].pieceLine3).append(" ".repeat(2))
                .append(scoreGeography.get(0)).append(" ".repeat(19-scoreGeography.get(0).length()))
                .append(scoreGeography.get(1)).append(" ".repeat(19-scoreGeography.get(1).length()))
                .append(scoreGeography.get(2)).append(" ".repeat(19-scoreGeography.get(2).length()-2))
                .append(positions[1].pieceLine3);
    }

    private void printSixthLine(Position[] positions, StringBuilder builder) {
        builder.append("\n")
                .append(positions[0].numberLine).append(" ".repeat(2))
                .append(scoreHistory.get(0)).append(" ".repeat(19-scoreHistory.get(0).length()))
                .append(scoreHistory.get(1)).append(" ".repeat(19-scoreHistory.get(1).length()))
                .append(scoreHistory.get(2)).append(" ".repeat(19-scoreHistory.get(2).length()-2))
                .append(positions[1].numberLine);
    }

    private void printSeventhLine(Position[] positions, StringBuilder builder) {
        builder.append("\n")
                .append(positions[0].edge).append(" ".repeat(57))
                .append(positions[1].edge).append("\n");
    }

    @SuppressWarnings("ConstantConditions")
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
        int positionId = findPositionByPiece(piece).positionId;

        if(positionId+diceRoll<17){
            return positionId+diceRoll;
        }
        return (positionId+diceRoll)-16;
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
        private final int positionId;
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
                themeLine = AnsiColors.Red.colorize(colorizeString) ;
                if(positionId == 7){
                    themeLine = AnsiColors.BgRed.colorize(colorString) + lastCharacterColorLess;
                }
            }
            if (colorizeString.equals("| " + QuestionType.GEOGRAPHY + " ".repeat(15 - QuestionType.GEOGRAPHY.toString().length()) + "| ")) {
                themeLine = AnsiColors.Yellow.colorize(colorizeString) ;
                if(positionId == 3){
                    themeLine = AnsiColors.BgYellow.colorize(colorString) + lastCharacterColorLess;
                }
            }
            if (colorizeString.equals("| " + QuestionType.ENTERTAINMENT + " ".repeat(15 - QuestionType.ENTERTAINMENT.toString().length()) + "| ")) {
                themeLine = AnsiColors.Blue.colorize(colorizeString);
                if(positionId == 11){
                    themeLine = AnsiColors.BgBlue.colorize(colorString) + lastCharacterColorLess;
                }
            }
            if (colorizeString.equals("| " + QuestionType.MUSIC + " ".repeat(15 - QuestionType.MUSIC.toString().length()) + "| ")) {
                themeLine = AnsiColors.Magenta.colorize(colorizeString);
                if(positionId == 15) {
                    themeLine = AnsiColors.BgMagenta.colorize(colorString) + lastCharacterColorLess;
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