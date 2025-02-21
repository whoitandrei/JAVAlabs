package src.commands;

import src.context.Context;
import src.exceptions.CommandException;

public class DefineCommand extends Command{
    @Override
    public void execute(Context context, String[] args) throws CommandException {
        if (args.length < 2) throw new CommandException("DEFINE requires 2 arguments");

        String name = args[0];
        Double value = Double.valueOf(args[1]);

        if (context.getDefines().containsKey(name)){
            throw new CommandException("this name [" + name +"] was defined earlier with "+ context.getDefines().get(name) + "value");
        }

        context.getDefines().put(name, value);
    }
}
