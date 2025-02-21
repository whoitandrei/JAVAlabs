package src.commands;

import src.context.Context;
import src.exceptions.CommandException;

import java.util.Stack;

public class DivCommand extends Command{
    @Override
    public void execute(Context context, String[] args) throws CommandException {
        Stack<Double> stack = context.getStack();

        if (stack.size() < 2) {
            throw new CommandException("Not enough elements in stack for DIVISION operation");
        }

        double operand2 = stack.pop();
        double operand1 = stack.pop();

        if (operand2 == 0) throw new CommandException("division by zero!");

        double result = operand1 / operand2;

        stack.push(result);
    }
}
