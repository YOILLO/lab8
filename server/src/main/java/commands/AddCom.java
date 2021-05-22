package commands;

import data.Flat;
import collection.CollectionManager;
import data.RowFlat;
import messages.AnswerMsg;

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
        answerMsg.addMsg(fl.toString());
        collection.AddToCollection(new Flat(fl, collection.generateNextId()));

        return true;
    }
}
