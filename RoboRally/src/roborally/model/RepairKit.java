package roborally.model;

import roborally.model.auxiliary.Energy;
import roborally.model.auxiliary.EnergyElement;
import roborally.model.auxiliary.Position;
import roborally.model.auxiliary.Weight;
import roborally.model.auxiliary.Energy.unitOfPower;

public class RepairKit extends Item implements EnergyElement {

    public RepairKit() {
        super();
        energy = new Energy(1000, unitOfPower.Ws);
    }

    public RepairKit(Position position, Weight weight, Energy energy) {
        super(position, weight);
        this.energy = energy;
    }

    @Override
    public void hit() {
        if (!isTerminated())
            energy.addEnergy(new Energy(500, unitOfPower.Ws));
    }

    @Override
    public void use(Robot robot) {
        Energy increaseEnergy = new Energy(energy.getAmountOfEnergy() / 2,
                unitOfPower.Ws);
        robot.increaseMaxEnergy(increaseEnergy);
        energy = new Energy(increaseEnergy.getAmountOfEnergy() * 2,
                unitOfPower.Ws);
    }

    public double getAmountOfEnergy() {
        return energy.getAmountOfEnergy();
    }

    private Energy energy;
}
