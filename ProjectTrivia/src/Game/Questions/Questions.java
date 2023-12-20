package Game.Questions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Questions {

    private final List<String> musicQuestions;
    private final List<String> musicChoices;
    private final List<String> musicAnswers;
    private final List<String> entertainmentQuestions;
    private final List<String> entertainmentChoices;
    private final List<String> entertainmentAnswers;
    private final List<String> historyQuestions;
    private final List<String> historyChoices;
    private final List<String> historyAnswers;
    private final List<String> geographyQuestions;
    private final List<String> geographyChoices;
    private final List<String> geographyAnswers;
    public static final String PATH_QUESTIONS = "src/Game/Questions/Resources/Questions";
    public static final String PATH_CHOICES = "src/Game/Questions/Resources/MultipleChoices";
    public static final String PATH_ANSWERS = "src/Game/Questions/Resources/CorrectAnswer";


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

    public String askQuestion(QuestionType questionType) {
        int random;
        return switch (questionType) {
            case HISTORY -> {
                random = new Random().nextInt(historyQuestions.size());
                yield historyQuestions.get(random);
            }
            case GEOGRAPHY -> {
                random = new Random().nextInt(geographyQuestions.size());
                yield geographyQuestions.get(random);
            }
            case ENTERTAINMENT -> {
                random = new Random().nextInt(entertainmentQuestions.size());
                yield entertainmentQuestions.get(random);
            }
            case MUSIC -> {
                random = new Random().nextInt(musicQuestions.size());
                yield musicQuestions.get(random);
            }
        };
    }

    public String getChoices(QuestionType questionType ,String currentQuestion) {
        return switch (questionType) {
            case HISTORY -> historyChoices.get(historyQuestions.indexOf(currentQuestion));
            case GEOGRAPHY -> geographyChoices.get(geographyQuestions.indexOf(currentQuestion));
            case ENTERTAINMENT -> entertainmentChoices.get(entertainmentQuestions.indexOf(currentQuestion));
            case MUSIC -> musicChoices.get(musicQuestions.indexOf(currentQuestion));
        };
    }

    public boolean checkIfAnswerIsCorrect(QuestionType questionType ,String question, String answer){
        return switch (questionType){
            case HISTORY -> answer.equals(historyAnswers.get(historyQuestions.indexOf(question)));
            case GEOGRAPHY -> answer.equals(geographyAnswers.get(geographyQuestions.indexOf(question)));
            case ENTERTAINMENT -> answer.equals(entertainmentAnswers.get(entertainmentQuestions.indexOf(question)));
            case MUSIC -> answer.equals(musicAnswers.get(musicQuestions.indexOf(question)));
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
