package ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.types.line;

public class FromTopLeftToBottomRightDiagonal extends Line {

    @Override
    public boolean isValidOnBottomRight() {
        return true;
    }

    @Override
    public boolean isValidOnTopLeft() {
        return true;
    }
}