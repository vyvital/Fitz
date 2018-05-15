package vyvital.fitz.data.models;

public class QA {

    private String question;
    private String answer;

    public QA() {
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public QA(String q, String a) {
        this.question = q;
        this.answer = a;

    }
}
