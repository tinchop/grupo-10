package ar.fiuba.tdd.grupo10.nikoligames.grid.rules.operations;


import ar.fiuba.tdd.grupo10.nikoligames.exceptions.RuleNotSatisfiedException;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Cell;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Container;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.MutableContainer;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.MutableContent;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.types.line.*;
import ar.fiuba.tdd.grupo10.nikoligames.grid.neighbour.NeighbourPosition;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.AlwaysVerifiableRule;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.GridRule;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.GridRuleCondition;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.GridRuleIterator;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.matchers.EqualsMatcher;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.operations.GridRuleOperation;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.operations.LineCircuitOperation;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class LineCircuitOperationTests {
    private List<Container> theCells;
    private GridRule<Boolean> ruleWithLineCircuitOperation;

    private static final String TAG = "tag";

    private void createRule() {
        GridRuleIterator iterator = new GridRuleIterator(
                theCells,
                "Iterator for LineCircuitOperationTests"
        );

        GridRuleCondition<Boolean> condition = new GridRuleCondition<>(
                new EqualsMatcher<>(),
                Boolean.TRUE
        );

        GridRuleOperation<Boolean> operation = new LineCircuitOperation(TAG);

        ruleWithLineCircuitOperation = new AlwaysVerifiableRule<>(
                iterator,
                operation,
                condition
        );
    }

    private Cell createCell( Line contentValue ) {
        return new Cell( new MutableContainer( new MutableContent<>(contentValue,TAG) ) );
    }

    private void setTopNeighbour(int index) {
        if (index > 2) {
            Cell actualCell = (Cell)theCells.get(index);
            actualCell.setNeighbourAt( theCells.get( index - 3 ), NeighbourPosition.TOP );
        }
    }

    private void setBottomNeighbour(int index) {
        if (index < 6) {
            Cell actualCell = (Cell)theCells.get(index);
            actualCell.setNeighbourAt( theCells.get( index + 3 ), NeighbourPosition.BOTTOM );
        }
    }

    private void setLeftNeighbour(int index) {
        List<Integer> notLeftNeighbour = new ArrayList<>();
        notLeftNeighbour.add(0);
        notLeftNeighbour.add(3);
        notLeftNeighbour.add(6);
        if (!notLeftNeighbour.contains(index)) {
            Cell actualCell = (Cell)theCells.get(index);
            actualCell.setNeighbourAt( theCells.get( index - 1 ), NeighbourPosition.LEFT );
        }
    }

    private void setRightNeighbour(int index) {
        List<Integer> notRighNeighbour = new ArrayList<>();
        notRighNeighbour.add(2);
        notRighNeighbour.add(5);
        notRighNeighbour.add(8);
        if ( !notRighNeighbour.contains(index) ) {
            Cell actualCell = (Cell)theCells.get(index);
            actualCell.setNeighbourAt( theCells.get( index + 1 ), NeighbourPosition.RIGHT );
        }
    }

    private void generateNeighbours() {
        for (int i = 0; i < theCells.size(); i++) {
            setLeftNeighbour(i);
            setTopNeighbour(i);
            setBottomNeighbour(i);
            setRightNeighbour(i);
        }
    }

    private void createCellsWithCircuit() {
        /*
           Lines: ┐ ┌ - | └ ┘
         =============
          1.  4 ┌ ┐
          2.  ┌ ┘ |
          3.  └ - ┘
         =============
         */
        theCells = new ArrayList<>();

        //First Row
        theCells.add( new Cell(new MutableContainer( new MutableContent<>(4,TAG) )) );
        theCells.add( createCell( new FromBottomToRightLine() ));
        theCells.add( createCell( new FromBottomToLeftLine() ));

        //Second Row
        theCells.add(createCell( new FromBottomToRightLine() ));
        theCells.add(createCell( new FromTopToLeftLine() ));
        theCells.add(createCell( new VerticalLine() ));

        //Third Row
        theCells.add(createCell( new FromTopToRightLine() ));
        theCells.add(createCell( new HorizontalLine() ));
        theCells.add(createCell( new FromTopToLeftLine() ));
    }

    private void createCellsWithoutCircuit() {
        createCellsWithCircuit();
        /*
           Lines: ┐ ┌ - | └ ┘
         =============
          1.  4 ┌ ┐
          2.  ┌ ┘ |
          3.  └ - ┘
         =============

         Change to generate invalid circuit:
         =============
          3.  └ | ┘
         =============
         */

        theCells.set(7, createCell( new VerticalLine() ));
    }

    @Test(expected = Test.None.class)
    public void cellsHaveCircuit() {
        createCellsWithCircuit();
        generateNeighbours();
        createRule();
        ruleWithLineCircuitOperation.verifyRule();
    }

    @Test(expected = RuleNotSatisfiedException.class)
    public void cellsNotHaveCircuit() {
        createCellsWithoutCircuit();
        generateNeighbours();
        createRule();
        ruleWithLineCircuitOperation.verifyRule();
    }


}