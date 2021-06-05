package commands;

import messages.AnswerMsg;
import messages.User;

/**
 * Interface for every command
 */
public interface ICommand {
    /**
     * Description for help command
     * @return Description
     */
    String getDescription();

    /**
     * Name for help and for command chose
     * @return Name
     */
    String getName();

    /**
     * Execution of command
     * @param argument Argument for some commands
     * @param objArg
     * @param answerMsg
     * @param user
     * @return End or not to end
     */
    boolean execute(String argument, Object objArg, AnswerMsg answerMsg, User user);
}
