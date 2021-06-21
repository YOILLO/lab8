package commands;

import collection.CollectionManager;
import messages.AnswerMsg;
import messages.Status;
import messages.User;

/**
 * Save collection command
 */
public class SaveCom extends commands.AbstractCommand {
    CollectionManager collection;

    public SaveCom(CollectionManager coll)
    {
        super("save", " : \u0441\u043E\u0445\u0440\u0430\u043D\u0438\u0442\u044C \u043A\u043E\u043B\u043B\u0435\u043A\u0446\u0438\u044E \u0432 \u0444\u0430\u0439\u043B");
        collection = coll;
    }

    @Override
    public boolean execute(String argument, Object objArg, AnswerMsg answerMsg, User user) {

        return true;
    }
}
