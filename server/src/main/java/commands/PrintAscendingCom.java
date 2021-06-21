package commands;

import collection.CollectionManager;
import messages.AnswerMsg;
import messages.Status;
import messages.User;

/**
 * Print sorted command
 */
public class PrintAscendingCom extends commands.AbstractCommand {
    CollectionManager collection;

    public PrintAscendingCom(CollectionManager col)
    {
        super("print_ascending", ": \u0432\u044B\u0432\u0435\u0441\u0442\u0438 \u044D\u043B\u0435\u043C\u0435\u043D\u0442\u044B \u043A\u043E\u043B\u043B\u0435\u043A\u0446\u0438\u0438 \u0432 \u043F\u043E\u0440\u044F\u0434\u043A\u0435 \u0432\u043E\u0437\u0440\u0430\u0441\u0442\u0430\u043D\u0438\u044F");
        collection = col;
    }

    @Override
    public boolean execute(String argument, Object objArg, AnswerMsg answerMsg, User user) {
        answerMsg.addMsg("\u041E\u0442\u0441\u043E\u0440\u0442\u0438\u0440\u043E\u0432\u0430\u043D\u043D\u0430\u044F \u043A\u043E\u043B\u043B\u0435\u043A\u0446\u0438\u044F:");
        answerMsg.addMsg(collection.printSort());
        answerMsg.setStatus(Status.OK);
        return true;
    }
}
