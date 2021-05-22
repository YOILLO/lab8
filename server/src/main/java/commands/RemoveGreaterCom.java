package commands;

import data.*;
import collection.CollectionManager;
import messages.AnswerMsg;

import java.util.NoSuchElementException;
import java.util.Scanner;


/**
 * Remove greatest command
 */
public class RemoveGreaterCom extends AbstractCommand{
    CollectionManager collection;
    Scanner scanner;

    public RemoveGreaterCom(CollectionManager col, Scanner sc)
    {
        super("remove_greater", " {element} : удалить из коллекции все элементы, превышающие заданный");
        collection = col;
        scanner = sc;
    }

    @Override
    public boolean execute(String argument, Object objArg, AnswerMsg answerMsg) {
        int ans = collection.deleteGreater((Flat) objArg);
        answerMsg.addMsg("Удалено " + ans + " элементов");
        return true;
    }
}
