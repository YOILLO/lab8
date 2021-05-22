package commands;

import collection.CollectionManager;
import messages.AnswerMsg;

/**
 * Clear collection command
 */
public class ClearCom extends AbstractCommand{
    private CollectionManager collection;

    public ClearCom(CollectionManager col)
    {
        super("clear", " очистить коллекцию");
        collection = col;
    }

    public boolean execute(String argument, Object objArg, AnswerMsg answerMsg) {
        collection.ClearCollection();
        answerMsg.addMsg("Успешно очищено!");
        return true;
    }
}
