public class Question{
    private String question;
    private String[] options;
    private char correct;

    public Question(String question, String[] options, char correct){
        this.question = question;
        this.options = options;
        this.correct = correct;
    }

    public void display(){
        System.out.println(question);
        char optionLabel = 'A';
        for(String op : options){
            System.out.println(optionLabel+". "+op);
            optionLabel++;
        }
    }

    public boolean isCorrect(char userAnswer) {
        return Character.toLowerCase(userAnswer) == Character.toLowerCase(correct);
    }
}