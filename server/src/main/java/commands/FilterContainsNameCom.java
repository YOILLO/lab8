package commands;

import collection.CollectionManager;
import exceptions.WrongFormat;
import messages.AnswerMsg;
import messages.Status;
import messages.User;

import java.util.regex.PatternSyntaxException;

/**
 * Filter by name contains
 */
public class FilterContainsNameCom extends AbstractCommand {
    private CollectionManager collection;

    public FilterContainsNameCom(CollectionManager col)
    {
        super("filter_contains_name"," name: \u0432\u044B\u0432\u0435\u0441\u0442\u0438 \u044D\u043B\u0435\u043C\u0435\u043D\u0442\u044B, \u0437\u043D\u0430\u0447\u0435\u043D\u0438\u0435 \u043F\u043E\u043B\u044F name \u043A\u043E\u0442\u043E\u0440\u044B\u0445 \u0441\u043E\u0434\u0435\u0440\u0436\u0438\u0442 \u0437\u0430\u0434\u0430\u043D\u043D\u0443\u044E \u043F\u043E\u0434\u0441\u0442\u0440\u043E\u043A\u0443");
        collection = col;
    }

    public boolean execute(String argument, Object objArg, AnswerMsg answerMsg, User user) {
        try{
            if (argument.isEmpty()) throw new WrongFormat();
            answerMsg.addMsg("\u0438\u0449\u0443 \u043F\u043E \u0448\u0430\u0431\u043B\u043E\u043D\u0443 " + argument.trim());
            String info = collection.nameFillteredInfo(argument.trim());
            if (info.isEmpty())
            {
                answerMsg.addError("\u0412 \u043A\u043E\u043B\u0435\u043A\u0446\u0438\u0438 \u043D\u0435\u0442 \u0442\u0430\u043A\u0438\u0445 \u0438\u043C\u0435\u043D");
                return true;
            }
            else
            {
                answerMsg.addMsg(info);
                return true;
            }

        } catch (WrongFormat e){
            answerMsg.addError("\u041D\u0435\u0432\u0435\u0440\u043D\u044B\u0439 \u0444\u043E\u0440\u043C\u0430\u0442, \u0437\u0430\u0431\u044B\u043B \u0438\u043C\u044F");
        } catch (PatternSyntaxException e)
        {
            answerMsg.addError("\u041D\u0435\u0432\u0435\u0440\u043D\u044B\u0439 \u0448\u0430\u0431\u043B\u043E\u043D");
        }
        answerMsg.setStatus(Status.OK);
        return true;
    }
}
