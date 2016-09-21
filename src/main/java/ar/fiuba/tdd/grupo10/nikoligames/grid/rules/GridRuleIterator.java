package ar.fiuba.tdd.grupo10.nikoligames.grid.rules;

import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.GridCell;

import java.util.Iterator;
import java.util.List;

/**
 * Iterator that contains all the cells of matter for the rule that use it.
 */
public class GridRuleIterator implements Iterator {
    private final List<GridCell> cells;
    private final String explanation;
    private int actualIndex = -1;

    public GridRuleIterator(List<GridCell> cells, String explanation) {
        this.cells = cells;
        this.explanation = explanation;
    }

    @Override
    public boolean hasNext() {
        try {
            cells.get(actualIndex + 1);
            return true;
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    @Override
    public GridCell next() {
        actualIndex++;
        return cells.get(actualIndex);
    }

    public String getCellsInvolvedExplanation() {
        return this.explanation;
    }
}