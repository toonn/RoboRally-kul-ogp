package roborally.model;

import roborally.IFacade;
import roborally.model.Energy.unitOfPower;

import java.util.HashSet;
import java.util.Set;

/**
 * Implement this interface to connect your code to the user interface.
 * 
 * <ul>
 * <li>Connect your classes to the user interface by creating a class named <code>Facade</code> that implements <code>IFacade</code>. The header
 *     of the class <code>Facade</code> should look as follows:
 *     <p><code>class Facade implements IFacade&ltBoardImpl, RobotImpl, WallImpl, BatteryImpl&gt { ... }</code></p>
 *     The code snippet shown above assumes that your classes representing boards, robots, walls and batteries are respectively named
 *     <code>BoardImpl</code>, <code>RobotImpl</code>, <code>WallImpl</code> and <code>BatteryImpl</code>. Consult the
 *     <a href="http://docs.oracle.com/javase/tutorial/java/IandI/createinterface.html">Java tutorial</a> for more information on interfaces.</li>
 * <li>Modify the code between <code>&ltbegin&gt</code> and <code>&ltend&gt</code> in RoboRally.java: instantiate the generic arguments with
 *     your own classes and replace <code>new roborally.model.Facade()</code> with <code>new yourpackage.Facade()</code>.
 * <li>You may assume that only non-null objects returned by <code>createBoard</code>, <code>createRobot</code>, <code>createWall</code> and <code>createBattery</code>
 *     are passed to <code>putRobot</code>, <code>getBatteryX</code>, <code>getWallY</code>, <code>move</code>, etc.</li>
 * <li>The methods in this interface should not throw exceptions (unless specified otherwise in the documentation of a method). Prevent precondition violations for nominal methods (by checking before calling a method that its precondition holds)
 *   and catch exceptions for defensive methods. If a problem occurs (e.g. insufficient energy to move, trying to use a battery not held by the robot, ...), do not modify the program state and print an error message on standard error (<code>System.err</code>).</li>
 * <li>The rules described above and the documentation described below for each method apply only to the class implementing IFacade. Your classes for representing boards, robots, walls and batteries should follow the rules described in the assignment.</li>
 * <li>Do not modify the signatures of the methods defined in this interface. You can however add additional methods, as long as these additional methods do not overload the existing ones. Each additional method should of course
 *     be implemented in your class <code>Facade</code>.</li>
 * </ul> 
 */
public class Facade implements IFacade<Board, Robot, Wall, Battery> {

    /**
     * Create a new board with the given <code>width</code> and <code>height</code>. 
     * 
     * This method must return <code>null</code> if the given <code>width</code> and <code>height</code> are invalid. 
     */
    public Board createBoard(long width, long height) {
        try {
            return new Board(width, height);
        } catch (Exception exc) {
            return null;
        }
    }

    /**
     * Merge <code>board1</code> and <code>board2</code>. 
     */
    public void merge(Board board1, Board board2) {
        if (board1 != null)
            board1.merge(board2);
    }

    /**
     * Create a new battery with initial energy equal to <code>initialEnergy</code> and weight equal to <code>weight</code>. 
     * 
     * This method must return <code>null</code> if the given parameters are invalid (e.g. negative weight). 
     */
    public Battery createBattery(double initialEnergy, int weight) {
        if (initialEnergy >= 0 && initialEnergy <= 5000)
            return new Battery(null, new Energy(initialEnergy,
                    Energy.unitOfPower.Ws), new Weight(weight,
                    Weight.unitOfMass.g));
        return null;
    }

    /**
     * Put <code>battery</code> at position (<code>x</code>, <code>y</code>) on <code>board</code> (if possible).
     */
    public void putBattery(Board board, long x, long y, Battery battery) {
        if (board != null && board.isValidPosition(x, y))
            board.putElement(new Position(x, y, board), battery);
    }

    /**
     * Return the x-coordinate of <code>battery</code>.
     * 
     * This method must throw <code>IllegalStateException</code> if <code>battery</code> is not placed on a board.
     */
    public long getBatteryX(Battery battery) throws IllegalStateException {
        if (battery.getPosition() == null)
            throw new IllegalStateException();
        return battery.getPosition().X;
    }

    /**
     * Return the y-coordinate of <code>battery</code>.
     * 
     * This method must throw <code>IllegalStateException</code> if <code>battery</code> is not placed on a board.
     */
    public long getBatteryY(Battery battery) throws IllegalStateException {
        if (battery.getPosition() == null)
            throw new IllegalStateException();
        return battery.getPosition().Y;
    }

    /** 
     * Create a new Robot looking at <code>orientation</code> with <code>energy</code> watt-second.
     * 
     * This method must return <code>null</code> if the given parameters are invalid (e.g. negative energy). 
     *  
     * <p>0, 1, 2, 3 respectively represent up, right, down and left.</p>
     */
    public Robot createRobot(int orientation, double initialEnergy) {
        if (orientation >= 0 && orientation <= 3 && initialEnergy >= 0
                && initialEnergy <= 20000) {
            Orientation initialOrienation = Orientation.UP;
            switch (orientation) {
            case 0:
                initialOrienation = Orientation.UP;
                break;
            case 1:
                initialOrienation = Orientation.RIGHT;
                break;
            case 2:
                initialOrienation = Orientation.DOWN;
                break;
            case 3:
                initialOrienation = Orientation.LEFT;
                break;
            }
            return new Robot(new Energy(initialEnergy, unitOfPower.Ws),
                    initialOrienation);
        }
        return null;
    }

    /**
     * Put <code>robot</code> at position (<code>x</code>, <code>y</code>) on <code>board</code> (if possible).
     */
    public void putRobot(Board board, long x, long y, Robot robot) {
        if (board != null && board.isValidPosition(x, y))
            board.putElement(new Position(x, y, board), robot);
    }

    /**
     * Return the x-coordinate of <code>robot</code>.
     * 
     * This method must throw <code>IllegalStateException</code> if <code>robot</code> is not placed on a board.
     */
    public long getRobotX(Robot robot) throws IllegalStateException {
        if (robot == null)
            throw new IllegalStateException();
        return robot.getPosition().X;
    }

    /**
     * Return the y-coordinate of <code>robot</code>.
     * 
     * This method must throw <code>IllegalStateException</code> if <code>robot</code> is not placed on a board.
     */
    public long getRobotY(Robot robot) throws IllegalStateException {
        if (robot == null)
            throw new IllegalStateException();
        return robot.getPosition().Y;
    }

    /**
     * Return the orientation (either 0, 1, 2 or 3) of <code>robot</code>. 
     * 
     * <p>0, 1, 2, 3 respectively represent up, right, down and left.</p>
     */
    public int getOrientation(Robot robot) {
        return robot.getOrientationInt();
    }

    /**
     * Return the current energy in watt-second of <code>robot</code>.
     */
    public double getEnergy(Robot robot) {
        return robot.getAmountOfEnergy();
    }

    /**
     * Move <code>robot</code> one step in its current direction if the robot has sufficient energy. Do not modify the state of the robot
     * if it has insufficient energy.
     */
    public void move(Robot robot) {
        if (robot != null)
            robot.move();
    }

    /**
     * Turn <code>robot</code> 90 degrees in clockwise direction if the robot has sufficient energy. Do not modify the state of the robot
     * if it has insufficient energy.
     */
    public void turn(Robot robot) {
        if (robot != null)
            robot.turnClockwise90();
    }

    /**
     * Return the set of batteries that <code>robot</code> is carrying.
     */
    public Set<Battery> getPossessions(Robot robot) {
        if (robot != null) {
            Set<Battery> possesions = robot.getPossesions();
            return possesions;
        }
        return new HashSet<Battery>();
    }

    /**
     * Make <code>robot</code> pick up <code>battery</code> (if possible).
     */
    public void pickUp(Robot robot, Battery battery) {
        if (robot != null)
            robot.pickup(battery);
    }

    /**
     * Make <code>robot</code> use <code>battery</code> (if possible).
     */
    public void use(Robot robot, Battery battery) {
        if (robot != null)
            robot.use(battery);
    }

    /**
     * Make <code>robot</code> drop <code>battery</code> (if possible).
     */
    public void drop(Robot robot, Battery battery) {
        if (robot != null)
            robot.drop(battery);
    }

    /**
     * Return whether your implementation of <code>isMinimalCostToReach</code> takes into account other robots, walls and turning (required to score 17+). The return
     * value of this method determines the expected return value of <code>isMinimalCostToReach</code> in the test suite.
     * 
     * This method must return either 0 or 1.
     */
    // TODO: 17+ minimalCostToReach: robots, walls, turning
    public int isMinimalCostToReach17Plus() {
        return 0;
    }

    /**
     * Return the minimal amount of energy required for <code>robot</code> to reach (<code>x</code>, </code>y</code>) taking into account the robot's current load and energy level. Do not take into account
     * shooting and picking up/using/dropping batteries. 
     * <p>
     * The expected return value of this method depends on <code>isMinimalCostToReach17Plus</code>:
     * <ul>
     * <li>If <code>isMinimalCostToReach17Plus</code> returns <code>0</code>, then <code>getMinimalCostToReach</code> will only be called if there are no obstacles in the rectangle
     * covering <code>robot</code> and the given position. Moreover, the result of this method should not include the energy required for turning.</li>
     * <li>If <code>isMinimalCostToReach17Plus</code> returns <code>1</code>, then <code>getMinimalCostToReach</code> must take into account obstacles (i.e. walls, other robots) and the 
     * fact that turning consumes energy. This method must return <code>-1</code> if the given position is not reachable because of obstacles.</li>
     * </ul>
     * </p>
     * In any case, this method must return <code>-1</code> if <code>robot</code> is not placed on a board. Moreover, this method must return <code>-2</code> if <code>robot</code> has
     * insufficient energy to reach (<code>x</code>, <code>y</code>).
     */
    public double getMinimalCostToReach(Robot robot, long x, long y) {
        if (robot != null)
            return robot.getEnergyRequiredToReachWs(new Position(x, y, robot
                    .getPosition().BOARD));
        return 0;
    }

    /**
     * Return whether your implementation of <code>moveNextTo</code> takes into account other robots, walls and the fact that turning consumes energy (required to score 18+). The return
     * value of this method determines the expected effect of <code>moveNextTo</code> in the test suite.
     * 
     * This method must return either 0 or 1.
     */
    // TODO: 18+ moveNextTo: robots, walls, turning
    public int isMoveNextTo18Plus() {
        return 0;
    }

    /**
     * Move <code>robot</code> as close as possible (expressed as the manhattan distance) to <code>other</code> given their current energy and load. If multiple optimal (in distance) solutions
     * exist, select the solution that requires the least amount of total energy. Both robots can move and turn to end up closer to each other. Do not take into account shooting and picking up/using/dropping
     * batteries.  
     * <p>
     * The expected return value of this method depends on <code>isMoveNextTo18Plus</code>:
     * <ul>
     * <li>If <code>isMoveNextTo18Plus</code> returns <code>0</code>, then <code>moveNextTo</code> will only be called if there are no obstacles in the rectangle
     * covering <code>robot</code> and <code>other</code>. Moreover, your implementation must be optimal only in the number of moves (i.e. ignore the fact that turning consumes energy).</li>
     * <li>If <code>isMoveNextTo18Plus</code> returns <code>1</code>, then <code>moveNextTo</code> must take into account obstacles (i.e. walls, other robots) and the 
     * fact that turning consumes energy.</li>
     * </ul>
     * </p>
     * Do not change the state if <code>robot</code> and <code>other</code> are not located on the same board.
     */
    public void moveNextTo(Robot robot, Robot other) {
        if (robot != null)
            robot.moveNextTo(other);
    }

    /**
     * Make <code>robot</code> shoot in the orientation it is currently facing (if possible).
     * 
     * Students working on their own are allowed to throw <code>UnsupportedOperationException</code>.
     */
    public void shoot(Robot robot) throws UnsupportedOperationException {
        if (robot != null)
            robot.shoot();
    }

    /**
     * Create a new wall.
     * 
     * Students working on their own are allowed to throw <code>UnsupportedOperationException</code>.
     */
    public Wall createWall() throws UnsupportedOperationException {
        return new Wall();
    }

    /**
     * Put <code>robot</code> at position (<code>x</code>, <code>y</code>) on <code>board</code> (if possible).
     * 
     * Students working on their own are allowed to throw <code>UnsupportedOperationException</code>.
     */
    public void putWall(Board board, long x, long y, Wall wall)
            throws UnsupportedOperationException {
        if (board != null && board.isValidPosition(x, y))
            board.putElement(new Position(x, y, board), wall);
    }

    /**
     * Return the x-coordinate of <code>wall</code>.
     * 
     * This method must throw <code>IllegalStateException</code> if <code>wall</code> is not placed on a board.
     * 
     * Students working on their own are allowed to throw <code>UnsupportedOperationException</code>.
     */
    public long getWallX(Wall wall) throws IllegalStateException,
            UnsupportedOperationException {
        if (wall == null || wall.getPosition() == null)
            throw new IllegalStateException();
        return wall.getPosition().X;
    }

    /**
     * Return the y-coordinate of <code>wall</code>.
     * 
     * This method must throw <code>IllegalStateException</code> if <code>wall</code> is not placed on a board.
     * 
     * Students working on their own are allowed to throw <code>UnsupportedOperationException</code>.
     */
    public long getWallY(Wall wall) throws IllegalStateException,
            UnsupportedOperationException {
        if (wall == null || wall.getPosition() == null)
            throw new IllegalStateException();
        return wall.getPosition().Y;
    }

    /**
     * Return a set containing all robots on <code>board</code>.
     */
    public Set<Robot> getRobots(Board board) {
        Set<Robot> robots = new HashSet<Robot>();
        if (board != null)
            for (Element elem : board.getElementsOf(Robot.class))
                robots.add((Robot) elem);
        return robots;
    }

    /**
     * Return a set containing all batteries on <code>board</code>.
     */
    public Set<Battery> getBatteries(Board board) {
        Set<Battery> batteries = new HashSet<Battery>();
        if (board != null)
            for (Element elem : board.getElementsOf(Battery.class))
                batteries.add((Battery) elem);
        return batteries;
    }

    /**
     * Return a set containing all walls on <code>board</code>.
     * 
     * Students working on their own are allowed to throw <code>UnsupportedOperationException</code>.
     */
    public Set<Wall> getWalls(Board board) throws UnsupportedOperationException {
        Set<Wall> walls = new HashSet<Wall>();
        if (board != null)
            for (Element elem : board.getElementsOf(Wall.class))
                walls.add((Wall) elem);
        return walls;
    }
}