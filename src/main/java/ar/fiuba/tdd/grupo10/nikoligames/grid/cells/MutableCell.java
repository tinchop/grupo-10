package ar.fiuba.tdd.grupo10.nikoligames.grid.cells;

public class MutableCell extends GridCell {

    public MutableCell(CellState cellState) {
        super(cellState);
    }

    @Override
    public boolean isContentEditable() {
        return true;
    }

    @Override
    public boolean areRulesApplicable() {
        return true;
    }
}