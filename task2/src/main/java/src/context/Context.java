package src.context;

import java.util.Map;
import java.util.HashMap;
import java.util.Stack;

public class Context {
    private Stack<Double> stack = new Stack<>();
    private Map<String, Double> defines = new HashMap<>();

    public Stack<Double> getStack() {
        return stack;
    }

    public Map<String, Double> getDefines() {
        return defines;
    }
}
