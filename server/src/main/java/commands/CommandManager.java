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
            Main.logger.error("Ошбика");
            answerMsg.setStatus(Status.ERROR);
            return false;
        }
        Main.logger.info("Вызывается команда " + commandMsg.getCommand());
        if (commandMsg.getCommand().trim().equals("help")) {
            answerMsg.addMsg("help : вывести справку по доступным командам");
            answerMsg.addMsg("execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.");
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
                answerMsg.addError("Необходим file_name");
            }
        }
        else {
            for (commands.AbstractCommand comm : commands) {
                if (comm.getName().equals(commandMsg.getCommand())) {
                    return comm.execute(commandMsg.getArg(), commandMsg.getObjArg(), answerMsg, commandMsg.getUser());
                }
            }
            answerMsg.addError("Такой команды нет, проверь help");
        }
        return true;
    }

    /**
     * Launch command for script
     * @return End or not to end
     */
    private boolean launchScriptCommand(AnswerMsg answerMsg, CommandMsg commandMsg)
    {
        Main.logger.info("Вызывается команда " + commandMsg.getCommand());
        if (commandMsg.getCommand().trim().equals("help")) {
            answerMsg.addMsg("help : вывести справку по доступным командам");
            answerMsg.addMsg("execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.");
            for (commands.AbstractCommand comm : commands) {
                answerMsg.addMsg(comm.getName() + comm.getDescription());
            }
        }
        else if (commandMsg.getCommand().trim().equals("execute_script")){
            if (!commandMsg.getArg().equals("")) {
                if (files.contains(commandMsg.getArg().trim()))
                    answerMsg.addError("попытка рекурсивно вызвать скрипт");
                else
                    ScripMode(commandMsg.getArg().trim(), answerMsg, commandMsg.getUser());
            }
            else{
                answerMsg.addError("Необходим file_name");
            }
        }
        else {
            for (commands.AbstractCommand comm : commands) {
                if (comm.getName().trim().equals(commandMsg.getCommand())) {
                    return comm.execute(commandMsg.getArg(), commandMsg.getObjArg(), answerMsg, commandMsg.getUser());
                }
            }
            answerMsg.addError("Такой команды нет, проверь help");
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
            answerMsg.addError("Не открывается скрипт");
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
            answerMsg.addMsg(files.pop() + " выполнен");
        }
    }
}
