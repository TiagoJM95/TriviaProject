package Game.Questions;

import java.util.*;

public class Questions {
    private static List<String> questions = new ArrayList<>();
    private List<String> correctAnswer = new ArrayList<>();
    private List<String> answers = new ArrayList<>();
    private QuestionType questionType;
    private final int random = new Random().nextInt(5);

    public Questions(QuestionType questionType){
        addHistoryQuestion();
        addAllAnswers();
        questionWithAllAnswers();
    }





    private void addHistoryQuestion(){
        questions.add("How old was Queen Elizabeth II when she was crowned the Queen of England?");
        questions.add("What was the first message sent by morse code?");
        questions.add("What president was a licensed bartender?");
        questions.add("Who was the first president to visit all 50 states? ");
        questions.add("What inspired the creation of Google images?");
    }
    private void addCorrectAnswers(){
        correctAnswer.add(" 27");
        correctAnswer.add("What hath God wrought");
        correctAnswer.add("Abraham Lincoln");
        correctAnswer.add("Richard Nixon");
        correctAnswer.add("Jennifer Lopez's dress at the 2000 Grammys");
    }
    public String getCorrectAnswer(){
        return correctAnswer.get(random);
    }
    public String getQuestion(){
       return questions.get(random);
    }

    private void addAllAnswers(){
        answers.add(" 33\n" + " 40\n" + " 22");
        answers.add("Hello, World!\n" + "What hath God wrought?\n" + "SOS\n" + "Eureka!");
        answers.add("Abraham Lincoln\n" + " John F. Kennedy\n" + " Ronald Reagan\n" + " Franklin D. Roosevelt");
        answers.add("Richard Nixon\n" + " Jimmy Carter\n" + " George Washington\n" + " Barack Obama");
        answers.add("Jennifer Lopez's dress at the 2000 Grammys\n" + "The Mona Lisa\n" + "The Eiffel Tower\n" +
                "The Hubble Space Telescope images");


    }
    private void shuffleAnswers(){
        String allAnswers = answers.get(random);
        String[] lines = allAnswers.split("\n");
        List<String> answersList = Arrays.asList(lines);
        Collections.shuffle(answersList);

        for (int i = 0; i < answersList.size(); i++) {
            String value = answersList.get(i).trim();
            System.out.println((i + 1) + ": " + value);
        }
    }
    public void questionWithAllAnswers(){
        System.out.println(getQuestion());
        shuffleAnswers();
    }
    public void checkIfAnswerIsCorrect(String answer){
        if(answer.equals(getCorrectAnswer())){
            System.out.println("You responded" + answer +"and your answer ...." + " is correct");
        }
        if(answer.equals(getCorrectAnswer())){

        }


    }


    public static void main(String[] args) {
        new Questions(QuestionType.HISTORY);
    }



}
