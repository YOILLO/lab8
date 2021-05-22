package commands;

import messages.AnswerMsg;

/**
 * Command for exit
 */
public class ExitCom extends AbstractCommand{
    public ExitCom()
    {
        super("exit", " завершить программу (без сохранения в файл)");
    }

    public boolean execute(String argument, Object objArg, AnswerMsg answerMsg) {
        return false;
    }
}
