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
        super("remove_any_by_house", " house : \u0443\u0434\u0430\u043B\u0438\u0442\u044C \u0438\u0437 \u043A\u043E\u043B\u043B\u0435\u043A\u0446\u0438\u0438 \u043E\u0434\u0438\u043D \u044D\u043B\u0435\u043C\u0435\u043D\u0442, \u0437\u043D\u0430\u0447\u0435\u043D\u0438\u0435 \u043F\u043E\u043B\u044F house \u043A\u043E\u0442\u043E\u0440\u043E\u0433\u043E \u044D\u043A\u0432\u0438\u0432\u0430\u043B\u0435\u043D\u0442\u043D\u043E \u0437\u0430\u0434\u0430\u043D\u043D\u043E\u043C\u0443");
        collection = col;
    }

    @Override
    public boolean execute(String argument, Object objArg, AnswerMsg answerMsg, User user) {
        if (collection.deleteByHouse((House) objArg)){
            answerMsg.addMsg("\u0423\u0441\u043F\u0435\u0448\u043D\u043E \u0443\u0434\u0430\u043B\u0435\u043D\u043E");
        }else {
            answerMsg.addMsg("\u041D\u0435\u0442 \u0442\u0430\u043A\u043E\u0433\u043E \u044D\u043B\u0435\u043C\u0435\u043D\u0442\u0430");
        }
        answerMsg.setStatus(Status.OK);
        return true;
    }
}
