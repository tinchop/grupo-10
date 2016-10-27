package ar.fiuba.tdd.grupo10.nikoligames.grid.rules.operations;

import ar.fiuba.tdd.grupo10.nikoligames.exceptions.RuleNotSatisfiedException;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Cell;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Container;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.ImmutableContainer;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.MutableContainer;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.Content;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.ImmutableContent;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.MutableContent;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.types.Number;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.types.line.HorizontalLine;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.types.line.Line;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.types.line.VerticalLine;
import ar.fiuba.tdd.grupo10.nikoligames.grid.neighbour.NeighbourPosition;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.*;
import ar.fiuba.tdd.grupo10.nikoligames.grid.rules.matchers.EqualsMatcher;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class CheckLeftBorderOperationTest {
    GridRule<Boolean> theRule;
    String tag = "aTag";

    private void createRule(List<Cell> cells) {
        GridRuleIterator iterator = new GridRuleIterator(cells.stream().map(c -> (Container) c).collect(Collectors.toList()),
                "Iterator to check a single cell of the Grid");
        GridRuleCondition<Boolean> condition = new GridRuleCondition<>(new EqualsMatcher<>(), Boolean.TRUE);
        GridRuleOperation<Boolean> operation = new CheckLeftBorderOperation(tag);

        theRule = new CompleteIteratorRule<>(iterator, operation, condition);
    }

    @Test(expected = Test.None.class)
    public void ruleVerifiesBordersCorrectlySet() {

        List<Cell> cellToVerify = new ArrayList<>();
        List<Content> lines = new ArrayList<>();
        Line theLine = new VerticalLine("VerticalLine");
        Content theContent = new MutableContent(theLine, tag);
        lines.add(theContent);
        Container theContainer = new Container(new ImmutableContainer(lines));
        Cell theCell = new Cell(new MutableContainer(new MutableContent(new Number("1"),tag)));
        theCell.setLimitAt(theContainer, NeighbourPosition.LEFT);
        cellToVerify.add(theCell);
        createRule(cellToVerify);
        theRule.verifyRule();
    }

    @Test(expected = RuleNotSatisfiedException.class)
    public void ruleVerifiesBordersNotCorrectlySet() {
        List<Content> lines = new ArrayList<>();
        Line theLine = new VerticalLine("VerticalLine");
        Content theContent = new MutableContent(theLine, tag);
        lines.add(theContent);
        Container theContainer = new Container(new ImmutableContainer(lines));
        Cell theCell = new Cell(new MutableContainer(new MutableContent(new Number("1"), tag)));
        Cell anotherCell = new Cell(new MutableContainer(new MutableContent(new Number("1"), tag)));
        anotherCell.setLimitAt(theContainer, NeighbourPosition.LEFT);
        theCell.setLimitAt(theContainer, NeighbourPosition.RIGHT);
        List<Cell> cellToVerify = new ArrayList<>();
        cellToVerify.add(theCell);
        cellToVerify.add(anotherCell);
        createRule(cellToVerify);
        theRule.verifyRule();
    }
}