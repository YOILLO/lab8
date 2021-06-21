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
        super("exit", " \u0437\u0430\u0432\u0435\u0440\u0448\u0438\u0442\u044C \u043F\u0440\u043E\u0433\u0440\u0430\u043C\u043C\u0443 (\u0431\u0435\u0437 \u0441\u043E\u0445\u0440\u0430\u043D\u0435\u043D\u0438\u044F \u0432 \u0444\u0430\u0439\u043B)");
    }

    public boolean execute(String argument, Object objArg, AnswerMsg answerMsg, User user) {
        answerMsg.setStatus(Status.EXIT);
        return false;
    }
}
