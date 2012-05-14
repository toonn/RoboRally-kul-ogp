package roborally.model;

import roborally.model.Energy.unitOfPower;

/**
 * @author Ben Adriaenssens <ben.adriaenssens@student.kuleuven.be>, Toon Nolten <toon.nolten@student.kuleuven.be>
 */
public class Battery extends Item {

    public Battery() {
        super();
        this.maxEnergy = new Energy(5000, Energy.unitOfPower.Ws);
        this.energy = new Energy(1000, unitOfPower.Ws);
        this.weight = new Weight(100, Weight.unitOfMass.g);
    }

    public Battery(Position position) {
        super(position);
        this.maxEnergy = new Energy(5000, Energy.unitOfPower.Ws);
        this.energy = new Energy(1000, unitOfPower.Ws);
        this.weight = new Weight(100, Weight.unitOfMass.g);
    }

    public Battery(Position position, Energy energy) {
        super(position);
        this.maxEnergy = new Energy(5000, Energy.unitOfPower.Ws);
        assert (energy != null);
        assert energy.isValidEnergy(maxEnergy);
        this.energy = energy;
        this.weight = new Weight(100, Weight.unitOfMass.g);
    }

    public Battery(Position position, Energy energy, Weight weight) {
        this(position, energy);
        this.weight.removeWeight(new Weight(100, Weight.unitOfMass.g));
        this.weight.addWeight(weight);

    }

    public void use(Robot robot) {
        if (robot != null) {
            if (isTerminated())
                robot.drop(this);
            else if (!robot.isTerminated())
                robot.recharge(energy);
        }
    }

    public void hit() {
        if (!isTerminated())
            energy.addEnergy(new Energy(500, unitOfPower.Ws));
    }

    public Energy getEnergy() {
        return energy;
    }

    public Weight getWeight() {
        return weight;
    }

    public final Energy getMaxEnergy() {
        return maxEnergy;
    }

    private final Energy energy;
    private final Energy maxEnergy;
    private final Weight weight;
}
