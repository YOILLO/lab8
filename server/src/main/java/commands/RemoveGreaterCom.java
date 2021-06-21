package commands;

import data.*;
import collection.CollectionManager;
import messages.AnswerMsg;
import messages.Status;
import messages.User;


/**
 * Remove greatest command
 */
public class RemoveGreaterCom extends AbstractCommand{
    CollectionManager collection;

    public RemoveGreaterCom(CollectionManager col)
    {
        super("remove_greater", " {element} : \u0443\u0434\u0430\u043B\u0438\u0442\u044C \u0438\u0437 \u043A\u043E\u043B\u043B\u0435\u043A\u0446\u0438\u0438 \u0432\u0441\u0435 \u044D\u043B\u0435\u043C\u0435\u043D\u0442\u044B, \u043F\u0440\u0435\u0432\u044B\u0448\u0430\u044E\u0449\u0438\u0435 \u0437\u0430\u0434\u0430\u043D\u043D\u044B\u0439");
        collection = col;
    }

    @Override
    public boolean execute(String argument, Object objArg, AnswerMsg answerMsg, User user) {
        int ans = collection.deleteGreater((Flat) objArg);
        answerMsg.addMsg("\u0423\u0434\u0430\u043B\u0435\u043D\u043E " + ans + " \u044D\u043B\u0435\u043C\u0435\u043D\u0442\u043E\u0432");
        answerMsg.setStatus(Status.OK);
        return true;
    }
}
