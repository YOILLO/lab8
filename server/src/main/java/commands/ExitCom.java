package commands;

import messages.AnswerMsg;
import messages.Status;
import messages.User;

/**
 * Command for exit
 */
public class ExitCom extends AbstractCommand{
    public ExitCom()
    {
        super("exit", " завершить программу (без сохранения в файл)");
    }

    public boolean execute(String argument, Object objArg, AnswerMsg answerMsg, User user) {
        answerMsg.setStatus(Status.EXIT);
        return false;
    }
}
