package ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.types.line;

public class FromBottomToRightLine extends Line {

    @Override
    public boolean isValidOnRight() {
        return true;
    }

    @Override
    public boolean isValidOnBottom() {
        return true;
    }

}
