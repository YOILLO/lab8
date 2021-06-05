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
        super("add", " {element}: Добавить новый элемент в коллекцию");
        collection = col;
    }

    public boolean execute(String argument, Object objArg, AnswerMsg answerMsg, User user) {
        RowFlat fl = (RowFlat) objArg;
        collection.addToCollection(fl, user);
        answerMsg.setStatus(Status.OK);
        return true;
    }
}
