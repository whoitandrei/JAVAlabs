// ДОБАВИТЬ ОБРАБОТКУ 2 И БОЛЕЕ АРГУМНТОВ

package src.commands;

import src.context.Context;
import src.exceptions.CommandException;

public class PushCommand extends Command{
    @Override
    public void execute(Context context, String[] args) throws CommandException {
        if (args.length < 1) throw new CommandException("PUSH command requires an argument");

        String arg = args[0];
        double value;

        if (context.getDefines().containsKey(arg)){
            value = context.getDefines().get(arg);
        }
        else {
            try {
                value = Double.parseDouble(arg);
            } catch (NumberFormatException e) {
                throw new CommandException("Invalid argument for PUSH: " + arg);
            }
        }
        context.getStack().push(value);
    }
}
