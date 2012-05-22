package roborally.test;

import static org.junit.Assert.*;

import java.io.*;

import java.util.*;

import org.junit.*;

import roborally.model.*;
import roborally.model.auxiliary.*;
import roborally.model.auxiliary.Energy.unitOfPower;
import roborally.program.Program;

public class ProgramTest {

    @BeforeClass
    public static void setUpBeforeClass() {
        testProgramsDirectory = "src/roborally/test/testPrograms/";
        cowboy = readProgramFromFile(testProgramsDirectory + "cowboy.prog");
        emptyString = readProgramFromFile(testProgramsDirectory
                + "emptystring.prog");
        example = readProgramFromFile(testProgramsDirectory + "example.prog");
        example4spaceextranewlines = readProgramFromFile(testProgramsDirectory
                + "example4spaceextranewlines.prog");
        exampleinoneline = readProgramFromFile(testProgramsDirectory
                + "exampleinoneline.prog");
        gump = readProgramFromFile(testProgramsDirectory + "gump.prog");
        move = readProgramFromFile(testProgramsDirectory + "move.prog");
        whileinseq = readProgramFromFile(testProgramsDirectory
                + "whileinseq.prog");
    }

    @Before
    public void setUp() {
        robot = new Robot(new Energy(7300, unitOfPower.Ws), Orientation.RIGHT);
        board = new Board(75, 63);
    }

    @Test
    public void testCowboy() {
        Program cowboyProgram = new Program(cowboy, robot);

        assertNotNull(cowboyProgram);
    }

    /* TODO: Robots worden ge'hit' als ze beschoten worden, niet ge'terminate. */
    @Test
    public void testCowboyStep() {
        robot.setPosition(Position.newPosition(3, 4, board));
        board.putElement(Position.newPosition(5, 4, board), new Robot(
                new Energy(2300, unitOfPower.Ws), Orientation.LEFT));
        board.putElement(Position.newPosition(6, 4, board), new Robot(
                new Energy(2300, unitOfPower.Ws), Orientation.LEFT));
        board.putElement(Position.newPosition(7, 4, board), new Robot(
                new Energy(2300, unitOfPower.Ws), Orientation.LEFT));
        board.putElement(Position.newPosition(8, 4, board), new Robot(
                new Energy(2300, unitOfPower.Ws), Orientation.LEFT));
        board.putElement(Position.newPosition(9, 4, board), new Robot(
                new Energy(2300, unitOfPower.Ws), Orientation.LEFT));
        board.putElement(Position.newPosition(10, 4, board), new Robot(
                new Energy(2300, unitOfPower.Ws), Orientation.LEFT));
        board.putElement(Position.newPosition(1, 4, board), new Robot(
                new Energy(2300, unitOfPower.Ws), Orientation.LEFT));
        board.putElement(Position.newPosition(3, 2, board), new Robot(
                new Energy(2300, unitOfPower.Ws), Orientation.LEFT));

        Program cowboyProgram = new Program(cowboy, robot);
        robot.setProgram(cowboyProgram);

        assertEquals(1,
                Position.newPosition(5, 4, board).getElementsOf(Robot.class)
                        .size());
        assertEquals(1,
                Position.newPosition(6, 4, board).getElementsOf(Robot.class)
                        .size());
        assertEquals(1,
                Position.newPosition(7, 4, board).getElementsOf(Robot.class)
                        .size());
        assertEquals(1,
                Position.newPosition(8, 4, board).getElementsOf(Robot.class)
                        .size());
        assertEquals(1,
                Position.newPosition(9, 4, board).getElementsOf(Robot.class)
                        .size());
        assertEquals(1,
                Position.newPosition(10, 4, board).getElementsOf(Robot.class)
                        .size());
        assertEquals(1,
                Position.newPosition(1, 4, board).getElementsOf(Robot.class)
                        .size());
        assertEquals(1,
                Position.newPosition(3, 2, board).getElementsOf(Robot.class)
                        .size());
        assertEquals(Orientation.RIGHT, robot.getOrientation());
        assertEquals(7300, robot.getAmountOfEnergy(), epsilon);

        cowboyProgram.step();

        assertEquals(0,
                Position.newPosition(5, 4, board).getElementsOf(Robot.class)
                        .size());
        assertEquals(1,
                Position.newPosition(6, 4, board).getElementsOf(Robot.class)
                        .size());
        assertEquals(1,
                Position.newPosition(7, 4, board).getElementsOf(Robot.class)
                        .size());
        assertEquals(1,
                Position.newPosition(8, 4, board).getElementsOf(Robot.class)
                        .size());
        assertEquals(1,
                Position.newPosition(9, 4, board).getElementsOf(Robot.class)
                        .size());
        assertEquals(1,
                Position.newPosition(10, 4, board).getElementsOf(Robot.class)
                        .size());
        assertEquals(1,
                Position.newPosition(1, 4, board).getElementsOf(Robot.class)
                        .size());
        assertEquals(1,
                Position.newPosition(3, 2, board).getElementsOf(Robot.class)
                        .size());
        assertEquals(Orientation.RIGHT, robot.getOrientation());
        assertEquals(6300, robot.getAmountOfEnergy(), epsilon);

        cowboyProgram.step();

        assertEquals(0,
                Position.newPosition(5, 4, board).getElementsOf(Robot.class)
                        .size());
        assertEquals(0,
                Position.newPosition(6, 4, board).getElementsOf(Robot.class)
                        .size());
        assertEquals(1,
                Position.newPosition(7, 4, board).getElementsOf(Robot.class)
                        .size());
        assertEquals(1,
                Position.newPosition(8, 4, board).getElementsOf(Robot.class)
                        .size());
        assertEquals(1,
                Position.newPosition(9, 4, board).getElementsOf(Robot.class)
                        .size());
        assertEquals(1,
                Position.newPosition(10, 4, board).getElementsOf(Robot.class)
                        .size());
        assertEquals(1,
                Position.newPosition(1, 4, board).getElementsOf(Robot.class)
                        .size());
        assertEquals(1,
                Position.newPosition(3, 2, board).getElementsOf(Robot.class)
                        .size());
        assertEquals(Orientation.RIGHT, robot.getOrientation());
        assertEquals(5300, robot.getAmountOfEnergy(), epsilon);

        cowboyProgram.step();

        assertEquals(0,
                Position.newPosition(5, 4, board).getElementsOf(Robot.class)
                        .size());
        assertEquals(0,
                Position.newPosition(6, 4, board).getElementsOf(Robot.class)
                        .size());
        assertEquals(0,
                Position.newPosition(7, 4, board).getElementsOf(Robot.class)
                        .size());
        assertEquals(1,
                Position.newPosition(8, 4, board).getElementsOf(Robot.class)
                        .size());
        assertEquals(1,
                Position.newPosition(9, 4, board).getElementsOf(Robot.class)
                        .size());
        assertEquals(1,
                Position.newPosition(10, 4, board).getElementsOf(Robot.class)
                        .size());
        assertEquals(1,
                Position.newPosition(1, 4, board).getElementsOf(Robot.class)
                        .size());
        assertEquals(1,
                Position.newPosition(3, 2, board).getElementsOf(Robot.class)
                        .size());
        assertEquals(Orientation.RIGHT, robot.getOrientation());
        assertEquals(4300, robot.getAmountOfEnergy(), epsilon);

        cowboyProgram.step();

        assertEquals(0,
                Position.newPosition(5, 4, board).getElementsOf(Robot.class)
                        .size());
        assertEquals(0,
                Position.newPosition(6, 4, board).getElementsOf(Robot.class)
                        .size());
        assertEquals(0,
                Position.newPosition(7, 4, board).getElementsOf(Robot.class)
                        .size());
        assertEquals(0,
                Position.newPosition(8, 4, board).getElementsOf(Robot.class)
                        .size());
        assertEquals(1,
                Position.newPosition(9, 4, board).getElementsOf(Robot.class)
                        .size());
        assertEquals(1,
                Position.newPosition(10, 4, board).getElementsOf(Robot.class)
                        .size());
        assertEquals(1,
                Position.newPosition(1, 4, board).getElementsOf(Robot.class)
                        .size());
        assertEquals(1,
                Position.newPosition(3, 2, board).getElementsOf(Robot.class)
                        .size());
        assertEquals(Orientation.RIGHT, robot.getOrientation());
        assertEquals(3300, robot.getAmountOfEnergy(), epsilon);

        cowboyProgram.step();

        assertEquals(0,
                Position.newPosition(5, 4, board).getElementsOf(Robot.class)
                        .size());
        assertEquals(0,
                Position.newPosition(6, 4, board).getElementsOf(Robot.class)
                        .size());
        assertEquals(0,
                Position.newPosition(7, 4, board).getElementsOf(Robot.class)
                        .size());
        assertEquals(0,
                Position.newPosition(8, 4, board).getElementsOf(Robot.class)
                        .size());
        assertEquals(0,
                Position.newPosition(9, 4, board).getElementsOf(Robot.class)
                        .size());
        assertEquals(1,
                Position.newPosition(10, 4, board).getElementsOf(Robot.class)
                        .size());
        assertEquals(1,
                Position.newPosition(1, 4, board).getElementsOf(Robot.class)
                        .size());
        assertEquals(1,
                Position.newPosition(3, 2, board).getElementsOf(Robot.class)
                        .size());
        assertEquals(Orientation.RIGHT, robot.getOrientation());
        assertEquals(2300, robot.getAmountOfEnergy(), epsilon);

        cowboyProgram.step();

        assertEquals(0,
                Position.newPosition(5, 4, board).getElementsOf(Robot.class)
                        .size());
        assertEquals(0,
                Position.newPosition(6, 4, board).getElementsOf(Robot.class)
                        .size());
        assertEquals(0,
                Position.newPosition(7, 4, board).getElementsOf(Robot.class)
                        .size());
        assertEquals(0,
                Position.newPosition(8, 4, board).getElementsOf(Robot.class)
                        .size());
        assertEquals(0,
                Position.newPosition(9, 4, board).getElementsOf(Robot.class)
                        .size());
        assertEquals(0,
                Position.newPosition(10, 4, board).getElementsOf(Robot.class)
                        .size());
        assertEquals(1,
                Position.newPosition(1, 4, board).getElementsOf(Robot.class)
                        .size());
        assertEquals(1,
                Position.newPosition(3, 2, board).getElementsOf(Robot.class)
                        .size());
        assertEquals(Orientation.RIGHT, robot.getOrientation());
        assertEquals(1300, robot.getAmountOfEnergy(), epsilon);

        cowboyProgram.step();

        assertEquals(0,
                Position.newPosition(5, 4, board).getElementsOf(Robot.class)
                        .size());
        assertEquals(0,
                Position.newPosition(6, 4, board).getElementsOf(Robot.class)
                        .size());
        assertEquals(0,
                Position.newPosition(7, 4, board).getElementsOf(Robot.class)
                        .size());
        assertEquals(0,
                Position.newPosition(8, 4, board).getElementsOf(Robot.class)
                        .size());
        assertEquals(0,
                Position.newPosition(9, 4, board).getElementsOf(Robot.class)
                        .size());
        assertEquals(0,
                Position.newPosition(10, 4, board).getElementsOf(Robot.class)
                        .size());
        assertEquals(1,
                Position.newPosition(1, 4, board).getElementsOf(Robot.class)
                        .size());
        assertEquals(1,
                Position.newPosition(3, 2, board).getElementsOf(Robot.class)
                        .size());
        assertEquals(Orientation.DOWN, robot.getOrientation());
        assertEquals(1200, robot.getAmountOfEnergy(), epsilon);

        cowboyProgram.step();

        assertEquals(0,
                Position.newPosition(5, 4, board).getElementsOf(Robot.class)
                        .size());
        assertEquals(0,
                Position.newPosition(6, 4, board).getElementsOf(Robot.class)
                        .size());
        assertEquals(0,
                Position.newPosition(7, 4, board).getElementsOf(Robot.class)
                        .size());
        assertEquals(0,
                Position.newPosition(8, 4, board).getElementsOf(Robot.class)
                        .size());
        assertEquals(0,
                Position.newPosition(9, 4, board).getElementsOf(Robot.class)
                        .size());
        assertEquals(0,
                Position.newPosition(10, 4, board).getElementsOf(Robot.class)
                        .size());
        assertEquals(1,
                Position.newPosition(1, 4, board).getElementsOf(Robot.class)
                        .size());
        assertEquals(1,
                Position.newPosition(3, 2, board).getElementsOf(Robot.class)
                        .size());
        assertEquals(Orientation.LEFT, robot.getOrientation());
        assertEquals(1100, robot.getAmountOfEnergy(), epsilon);

        cowboyProgram.step();

        assertEquals(0,
                Position.newPosition(5, 4, board).getElementsOf(Robot.class)
                        .size());
        assertEquals(0,
                Position.newPosition(6, 4, board).getElementsOf(Robot.class)
                        .size());
        assertEquals(0,
                Position.newPosition(7, 4, board).getElementsOf(Robot.class)
                        .size());
        assertEquals(0,
                Position.newPosition(8, 4, board).getElementsOf(Robot.class)
                        .size());
        assertEquals(0,
                Position.newPosition(9, 4, board).getElementsOf(Robot.class)
                        .size());
        assertEquals(0,
                Position.newPosition(10, 4, board).getElementsOf(Robot.class)
                        .size());
        assertEquals(0,
                Position.newPosition(1, 4, board).getElementsOf(Robot.class)
                        .size());
        assertEquals(1,
                Position.newPosition(3, 2, board).getElementsOf(Robot.class)
                        .size());
        assertEquals(Orientation.LEFT, robot.getOrientation());
        assertEquals(100, robot.getAmountOfEnergy(), epsilon);

        cowboyProgram.step();

        assertEquals(0,
                Position.newPosition(5, 4, board).getElementsOf(Robot.class)
                        .size());
        assertEquals(0,
                Position.newPosition(6, 4, board).getElementsOf(Robot.class)
                        .size());
        assertEquals(0,
                Position.newPosition(7, 4, board).getElementsOf(Robot.class)
                        .size());
        assertEquals(0,
                Position.newPosition(8, 4, board).getElementsOf(Robot.class)
                        .size());
        assertEquals(0,
                Position.newPosition(9, 4, board).getElementsOf(Robot.class)
                        .size());
        assertEquals(0,
                Position.newPosition(10, 4, board).getElementsOf(Robot.class)
                        .size());
        assertEquals(0,
                Position.newPosition(1, 4, board).getElementsOf(Robot.class)
                        .size());
        assertEquals(1,
                Position.newPosition(3, 2, board).getElementsOf(Robot.class)
                        .size());
        assertEquals(Orientation.UP, robot.getOrientation());
        assertEquals(0, robot.getAmountOfEnergy(), epsilon);

        cowboyProgram.step();

        assertEquals(0,
                Position.newPosition(5, 4, board).getElementsOf(Robot.class)
                        .size());
        assertEquals(0,
                Position.newPosition(6, 4, board).getElementsOf(Robot.class)
                        .size());
        assertEquals(0,
                Position.newPosition(7, 4, board).getElementsOf(Robot.class)
                        .size());
        assertEquals(0,
                Position.newPosition(8, 4, board).getElementsOf(Robot.class)
                        .size());
        assertEquals(0,
                Position.newPosition(9, 4, board).getElementsOf(Robot.class)
                        .size());
        assertEquals(0,
                Position.newPosition(10, 4, board).getElementsOf(Robot.class)
                        .size());
        assertEquals(0,
                Position.newPosition(1, 4, board).getElementsOf(Robot.class)
                        .size());
        assertEquals(1,
                Position.newPosition(3, 2, board).getElementsOf(Robot.class)
                        .size());
        assertEquals(Orientation.UP, robot.getOrientation());
        assertEquals(0, robot.getAmountOfEnergy(), epsilon);

        cowboyProgram.step();
        cowboyProgram.step();
        cowboyProgram.step();
        cowboyProgram.step();
        cowboyProgram.step();
        cowboyProgram.step();
        cowboyProgram.step();
        cowboyProgram.step();
        cowboyProgram.step();
        cowboyProgram.step();

        assertEquals(0,
                Position.newPosition(5, 4, board).getElementsOf(Robot.class)
                        .size());
        assertEquals(0,
                Position.newPosition(6, 4, board).getElementsOf(Robot.class)
                        .size());
        assertEquals(0,
                Position.newPosition(7, 4, board).getElementsOf(Robot.class)
                        .size());
        assertEquals(0,
                Position.newPosition(8, 4, board).getElementsOf(Robot.class)
                        .size());
        assertEquals(0,
                Position.newPosition(9, 4, board).getElementsOf(Robot.class)
                        .size());
        assertEquals(0,
                Position.newPosition(10, 4, board).getElementsOf(Robot.class)
                        .size());
        assertEquals(0,
                Position.newPosition(1, 4, board).getElementsOf(Robot.class)
                        .size());
        assertEquals(1,
                Position.newPosition(3, 2, board).getElementsOf(Robot.class)
                        .size());
        assertEquals(Orientation.UP, robot.getOrientation());
        assertEquals(0, robot.getAmountOfEnergy(), epsilon);
    }

    @Test
    public void testCowboyToString() {
        Program cowboyProgram = new Program(cowboy, robot);

        assertTrue(cowboy.equals(cowboyProgram.toString()));
    }

    @Test
    public void testEmptyString() {
        Program emptyStringProgram = new Program(emptyString, robot);

        assertNotNull(emptyStringProgram);
    }

    @Test
    public void testEmptyStringStep() {
        Program emptyStringProgram = new Program(emptyString, robot);

        assertEquals(Orientation.RIGHT, robot.getOrientation());
        assertEquals(7300, robot.getAmountOfEnergy(), epsilon);

        emptyStringProgram.step();

        assertEquals(Orientation.RIGHT, robot.getOrientation());
        assertEquals(7300, robot.getAmountOfEnergy(), epsilon);

        emptyStringProgram.step();
        emptyStringProgram.step();
        emptyStringProgram.step();
        emptyStringProgram.step();
        emptyStringProgram.step();
        emptyStringProgram.step();
        emptyStringProgram.step();
        emptyStringProgram.step();
        emptyStringProgram.step();
        emptyStringProgram.step();

        assertEquals(Orientation.RIGHT, robot.getOrientation());
        assertEquals(7300, robot.getAmountOfEnergy(), epsilon);
    }

    @Test
    public void testEmptyStringToString() {
        Program emptyStringProgram = new Program(emptyString, robot);

        assertTrue(emptyString.equals(emptyStringProgram.toString()));
    }

    @Test
    public void testExample() {
        Program exampleProgram = new Program(example, robot);

        assertNotNull(exampleProgram);
    }

    @Test
    public void testExampleStep() {
        robot = new Robot(new Energy(3900, unitOfPower.Ws), Orientation.RIGHT);
        robot.setPosition(Position.newPosition(3, 4, board));

        Program exampleProgram = new Program(example, robot);
        robot.setProgram(exampleProgram);

        assertEquals(1, Position.newPosition(3, 4, board).getElements().size());
        assertEquals(0, Position.newPosition(4, 4, board).getElements().size());
        assertEquals(0, Position.newPosition(4, 5, board).getElements().size());
        assertEquals(0, Position.newPosition(3, 5, board).getElements().size());
        assertEquals(Orientation.RIGHT, robot.getOrientation());
        assertEquals(3900, robot.getAmountOfEnergy(), epsilon);

        exampleProgram.step();

        assertEquals(0, Position.newPosition(3, 4, board).getElements().size());
        assertEquals(1, Position.newPosition(4, 4, board).getElements().size());
        assertEquals(0, Position.newPosition(4, 5, board).getElements().size());
        assertEquals(0, Position.newPosition(3, 5, board).getElements().size());
        assertEquals(Orientation.RIGHT, robot.getOrientation());
        assertEquals(3400, robot.getAmountOfEnergy(), epsilon);

        exampleProgram.step();

        assertEquals(0, Position.newPosition(3, 4, board).getElements().size());
        assertEquals(1, Position.newPosition(4, 4, board).getElements().size());
        assertEquals(0, Position.newPosition(4, 5, board).getElements().size());
        assertEquals(0, Position.newPosition(3, 5, board).getElements().size());
        assertEquals(Orientation.DOWN, robot.getOrientation());
        assertEquals(3300, robot.getAmountOfEnergy(), epsilon);

        exampleProgram.step();

        assertEquals(0, Position.newPosition(3, 4, board).getElements().size());
        assertEquals(0, Position.newPosition(4, 4, board).getElements().size());
        assertEquals(1, Position.newPosition(4, 5, board).getElements().size());
        assertEquals(0, Position.newPosition(3, 5, board).getElements().size());
        assertEquals(Orientation.DOWN, robot.getOrientation());
        assertEquals(2800, robot.getAmountOfEnergy(), epsilon);

        exampleProgram.step();

        assertEquals(0, Position.newPosition(3, 4, board).getElements().size());
        assertEquals(0, Position.newPosition(4, 4, board).getElements().size());
        assertEquals(1, Position.newPosition(4, 5, board).getElements().size());
        assertEquals(0, Position.newPosition(3, 5, board).getElements().size());
        assertEquals(Orientation.LEFT, robot.getOrientation());
        assertEquals(2700, robot.getAmountOfEnergy(), epsilon);

        exampleProgram.step();

        assertEquals(0, Position.newPosition(3, 4, board).getElements().size());
        assertEquals(0, Position.newPosition(4, 4, board).getElements().size());
        assertEquals(0, Position.newPosition(4, 5, board).getElements().size());
        assertEquals(1, Position.newPosition(3, 5, board).getElements().size());
        assertEquals(Orientation.LEFT, robot.getOrientation());
        assertEquals(2200, robot.getAmountOfEnergy(), epsilon);

        exampleProgram.step();

        assertEquals(0, Position.newPosition(3, 4, board).getElements().size());
        assertEquals(0, Position.newPosition(4, 4, board).getElements().size());
        assertEquals(0, Position.newPosition(4, 5, board).getElements().size());
        assertEquals(1, Position.newPosition(3, 5, board).getElements().size());
        assertEquals(Orientation.UP, robot.getOrientation());
        assertEquals(2100, robot.getAmountOfEnergy(), epsilon);

        exampleProgram.step();

        assertEquals(1, Position.newPosition(3, 4, board).getElements().size());
        assertEquals(0, Position.newPosition(4, 4, board).getElements().size());
        assertEquals(0, Position.newPosition(4, 5, board).getElements().size());
        assertEquals(0, Position.newPosition(3, 5, board).getElements().size());
        assertEquals(Orientation.UP, robot.getOrientation());
        assertEquals(1600, robot.getAmountOfEnergy(), epsilon);

        exampleProgram.step();

        assertEquals(1, Position.newPosition(3, 4, board).getElements().size());
        assertEquals(0, Position.newPosition(4, 4, board).getElements().size());
        assertEquals(0, Position.newPosition(4, 5, board).getElements().size());
        assertEquals(0, Position.newPosition(3, 5, board).getElements().size());
        assertEquals(Orientation.RIGHT, robot.getOrientation());
        assertEquals(1500, robot.getAmountOfEnergy(), epsilon);

        exampleProgram.step();

        assertEquals(0, Position.newPosition(3, 4, board).getElements().size());
        assertEquals(1, Position.newPosition(4, 4, board).getElements().size());
        assertEquals(0, Position.newPosition(4, 5, board).getElements().size());
        assertEquals(0, Position.newPosition(3, 5, board).getElements().size());
        assertEquals(Orientation.RIGHT, robot.getOrientation());
        assertEquals(1000, robot.getAmountOfEnergy(), epsilon);

        exampleProgram.step();

        assertEquals(0, Position.newPosition(3, 4, board).getElements().size());
        assertEquals(1, Position.newPosition(4, 4, board).getElements().size());
        assertEquals(0, Position.newPosition(4, 5, board).getElements().size());
        assertEquals(0, Position.newPosition(3, 5, board).getElements().size());
        assertEquals(Orientation.DOWN, robot.getOrientation());
        assertEquals(900, robot.getAmountOfEnergy(), epsilon);

        exampleProgram.step();
        exampleProgram.step();
        exampleProgram.step();
        exampleProgram.step();
        exampleProgram.step();
        exampleProgram.step();
        exampleProgram.step();
        exampleProgram.step();
        exampleProgram.step();
        exampleProgram.step();

        assertEquals(0, Position.newPosition(3, 4, board).getElements().size());
        assertEquals(1, Position.newPosition(4, 4, board).getElements().size());
        assertEquals(0, Position.newPosition(4, 5, board).getElements().size());
        assertEquals(0, Position.newPosition(3, 5, board).getElements().size());
        assertEquals(Orientation.DOWN, robot.getOrientation());
        assertEquals(900, robot.getAmountOfEnergy(), epsilon);

        exampleProgram.step();

        assertEquals(0, Position.newPosition(3, 4, board).getElements().size());
        assertEquals(1, Position.newPosition(4, 4, board).getElements().size());
        assertEquals(0, Position.newPosition(4, 5, board).getElements().size());
        assertEquals(0, Position.newPosition(3, 5, board).getElements().size());
        assertEquals(Orientation.DOWN, robot.getOrientation());
        assertEquals(900, robot.getAmountOfEnergy(), epsilon);
    }

    @Test
    public void testExampleToString() {
        Program exampleProgram = new Program(example, robot);

        assertTrue(example.equals(exampleProgram.toString()));
    }

    @Test
    public void testExample4SpaceExtraNewLines() {
        Program example4SpaceExtraNewLinesProgram = new Program(
                example4spaceextranewlines, robot);

        assertNotNull(example4SpaceExtraNewLinesProgram);
    }

    @Test
    public void testExample4SpaceExtraNewLinesStep() {
        robot = new Robot(new Energy(3900, unitOfPower.Ws), Orientation.RIGHT);
        robot.setPosition(Position.newPosition(3, 4, board));

        Program example4SpaceExtraNewLinesProgram = new Program(
                example4spaceextranewlines, robot);
        robot.setProgram(example4SpaceExtraNewLinesProgram);

        assertEquals(1, Position.newPosition(3, 4, board).getElements().size());
        assertEquals(0, Position.newPosition(4, 4, board).getElements().size());
        assertEquals(0, Position.newPosition(4, 5, board).getElements().size());
        assertEquals(0, Position.newPosition(3, 5, board).getElements().size());
        assertEquals(Orientation.RIGHT, robot.getOrientation());
        assertEquals(3900, robot.getAmountOfEnergy(), epsilon);

        example4SpaceExtraNewLinesProgram.step();

        assertEquals(0, Position.newPosition(3, 4, board).getElements().size());
        assertEquals(1, Position.newPosition(4, 4, board).getElements().size());
        assertEquals(0, Position.newPosition(4, 5, board).getElements().size());
        assertEquals(0, Position.newPosition(3, 5, board).getElements().size());
        assertEquals(Orientation.RIGHT, robot.getOrientation());
        assertEquals(3400, robot.getAmountOfEnergy(), epsilon);

        example4SpaceExtraNewLinesProgram.step();

        assertEquals(0, Position.newPosition(3, 4, board).getElements().size());
        assertEquals(1, Position.newPosition(4, 4, board).getElements().size());
        assertEquals(0, Position.newPosition(4, 5, board).getElements().size());
        assertEquals(0, Position.newPosition(3, 5, board).getElements().size());
        assertEquals(Orientation.DOWN, robot.getOrientation());
        assertEquals(3300, robot.getAmountOfEnergy(), epsilon);

        example4SpaceExtraNewLinesProgram.step();

        assertEquals(0, Position.newPosition(3, 4, board).getElements().size());
        assertEquals(0, Position.newPosition(4, 4, board).getElements().size());
        assertEquals(1, Position.newPosition(4, 5, board).getElements().size());
        assertEquals(0, Position.newPosition(3, 5, board).getElements().size());
        assertEquals(Orientation.DOWN, robot.getOrientation());
        assertEquals(2800, robot.getAmountOfEnergy(), epsilon);

        example4SpaceExtraNewLinesProgram.step();

        assertEquals(0, Position.newPosition(3, 4, board).getElements().size());
        assertEquals(0, Position.newPosition(4, 4, board).getElements().size());
        assertEquals(1, Position.newPosition(4, 5, board).getElements().size());
        assertEquals(0, Position.newPosition(3, 5, board).getElements().size());
        assertEquals(Orientation.LEFT, robot.getOrientation());
        assertEquals(2700, robot.getAmountOfEnergy(), epsilon);

        example4SpaceExtraNewLinesProgram.step();

        assertEquals(0, Position.newPosition(3, 4, board).getElements().size());
        assertEquals(0, Position.newPosition(4, 4, board).getElements().size());
        assertEquals(0, Position.newPosition(4, 5, board).getElements().size());
        assertEquals(1, Position.newPosition(3, 5, board).getElements().size());
        assertEquals(Orientation.LEFT, robot.getOrientation());
        assertEquals(2200, robot.getAmountOfEnergy(), epsilon);

        example4SpaceExtraNewLinesProgram.step();

        assertEquals(0, Position.newPosition(3, 4, board).getElements().size());
        assertEquals(0, Position.newPosition(4, 4, board).getElements().size());
        assertEquals(0, Position.newPosition(4, 5, board).getElements().size());
        assertEquals(1, Position.newPosition(3, 5, board).getElements().size());
        assertEquals(Orientation.UP, robot.getOrientation());
        assertEquals(2100, robot.getAmountOfEnergy(), epsilon);

        example4SpaceExtraNewLinesProgram.step();

        assertEquals(1, Position.newPosition(3, 4, board).getElements().size());
        assertEquals(0, Position.newPosition(4, 4, board).getElements().size());
        assertEquals(0, Position.newPosition(4, 5, board).getElements().size());
        assertEquals(0, Position.newPosition(3, 5, board).getElements().size());
        assertEquals(Orientation.UP, robot.getOrientation());
        assertEquals(1600, robot.getAmountOfEnergy(), epsilon);

        example4SpaceExtraNewLinesProgram.step();

        assertEquals(1, Position.newPosition(3, 4, board).getElements().size());
        assertEquals(0, Position.newPosition(4, 4, board).getElements().size());
        assertEquals(0, Position.newPosition(4, 5, board).getElements().size());
        assertEquals(0, Position.newPosition(3, 5, board).getElements().size());
        assertEquals(Orientation.RIGHT, robot.getOrientation());
        assertEquals(1500, robot.getAmountOfEnergy(), epsilon);

        example4SpaceExtraNewLinesProgram.step();

        assertEquals(0, Position.newPosition(3, 4, board).getElements().size());
        assertEquals(1, Position.newPosition(4, 4, board).getElements().size());
        assertEquals(0, Position.newPosition(4, 5, board).getElements().size());
        assertEquals(0, Position.newPosition(3, 5, board).getElements().size());
        assertEquals(Orientation.RIGHT, robot.getOrientation());
        assertEquals(1000, robot.getAmountOfEnergy(), epsilon);

        example4SpaceExtraNewLinesProgram.step();

        assertEquals(0, Position.newPosition(3, 4, board).getElements().size());
        assertEquals(1, Position.newPosition(4, 4, board).getElements().size());
        assertEquals(0, Position.newPosition(4, 5, board).getElements().size());
        assertEquals(0, Position.newPosition(3, 5, board).getElements().size());
        assertEquals(Orientation.DOWN, robot.getOrientation());
        assertEquals(900, robot.getAmountOfEnergy(), epsilon);

        example4SpaceExtraNewLinesProgram.step();
        example4SpaceExtraNewLinesProgram.step();
        example4SpaceExtraNewLinesProgram.step();
        example4SpaceExtraNewLinesProgram.step();
        example4SpaceExtraNewLinesProgram.step();
        example4SpaceExtraNewLinesProgram.step();
        example4SpaceExtraNewLinesProgram.step();
        example4SpaceExtraNewLinesProgram.step();
        example4SpaceExtraNewLinesProgram.step();
        example4SpaceExtraNewLinesProgram.step();

        assertEquals(0, Position.newPosition(3, 4, board).getElements().size());
        assertEquals(1, Position.newPosition(4, 4, board).getElements().size());
        assertEquals(0, Position.newPosition(4, 5, board).getElements().size());
        assertEquals(0, Position.newPosition(3, 5, board).getElements().size());
        assertEquals(Orientation.DOWN, robot.getOrientation());
        assertEquals(900, robot.getAmountOfEnergy(), epsilon);

        example4SpaceExtraNewLinesProgram.step();

        assertEquals(0, Position.newPosition(3, 4, board).getElements().size());
        assertEquals(1, Position.newPosition(4, 4, board).getElements().size());
        assertEquals(0, Position.newPosition(4, 5, board).getElements().size());
        assertEquals(0, Position.newPosition(3, 5, board).getElements().size());
        assertEquals(Orientation.DOWN, robot.getOrientation());
        assertEquals(900, robot.getAmountOfEnergy(), epsilon);
    }

    @Test
    public void testExample4SpaceExtraNewLinesToString() {
        Program example4SpaceExtraNewLinesProgram = new Program(
                example4spaceextranewlines, robot);

        assertTrue(example.equals(example4SpaceExtraNewLinesProgram.toString()));
    }

    public void testExampleInOneLine() {
        Program exampleInOneLineProgram = new Program(exampleinoneline, robot);

        assertNotNull(exampleInOneLineProgram);
    }

    @Test
    public void testExampleInOneLineStep() {
        robot = new Robot(new Energy(3900, unitOfPower.Ws), Orientation.RIGHT);
        robot.setPosition(Position.newPosition(3, 4, board));

        Program exampleInOneLineProgram = new Program(example, robot);
        robot.setProgram(exampleInOneLineProgram);

        assertEquals(1, Position.newPosition(3, 4, board).getElements().size());
        assertEquals(0, Position.newPosition(4, 4, board).getElements().size());
        assertEquals(0, Position.newPosition(4, 5, board).getElements().size());
        assertEquals(0, Position.newPosition(3, 5, board).getElements().size());
        assertEquals(Orientation.RIGHT, robot.getOrientation());
        assertEquals(3900, robot.getAmountOfEnergy(), epsilon);

        exampleInOneLineProgram.step();

        assertEquals(0, Position.newPosition(3, 4, board).getElements().size());
        assertEquals(1, Position.newPosition(4, 4, board).getElements().size());
        assertEquals(0, Position.newPosition(4, 5, board).getElements().size());
        assertEquals(0, Position.newPosition(3, 5, board).getElements().size());
        assertEquals(Orientation.RIGHT, robot.getOrientation());
        assertEquals(3400, robot.getAmountOfEnergy(), epsilon);

        exampleInOneLineProgram.step();

        assertEquals(0, Position.newPosition(3, 4, board).getElements().size());
        assertEquals(1, Position.newPosition(4, 4, board).getElements().size());
        assertEquals(0, Position.newPosition(4, 5, board).getElements().size());
        assertEquals(0, Position.newPosition(3, 5, board).getElements().size());
        assertEquals(Orientation.DOWN, robot.getOrientation());
        assertEquals(3300, robot.getAmountOfEnergy(), epsilon);

        exampleInOneLineProgram.step();

        assertEquals(0, Position.newPosition(3, 4, board).getElements().size());
        assertEquals(0, Position.newPosition(4, 4, board).getElements().size());
        assertEquals(1, Position.newPosition(4, 5, board).getElements().size());
        assertEquals(0, Position.newPosition(3, 5, board).getElements().size());
        assertEquals(Orientation.DOWN, robot.getOrientation());
        assertEquals(2800, robot.getAmountOfEnergy(), epsilon);

        exampleInOneLineProgram.step();

        assertEquals(0, Position.newPosition(3, 4, board).getElements().size());
        assertEquals(0, Position.newPosition(4, 4, board).getElements().size());
        assertEquals(1, Position.newPosition(4, 5, board).getElements().size());
        assertEquals(0, Position.newPosition(3, 5, board).getElements().size());
        assertEquals(Orientation.LEFT, robot.getOrientation());
        assertEquals(2700, robot.getAmountOfEnergy(), epsilon);

        exampleInOneLineProgram.step();

        assertEquals(0, Position.newPosition(3, 4, board).getElements().size());
        assertEquals(0, Position.newPosition(4, 4, board).getElements().size());
        assertEquals(0, Position.newPosition(4, 5, board).getElements().size());
        assertEquals(1, Position.newPosition(3, 5, board).getElements().size());
        assertEquals(Orientation.LEFT, robot.getOrientation());
        assertEquals(2200, robot.getAmountOfEnergy(), epsilon);

        exampleInOneLineProgram.step();

        assertEquals(0, Position.newPosition(3, 4, board).getElements().size());
        assertEquals(0, Position.newPosition(4, 4, board).getElements().size());
        assertEquals(0, Position.newPosition(4, 5, board).getElements().size());
        assertEquals(1, Position.newPosition(3, 5, board).getElements().size());
        assertEquals(Orientation.UP, robot.getOrientation());
        assertEquals(2100, robot.getAmountOfEnergy(), epsilon);

        exampleInOneLineProgram.step();

        assertEquals(1, Position.newPosition(3, 4, board).getElements().size());
        assertEquals(0, Position.newPosition(4, 4, board).getElements().size());
        assertEquals(0, Position.newPosition(4, 5, board).getElements().size());
        assertEquals(0, Position.newPosition(3, 5, board).getElements().size());
        assertEquals(Orientation.UP, robot.getOrientation());
        assertEquals(1600, robot.getAmountOfEnergy(), epsilon);

        exampleInOneLineProgram.step();

        assertEquals(1, Position.newPosition(3, 4, board).getElements().size());
        assertEquals(0, Position.newPosition(4, 4, board).getElements().size());
        assertEquals(0, Position.newPosition(4, 5, board).getElements().size());
        assertEquals(0, Position.newPosition(3, 5, board).getElements().size());
        assertEquals(Orientation.RIGHT, robot.getOrientation());
        assertEquals(1500, robot.getAmountOfEnergy(), epsilon);

        exampleInOneLineProgram.step();

        assertEquals(0, Position.newPosition(3, 4, board).getElements().size());
        assertEquals(1, Position.newPosition(4, 4, board).getElements().size());
        assertEquals(0, Position.newPosition(4, 5, board).getElements().size());
        assertEquals(0, Position.newPosition(3, 5, board).getElements().size());
        assertEquals(Orientation.RIGHT, robot.getOrientation());
        assertEquals(1000, robot.getAmountOfEnergy(), epsilon);

        exampleInOneLineProgram.step();

        assertEquals(0, Position.newPosition(3, 4, board).getElements().size());
        assertEquals(1, Position.newPosition(4, 4, board).getElements().size());
        assertEquals(0, Position.newPosition(4, 5, board).getElements().size());
        assertEquals(0, Position.newPosition(3, 5, board).getElements().size());
        assertEquals(Orientation.DOWN, robot.getOrientation());
        assertEquals(900, robot.getAmountOfEnergy(), epsilon);

        exampleInOneLineProgram.step();
        exampleInOneLineProgram.step();
        exampleInOneLineProgram.step();
        exampleInOneLineProgram.step();
        exampleInOneLineProgram.step();
        exampleInOneLineProgram.step();
        exampleInOneLineProgram.step();
        exampleInOneLineProgram.step();
        exampleInOneLineProgram.step();
        exampleInOneLineProgram.step();

        assertEquals(0, Position.newPosition(3, 4, board).getElements().size());
        assertEquals(1, Position.newPosition(4, 4, board).getElements().size());
        assertEquals(0, Position.newPosition(4, 5, board).getElements().size());
        assertEquals(0, Position.newPosition(3, 5, board).getElements().size());
        assertEquals(Orientation.DOWN, robot.getOrientation());
        assertEquals(900, robot.getAmountOfEnergy(), epsilon);

        exampleInOneLineProgram.step();

        assertEquals(0, Position.newPosition(3, 4, board).getElements().size());
        assertEquals(1, Position.newPosition(4, 4, board).getElements().size());
        assertEquals(0, Position.newPosition(4, 5, board).getElements().size());
        assertEquals(0, Position.newPosition(3, 5, board).getElements().size());
        assertEquals(Orientation.DOWN, robot.getOrientation());
        assertEquals(900, robot.getAmountOfEnergy(), epsilon);
    }

    @Test
    public void testExampleInOneLineToString() {
        Program exampleInOneLineProgram = new Program(exampleinoneline, robot);

        assertTrue(example.equals(exampleInOneLineProgram.toString()));
    }

    @Test
    public void testGump() {
        Program gumpProgram = new Program(gump, robot);

        assertNotNull(gumpProgram);
    }

    @Test
    public void testGumpStep() {
        robot = new Robot(new Energy(2400, unitOfPower.Ws), Orientation.RIGHT);
        robot.setPosition(Position.newPosition(3, 4, board));

        Program gumpProgram = new Program(gump, robot);
        robot.setProgram(gumpProgram);

        assertEquals(1, Position.newPosition(3, 4, board).getElements().size());
        assertEquals(0, Position.newPosition(4, 4, board).getElements().size());
        assertEquals(0, Position.newPosition(5, 4, board).getElements().size());
        assertEquals(0, Position.newPosition(6, 4, board).getElements().size());
        assertEquals(0, Position.newPosition(7, 4, board).getElements().size());
        assertEquals(Orientation.RIGHT, robot.getOrientation());
        assertEquals(2400, robot.getAmountOfEnergy(), epsilon);

        gumpProgram.step();

        assertEquals(0, Position.newPosition(3, 4, board).getElements().size());
        assertEquals(1, Position.newPosition(4, 4, board).getElements().size());
        assertEquals(0, Position.newPosition(5, 4, board).getElements().size());
        assertEquals(0, Position.newPosition(6, 4, board).getElements().size());
        assertEquals(0, Position.newPosition(7, 4, board).getElements().size());
        assertEquals(Orientation.RIGHT, robot.getOrientation());
        assertEquals(1900, robot.getAmountOfEnergy(), epsilon);

        gumpProgram.step();

        assertEquals(0, Position.newPosition(3, 4, board).getElements().size());
        assertEquals(0, Position.newPosition(4, 4, board).getElements().size());
        assertEquals(1, Position.newPosition(5, 4, board).getElements().size());
        assertEquals(0, Position.newPosition(6, 4, board).getElements().size());
        assertEquals(0, Position.newPosition(7, 4, board).getElements().size());
        assertEquals(Orientation.RIGHT, robot.getOrientation());
        assertEquals(1400, robot.getAmountOfEnergy(), epsilon);

        gumpProgram.step();

        assertEquals(0, Position.newPosition(3, 4, board).getElements().size());
        assertEquals(0, Position.newPosition(4, 4, board).getElements().size());
        assertEquals(0, Position.newPosition(5, 4, board).getElements().size());
        assertEquals(1, Position.newPosition(6, 4, board).getElements().size());
        assertEquals(0, Position.newPosition(7, 4, board).getElements().size());
        assertEquals(Orientation.RIGHT, robot.getOrientation());
        assertEquals(900, robot.getAmountOfEnergy(), epsilon);

        gumpProgram.step();

        assertEquals(0, Position.newPosition(3, 4, board).getElements().size());
        assertEquals(0, Position.newPosition(4, 4, board).getElements().size());
        assertEquals(0, Position.newPosition(5, 4, board).getElements().size());
        assertEquals(1, Position.newPosition(6, 4, board).getElements().size());
        assertEquals(0, Position.newPosition(7, 4, board).getElements().size());
        assertEquals(Orientation.RIGHT, robot.getOrientation());
        assertEquals(900, robot.getAmountOfEnergy(), epsilon);

        gumpProgram.step();
        gumpProgram.step();
        gumpProgram.step();
        gumpProgram.step();
        gumpProgram.step();
        gumpProgram.step();
        gumpProgram.step();
        gumpProgram.step();
        gumpProgram.step();
        gumpProgram.step();

        assertEquals(0, Position.newPosition(3, 4, board).getElements().size());
        assertEquals(0, Position.newPosition(4, 4, board).getElements().size());
        assertEquals(0, Position.newPosition(5, 4, board).getElements().size());
        assertEquals(1, Position.newPosition(6, 4, board).getElements().size());
        assertEquals(0, Position.newPosition(7, 4, board).getElements().size());
        assertEquals(Orientation.RIGHT, robot.getOrientation());
        assertEquals(900, robot.getAmountOfEnergy(), epsilon);
    }

    @Test
    public void testGumpToString() {
        Program gumpProgram = new Program(gump, robot);

        assertTrue(gump.equals(gumpProgram.toString()));
    }

    @Test
    public void testMove() {
        Program moveProgram = new Program(move, robot);

        assertNotNull(moveProgram);
    }

    @Test
    public void testMoveStep() {
        robot = new Robot(new Energy(2400, unitOfPower.Ws), Orientation.RIGHT);
        robot.setPosition(Position.newPosition(3, 4, board));

        Program moveProgram = new Program(move, robot);
        robot.setProgram(moveProgram);

        assertEquals(1, Position.newPosition(3, 4, board).getElements().size());
        assertEquals(0, Position.newPosition(4, 4, board).getElements().size());
        assertEquals(0, Position.newPosition(5, 4, board).getElements().size());
        assertEquals(Orientation.RIGHT, robot.getOrientation());
        assertEquals(2400, robot.getAmountOfEnergy(), epsilon);

        moveProgram.step();

        assertEquals(0, Position.newPosition(3, 4, board).getElements().size());
        assertEquals(1, Position.newPosition(4, 4, board).getElements().size());
        assertEquals(0, Position.newPosition(5, 4, board).getElements().size());
        assertEquals(Orientation.RIGHT, robot.getOrientation());
        assertEquals(1900, robot.getAmountOfEnergy(), epsilon);

        moveProgram.step();
        moveProgram.step();
        moveProgram.step();
        moveProgram.step();
        moveProgram.step();
        moveProgram.step();
        moveProgram.step();
        moveProgram.step();
        moveProgram.step();
        moveProgram.step();

        assertEquals(0, Position.newPosition(3, 4, board).getElements().size());
        assertEquals(1, Position.newPosition(4, 4, board).getElements().size());
        assertEquals(0, Position.newPosition(5, 4, board).getElements().size());
        assertEquals(Orientation.RIGHT, robot.getOrientation());
        assertEquals(1900, robot.getAmountOfEnergy(), epsilon);
    }

    @Test
    public void testMoveToString() {
        Program moveProgram = new Program(move, robot);

        assertTrue(move.equals(moveProgram.toString()));
    }

    @Test
    public void testWhileInSeq() {
        Program whileInSeqProgram = new Program(whileinseq, robot);

        assertNotNull(whileInSeqProgram);
    }

    @Test
    public void testWhileInSeqStep() {
        robot = new Robot(new Energy(2400, unitOfPower.Ws), Orientation.RIGHT);
        robot.setPosition(Position.newPosition(3, 4, board));

        Program whileInSeqProgram = new Program(whileinseq, robot);
        robot.setProgram(whileInSeqProgram);

        assertEquals(1, Position.newPosition(3, 4, board).getElements().size());
        assertEquals(0, Position.newPosition(4, 4, board).getElements().size());
        assertEquals(0, Position.newPosition(5, 4, board).getElements().size());
        assertEquals(0, Position.newPosition(6, 4, board).getElements().size());
        assertEquals(0, Position.newPosition(7, 4, board).getElements().size());
        assertEquals(Orientation.RIGHT, robot.getOrientation());
        assertEquals(2400, robot.getAmountOfEnergy(), epsilon);

        whileInSeqProgram.step();

        assertEquals(0, Position.newPosition(3, 4, board).getElements().size());
        assertEquals(1, Position.newPosition(4, 4, board).getElements().size());
        assertEquals(0, Position.newPosition(5, 4, board).getElements().size());
        assertEquals(0, Position.newPosition(6, 4, board).getElements().size());
        assertEquals(0, Position.newPosition(7, 4, board).getElements().size());
        assertEquals(Orientation.RIGHT, robot.getOrientation());
        assertEquals(1900, robot.getAmountOfEnergy(), epsilon);

        whileInSeqProgram.step();

        assertEquals(0, Position.newPosition(3, 4, board).getElements().size());
        assertEquals(0, Position.newPosition(4, 4, board).getElements().size());
        assertEquals(1, Position.newPosition(5, 4, board).getElements().size());
        assertEquals(0, Position.newPosition(6, 4, board).getElements().size());
        assertEquals(0, Position.newPosition(7, 4, board).getElements().size());
        assertEquals(Orientation.RIGHT, robot.getOrientation());
        assertEquals(1400, robot.getAmountOfEnergy(), epsilon);

        whileInSeqProgram.step();

        assertEquals(0, Position.newPosition(3, 4, board).getElements().size());
        assertEquals(0, Position.newPosition(4, 4, board).getElements().size());
        assertEquals(0, Position.newPosition(5, 4, board).getElements().size());
        assertEquals(1, Position.newPosition(6, 4, board).getElements().size());
        assertEquals(0, Position.newPosition(7, 4, board).getElements().size());
        assertEquals(Orientation.RIGHT, robot.getOrientation());
        assertEquals(900, robot.getAmountOfEnergy(), epsilon);

        whileInSeqProgram.step();

        assertEquals(0, Position.newPosition(3, 4, board).getElements().size());
        assertEquals(0, Position.newPosition(4, 4, board).getElements().size());
        assertEquals(0, Position.newPosition(5, 4, board).getElements().size());
        assertEquals(1, Position.newPosition(6, 4, board).getElements().size());
        assertEquals(0, Position.newPosition(7, 4, board).getElements().size());
        assertEquals(Orientation.DOWN, robot.getOrientation());
        assertEquals(800, robot.getAmountOfEnergy(), epsilon);

        whileInSeqProgram.step();

        assertEquals(0, Position.newPosition(3, 4, board).getElements().size());
        assertEquals(0, Position.newPosition(4, 4, board).getElements().size());
        assertEquals(0, Position.newPosition(5, 4, board).getElements().size());
        assertEquals(1, Position.newPosition(6, 4, board).getElements().size());
        assertEquals(0, Position.newPosition(7, 4, board).getElements().size());
        assertEquals(Orientation.DOWN, robot.getOrientation());
        assertEquals(800, robot.getAmountOfEnergy(), epsilon);

        whileInSeqProgram.step();
        whileInSeqProgram.step();
        whileInSeqProgram.step();
        whileInSeqProgram.step();
        whileInSeqProgram.step();
        whileInSeqProgram.step();
        whileInSeqProgram.step();
        whileInSeqProgram.step();
        whileInSeqProgram.step();
        whileInSeqProgram.step();

        assertEquals(0, Position.newPosition(3, 4, board).getElements().size());
        assertEquals(0, Position.newPosition(4, 4, board).getElements().size());
        assertEquals(0, Position.newPosition(5, 4, board).getElements().size());
        assertEquals(1, Position.newPosition(6, 4, board).getElements().size());
        assertEquals(0, Position.newPosition(7, 4, board).getElements().size());
        assertEquals(Orientation.DOWN, robot.getOrientation());
        assertEquals(800, robot.getAmountOfEnergy(), epsilon);
    }

    @Test
    public void testWhileInSeqToString() {
        Program whileInSeqProgram = new Program(whileinseq, robot);

        assertTrue(whileinseq.equals(whileInSeqProgram.toString()));
    }

    // /*
    // * Maak decrementProgramCounter public om deze test uit te voeren.
    // */
    // @Test
    // public void testDecrementProgramCounter() {
    // robot.setPosition(Position.newPosition(3, 5, board));
    // double energy = robot.getAmountOfEnergy();
    // Program moveProgram = new Program(move, robot);
    //
    // System.out.println(robot.getPosition());
    //
    // moveProgram.step();
    //
    // System.out.println(robot.getPosition());
    //
    // assertTrue(robot.getPosition()
    // .equals(Position.newPosition(4, 5, board)));
    //
    // if (energy > robot.getAmountOfEnergy())
    // energy = robot.getAmountOfEnergy();
    //
    // moveProgram.decrementProgramCounter();
    //
    // moveProgram.step();
    //
    // System.out.println(robot.getPosition());
    //
    // assertTrue(robot.getPosition()
    // .equals(Position.newPosition(5, 5, board)));
    //
    // assertTrue(energy > robot.getAmountOfEnergy());
    // }

    // /* Maak allSubParenthesed public om deze test uit te voeren. */
    // @Test
    // public void testMoveAllSubParenthesed() {
    // List<String> allSubParenthesed = Program.allSubParenthesed(new Program(
    // move, robot).toString());
    //
    // assertEquals(1, allSubParenthesed.size());
    // }

    public static String readProgramFromFile(String path) {
        StringBuilder programString = new StringBuilder();
        String newLine = System.getProperty("line.separator");
        Scanner scanner = new Scanner("");
        try {
            scanner = new Scanner(new FileReader(path));
            while (scanner.hasNextLine())
                programString.append(scanner.nextLine().replaceAll(
                        "\\p{javaWhitespace}+$", "")
                        + newLine);
        } catch (FileNotFoundException e) {
            System.err.println("readProgramFromFile: "
                    + "The file with the provided path was not found.");
        } finally {
            scanner.close();
        }

        return programString.toString().trim();
    }

    private Robot robot;
    private Board board;
    private final double epsilon = 0.01;
    private static String testProgramsDirectory;
    private static String cowboy;
    private static String emptyString;
    private static String example;
    private static String example4spaceextranewlines;
    private static String exampleinoneline;
    private static String gump;
    private static String move;
    private static String whileinseq;
}
