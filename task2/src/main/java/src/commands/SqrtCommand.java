package src.commands;

import src.context.Context;
import src.exceptions.CommandException;

import java.util.Stack;

import static java.lang.Math.sqrt;

public class SqrtCommand extends Command{
    @Override
    public void execute(Context context, String[] args) throws CommandException {
        Stack<Double> stack = context.getStack();

        if (stack.isEmpty()) {
            throw new CommandException("Not enough elements in stack for SQRT operation");
        }

        double operand = stack.pop();
        double result = sqrt(operand);
        stack.push(result);
    }
}
