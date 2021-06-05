package commands;

import collection.CollectionManager;
import data.House;
import messages.AnswerMsg;
import messages.Status;
import messages.User;

/**
 * Remove one element by house
 */
public class RemoveAnyByHouseCom extends AbstractCommand{
    private CollectionManager collection;

    public RemoveAnyByHouseCom(CollectionManager col)
    {
        super("remove_any_by_house", " house : удалить из коллекции один элемент, значение поля house которого эквивалентно заданному");
        collection = col;
    }

    @Override
    public boolean execute(String argument, Object objArg, AnswerMsg answerMsg, User user) {
        if (collection.deleteByHouse((House) objArg)){
            answerMsg.addMsg("Успешно удалено");
        }else {
            answerMsg.addMsg("Нет такого элемента");
        }
        answerMsg.setStatus(Status.OK);
        return true;
    }
}
