package receiver.service.android.vogella.com.project1edx.model;

/**
 * Created by hp on 06/05/2017.
 */

public class Card {
    String question;
    String answer;
    int id;

    public Card(String question) {
        this.question = question;
    }

    public Card() {
    }

    public Card(String question, String answer) {

        this.question = question;
        this.answer = answer;
    }

    public void setQuestion(String question) {

        this.question = question;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getQuestion() {

        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {

        this.id = id;
    }

}
