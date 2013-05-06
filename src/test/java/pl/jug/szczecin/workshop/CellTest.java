package pl.jug.szczecin.workshop;


import com.google.common.collect.Lists;
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


}
