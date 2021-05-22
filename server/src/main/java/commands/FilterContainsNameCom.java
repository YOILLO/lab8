package commands;

import collection.CollectionManager;
import exceptions.WrongFormat;
import messages.AnswerMsg;

import java.util.regex.PatternSyntaxException;

/**
 * Filter by name contains
 */
public class FilterContainsNameCom extends AbstractCommand {
    private CollectionManager collection;

    public FilterContainsNameCom(CollectionManager col)
    {
        super("filter_contains_name"," name: вывести элементы, значение поля name которых содержит заданную подстроку");
        collection = col;
    }

    public boolean execute(String argument, Object objArg, AnswerMsg answerMsg) {
        try{
            if (argument.isEmpty()) throw new WrongFormat();
            answerMsg.addMsg("Ищу по шаблону " + argument.trim());
            String info = collection.nameFillteredInfo(argument.trim());
            if (info.isEmpty())
            {
                answerMsg.addError("В колекции нет таких имен");
                return true;
            }
            else
            {
                answerMsg.addMsg(info);
                return true;
            }

        } catch (WrongFormat e){
            answerMsg.addError("Неверный формат, забыл имя");
        } catch (PatternSyntaxException e)
        {
            answerMsg.addError("Неверный шаблон");
        }
        return true;
    }
}
