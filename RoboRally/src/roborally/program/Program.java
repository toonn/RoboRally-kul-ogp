package roborally.program;

import java.util.*;

import roborally.model.Robot;

public class Program {

    public Program(String programString, Robot executingRobot) {
        programCounter = 0;
        this.programString = programString;
        commands = new ArrayList<Command>();

        for (String commandString : allSubParenthesed(programString))
            commands.add(Command.newCommand(commandString, executingRobot));
    }

    public void step() {
        if (programCounter < commands.size())
            commands.get(programCounter).execute();
        programCounter++;
    }

    @Override
    public String toString() {
        return programString;
    }

    void decrementProgramCounter() {
        programCounter--;
    }

    public static List<String> allSubParenthesed(String string) {
        List<String> allSubParenthesed = new ArrayList<String>();

        int parenthesesCounter = 0;
        int beginIndex = string.indexOf("(");

        for (int endIndex = beginIndex; endIndex < string.length(); endIndex++) {
            if (beginIndex < 0 || endIndex < 0)
                break;
            if ('(' == string.charAt(endIndex))
                parenthesesCounter++;
            else if (')' == string.charAt(endIndex))
                parenthesesCounter--;
            if (parenthesesCounter == 0) {
                allSubParenthesed.add(string
                        .substring(beginIndex, endIndex + 1));
                int nextOpenParen = string.indexOf("(", endIndex + 1);
                if (nextOpenParen < 0)
                    break;
                beginIndex = string.indexOf("(", endIndex + 1);
                endIndex = string.indexOf("(", endIndex + 1) - 1;
            }
        }

        return allSubParenthesed;
    }

    private int programCounter;
    private String programString;
    private final List<Command> commands;
}
