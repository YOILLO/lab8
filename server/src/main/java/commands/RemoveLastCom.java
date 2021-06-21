package commands;

import collection.CollectionManager;
import messages.AnswerMsg;
import messages.Status;
import messages.User;

/**
 * Remove last command
 */
public class RemoveLastCom extends commands.AbstractCommand {
    private CollectionManager collection;

    public RemoveLastCom(CollectionManager col)
    {
        super("remove_last", ": \u0443\u0434\u0430\u043B\u0438\u0442\u044C \u043F\u043E\u0441\u043B\u0435\u0434\u043D\u0438\u0439 \u044D\u043B\u0435\u043C\u0435\u043D\u0442 \u0438\u0437 \u043A\u043E\u043B\u043B\u0435\u043A\u0446\u0438\u0438");
        collection = col;
    }

    @Override
    public boolean execute(String argument, Object objArg, AnswerMsg answerMsg, User user) {
        if(collection.removeLast())
            answerMsg.addMsg("\u041F\u043E\u0441\u043B\u0435\u0434\u043D\u0438\u0439 \u044D\u043B\u0435\u043C\u0435\u043D\u0442 \u0443\u0434\u0430\u043B\u0435\u043D");
        else
            answerMsg.addMsg("\u041A\u043E\u043B\u043B\u0435\u043A\u0446\u0438\u044F \u043F\u0443\u0441\u0442\u0430");
        answerMsg.setStatus(Status.OK);
        return true;
    }
}
