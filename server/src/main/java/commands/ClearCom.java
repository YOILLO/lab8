package commands;

import collection.CollectionManager;
import messages.AnswerMsg;
import messages.Status;
import messages.User;

/**
 * Clear collection command
 */
public class ClearCom extends AbstractCommand{
    private CollectionManager collection;

    public ClearCom(CollectionManager col)
    {
        super("clear", " \u043E\u0447\u0438\u0441\u0442\u0438\u0442\u044C \u043A\u043E\u043B\u043B\u0435\u043A\u0446\u0438\u044E");
        collection = col;
    }

    public boolean execute(String argument, Object objArg, AnswerMsg answerMsg, User user) {
        collection.clearCollection(user);
        answerMsg.addMsg("\u0423\u0441\u043F\u0435\u0448\u043D\u043E \u043E\u0447\u0438\u0449\u0435\u043D\u043E!");
        answerMsg.setStatus(Status.OK);
        return true;
    }
}
