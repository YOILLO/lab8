package commands;

import data.Flat;
import collection.CollectionManager;
import data.RowFlat;
import messages.AnswerMsg;
import messages.Status;
import messages.User;

/**
 * Add element command
 */
public class AddCom extends AbstractCommand{
    private CollectionManager collection;

    public AddCom(CollectionManager col)
    {
        super("add", " {element}: \u0414\u043E\u0431\u0430\u0432\u0438\u0442\u044C \u043D\u043E\u0432\u044B\u0439 \u044D\u043B\u0435\u043C\u0435\u043D\u0442 \u0432 \u043A\u043E\u043B\u043B\u0435\u043A\u0446\u0438\u044E");
        collection = col;
    }

    public boolean execute(String argument, Object objArg, AnswerMsg answerMsg, User user) {
        RowFlat fl = (RowFlat) objArg;
        collection.addToCollection(fl, user);
        answerMsg.setStatus(Status.OK);
        return true;
    }
}
