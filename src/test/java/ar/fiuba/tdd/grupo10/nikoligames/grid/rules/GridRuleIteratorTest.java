package ar.fiuba.tdd.grupo10.nikoligames.grid.rules;

import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.Cell;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.MutableCell;
import ar.fiuba.tdd.grupo10.nikoligames.grid.cells.content.MutableContent;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;

public class GridRuleIteratorTest {

    private GridRuleIterator createIterator() {
        List<Cell> list = new ArrayList<>();
        list.add(new MutableCell(new MutableContent<>(1,"number")));
        list.add(new MutableCell(new MutableContent<>(2,"number")));
        list.add(new MutableCell(new MutableContent<>(3,"number")));

        return new GridRuleIterator(list,"test");
    }

    @Test
    public void hasNext() throws Exception {
        GridRuleIterator iterator = createIterator();
        boolean hasNextCell = iterator.hasNext();
        assertTrue(hasNextCell);
    }

    @Test
    public void next() throws Exception {
        GridRuleIterator iterator = createIterator();
        Cell cell = iterator.next();
        assertEquals(cell.getValue(),1);
    }

    @Test
    public void iteration() {
        GridRuleIterator iterator = createIterator();

        Cell cell = iterator.next();
        assertEquals(cell.getValue(),1);

        cell = iterator.next();
        assertEquals(cell.getValue(),2);

        cell = iterator.next();
        assertEquals(cell.getValue(),3);
    }

    @Test
    public void iterateAndRestart() {
        GridRuleIterator iterator = createIterator();
        Cell cell = null;
        int i = 1;
        while (iterator.hasNext()){
            cell = iterator.next();
            Assert.assertEquals(cell.getValue(),i);
            i++;
        }

        iterator.restart();
        cell = iterator.next();
        Assert.assertEquals( cell.getValue(), 1 );


    }
}