package commands;

import collection.CollectionManager;
import messages.AnswerMsg;
import messages.Status;

/**
 * Remove by id
 */
public class RemoveById extends AbstractCommand{
    CollectionManager collection;

    public RemoveById(CollectionManager col)
    {
        super("remove_by_id", ": удалить элемент из коллекции по его id");
        collection = col;
    }

    @Override
    public boolean execute(String argument, Object objArg, AnswerMsg answerMsg) {
        try {
            if (collection.deleteByID(Integer.parseInt(argument.trim())))
                answerMsg.addMsg("Успешно удалено");
            else
                answerMsg.addMsg("Нет такого элемента");
            answerMsg.addMsg("Удалено");
        }
        catch (NumberFormatException e)
        {
            answerMsg.addError("ID должен быть числом");
        }
        answerMsg.setStatus(Status.OK);
        return true;
    }
}
