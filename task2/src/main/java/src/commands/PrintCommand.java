package src.commands;

import src.context.Context;
import src.exceptions.CommandException;

public class PrintCommand extends Command{

    @Override
    public void execute(Context context, String[] args) throws CommandException {
        System.out.println(context.getStack().peek());
    }
}
