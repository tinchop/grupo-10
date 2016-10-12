package ar.fiuba.tdd.grupo10.nikoligames.uidelegate.views.mainmenu.chain;

import ar.fiuba.tdd.grupo10.nikoligames.SudokuFactory;
import ar.fiuba.tdd.grupo10.nikoligames.uidelegate.controllers.GridController;

class SudokuLink extends GameLink {

    @Override
    public void execute(GameEnum gameEnum) {
        if (GameEnum.SUDOKU.equals(gameEnum)) {
            try {
                new GridController(SudokuFactory.createGridFromScratch(79));
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        } else {
            next.execute(gameEnum);
        }
    }

}