package commands;

import collection.CollectionManager;
import messages.AnswerMsg;
import messages.Status;
import messages.User;

/**
 * Information about collection command
 */
public class InfoCom extends AbstractCommand{
    CollectionManager collection;

    public InfoCom(CollectionManager col)
    {
        super("info", ": \u0432\u044B\u0432\u0435\u0441\u0442\u0438 \u0432 \u0441\u0442\u0430\u043D\u0434\u0430\u0440\u0442\u043D\u044B\u0439 \u043F\u043E\u0442\u043E\u043A \u0432\u044B\u0432\u043E\u0434\u0430 \u0438\u043D\u0444\u043E\u0440\u043C\u0430\u0446\u0438\u044E \u043E \u043A\u043E\u043B\u043B\u0435\u043A\u0446\u0438\u0438 (\u0442\u0438\u043F, \u0434\u0430\u0442\u0430 \u0438\u043D\u0438\u0446\u0438\u0430\u043B\u0438\u0437\u0430\u0446\u0438\u0438, \u043A\u043E\u043B\u0438\u0447\u0435\u0441\u0442\u0432\u043E \u044D\u043B\u0435\u043C\u0435\u043D\u0442\u043E\u0432 \u0438 \u0442.\u0434.)");
        collection = col;
    }

    @Override
    public boolean execute(String argument, Object objArg, AnswerMsg answerMsg, User user) {
        answerMsg.addMsg("\u0438\u043D\u0444\u0440\u043C\u0430\u0446\u0438\u044F \u043E \u043A\u043E\u043B\u043B\u0435\u043A\u0446\u0438\u0438:");
        answerMsg.addMsg(collection.toString());
        answerMsg.setStatus(Status.OK);
        return true;
    }
}
