package Game.Questions;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Questions {

    private List<String> musicQuestions;
    private List<String> musicChoices;
    private List<String> musicAnswers;
    private List<String> entertainmentQuestions;
    private List<String> entertainmentChoices;
    private List<String> entertainmentAnswers;
    private List<String> historyQuestions;
    private List<String> historyChoices;
    private List<String> historyAnswers;
    private List<String> geographyQuestions;
    private List<String> geographyChoices;
    private List<String> geographyAnswers;



    public Questions() {
        try {
            this.musicQuestions = Files.readAllLines(Paths.get(PATH_QUESTIONS + "/Music.txt"));
            this.musicChoices = Files.readAllLines(Paths.get(PATH_CHOICES + "/Music.txt"));
            this.musicAnswers = Files.readAllLines(Paths.get(PATH_ANSWERS + "/Music.txt"));
            this.entertainmentQuestions = Files.readAllLines(Paths.get(PATH_QUESTIONS + "/Entertainment.txt"));
            this.entertainmentChoices = Files.readAllLines(Paths.get(PATH_CHOICES + "/Entertainment.txt"));
            this.entertainmentAnswers = Files.readAllLines(Paths.get(PATH_ANSWERS + "/Entertainment.txt"));
            this.historyQuestions = Files.readAllLines(Paths.get(PATH_QUESTIONS + "/History.txt"));
            this.historyChoices = Files.readAllLines(Paths.get(PATH_CHOICES + "/History.txt"));
            this.historyAnswers = Files.readAllLines(Paths.get(PATH_ANSWERS + "/History.txt"));
            this.geographyQuestions = Files.readAllLines(Paths.get(PATH_QUESTIONS + "/Geography.txt"));
            this.geographyChoices = Files.readAllLines(Paths.get(PATH_CHOICES + "/Geography.txt"));
            this.geographyAnswers = Files.readAllLines(Paths.get(PATH_ANSWERS + "/Geography.txt"));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static final String PATH_QUESTIONS = "src/Game/Questions/Resources/Questions";
    public static final String PATH_CHOICES = "src/Game/Questions/Resources/MultipleChoices";
    public static final String PATH_ANSWERS = "src/Game/Questions/Resources/CorrectAnswer";


    public boolean checkIfAnswerIsCorrect(QuestionType questionType ,String question, String answer){

        switch (questionType) {

            case HISTORY -> { return answer.equals(historyAnswers.get(historyQuestions.indexOf(question)));}

            case GEOGRAPHY -> { return answer.equals(geographyAnswers.get(geographyQuestions.indexOf(question)));}

            case ENTERTAINMENT -> { return answer.equals(entertainmentAnswers.get(entertainmentQuestions.indexOf(question)));}

            case MUSIC -> { return answer.equals(musicAnswers.get(musicQuestions.indexOf(question)));}
        }
        return false;
    }


    public String askQuestion(QuestionType questionType) {
        int random;
        switch (questionType){
            case HISTORY:
                random = new Random().nextInt(historyQuestions.size());
                return historyQuestions.get(random);
            case GEOGRAPHY:
                random = new Random().nextInt(geographyQuestions.size());
                return geographyQuestions.get(random);
            case ENTERTAINMENT:
                random = new Random().nextInt(entertainmentQuestions.size());
                return entertainmentQuestions.get(random);
            case MUSIC:
                random = new Random().nextInt(musicQuestions.size());
                return musicQuestions.get(random);
        }
        return null;
    }

    public String getChoices(QuestionType questionType ,String currentQuestion) {
        return switch (questionType) {
            case HISTORY -> historyChoices.get(historyQuestions.indexOf(currentQuestion));
            case GEOGRAPHY -> geographyChoices.get(geographyQuestions.indexOf(currentQuestion));
            case ENTERTAINMENT -> entertainmentChoices.get(entertainmentQuestions.indexOf(currentQuestion));
            case MUSIC -> musicChoices.get(musicQuestions.indexOf(currentQuestion));
        };
    }

    public void removeQuestion(QuestionType questionType, String currentQuestion) {
        int index;
        switch (questionType) {
            case HISTORY:
                index = historyQuestions.indexOf(currentQuestion);
                historyQuestions.remove(index);
                historyChoices.remove(index);
                historyAnswers.remove(index);
                return;
            case ENTERTAINMENT:
                index = entertainmentQuestions.indexOf(currentQuestion);
                entertainmentQuestions.remove(index);
                entertainmentChoices.remove(index);
                entertainmentAnswers.remove(index);
                return;
            case GEOGRAPHY:
                index = geographyQuestions.indexOf(currentQuestion);
                geographyQuestions.remove(index);
                geographyChoices.remove(index);
                geographyAnswers.remove(index);
                return;
            case MUSIC:
                index = musicQuestions.indexOf(currentQuestion);
                musicQuestions.remove(index);
                musicChoices.remove(index);
                musicAnswers.remove(index);
        }
    }
}
