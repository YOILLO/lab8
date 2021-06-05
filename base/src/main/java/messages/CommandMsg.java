package messages;

import java.io.Serializable;

public class CommandMsg implements Serializable {
    private String command;
    private String arg;
    private Serializable objArg;
    private User user;

    public CommandMsg(String com, String ar, Serializable obAr, User us){
        command = com;
        arg = ar;
        objArg = obAr;
        user = us;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
