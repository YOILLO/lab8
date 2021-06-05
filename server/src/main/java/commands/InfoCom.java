package commands;

import collection.CollectionManager;
import messages.AnswerMsg;
import messages.Status;
import messages.User;

/**
 * Information about collection command
 */
public class InfoCom extends AbstractCommand{
    CollectionManager collection;

    public InfoCom(CollectionManager col)
    {
        super("info", ": вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)");
        collection = col;
    }

    @Override
    public boolean execute(String argument, Object objArg, AnswerMsg answerMsg, User user) {
        answerMsg.addMsg("инфрмация о коллекции:");
        answerMsg.addMsg(collection.toString());
        answerMsg.setStatus(Status.OK);
        return true;
    }
}
