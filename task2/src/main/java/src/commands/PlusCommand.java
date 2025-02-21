package src.commands;

import src.context.Context;
import src.exceptions.CommandException;

import java.util.Stack;

public class PlusCommand extends Command{
    @Override
    public void execute(Context context, String[] args) throws CommandException {
        Stack<Double> stack = context.getStack();

        if (stack.size() < 2) {
            throw new CommandException("Not enough elements in stack for PLUS operation");
        }

        double operand2 = stack.pop();
        double operand1 = stack.pop();

        double result = operand1 + operand2;

        stack.push(result);
    }
}
