package commands;

import collection.CollectionManager;
import messages.AnswerMsg;
import messages.Status;
import messages.User;

/**
 * Remove last command
 */
public class RemoveLastCom extends commands.AbstractCommand {
    private CollectionManager collection;

    public RemoveLastCom(CollectionManager col)
    {
        super("remove_last", ": удалить последний элемент из коллекции");
        collection = col;
    }

    @Override
    public boolean execute(String argument, Object objArg, AnswerMsg answerMsg, User user) {
        if(collection.removeLast())
            answerMsg.addMsg("Последний элемент удален");
        else
            answerMsg.addMsg("Коллекция пуста");
        answerMsg.setStatus(Status.OK);
        return true;
    }
}
