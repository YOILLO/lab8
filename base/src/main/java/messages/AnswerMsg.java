package messages;

import java.io.Serializable;

public class AnswerMsg implements Serializable {

    String answer;
    Status status;
    Serializable obj;

    public AnswerMsg(){
        answer = "";
        obj = null;
    }

    public void addMsg(String msg){
        answer += msg + "\n";
    }

    public void addError(String msg){
        answer += "err: " + msg + "\n";
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

    public Serializable getObj() {
        return obj;
    }

    public void setObj(Serializable obj) {
        this.obj = obj;
    }
}
