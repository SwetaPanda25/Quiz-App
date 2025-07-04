import java.util.*;
import java.util.concurrent.TimeUnit;

public class QuizApp {
    private static final int TIME_LIMIT = 20;

    public static void main(String [] args){
        Scanner sc = new Scanner(System.in);
        List<Question> q = new ArrayList<>();

        q.add(new Question("Who invented Java?", new String[]{"James Gosling", "Dennis Ritchie", "Bjarne Stroustrup", "Guido van Rossum"}, 'A'));
        q.add(new Question("When did implementation of python started?", new String[]{"1990", "1980", "1989", "2002"}, 'C'));
        q.add(new Question("What is size of int in java?", new String[]{"32", "16", "64", "8"}, 'A'));

        int score = 0;
        int QNo = 1;

        long quizStart = System.nanoTime();

        System.out.println("Let's start the Quiz!");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for(Question que : q){
            System.out.println("\nQuestion "+QNo+":");
            que.display();

            Timer t = new Timer();
            Answer task = new Answer();
            t.schedule(task, TIME_LIMIT*1000);

            System.out.print("You have "+TIME_LIMIT+" seconds.\nYour answer: ");
            String input = null;

            try{
                input = sc.nextLine();
            }
            catch(Exception e){
                System.out.println("No input received.");
            }
            t.cancel();

            if(task.isTimeout()){
                System.out.println("Time's up! You didn't answer in time.");
            }
            else if(input != null && !input.isEmpty()){
                char userAnswer = input.charAt(0);
                if(que.isCorrect(userAnswer)){
                    System.out.println("Correct!");
                    score++;
                }
                else{
                    System.out.println("Incorrect!");
                }
            }
            QNo++;
        }
        long quizEnd = System.nanoTime();
        long timeTakenInSeconds = TimeUnit.NANOSECONDS.toSeconds(quizEnd - quizStart);

        System.out.println("\nYour final score: "+score);
        System.out.println("Total time taken: " + timeTakenInSeconds + " seconds");
    }

    static class Answer extends TimerTask{
        private boolean timeout = false;

        public void run(){
            timeout = true;
            System.out.println("Time Expired!");
        }

        public boolean isTimeout(){
            return timeout;
        }
    }
}
