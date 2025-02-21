package src.commands;

import src.context.Context;
import src.exceptions.CommandException;

public abstract class Command {
    public abstract void execute(Context context, String[] args) throws CommandException;
}
