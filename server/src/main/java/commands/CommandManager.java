package commands;

import database_managers.DatabaseUserManager;
import io.ScriptManager;
import main.Main;
import messages.AnswerMsg;
import messages.CommandMsg;
import messages.Status;
import messages.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Manager that run all commands
 */
public class CommandManager {
    private List<commands.AbstractCommand> commands= new ArrayList<>();
    private Stack<String> files = new Stack<>();
    private DatabaseUserManager databaseUserManager;

    public CommandManager(commands.AbstractCommand[] com, DatabaseUserManager dbum){

        files.clear();

        databaseUserManager = dbum;

        for (commands.AbstractCommand comm : com)
        {
            commands.add(comm);
        }
    }

    /**
     * Launch console command
     * @return End or not to end
     */
    public synchronized boolean launchCommand(CommandMsg commandMsg, AnswerMsg answerMsg)
    {
        if (!databaseUserManager.checkUserByUsernameAndPassword(commandMsg.getUser())){
            Main.logger.error("\u041E\u0448\u0431\u0438\u043A\u0430");
            answerMsg.setStatus(Status.ERROR);
            return false;
        }
        Main.logger.info("\u0412\u044B\u0437\u044B\u0432\u0430\u0435\u0442\u0441\u044F \u043A\u043E\u043C\u0430\u043D\u0434\u0430 " + commandMsg.getCommand());
        if (commandMsg.getCommand().trim().equals("help")) {
            answerMsg.addMsg("help : \u0432\u044B\u0432\u0435\u0441\u0442\u0438 \u0441\u043F\u0440\u0430\u0432\u043A\u0443 \u043F\u043E \u0434\u043E\u0441\u0442\u0443\u043F\u043D\u044B\u043C \u043A\u043E\u043C\u0430\u043D\u0434\u0430\u043C");
            answerMsg.addMsg("execute_script file_name : \u0441\u0447\u0438\u0442\u0430\u0442\u044C \u0438 \u0438\u0441\u043F\u043E\u043B\u043D\u0438\u0442\u044C \u0441\u043A\u0440\u0438\u043F\u0442 \u0438\u0437 \u0443\u043A\u0430\u0437\u0430\u043D\u043D\u043E\u0433\u043E \u0444\u0430\u0439\u043B\u0430. \u0412 \u0441\u043A\u0440\u0438\u043F\u0442\u0435 \u0441\u043E\u0434\u0435\u0440\u0436\u0430\u0442\u0441\u044F \u043A\u043E\u043C\u0430\u043D\u0434\u044B \u0432 \u0442\u0430\u043A\u043E\u043C \u0436\u0435 \u0432\u0438\u0434\u0435, \u0432 \u043A\u043E\u0442\u043E\u0440\u043E\u043C \u0438\u0445 \u0432\u0432\u043E\u0434\u0438\u0442 \u043F\u043E\u043B\u044C\u0437\u043E\u0432\u0430\u0442\u0435\u043B\u044C \u0432 \u0438\u043D\u0442\u0435\u0440\u0430\u043A\u0442\u0438\u0432\u043D\u043E\u043C \u0440\u0435\u0436\u0438\u043C\u0435.");
            for (commands.AbstractCommand comm : commands) {
                answerMsg.addMsg(comm.getName() + comm.getDescription());
            }
        }
        else if (commandMsg.getCommand().trim().equals("execute_script")){
            if (!commandMsg.getArg().equals("")) {
                ScripMode(commandMsg.getArg().trim(), answerMsg, commandMsg.getUser());
                files.clear();
            }
            else{
                answerMsg.addError("\u041D\u0435\u043E\u0431\u0445\u043E\u0434\u0438\u043C file_name");
            }
        }
        else {
            for (commands.AbstractCommand comm : commands) {
                if (comm.getName().equals(commandMsg.getCommand())) {
                    return comm.execute(commandMsg.getArg(), commandMsg.getObjArg(), answerMsg, commandMsg.getUser());
                }
            }
            answerMsg.addError("\u0422\u0430\u043A\u043E\u0439 \u043A\u043E\u043C\u0430\u043D\u0434\u044B \u043D\u0435\u0442, \u043F\u0440\u043E\u0432\u0435\u0440\u044C help");
        }
        return true;
    }

    /**
     * Launch command for script
     * @return End or not to end
     */
    private boolean launchScriptCommand(AnswerMsg answerMsg, CommandMsg commandMsg)
    {
        Main.logger.info("\u0412\u044B\u0437\u044B\u0432\u0430\u0435\u0442\u0441\u044F \u043A\u043E\u043C\u0430\u043D\u0434\u0430 " + commandMsg.getCommand());
        if (commandMsg.getCommand().trim().equals("help")) {
            answerMsg.addMsg("help : \u0432\u044B\u0432\u0435\u0441\u0442\u0438 \u0441\u043F\u0440\u0430\u0432\u043A\u0443 \u043F\u043E \u0434\u043E\u0441\u0442\u0443\u043F\u043D\u044B\u043C \u043A\u043E\u043C\u0430\u043D\u0434\u0430\u043C");
            answerMsg.addMsg("execute_script file_name : \u0441\u0447\u0438\u0442\u0430\u0442\u044C \u0438 \u0438\u0441\u043F\u043E\u043B\u043D\u0438\u0442\u044C \u0441\u043A\u0440\u0438\u043F\u0442 \u0438\u0437 \u0443\u043A\u0430\u0437\u0430\u043D\u043D\u043E\u0433\u043E \u0444\u0430\u0439\u043B\u0430. \u0412 \u0441\u043A\u0440\u0438\u043F\u0442\u0435 \u0441\u043E\u0434\u0435\u0440\u0436\u0430\u0442\u0441\u044F \u043A\u043E\u043C\u0430\u043D\u0434\u044B \u0432 \u0442\u0430\u043A\u043E\u043C \u0436\u0435 \u0432\u0438\u0434\u0435, \u0432 \u043A\u043E\u0442\u043E\u0440\u043E\u043C \u0438\u0445 \u0432\u0432\u043E\u0434\u0438\u0442 \u043F\u043E\u043B\u044C\u0437\u043E\u0432\u0430\u0442\u0435\u043B\u044C \u0432 \u0438\u043D\u0442\u0435\u0440\u0430\u043A\u0442\u0438\u0432\u043D\u043E\u043C \u0440\u0435\u0436\u0438\u043C\u0435.");
            for (commands.AbstractCommand comm : commands) {
                answerMsg.addMsg(comm.getName() + comm.getDescription());
            }
        }
        else if (commandMsg.getCommand().trim().equals("execute_script")){
            if (!commandMsg.getArg().equals("")) {
                if (files.contains(commandMsg.getArg().trim()))
                    answerMsg.addError("\u043F\u043E\u043F\u044B\u0442\u043A\u0430 \u0440\u0435\u043A\u0443\u0440\u0441\u0438\u0432\u043D\u043E \u0432\u044B\u0437\u0432\u0430\u0442\u044C \u0441\u043A\u0440\u0438\u043F\u0442");
                else
                    ScripMode(commandMsg.getArg().trim(), answerMsg, commandMsg.getUser());
            }
            else{
                answerMsg.addError("\u041D\u0435\u043E\u0431\u0445\u043E\u0434\u0438\u043C file_name");
            }
        }
        else {
            for (commands.AbstractCommand comm : commands) {
                if (comm.getName().trim().equals(commandMsg.getCommand())) {
                    return comm.execute(commandMsg.getArg(), commandMsg.getObjArg(), answerMsg, commandMsg.getUser());
                }
            }
            answerMsg.addError("\u0422\u0430\u043A\u043E\u0439 \u043A\u043E\u043C\u0430\u043D\u0434\u044B \u043D\u0435\u0442, \u043F\u0440\u043E\u0432\u0435\u0440\u044C help");
        }
        return true;
    }

    /**
     * Start script mode
     * @param fileName Script file
     */
    private void ScripMode(String fileName, AnswerMsg answerMsg, User user){
        ScriptManager scr = new ScriptManager(fileName.trim());
        if (scr == null){
            answerMsg.addError("\u041D\u0435 \u043E\u0442\u043A\u0440\u044B\u0432\u0430\u0435\u0442\u0441\u044F \u0441\u043A\u0440\u0438\u043F\u0442");
        }
        else{
            files.push(fileName.trim());
            boolean isWork = true;
            while (isWork){
                String str = scr.readLine();
                if (str == null)
                    break;
                String[] userCommand;
                userCommand = (str + " ").split(" ", 2);
                Serializable obj = null;
                if (userCommand[0].trim().equals("add")){
                    obj = scr.readRowFlat();
                } else if (userCommand[0].trim().equals("update")){
                    obj = scr.readRowFlat();
                } else if (userCommand[0].trim().equals("remove_any_by_house")){
                    obj = scr.readHouse();
                } else if (userCommand[0].trim().equals("remove_greater")){
                    obj = scr.readRowFlat();
                }
                CommandMsg commandMsg = new CommandMsg(userCommand[0], userCommand[1], obj, user);
                isWork = launchScriptCommand(answerMsg, commandMsg);
            }
            answerMsg.addMsg(files.pop() + " \u0432\u044B\u043F\u043E\u043B\u043D\u0435\u043D");
        }
    }
}
