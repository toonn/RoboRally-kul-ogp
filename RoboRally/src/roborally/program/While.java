package roborally.program;

import java.util.List;

import roborally.model.Robot;

/**
 * A class for representing the while construct.
 * 
 * @author Ben Adriaenssens <ben.adriaenssens@student.kuleuven.be> - WtkCws,
 *         Toon Nolten <toon.nolten@student.kuleuven.be> - CwsElt.
 */
class While extends CombinedCommand {

    /**
     * Initializes the while construct with a string and a robot.
     * 
     * @param commandString
     *      The parameter commandString holds the contents of the while
     *      construct.
     * @param robot
     *      The parameter robot is the executing robot.
     */
    While(String commandString, Robot robot) {
        super(robot);

        List<String> whileString = Program.allSubParenthesed(commandString
                .substring(7, commandString.length() - 1));

        condition = Condition.newCondition(whileString.get(0), robot);
        command = newCommand(whileString.get(1), robot);
    }

    /**
     * Executes this while's command if condition evaluates to true or
     * this while's command is still busy.
     */
    @Override
    void execute() {
        if (robot != null)
            isBusy = true;
        if (condition.evaluate() || command instanceof CombinedCommand
                && ((CombinedCommand) command).isBusy()) {
            command.execute();
            robot.getProgram().decrementProgramCounter();
        }
        if (!(condition.evaluate() || command instanceof CombinedCommand
                && ((CombinedCommand) command).isBusy()))
            isBusy = false;
    }

    /**
     * This method returns whether or not this while is busy.
     * 
     * @return true if this while's command is busy or this while has been
     *      executed and it's condition is still true.
     * @return false otherwise.
     */
    @Override
    boolean isBusy() {
        return isBusy;
    }

    /*
     * (non-Javadoc)
     * @see roborally.program.Command#toString()
     */
    @Override
    public String toString() {
        String newLine = System.getProperty("line.separator");
        String commandString = "(while" + newLine + "  "
                + condition.toString().replace(newLine, newLine + "  ")
                + newLine + "  "
                + command.toString().replace(newLine, newLine + "  ") + newLine
                + ")";

        return commandString;
    }

    private Condition condition;
    private Command command;
    private boolean isBusy;
}
