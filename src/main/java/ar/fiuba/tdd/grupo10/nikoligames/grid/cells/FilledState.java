package ar.fiuba.tdd.grupo10.nikoligames.grid.cells;

public class FilledState implements CellState {
    private CellContent content;

    public FilledState(CellContent content) {
        this.content = content;
    }

    @Override
    public CellContent getContent() {
        return content;
    }

    @Override
    public void setContent(CellContent content, GridCell cell) {
        cell.setContent(content);
    }

    @Override
    public void clearContent(GridCell cell) {
        CellState newState = new EmptyState();
        cell.setState(newState);
    }
}