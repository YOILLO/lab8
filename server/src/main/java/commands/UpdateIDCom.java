package commands;

import com.sun.rowset.internal.Row;
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
        super("update", " id {element} : обновить значение элемента коллекции, id которого равен заданному");
        collection = col;
    }

    @Override
    public boolean execute(String argument, Object objArg, AnswerMsg answerMsg, User user) {
        int id;
        if (argument.equals(""))
        {
            answerMsg.addError("Нужен ID");
            return true;
        }
        try{
            id = Integer.parseInt(argument.trim());
        }catch (NumberFormatException e){
            answerMsg.addError("ID долже быть числом");
            return true;
        }
        RowFlat flt = (RowFlat)objArg;
        if (collection.replace(id, flt)){
            answerMsg.addMsg("Успешно заменено");
        }
        else {
            answerMsg.addMsg("Нет такого элемента");
        }
        answerMsg.setStatus(Status.OK);
        return true;
    }
}
