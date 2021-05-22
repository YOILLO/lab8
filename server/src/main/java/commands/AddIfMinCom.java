package commands;

import data.Flat;
import collection.CollectionManager;
import messages.AnswerMsg;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Add if element is less then min in collection
 */
public class AddIfMinCom extends AbstractCommand{
    private CollectionManager collection;
    private Scanner scanner;

    public AddIfMinCom(CollectionManager col, Scanner sc)
    {
        super("add_if_min", " {element}: добавить новый элемент, если его значение меньше, чем у наименьшего");
        collection = col;
        scanner = sc;
    }

    public boolean execute(String argument, Object objArg, AnswerMsg answerMsg) {

        return true;

    }
}
