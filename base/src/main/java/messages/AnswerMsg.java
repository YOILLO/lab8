package messages;

import java.io.Serializable;

public class AnswerMsg implements Serializable {
    String answer;
    Status status;

    public AnswerMsg(){
        answer = "";
    }

    public void addMsg(String msg){
        answer += msg + "\n";
    }

    public void addError(String msg){
        answer += "error: " + msg + "\n";
    }

    public void setStatus(Status st){
        status = st;
    }

    public String getAnswer(){
        return answer;
    }

    public Status getStatus(){
        return status;
    }

}
