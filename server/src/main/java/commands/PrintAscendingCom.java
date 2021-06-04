package commands;

import collection.CollectionManager;
import messages.AnswerMsg;
import messages.Status;

/**
 * Print sorted command
 */
public class PrintAscendingCom extends commands.AbstractCommand {
    CollectionManager collection;

    public PrintAscendingCom(CollectionManager col)
    {
        super("print_ascending", ": вывести элементы коллекции в порядке возрастания");
        collection = col;
    }

    @Override
    public boolean execute(String argument, Object objArg, AnswerMsg answerMsg) {
        answerMsg.addMsg("Отсортированная коллекция:");
        answerMsg.addMsg(collection.printSort());
        answerMsg.setStatus(Status.OK);
        return true;
    }
}
