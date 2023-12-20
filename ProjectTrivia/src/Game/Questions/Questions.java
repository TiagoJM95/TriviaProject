package Game.Questions;

import java.util.*;

public class Questions {


    public static boolean answerIsCorrect;
    public Questions(){
        answerIsCorrect = false;
    }
    private static List<String> questionList = new ArrayList<>(List.of(new String[]{
            "How old was Queen Elizabeth II when she was crowned the Queen of England?" +
                    "A: 33      B: 40      C: 22        D: 27",
            "What was the first message sent by morse code?" +
                    "A: 33      B: 40      C: 22        D: 27",
            "What president was a licensed bartender?" +
                    "A: 33      B: 40      C: 22        D: 27",
            "Who was the first president to visit all 50 states? " +
                    "A: 33      B: 40      C: 22        D: 27",
            "What inspired the creation of Google images?" +
                    "A: 33      B: 40      C: 22        D: 27"
    }));

    private static List<String> correctAnswers = new ArrayList<>(List.of(new String[]{
            "A",
            "A",
            "A",
            "A",
            "A"
    }));

    public static boolean checkIfAnswerIsCorrect(String question, String answer){
        int index = questionList.indexOf(question);
        boolean isCorrect = answer.equals(correctAnswers.get(index));
        answerIsCorrect = isCorrect;
        return isCorrect;
    }
    public boolean isAnswerIsCorrect() {
        return answerIsCorrect;
    }

    public static List<String> getQuestionList() {
        return questionList;
    }
}
