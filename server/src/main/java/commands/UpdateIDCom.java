package commands;

import data.Flat;
import collection.CollectionManager;
import data.RowFlat;
import messages.AnswerMsg;

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
    public boolean execute(String argument, Object objArg, AnswerMsg answerMsg) {
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
        Flat flt = new Flat((RowFlat)objArg, id);
        if (collection.replace(flt)){
            answerMsg.addMsg("Успешно заменено");
        }
        else {
            answerMsg.addMsg("Нет такого элемента");
        }
        return true;
    }
}
