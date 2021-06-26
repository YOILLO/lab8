package messages;

import data.Flat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Vector;

public class AnswerMsg implements Serializable {

    String answer;
    Status status;
    ArrayList<Flat> obj;

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

    public ArrayList<Flat> getObj() {
        return obj;
    }

    public void setObj(ArrayList<Flat> obj) {
        this.obj = obj;
    }
}
