package commands;

import collection.CollectionManager;
import messages.AnswerMsg;
import messages.Status;
import messages.User;

/**
 * Remove by id
 */
public class RemoveById extends AbstractCommand{
    CollectionManager collection;

    public RemoveById(CollectionManager col)
    {
        super("remove_by_id", ": \u0443\u0434\u0430\u043B\u0438\u0442\u044C \u044D\u043B\u0435\u043C\u0435\u043D\u0442 \u0438\u0437 \u043A\u043E\u043B\u043B\u0435\u043A\u0446\u0438\u0438 \u043F\u043E \u0435\u0433\u043E id");
        collection = col;
    }

    @Override
    public boolean execute(String argument, Object objArg, AnswerMsg answerMsg, User user) {
        try {
            if (collection.deleteByID(Integer.parseInt(argument.trim())))
                answerMsg.addMsg("\u0423\u0441\u043F\u0435\u0448\u043D\u043E \u0443\u0434\u0430\u043B\u0435\u043D\u043E");
            else
                answerMsg.addMsg("\u041D\u0435\u0442 \u0442\u0430\u043A\u043E\u0433\u043E \u044D\u043B\u0435\u043C\u0435\u043D\u0442\u0430");
            answerMsg.addMsg("\u0423\u0434\u0430\u043B\u0435\u043D\u043E");
        }
        catch (NumberFormatException e)
        {
            answerMsg.addError("ID \u0434\u043E\u043B\u0436\u0435\u043D \u0431\u044B\u0442\u044C \u0447\u0438\u0441\u043B\u043E\u043C");
        }
        answerMsg.setStatus(Status.OK);
        return true;
    }
}
