package roborally.model;

import java.util.*;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;

//TODO: Maak van Position een "Value klasse"
public class Position {

    public Position(long x, long y, Board board)
            throws IllegalArgumentException {
        this.BOARD = board;
        if (!board.isValidPosition(x, y))
            throw new IllegalArgumentException();
        this.X = x;
        this.Y = y;
        elements = new HashSet<Element>();
        this.isTerminated = false;
    }

    public void addElement(Element element) {
        if (!isTerminated() && !elements.contains(new Wall())
                && !elements.contains(element)
                //TODO: klopt volgend statement?
                && !(!elements.isEmpty() && element.equals(new Wall()))) {
            elements.add(element);
            element.setPosition(this);
        }
    }

    public void removeElement(Element element) {
        elements.remove(element);
        element.removePosition();

        if (elements.isEmpty()) {
            BOARD.removePosition(this);
            this.terminate();
        }
    }

    public Set<Element> getElements() {
        return elements;
    }

    public boolean containsElement(Element element) {
        if (elements != null)
            return elements.contains(element);
        return false;
    }

    public boolean isEmpty() {
        return elements.isEmpty();
    }

    public boolean hasSameCoordinates(Position position) {
        return (X == position.X && Y == position.Y);
    }

    public boolean equals(Object o) {
        return hashCode() == o.hashCode();
    }

    public int hashCode() {
        long positionNumber = X + BOARD.WIDTH * (Y - 1);
        return BOARD.hashCode() + (int) positionNumber;
    }

    public void terminate() {
        for (Element elem : elements)
            elem.terminate();
        elements = null;
        this.isTerminated = true;
    }

    @Raw
    @Basic
    public boolean isTerminated() {
        return isTerminated;
    }

    private boolean isTerminated;

    public final long X;
    public final long Y;

    private Set<Element> elements;
    private final Board BOARD;

}
