package commands;

import collection.CollectionManager;
import messages.AnswerMsg;
import messages.Status;
import messages.User;

/**
 * Show collection command
 */
public class ShowCom extends AbstractCommand{
    CollectionManager collection;

    public ShowCom(CollectionManager col){
        super("show", ": вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
        collection = col;
    }

    @Override
    public boolean execute(String argument, Object objArg, AnswerMsg answerMsg, User user) {
        answerMsg.addMsg("Колекция:");
        answerMsg.addMsg(collection.printNormal());
        answerMsg.setStatus(Status.OK);
        return true;
    }
}
