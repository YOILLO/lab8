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
        super("add_if_min", " {element}: добавить новый элемент, если его значение меньше, чем у наименьшего");
        collection = col;
    }

    public boolean execute(String argument, Object objArg, AnswerMsg answerMsg, User user) {
        RowFlat fl = (RowFlat) objArg;
        Flat first = collection.getFirst();
        if (first == null) {
            answerMsg.addError("Коллекция пуста, ошибка");
            return true;
        }
        if (first.getPrice() > fl.getPrice())
        {
            collection.addToCollection(fl, user);
            answerMsg.addMsg("Добавлено");
        }
        else {
            answerMsg.addMsg("Недобавлено");
        }
        answerMsg.setStatus(Status.OK);
        return true;

    }
}
