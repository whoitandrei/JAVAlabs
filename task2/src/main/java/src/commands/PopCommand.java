package src.commands;

import src.context.Context;
import src.exceptions.CommandException;

import java.util.Stack;

public class PopCommand extends Command{
    @Override
    public void execute(Context context, String[] args) throws CommandException {
        Stack<Double> stack = context.getStack();

        if (stack.isEmpty()) throw new CommandException("Stack is empty, cannot execute POP");

        stack.pop();
    }
}
