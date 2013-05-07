package pl.jug.szczecin.workshop;


import com.google.common.collect.Lists;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import static pl.jug.szczecin.workshop.Cell.CellState;
import static pl.jug.szczecin.workshop.Cell.CellState.ALIVE;
import static pl.jug.szczecin.workshop.Cell.CellState.DEAD;

/**
 * Created for 2013 workshop, by Filip Pająk
 */
public class CellTest {

	@Test(expectedExceptions = NullPointerException.class)
	public void shouldNotBeCreatedWithNotDefinedState() {
		//given
		final CellState state = null;

		//when
		final Cell cell = new Cell(state);

		//then
		//Exception should be thrown
	}

	@Test
	public void shouldBeCreatedButNotInitializedWithGivenState() {
		//given
		final CellState state = DEAD;

		//when
		final Cell cell = new Cell(state);

		//then
		assertFalse(cell.isInitialized());
	}


	@Test
	public void shouldBeInitializedWithListOfNeighbours() {
		//given
		final Cell aliveCell = new Cell(ALIVE);

		final Cell deadCell = new Cell(DEAD);

		final List<Cell> neighbours = Lists.newArrayList(aliveCell, aliveCell, deadCell, deadCell, deadCell, deadCell, deadCell, deadCell);

		final Cell cell = new Cell(ALIVE);

		//when
		cell.initialize(neighbours);

		//then
		assertTrue(cell.isInitialized());
	}

	@Test
	public void shouldBeAliveFromDeadIfHasThreeAliveNeighbours() {
		//given
		final Cell deadCell = new Cell(DEAD);
		final Cell aliveCell = new Cell(ALIVE);

		final List<Cell> neighbours = Lists.newArrayList(aliveCell,aliveCell,aliveCell, deadCell, deadCell, deadCell, deadCell, deadCell);

		final Cell cell = new Cell(DEAD);

		//when
		cell.initialize(neighbours);
//		cell.calculateNewState();
		cell.actualizeState();

		//then
		assertTrue(cell.isAlive(), "Cell should be alive");
	}

	@DataProvider(name = "cells")
	public Object[][] dataProvider() {
		final Cell deadCell = new Cell(DEAD);
		final Cell aliveCell = new Cell(ALIVE);

		final Object[] row1 = new Object[]{aliveCell,aliveCell,aliveCell, deadCell, deadCell, deadCell, deadCell, deadCell};
		final Object[] row2 = new Object[]{aliveCell,aliveCell,deadCell, aliveCell, deadCell, deadCell, deadCell, deadCell};
		final Object[] row3 = new Object[]{aliveCell,aliveCell,aliveCell, aliveCell, deadCell, deadCell, deadCell, deadCell};

		final Object[][] cells = new Object[][] {
				row1,
				row2,
				row3
		};

		return cells;
	}


	@Test(dataProvider = "cells")
	public void shouldBeStillAliveIfHasThreeAliveNeighbours(final Cell leftTop, final Cell top, final Cell rightTop, final Cell right, final Cell rightBottom, final Cell bottom, final Cell leftBottom, final Cell left) {
		//given
		final Cell deadCell = new Cell(DEAD);
		final Cell aliveCell = new Cell(ALIVE);

//		final List<Cell> neighbours = Lists.newArrayList(aliveCell,aliveCell,aliveCell, deadCell, deadCell, deadCell, deadCell, deadCell);
		final List<Cell> neighbours = Lists.newArrayList(leftTop, top, rightTop, right, rightBottom, bottom, leftBottom, left);

		final Cell cell = new Cell(ALIVE);

		//when
		cell.initialize(neighbours);
		cell.calculateNewState();
		cell.actualizeState();

		//then
		assertTrue(cell.isAlive(), "Cell should be alive");
	}


}
