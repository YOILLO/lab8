package commands;

import data.Flat;
import collection.CollectionManager;
import data.RowFlat;
import messages.AnswerMsg;
import messages.Status;
import messages.User;

/**
 * Add if element is less then min in collection
 */
public class AddIfMinCom extends AbstractCommand{
    private CollectionManager collection;

    public AddIfMinCom(CollectionManager col)
    {
        super("add_if_min", " {element}: \u0434\u043E\u0431\u0430\u0432\u0438\u0442\u044C \u043D\u043E\u0432\u044B\u0439 \u044D\u043B\u0435\u043C\u0435\u043D\u0442, \u0435\u0441\u043B\u0438 \u0435\u0433\u043E \u0437\u043D\u0430\u0447\u0435\u043D\u0438\u0435 \u043C\u0435\u043D\u044C\u0448\u0435, \u0447\u0435\u043C \u0443 \u043D\u0430\u0438\u043C\u0435\u043D\u044C\u0448\u0435\u0433\u043E");
        collection = col;
    }

    public boolean execute(String argument, Object objArg, AnswerMsg answerMsg, User user) {
        RowFlat fl = (RowFlat) objArg;
        Flat first = collection.getFirst();
        if (first == null) {
            answerMsg.addError("\u041A\u043E\u043B\u043B\u0435\u043A\u0446\u0438\u044F \u043F\u0443\u0441\u0442\u0430, \u043E\u0448\u0438\u0431\u043A\u0430");
            return true;
        }
        if (first.getPrice() > fl.getPrice())
        {
            collection.addToCollection(fl, user);
            answerMsg.addMsg("\u0414\u043E\u0431\u0430\u0432\u043B\u0435\u043D\u043E");
        }
        else {
            answerMsg.addMsg("\u041D\u0435\u0434\u043E\u0431\u0430\u0432\u043B\u0435\u043D\u043E");
        }
        answerMsg.setStatus(Status.OK);
        return true;

    }
}
