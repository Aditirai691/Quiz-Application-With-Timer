import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

class Question {
    String question;
    String[] options;
    int correctAnswerIndex;

    public Question(String question, String[] options, int correctAnswerIndex) {
        this.question = question;
        this.options = options;
        this.correctAnswerIndex = correctAnswerIndex;
    }
}

public class QuizApplicationWithTimer {
    private List<Question> questions;
    private int score;

    public QuizApplicationWithTimer() {
        this.questions = new ArrayList<>();
        this.score = 0;
        loadQuestions();
    }

    private void loadQuestions() {
        // Sample questions, add more as needed
        questions.add(new Question("What is the capital of France?", new String[]{"1. Berlin", "2. London", "3. Paris", "4. Madrid"}, 2));
        questions.add(new Question("What is 5 + 3?", new String[]{"1. 6", "2. 8", "3. 9", "4. 7"}, 1));
        questions.add(new Question("Which planet is known as the Red Planet?", new String[]{"1. Earth", "2. Mars", "3. Venus", "4. Jupiter"}, 1));
    }

    public void startQuiz() {
        Scanner scanner = new Scanner(System.in);

        for (Question question : questions) {
            System.out.println(question.question);
            for (String option : question.options) {
                System.out.println(option);
            }

            boolean answered = false;
            long startTime = System.currentTimeMillis();
            int answer = -1;

            while (!answered) {
                if (System.currentTimeMillis() - startTime > TimeUnit.SECONDS.toMillis(10)) {
                    System.out.println("Time's up!");
                    break;
                }

                System.out.print("Enter your answer (1-4): ");
                if (scanner.hasNextInt()) {
                    answer = scanner.nextInt();
                    if (answer >= 1 && answer <= 4) {
                        answered = true;
                    } else {
                        System.out.println("Invalid option. Please choose between 1 and 4.");
                    }
                } else {
                    System.out.println("Please enter a number between 1 and 4.");
                    scanner.next();  // Clear invalid input
                }
            }

            // Check answer
            if (answered && answer - 1 == question.correctAnswerIndex) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Incorrect! The correct answer was " + (question.correctAnswerIndex + 1));
            }
            System.out.println();
        }

        displayResult();
        scanner.close();
    }

    private void displayResult() {
        System.out.println("Quiz Completed!");
        System.out.println("Your final score: " + score + " out of " + questions.size());
        System.out.println("Thanks for participating!");
    }

    public static void main(String[] args) {
        QuizApplicationWithTimer quiz = new QuizApplicationWithTimer();
        quiz.startQuiz();
    }
}
