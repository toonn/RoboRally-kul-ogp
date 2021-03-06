package roborally.model;

import be.kuleuven.cs.som.annotate.*;
import java.util.*;

import roborally.filters.BooleanExtractor;
import roborally.model.auxiliary.Position;

/**
 * This class represents a board.
 * 
 * @invar WIDTH is always a positive integer number less than Long.MAX_VALUE.
 *      | 0 <= WIDTH <= long.MAX_VALUE
 * @invar HEIGHT is always a positive integer number less than Long.MAX_VALUE.
 *      | 0 <= HEIGHT <= long.MAX_VALUE
 * 
 * @author Ben Adriaenssens (ben.adriaenssens@student.kuleuven.be) - WtkCws,
 *         Toon Nolten (toon.nolten@student.kuleuven.be) - CwsElt.
 */
public class Board {

    /**
     * Initializes a new board with the given width and height.
     * 
     * @param width
     *      The width of this board.
     * @param height
     *      The height of this board.
     * @throws IllegalArgumentException
     *      The given width or height is less than 0.
     *      | width < 0 || height < 0
     */
    public Board(long width, long height) throws IllegalArgumentException {
        this.isTerminated = false;
        if (width < 0 || height < 0)
            throw new IllegalArgumentException();
        this.WIDTH = width;
        this.HEIGHT = height;
    }

    /**
     * Merge this Board with another board.
     * 
     * @param board2
     *      The board to merge into this board.
     * @effect Each element on board2 is moved to the corresponding position on
     *      this Board if it can be placed there.
     *      | if (board2 != null)
     *      |   for occupiedPosition2 in board2.getOccupiedPositions()
     *      |       if (isValidPosition(occupiedPosition2))
     *      |           for element in occupiedPosition2.getElements()
     *      |               position = Position.newPosition(occupiedPosition2.X,
     *      |                   occupiedPosition2.Y, this)
     *      |               if (position.canContainElement(element))
     *      |                   putElement(position, elem)
     *      |               else
     *      |                   element.terminate()
     *      |   board2.terminate()
     *      
     */
    public void merge(Board board2) {
        if (board2 != null) {
            Set<Position> occupiedPositionsBoard2 = new HashSet<Position>();
            occupiedPositionsBoard2.addAll(board2.getOccupiedPositions());
            for (Position occupiedPosition2 : occupiedPositionsBoard2) {
                if (isValidPosition(occupiedPosition2)) {
                    Position position = Position.newPosition(
                            occupiedPosition2.X, occupiedPosition2.Y, this);
                    for (Element elem : occupiedPosition2.getElements()) {
                        if (position.canContainElement(elem))
                            putElement(position, elem);
                        else
                            elem.terminate();
                    }
                }
            }
            board2.terminate();
        }
    }

    /**
     * Place an element at the given position on this Board.
     * 
     * @param position
     *      The position the element will be placed at.
     * @param element
     *      The element to place at the given position.
     * @effect If the given position is a valid position for this Board then
     *      the element will be added to the position
     *      | if (isValidPosition(position))
     *      |   (new position).getElements().contains(element)
     * @post If the given position is a valid position for this Board then
     *      the position will be an occupied position.
     *      | if (isValidPosition(position))
     *      |   new.isOccupiedPosition(position)
     */
    public void putElement(Position position, Element element) {
        if (isValidPosition(position))
            if (occupiedPositions.contains(position)) {
                position.addElement(element);
            } else {
                addOccupiedPosition(position);
                putElement(position, element);
            }
    }

    /**
     * Remove an element from this Board.
     * 
     * @param element
     *      The element to remove from this Board.
     * @effect Remove the element from it's position.
     *      | element.getPosition().removeElement(element)
     */
    public void removeElement(Element element) {
        element.getPosition().removeElement(element);
    }

    /**
     * Return all the elements at the given position.
     * 
     * @param position
     *      The position whose elements will be returned.
     * @return If the given position is an occupied position return the elements
     *      at that position otherwise return an empty set.
     *      | if (isOccupiedPosition(position))
     *      |   result == position.getElements()
     *      | else
     *      |   result == new HashSet<Element>()
     */
    public Set<Element> getElementsAt(Position position) {
        if (isOccupiedPosition(position))
            return position.getElements();
        return new HashSet<Element>();
    }

    /**
     * Return all the elements of the given type.
     * 
     * @param type
     *      The type of the elements returned by this method.
     * @return Return a set containing all elements of the given type on this
     *      Board.
     *      | for occupiedPosition in getOccupiedPositions()
     *      |   result.addAll(occupiedPosition.getElementsOf(type))
     */
    public Set<Element> getElementsOf(Class<?> type) {
        Set<Element> elements = new HashSet<Element>();
        for (Position occupiedPosition : occupiedPositions) {
            elements.addAll(occupiedPosition.getElementsOf(type));
        }
        return elements;
    }

    /**
     * If the given position is a valid position on this Board add it to this
     * Board's occupied positions.
     * 
     * @param position
     *      The position to add to this Board's occupied positions.
     * @post If the position is a valid position add it to this Board's
     *      occupied positions.
     *      | if (isValidPosition(position))
     *      |   new.isOccupiedPosition(position)
     */
    public void addOccupiedPosition(Position position) {
        if (isValidPosition(position))
            occupiedPositions.add(position);
    }

    /**
     * Return the number of occupied positions on this Board.
     * 
     * @return getOccupiedPositions().size()
     */
    public int getNumberOfOccupiedPositions() {
        return occupiedPositions.size();
    }

    /**
     * Return a set of the occupied positions on this Board.
     */
    @Basic
    public Set<Position> getOccupiedPositions() {
        return occupiedPositions;
    }

    /**
     * Check if the given position is a valid position for this Board.
     * 
     * @param position
     *      The position to check.
     * @return true if the position is valid for this Board.
     *      | if (position != null)
     *      |   result == isValidPosition(position.X, position.Y)
     * @return false otherwise.
     *          
     */
    public boolean isValidPosition(Position position) {
        if (position != null)
            return isValidPosition(position.X, position.Y);
        return false;
    }

    /**
     * Check if the given coordinates are a valid position for this Board.
     */
    @Basic
    public boolean isValidPosition(long x, long y) {
        return x >= 0 && x <= WIDTH && y >= 0 && y <= HEIGHT;
    }

    /**
     * Check if the given position is an occupied position.
     * 
     * @param position
     *      The position to check.
     * @return getOccupiedPositions().contains(position)
     */
    public boolean isOccupiedPosition(Position position) {
        return occupiedPositions.contains(position);
    }

    /**
     * Remove a position from this Board.
     * 
     * @param position
     *      The position to remove.
     * @effect The position is removed from this Board.
     *      | getOccupiedPositions().remove(position)
     */
    public void removePosition(Position position) {
        occupiedPositions.remove(position);
    }

    /**
     * Terminate this Board.
     * 
     * @effect Every occupied position on this Board is terminated.
     *      | for position in getOccupiedPositions()
     *      |   position.terminate()
     * @post new.isTerminated() = true
     */
    public void terminate() {
        Set<Position> occupiedPositionIterator = new HashSet<Position>();
        occupiedPositionIterator.addAll(occupiedPositions);
        for (Position pos : occupiedPositionIterator)
            pos.terminate();
        this.isTerminated = true;
    }

    /**
     * Return whether or not this Board is terminated.
     */
    @Basic
    public boolean isTerminated() {
        return isTerminated;
    }

    /**
     * Return an Iterator over all elements on this board that are selected by
     * the given extractor.
     */
    public Iterator<Element> conditionIterator(final BooleanExtractor extractor) {
        return new Iterator<Element>() {
            private Iterator<Element> iterator;

            {
                Set<Element> allElements = new HashSet<Element>();
                for (Position position : occupiedPositions)
                    allElements.addAll(position.getElements());
                Set<Element> satisfyingElements = new HashSet<Element>();
                for (Element element : allElements)
                    if (extractor.isSatisfied(element))
                        satisfyingElements.add(element);
                iterator = satisfyingElements.iterator();
            }

            public Element next() throws NoSuchElementException {
                return iterator.next();
            }

            public void remove() throws UnsupportedOperationException {
                throw new UnsupportedOperationException();
            }

            public boolean hasNext() {
                return iterator.hasNext();
            }
        };
    }

    public final long WIDTH;
    public final long HEIGHT;

    private boolean isTerminated;
    private final Set<Position> occupiedPositions = new HashSet<Position>();
}