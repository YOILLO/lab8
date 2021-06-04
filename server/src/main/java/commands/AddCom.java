package commands;

import data.Flat;
import collection.CollectionManager;
import data.RowFlat;
import messages.AnswerMsg;
import messages.Status;

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

    public boolean execute(String argument, Object objArg, AnswerMsg answerMsg) {
        RowFlat fl = (RowFlat) objArg;
        answerMsg.addMsg("Добавлен элемент с ID " + collection.generateNextId());
        collection.addToCollection(new Flat(fl, collection.generateNextId()));
        answerMsg.setStatus(Status.OK);
        return true;
    }
}
