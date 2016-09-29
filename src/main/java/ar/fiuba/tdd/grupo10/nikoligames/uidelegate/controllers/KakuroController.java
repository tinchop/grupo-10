package ar.fiuba.tdd.grupo10.nikoligames.uidelegate.controllers;

import ar.fiuba.tdd.grupo10.nikoligames.grid.Grid;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.GameRulesObserver;
import ar.fiuba.tdd.grupo10.nikoligames.uidelegate.views.KakuroView;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

public class KakuroController implements TableModelListener, GameRulesObserver {
    private KakuroGridAdapter model;
    private KakuroView view;

    public KakuroController(Grid grid) {
        initComponents(grid);
    }

    private void initComponents(Grid grid) {
        grid.addRuleObserver(this);
        model = new KakuroGridAdapter(grid);
        model.addTableModelListener(this);
        view = new KakuroView(model);
    }

    @Override
    public void tableChanged(TableModelEvent event) {
        view.toString();
    }

    @Override
    public void onRuleUnsatisfied(String message) {
        view.updateConsole(message);
    }

    @Override
    public void onGameWon(String message) {
        view.updateConsole(message);
    }

}