package roborally.test;

import static org.junit.Assert.*;

import org.junit.*;

import roborally.model.*;
import roborally.model.Energy.unitOfPower;
import roborally.model.Weight.unitOfMass;

public class BatteryTest {

    @Before
    public void setUp() {
        battery = new Battery();
        battery2 = new Battery(POSITION, ENERGY);
        battery3 = new Battery(POSITION, ENERGY, WEIGHT);
    }

    @Test
    public void testBattery() {
        assertNotNull(battery);
        assertEquals(0, battery.getEnergy().compareTo(battery.getMaxEnergy()));
        assertEquals(0,
                battery.getWeight().compareTo(new Weight(100, unitOfMass.g)));
    }

    @Test
    public void testBatteryPositionEnergy() {
        assertNotNull(battery2);
        assertEquals(0, battery2.getEnergy().compareTo(ENERGY));
        assertEquals(0,
                battery.getWeight().compareTo(new Weight(100, unitOfMass.g)));
    }

    @Test
    public void testBatteryPositionEnergyWeight() {
        assertNotNull(battery2);
        assertEquals(0, battery2.getEnergy().compareTo(ENERGY));
        assertEquals(0, battery.getWeight().compareTo(WEIGHT));
    }

    @Test
    public void testGetEnergy() {
        assertEquals(0, battery.getEnergy().compareTo(battery.getMaxEnergy()));
        assertEquals(0, battery2.getEnergy().compareTo(ENERGY));
        assertEquals(0, battery3.getEnergy().compareTo(ENERGY));
    }

    @Test
    public void testGetMaxEnergy() {
        Energy maxEnergy = new Energy(5000, unitOfPower.Ws);
        assertEquals(0, battery.getMaxEnergy().compareTo(maxEnergy));
        assertEquals(0, battery2.getMaxEnergy().compareTo(maxEnergy));
        assertEquals(0, battery3.getMaxEnergy().compareTo(maxEnergy));
    }

    @Test
    public void testSetPosition() {
        Position position = new Position(3, 2, BOARD);

        battery.setPosition(position);

        assertTrue(battery.getPosition().equals(position));
        assertTrue(position.containsElement(battery));
    }

    @Test
    public void testGetPosition() {
        assertFalse(battery.getPosition() == POSITION);
        assertEquals(POSITION, battery2.getPosition());
    }
    
    @Test
    public void testCharge() {
        Energy zeroEnergy = new Energy(0, unitOfPower.Ws);
        Energy emptyEnergy = new Energy(0, unitOfPower.Ws);
        Energy hundredEnergy = new Energy(100, unitOfPower.Ws);
        Energy rechargedEnergy = new Energy(400, unitOfPower.Ws);
        Energy rechargedMaxEnergy = new Energy(300, unitOfPower.Ws);
        Battery battery4 = new Battery(POSITION, rechargedEnergy);
        
        battery2.charge(new Energy(0, unitOfPower.Ws), rechargedMaxEnergy);
        
        assertEquals(0, zeroEnergy.compareTo(battery2.getEnergy()));
        
        battery2.charge(zeroEnergy, rechargedMaxEnergy);
        
        assertEquals(0, zeroEnergy.compareTo(emptyEnergy));
        
        battery4.charge(new Energy(0, unitOfPower.Ws), rechargedMaxEnergy);
        
        assertEquals(0, hundredEnergy.compareTo(battery4.getEnergy()));
    }

    @Test
    public void testTerminate() {
        assertFalse(battery.isTerminated());
        assertFalse(battery2.isTerminated());
        assertFalse(battery3.isTerminated());

        battery.terminate();
        battery2.terminate();
        battery3.terminate();

        assertTrue(battery.isTerminated());
        assertTrue(battery2.isTerminated());
        assertFalse(POSITION.containsElement(battery2));
        assertTrue(battery3.isTerminated());
        assertFalse(POSITION.containsElement(battery3));
    }

    @Test
    public void testIsTerminated() {
        battery.terminate();

        assertTrue(battery.isTerminated());
        assertFalse(battery2.isTerminated());
    }

    private Battery battery;
    private Battery battery2;
    private Battery battery3;
    private final Energy ENERGY = new Energy(100, Energy.unitOfPower.Ws);
    private final Weight WEIGHT = new Weight(100, Weight.unitOfMass.g);
    private final Board BOARD = new Board(4, 5);
    private final Position POSITION = new Position(2, 3, BOARD);
}