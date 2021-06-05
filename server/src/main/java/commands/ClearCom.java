package commands;

import collection.CollectionManager;
import messages.AnswerMsg;
import messages.Status;
import messages.User;

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

    public boolean execute(String argument, Object objArg, AnswerMsg answerMsg, User user) {
        collection.clearCollection(user);
        answerMsg.addMsg("Успешно очищено!");
        answerMsg.setStatus(Status.OK);
        return true;
    }
}
