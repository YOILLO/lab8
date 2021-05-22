package messages;

import java.io.Serializable;

public class CommandMsg implements Serializable {
    private String command;
    private String arg;
    private Serializable objArg;

    public CommandMsg(String com, String ar, Serializable obAr){
        command = com;
        arg = ar;
        objArg = obAr;
    }

    public Serializable getObjArg() {
        return objArg;
    }

    public String getArg() {
        return arg;
    }

    public String getCommand() {
        return command;
    }
}
