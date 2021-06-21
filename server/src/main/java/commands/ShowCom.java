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
        super("show", ": \u0432\u044B\u0432\u0435\u0441\u0442\u0438 \u0432 \u0441\u0442\u0430\u043D\u0434\u0430\u0440\u0442\u043D\u044B\u0439 \u043F\u043E\u0442\u043E\u043A \u0432\u044B\u0432\u043E\u0434\u0430 \u0432\u0441\u0435 \u044D\u043B\u0435\u043C\u0435\u043D\u0442\u044B \u043A\u043E\u043B\u043B\u0435\u043A\u0446\u0438\u0438 \u0432 \u0441\u0442\u0440\u043E\u043A\u043E\u0432\u043E\u043C \u043F\u0440\u0435\u0434\u0441\u0442\u0430\u0432\u043B\u0435\u043D\u0438\u0438");
        collection = col;
    }

    @Override
    public boolean execute(String argument, Object objArg, AnswerMsg answerMsg, User user) {
        answerMsg.addMsg("\u041A\u043E\u043B\u0435\u043A\u0446\u0438\u044F:");
        answerMsg.addMsg(collection.printNormal());
        answerMsg.setStatus(Status.OK);
        return true;
    }
}
