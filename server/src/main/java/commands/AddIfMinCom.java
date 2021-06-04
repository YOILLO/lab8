package commands;

import data.Flat;
import collection.CollectionManager;
import messages.AnswerMsg;
import messages.Status;

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

    public boolean execute(String argument, Object objArg, AnswerMsg answerMsg) {
        Flat fl = (Flat) objArg;
        Flat first = collection.getFirst();
        if (first == null) {
            answerMsg.addError("Коллекция пуста, ошибка");
            return true;
        }
        if (first.getId() > fl.getId())
        {
            collection.addToCollection(fl);
            answerMsg.addMsg("Добавлено");
        }
        else {
            answerMsg.addMsg("Недобавлено");
        }
        answerMsg.setStatus(Status.OK);
        return true;

    }
}
