package Game.Score;

import Game.Questions.QuestionType;

public class Score {
    private boolean historyPoint = true;
    private boolean geographyPoint= true;
    private boolean musicPoint= true;
    private boolean entertainmentPoint= true;
    private boolean allPoints;

    public void updateScore(QuestionType questionType) {
        switch (questionType){
            case MUSIC -> setMusicPoint();
            case HISTORY -> setHistoryPoint();
            case GEOGRAPHY -> setGeographyPoint();
            case ENTERTAINMENT -> setEntertainmentPoint();
        }
    }

    private void setAllPoints(){
        if(historyPoint && geographyPoint && musicPoint && entertainmentPoint){
            allPoints = true;
        }
    }

    private void setHistoryPoint() {
       historyPoint = true;
        setAllPoints();
    }

    private void setGeographyPoint() {
        geographyPoint = true;
        setAllPoints();
    }

    private void setMusicPoint() {
        this.musicPoint = true;
        setAllPoints();
    }

    private void setEntertainmentPoint() {
        this.entertainmentPoint = true;
        setAllPoints();
    }

    public boolean isAllPoints() {
        return allPoints;
    }
}
