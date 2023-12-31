package Game.Score;

import Game.Questions.QuestionType;

public class Score {
    private boolean historyPoint;
    private boolean geographyPoint;
    private boolean musicPoint;
    private boolean entertainmentPoint;
    private boolean allPoints;

    public Score(){
        historyPoint = false;
        geographyPoint = false;
        musicPoint = false;
        entertainmentPoint = false;
        allPoints = false;
    }

    private void setAllPoints(){
        if(historyPoint && geographyPoint && musicPoint && entertainmentPoint){
            allPoints = true;
        }

    }

    private void setMusicPoint() {
        this.musicPoint = true;
        setAllPoints();
    }

    private void setHistoryPoint() {
       historyPoint = true;
        setAllPoints();
    }

    private void setGeographyPoint() {
        geographyPoint = true;
        setAllPoints();
    }

    private void setEntertainmentPoint() {
        this.entertainmentPoint = true;
        setAllPoints();
    }

    public boolean isAllPoints() {
        return allPoints;
    }

    public void updateScore(QuestionType questionType) {
        switch (questionType){
            case MUSIC -> setMusicPoint();
            case HISTORY -> setHistoryPoint();
            case GEOGRAPHY -> setGeographyPoint();
            case ENTERTAINMENT -> setEntertainmentPoint();
        }
    }
}
