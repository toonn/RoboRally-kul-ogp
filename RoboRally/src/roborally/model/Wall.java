package roborally.model;

import roborally.model.auxiliary.Position;

/**
 * This class represents walls. Robots cannot stand on nor walk over walls.
 * 
 * @author Ben Adriaenssens <(ben.adriaenssens@student.kuleuven.be)>, Toon Nolten (toon.nolten@student.kuleuven.be)
 */
public class Wall extends Element {

    /**
     * Initializes a new wall with no position.
     */
    public Wall() {
    }

    /**
     * Initializes a new wall with a position.
     * 
     * @param position
     *      | new.getPosition() == position
     */
    public Wall(Position position) {
        super(position);
    }

    /**
     * This method has no effect.
     */
    public void hit() {
        // This has no effect.
    }
}
