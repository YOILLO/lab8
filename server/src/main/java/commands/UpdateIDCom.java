package commands;

import data.Flat;
import collection.CollectionManager;
import data.RowFlat;
import messages.AnswerMsg;
import messages.Status;
import messages.User;

/**
 * Update id command
 */
public class UpdateIDCom extends AbstractCommand {
    CollectionManager collection;

    public UpdateIDCom(CollectionManager col)
    {
        super("update", " id {element} : \u043E\u0431\u043D\u043E\u0432\u0438\u0442\u044C \u0437\u043D\u0430\u0447\u0435\u043D\u0438\u0435 \u044D\u043B\u0435\u043C\u0435\u043D\u0442\u0430 \u043A\u043E\u043B\u043B\u0435\u043A\u0446\u0438\u0438, id \u043A\u043E\u0442\u043E\u0440\u043E\u0433\u043E \u0440\u0430\u0432\u0435\u043D \u0437\u0430\u0434\u0430\u043D\u043D\u043E\u043C\u0443");
        collection = col;
    }

    @Override
    public boolean execute(String argument, Object objArg, AnswerMsg answerMsg, User user) {
        int id;
        if (argument.equals(""))
        {
            answerMsg.addError("\u041D\u0443\u0436\u0435\u043D ID");
            return true;
        }
        try{
            id = Integer.parseInt(argument.trim());
        }catch (NumberFormatException e){
            answerMsg.addError("ID \u0434\u043E\u043B\u0436\u0435 \u0431\u044B\u0442\u044C \u0447\u0438\u0441\u043B\u043E\u043C");
            return true;
        }
        RowFlat flt = (RowFlat)objArg;
        if (collection.replace(id, flt)){
            answerMsg.addMsg("\u0423\u0441\u043F\u0435\u0448\u043D\u043E \u0437\u0430\u043C\u0435\u043D\u0435\u043D\u043E");
        }
        else {
            answerMsg.addMsg("\u041D\u0435\u0442 \u0442\u0430\u043A\u043E\u0433\u043E \u044D\u043B\u0435\u043C\u0435\u043D\u0442\u0430");
        }
        answerMsg.setStatus(Status.OK);
        return true;
    }
}
